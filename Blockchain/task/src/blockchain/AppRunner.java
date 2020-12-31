package blockchain;

public class AppRunner {

    private static Blockchain blockchain;

    static void generateBlockchain(int requiredZeros) {
        blockchain = new Blockchain(requiredZeros);
        for (int i = 0; i < 5; i++) {
            blockchain.generateNewBlock();
        }
    }

    static void printBlockchain() {
        System.out.println(blockchain);
        System.out.println(blockchain.validate());
    }
}
