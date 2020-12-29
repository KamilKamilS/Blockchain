package blockchain;

public class AppRunner {

    private static Blockchain blockchain;

    static void generateBlockchain(int size) {
        blockchain = new Blockchain(size);
        for (int i = 0; i < size; i++) {
            blockchain.generateNewBlock();
        }
    }

    static void printAllBlocks() {
        for (Block block : blockchain.getChain()) {
            System.out.println(block + "\n");
        }
    }
}
