package miner;

import blockchain.Block;
import blockchain.Blockchain;

public class Miner implements Runnable{

    private Blockchain blockchain;
    private Block temp;

    public Miner() {
        this.blockchain = Blockchain.getInstance();
    }

    @Override
    public void run() {
        while (true) {
            generateBlock();
            blockchain.addToChain(temp);
        }
    }

    private void generateBlock() {
        int minerId = (int) Thread.currentThread().getId();
        if (blockchain.getSize() == 0) {
            this.temp = new Block(null, blockchain.getRequiredZeros(), minerId);
        } else {
            this.temp = new Block(blockchain.getPreviousBlock(), blockchain.getRequiredZeros(), minerId);
        }
    }
}
