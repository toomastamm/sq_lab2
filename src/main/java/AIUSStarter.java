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
        Float sum = null;
        while (sum == null) {
            try {
                System.out.println("Claim sum:");
                sum = Float.parseFloat(s.nextLine());
                if (sum == 0) {
                    sum = null;
                    System.out.println("Amount to claim has to be bigger than 0.");
                }
            } catch (Exception ignored) {

            }
        }

        InsuranceClaim newClaim = new InsuranceClaim(name, description, sum, false);

        claims.add(newClaim);
        newClaim.setID(claims.indexOf(newClaim));

        System.out.println("Your claim has been filed, returning to menu.");
    }

    public static void approveInsuranceClaim(Scanner s) {
        System.out.println("-----------");
        int inputID = -1;

        while (inputID >= claims.size() || inputID < 0) {
            try {
                System.out.println("ID of insurance claim that will be approved:");
                String inputIDString = s.nextLine();
                inputID = Integer.parseInt(inputIDString);

                if (inputID >= claims.size() || inputID < 0) {
                    System.out.println("Invalid ID.");
                }
            } catch (Exception ignored) {

            }

        }

        InsuranceClaim claim = claims.get(inputID);
        if (claim.isApproved()) {
            System.out.println("This claim is already approved.");
            return;
        }

        claim.setApproved(true);

        System.out.println("Claim " + inputID + " has been approved, returning to menu.");
    }

    public static void requestInsuranceClaimsData() {
        if (claims.size() == 0) {
            System.out.println("There are no claims in the database");
            return;
        }

        for (InsuranceClaim claim : claims) {
            System.out.println("-----------");
            System.out.println("ID: " + claim.getID());
            System.out.println("Name: " + claim.getClaimerName());
            System.out.println("Description: " + claim.getDescription());
            System.out.println("Amount: " + claim.getClaimAmount());
        }
    }
}

