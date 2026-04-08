package regexmatcher;

public final class RegexMatcher {

    private RegexMatcher() {
    }

    public static boolean isMatch(String s, String p) {
        validateInput(s, p);

        int sLength = s.length();
        int pLength = p.length();

        // dp[i][j] tells us whether the first i characters of s
        // match the first j characters of p.
        boolean[][] dp = new boolean[sLength + 1][pLength + 1];
        dp[0][0] = true;

        // Patterns like a*, a*b*, or a*b*c* can match an empty string,
        // so we pre-fill the first row for those cases.
        for (int j = 2; j <= pLength; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        for (int i = 1; i <= sLength; i++) {
            for (int j = 1; j <= pLength; j++) {
                char patternChar = p.charAt(j - 1);

                if (patternChar == '*') {
                    // Treat x* as matching zero occurrences of x.
                    dp[i][j] = dp[i][j - 2];

                    char previousPatternChar = p.charAt(j - 2);

                    // If the character before * matches the current input character,
                    // x* can also consume one more character from the input string.
                    if (matchesCurrentCharacter(s.charAt(i - 1), previousPatternChar)) {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                } else if (matchesCurrentCharacter(s.charAt(i - 1), patternChar)) {
                    // A direct character match, or '.', advances both string and pattern.
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }

        return dp[sLength][pLength];
    }

    private static void validateInput(String s, String p) {
        if (s == null || p == null) {
            throw new IllegalArgumentException("Input string and pattern must not be null.");
        }

        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '*') {
                // '*' must always follow a valid token and cannot repeat another '*'.
                if (i == 0 || p.charAt(i - 1) == '*') {
                    throw new IllegalArgumentException("Pattern contains an invalid '*'.");
                }
            }
        }
    }

    private static boolean matchesCurrentCharacter(char inputChar, char patternChar) {
        return patternChar == '.' || inputChar == patternChar;
    }
}
