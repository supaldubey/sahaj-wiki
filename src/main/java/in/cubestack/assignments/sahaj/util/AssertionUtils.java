package in.cubestack.assignments.sahaj.util;

public class AssertionUtils {

    public static void assertThat(boolean condition, String message) {
        if (!condition) {
            throw new RuntimeException(message);
        }
    }
}
