import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
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
        System.out.println("Debug console prints");
        Assertions.assertTrue(outContent.toString().replace("\r", "").contains
                ("-----------\n" +
                        "ID: 0\n" +
                        "Name: TestingName\n" +
                        "Description: TestingDescription\n" +
                        "Amount: 9999.99\n" +
                        "-----------\n" +
                        "ID: 1\n" +
                        "Name: SecondTestEntry\n" +
                        "Description: SecondDescription\n" +
                        "Amount: 55.0"));
    }

    @Test
    public void testNoClaims() {
        AIUSStarter.init();
        AIUSStarter.requestInsuranceClaimsData();
        Assertions.assertTrue(outContent.toString().contains("There are no claims in the database"));
    }
}
