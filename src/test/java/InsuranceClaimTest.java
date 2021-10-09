import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

public class InsuranceClaimTest {

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
}