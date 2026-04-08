# Regex Matcher

`regex-matcher` is a small Java 21 console application that checks whether an input string fully matches a pattern.

It supports a simplified regular expression syntax:

- `.` matches any single character
- `*` matches zero or more occurrences of the previous character

The matcher is implemented with dynamic programming and includes JUnit 5 tests for valid matches, invalid matches, and invalid pattern input.

## Download Windows build

The repository includes a GitHub Actions workflow that builds a Windows launcher executable.

1. Open the `Actions` tab in GitHub
2. Run the `Build Windows EXE` workflow, or use the latest successful run on `main`
3. Download the `regex-matcher-windows` artifact
4. Open the `RegexMatcher` folder inside the artifact and run `RegexMatcher.exe`

## Run

```bash
./gradlew run --args="aab c*a*b"
```

You can also start the app without arguments and enter the string and pattern interactively.

## Test

```bash
./gradlew test
```

## Build Windows launcher locally

```powershell
.\gradlew.bat packageWindowsExe
```

The generated Windows application files will be placed in `build/jpackage/RegexMatcher`.
