# Regex Matcher

`regex-matcher` is a small Java 21 console application that checks whether an input string fully matches a pattern.

It supports a simplified regular expression syntax:

- `.` matches any single character
- `*` matches zero or more occurrences of the previous character

The matcher is implemented with dynamic programming and includes JUnit 5 tests for valid matches, invalid matches, and invalid pattern input.

## Run

```bash
./gradlew run --args="aab c*a*b"
```

You can also start the app without arguments and enter the string and pattern interactively.

## Test

```bash
./gradlew test
```
