
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import java.util.Scanner;

public class DataRequestTest {
    private final java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
    private final java.io.ByteArrayOutputStream errContent = new java.io.ByteArrayOutputStream();
    private final java.io.PrintStream originalOut = System.out;
    private final java.io.PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    private void setupTestEnv()
    {
        AIUSStarter.init();

        String name = "TestingName";
        String description = "TestingDescription";
        String amount = "9999.99";

        Scanner s = new Scanner(String.format("%s\n%s\n%s\n", name, description, amount)).useDelimiter("\n");
        AIUSStarter.fileInsuranceClaim(s);
    }

    @Test
    public void testInsuranceDataRequest() {
        setupTestEnv();
        String name = "SecondTestEntry";
        String description = "SecondDescription";
        String amount = "55.0";

        Scanner s = new Scanner(String.format("%s\n%s\n%s\n", name, description, amount)).useDelimiter("\n");
        AIUSStarter.fileInsuranceClaim(s);

        AIUSStarter.requestInsuranceClaimsData();
        Assertions.assertEquals(
                "-----------\r\n" +
                        "ID: 0\r\n" +
                        "Name: TestingName\r\n" +
                        "Description: TestingDescription\r\n" +
                        "Amount: 9999.99\r\n" +
                        "-----------\r\n" +
                        "ID: 1\r\n" +
                        "Name: SecondTestEntry\r\n" +
                        "Description: SecondDescription\r\n" +
                        "Amount: 55.0\r\n" +
                        "-----------\r\n" +
                        "Returning to menu.\r\n", outContent.toString());
    }
}
