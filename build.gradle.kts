plugins {
    application
}

group = "regexmatcher"
version = "1.0.0"

val appName = "RegexMatcher"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.12.1")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    mainClass = "regexmatcher.App"
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

val stageWindowsAppImage by tasks.registering(Sync::class) {
    dependsOn(tasks.named("jar"))

    from(tasks.named<Jar>("jar").flatMap { it.archiveFile })
    into(layout.buildDirectory.dir("jpackage-input"))
}

tasks.register<Exec>("packageWindowsExe") {
    dependsOn(stageWindowsAppImage)

    val inputDir = layout.buildDirectory.dir("jpackage-input")
    val outputDir = layout.buildDirectory.dir("jpackage")
    val jarFileName = tasks.named<Jar>("jar").flatMap { it.archiveFileName }
    val launcherName = appName
    val jpackageExecutable = "${System.getProperty("java.home")}\\bin\\jpackage.exe"

    onlyIf {
        System.getProperty("os.name").startsWith("Windows", ignoreCase = true)
    }

    doFirst {
        delete(outputDir)
    }

    commandLine(
        jpackageExecutable,
        "--type", "app-image",
        "--input", inputDir.get().asFile.absolutePath,
        "--dest", outputDir.get().asFile.absolutePath,
        "--name", launcherName,
        "--main-jar", jarFileName.get(),
        "--main-class", application.mainClass.get(),
        "--win-console"
    )
}
