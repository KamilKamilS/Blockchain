package blockchain;

public class AppRunner {

    private static Blockchain blockchain;

    static void generateBlockchain(int requiredZeros) {
        blockchain = new Blockchain(requiredZeros);
        for (int i = 0; i < 5; i++) {
            blockchain.generateNewBlock();
        }
    }

    static void printAllBlocks() {
        for (Block block : blockchain.getChain()) {
            System.out.println(block + "\n");
        }
    }
}
