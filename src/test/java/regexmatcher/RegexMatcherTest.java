package regexmatcher;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegexMatcherTest {

    @Test
    void returnsTrueForExactMatch() {
        assertTrue(RegexMatcher.isMatch("abc", "abc"));
    }

    @Test
    void returnsFalseWhenFullStringDoesNotMatch() {
        assertFalse(RegexMatcher.isMatch("abc", "ab"));
    }

    @Test
    void dotMatchesAnySingleCharacter() {
        assertTrue(RegexMatcher.isMatch("abc", "a.c"));
    }

    @Test
    void starMatchesZeroOccurrences() {
        assertTrue(RegexMatcher.isMatch("ab", "abc*"));
    }

    @Test
    void starMatchesMultipleOccurrences() {
        assertTrue(RegexMatcher.isMatch("aaa", "a*"));
    }

    @Test
    void combinedPatternMatchesExpectedCase() {
        assertTrue(RegexMatcher.isMatch("aab", "c*a*b"));
    }

    @Test
    void combinedPatternRejectsKnownFalseCase() {
        assertFalse(RegexMatcher.isMatch("mississippi", "mis*is*p*."));
    }

    @Test
    void emptyStringCanMatchStarSequence() {
        assertTrue(RegexMatcher.isMatch("", "a*b*c*"));
    }

    @Test
    void emptyPatternDoesNotMatchNonEmptyString() {
        assertFalse(RegexMatcher.isMatch("a", ""));
    }

    @Test
    void throwsForNullInputString() {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> RegexMatcher.isMatch(null, "a*"));

        assertEquals("Input string and pattern must not be null.", exception.getMessage());
    }

    @Test
    void throwsForPatternStartingWithStar() {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> RegexMatcher.isMatch("abc", "*abc"));

        assertEquals("Pattern contains an invalid '*'.", exception.getMessage());
    }

    @Test
    void throwsForRepeatedStarPattern() {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> RegexMatcher.isMatch("abc", "a**bc"));

        assertEquals("Pattern contains an invalid '*'.", exception.getMessage());
    }
}
