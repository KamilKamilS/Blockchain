package blockchain;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Enter how many zeros the hash must start with:");
        Scanner scanner = new Scanner(System.in);

        int numberOfZeros = Integer.valueOf(scanner.nextLine());
        AppRunner.generateBlockchain(numberOfZeros);
        AppRunner.printAllBlocks();
    }
}
