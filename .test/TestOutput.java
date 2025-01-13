import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.*;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestOutput
{
    public static final int BUFFER_LENGTH = 4096;
    private static ByteArrayInputStream in;
    private static InputStream originalIn;
    private static byte[] inputBuffer = new byte[BUFFER_LENGTH];

    @BeforeAll
    private static void init() {
        in = new ByteArrayInputStream(inputBuffer);
        originalIn = System.in;
        System.setIn(in);
    }

    @AfterAll
    private static void cleanUp() {
        System.setIn(originalIn);
    }

    @ParameterizedTest(name="{0}")
    @CsvFileSource(resources = "output_tests.csv")
    public void testOutputMatch(String testCaseName, String input, String expectedOutput, String matchType)
    {
        // Capture stdout
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Send input
        byte[] inputBytes = input.getBytes();
        TestOutput.in.reset();
        int destinationPos = BUFFER_LENGTH - inputBytes.length;
        System.arraycopy(inputBytes, 0, TestOutput.inputBuffer, destinationPos, inputBytes.length);
        TestOutput.in.skip(destinationPos);

        // Run the class and capture stdout
        Main.main(null);
        String actualOutput = outputStream.toString().trim();
        System.setOut(originalOut);

        // Perform the corresponding assertion based on the match type
        switch (matchType) {
            case "exact":
                assertEquals(expectedOutput, actualOutput, "Exact match failed for " + testCaseName);
                break;
            case "match":
                assertTrue(actualOutput.contains(expectedOutput), "Match failed for " + testCaseName +
                        "\n" + actualOutput + " does not contain " + expectedOutput);
                break;
            case "regex":
                assertTrue(Pattern.matches(expectedOutput, actualOutput), "Regex match failed for " + testCaseName +
                        "\n" + actualOutput + " does is not matched by pattern " + expectedOutput);
                break;
            default:
                fail("Invalid match type for " + testCaseName);
        }
//            } catch (ClassNotFoundException e)
//            {
//                throw new RuntimeException(e);
//            } catch (InvocationTargetException e)
//            {
//                throw new RuntimeException(e);
//            } catch (IllegalAccessException e)
//            {
//                throw new RuntimeException(e);
//            } catch (NoSuchMethodException e)
//            {
//                throw new RuntimeException(e);
//            } finally {
//                System.setIn(originalIn);
//            }
    }
}

