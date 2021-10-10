
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

public class InsuranceClaimTest {
    private final java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
    private final java.io.ByteArrayOutputStream errContent = new java.io.ByteArrayOutputStream();
    private final java.io.PrintStream originalOut = System.out;
    private final java.io.PrintStream originalErr = System.err;

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
}
