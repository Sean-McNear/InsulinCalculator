// Sean McNear
// This program will calculate the amount of insulin required based on blood glucose level (BG), ketones, carbs, and insulin-to-carb ratio (ICR).

import java.util.Scanner;

public class Main {

    private static int getBG(Scanner scanner) {
        System.out.print("Enter blood sugar level: ");
        return scanner.nextInt();
    }

    private static int getCarbs(Scanner scanner) {
        System.out.print("Enter number of carbs: ");
        return scanner.nextInt();
    }

    private static double getKetoneMultiplier(Scanner scanner) {
        double ketoneMultiplier = 1;
        // Check ketone severity and assign it to a multiplier
        scanner.nextLine();
        System.out.print("Enter ketone level (none, trace, moderate, large): ");
        String ketones = scanner.nextLine().trim();

        switch (ketones.toLowerCase()) {
            case "none":
                break;
            case "trace":
                ketoneMultiplier = 1.1;
                break;
            case "moderate":
                ketoneMultiplier = 1.2;
                break;
            case "large":
                ketoneMultiplier = 1.3;
                break;
            default:
                System.out.print("There was an error checking your ketones.");
                return 0;
        }
        return ketoneMultiplier;
    }

    private static double calculateBGCorrection(Scanner scanner, int BG) {

        // Get target BG and correction factor numbers
        scanner.nextLine(); // Clears input buffer
        System.out.print("Enter target blood sugar level: ");
        int targetBG = scanner.nextInt();
        System.out.print("Enter correction factor: ");
        int correctionFactor = scanner.nextInt();

        // Calculate correction
        return ((BG - targetBG) / (double) correctionFactor);
    }

    private static int getICR(Scanner scanner) {
        System.out.print("Enter insulin-to-carb ratio (1 : ?): ");
        return scanner.nextInt();
    }

    private static int calculateInsulin(Scanner scanner) {
        int carbs = getCarbs(scanner);
        int ICR = getICR(scanner);
        int BG = getBG(scanner);
        int correction = (int) calculateBGCorrection(scanner, BG);
        double ketoneMultiplier = getKetoneMultiplier(scanner);
        return (int) (Math.round(carbs / (double) ICR + correction) * ketoneMultiplier);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int insulin = calculateInsulin(scanner);
        System.out.print(insulin);
    }
}
