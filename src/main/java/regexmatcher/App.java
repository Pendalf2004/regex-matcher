package regexmatcher;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        String s;
        String p;

        // Allow fast execution with command-line arguments,
        // but fall back to interactive CLI input when args are omitted.
        if (args.length >= 2) {
            s = args[0];
            p = args[1];
        } else {
            try (Scanner scanner = new Scanner(System.in)) {
                System.out.print("Enter input string s: ");
                s = scanner.nextLine();

                System.out.print("Enter pattern p: ");
                p = scanner.nextLine();
            }
        }

        boolean matches = RegexMatcher.isMatch(s, p);
        System.out.println("s = " + s);
        System.out.println("p = " + p);
        System.out.println("matches = " + matches);
    }
}
