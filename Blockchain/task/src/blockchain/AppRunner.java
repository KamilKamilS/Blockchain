package blockchain;

public class AppRunner {

    private static Blockchain blockchain;

    static void generateBlockchain(int numberOfZeros) {
        blockchain = new Blockchain(numberOfZeros);
        for (int i = 0; i < blockchain.getChain().length; i++) {
            blockchain.generateNewBlock();
        }
    }

    static void printAllBlocks() {
        for (Block block : blockchain.getChain()) {
            System.out.println(block + "\n");
        }
    }
}
