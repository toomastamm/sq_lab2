import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AIUSStarter {

    public static List<InsuranceClaim> claims;

    public static void main(String[] args) {
        init();
        Scanner s = new Scanner(System.in);

        while (true) {
            System.out.println("-----------");
            System.out.println("Welcome to AIUS.");
            System.out.println("-----------");
            System.out.println("1. Exit");
            System.out.println("2. File insurance claim");
            System.out.println("3. Approve insurance claim");
            System.out.println("4. Show all insurance claims");
            System.out.println("-----------");
            System.out.println("Choose action: ");

            String input = s.nextLine();

            switch (input) {
                case "1" -> System.exit(0);
                case "2" -> fileInsuranceClaim(s);
                case "3" -> approveInsuranceClaim(s);
                case "4" -> requestInsuranceClaimsData();
                default -> System.out.println("Invalid action. Choose action 1-4.");
            }
        }
    }

    public static void init() {
        claims = new ArrayList<>();
    }


    public static void fileInsuranceClaim(Scanner s) {
        System.out.println("-----------");
        System.out.println("Insurance claim form.");
        System.out.println("Name of claimant:");
        String name = s.nextLine();
        System.out.println("What happened:");
        String description = s.nextLine();
        System.out.println("Claim sum:");
        float sum = Float.parseFloat(s.nextLine());

        InsuranceClaim newClaim = new InsuranceClaim(name, description, sum, false);
        claims.add(newClaim);
        newClaim.setID(claims.indexOf(newClaim));

        System.out.println("Your claim has been filed, returning to menu.");
    }

    public static void approveInsuranceClaim(Scanner s) {
        System.out.println("-----------");
        System.out.println("ID of insurance claim that will be approved:");
        String inputIDString = s.nextLine();
        int inputID = Integer.parseInt(inputIDString);

        if (inputID >= claims.size() || inputID < 0)
        {
            System.out.println("Invalid index, returning to menu.");
            return;
        }
        claims.get(inputID).setApproved(true);

        System.out.println("Claim " + inputIDString + " has been approved, returning to menu.");
    }

    public static void requestInsuranceClaimsData() {
        for (InsuranceClaim claim : claims)
        {
            System.out.println("-----------");
            System.out.println("ID: " + Integer.toString(claim.getID()));
            System.out.println("Name: " + claim.getClaimerName());
            System.out.println("Description: " + claim.getDescription());
            System.out.println("Amount: " + claim.getClaimAmount());
        }
    }
}

