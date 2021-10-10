
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.PrintStream;
import java.util.Scanner;

public class InsuranceClaimTest {
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
    public void testFileInsuranceClaim() {
        String name = "TestingName";
        String description = "TestingDescription";
        String amount = "5000.00";

        AIUSStarter.init();
        Scanner s = new Scanner(String.format("%s\n%s\n%s\n", name, description, amount)).useDelimiter("\n");
        AIUSStarter.fileInsuranceClaim(s);

        InsuranceClaim claim = AIUSStarter.claims.get(0);

        Assertions.assertEquals(claim.getClaimerName(), name);
        Assertions.assertEquals(claim.getDescription(), description);
        Assertions.assertEquals(claim.getClaimAmount(), Float.parseFloat(amount));
        Assertions.assertFalse(claim.isApproved());
    }

    @Test
    public void testInsuranceClaimApproval() {
        setupTestEnv();
        InsuranceClaim claim = AIUSStarter.claims.get(0);

        String insuranceClaimID = Integer.toString(claim.getID());
        Scanner s2 = new Scanner(String.format("%s\n", insuranceClaimID));
        AIUSStarter.approveInsuranceClaim(s2);

        Assertions.assertTrue(claim.isApproved());
    }

    @Test
    public void testInsuranceClaimZeroSum() {
        String name = "TestingName";
        String description = "TestingDescription";
        String amount = "0";

        AIUSStarter.init();
        Scanner s = new Scanner(String.format("%s\n%s\n%s\n%s\n", name, description, amount, 100)).useDelimiter("\n");
        AIUSStarter.fileInsuranceClaim(s);

        Assertions.assertTrue(outContent.toString().contains("Amount to claim has to be bigger than 0."));
    }

    @Test
    public void testInsuranceClaimReapproval() {
        AIUSStarter.init();
        InsuranceClaim claim = new InsuranceClaim("a", "b", 100, true);
        AIUSStarter.claims.add(claim);

        Scanner s = new Scanner(String.format("%s\n", 0)).useDelimiter("\n");
        AIUSStarter.approveInsuranceClaim(s);

        Assertions.assertTrue(outContent.toString().contains("This claim is already approved."));
    }

    @Test
    public void testInsuranceClaimNegativeID() {
        AIUSStarter.init();
        InsuranceClaim claim = new InsuranceClaim("a", "b", 100, false);
        AIUSStarter.claims.add(claim);

        Scanner s = new Scanner(String.format("%s\n%s\n", -1, 0)).useDelimiter("\n");
        AIUSStarter.approveInsuranceClaim(s);

        Assertions.assertTrue(outContent.toString().contains("Invalid ID."));
    }

    @Test
    public void testInsuranceClaimTooHighID() {
        AIUSStarter.init();
        InsuranceClaim claim = new InsuranceClaim("a", "b", 100, false);
        AIUSStarter.claims.add(claim);

        Scanner s = new Scanner(String.format("%s\n%s\n", 999999999, 0)).useDelimiter("\n");
        AIUSStarter.approveInsuranceClaim(s);

        Assertions.assertTrue(outContent.toString().contains("Invalid ID."));
    }
}
