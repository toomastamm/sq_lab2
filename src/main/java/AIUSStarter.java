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
            System.out.println("3. ");
            System.out.println("4. ");
            System.out.println("-----------");
            System.out.println("Choose action: ");

            String input = s.nextLine();

            switch (input) {
                case "1" -> System.exit(0);
                case "2" -> fileInsuranceClaim(s);
                case "3" -> throw new UnsupportedOperationException();
                case "4" -> throw new UnsupportedOperationException();
                default -> {
                }
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

        claims.add(new InsuranceClaim(name, description, sum, false));
        System.out.println("Your claim has been filed, returning to menu.");
    }
}

