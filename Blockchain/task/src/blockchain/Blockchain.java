package blockchain;

import blockchain.util.HashUtil;

import java.util.Date;

public class Blockchain {

    private Block[] chain;
    private int id;

    public Blockchain(int size) {
        this.id = 1;
        chain = new Block[size];
    }

    public Block[] getChain() {
        return chain;
    }

    void generateNewBlock() {
        Block block;
        if (id == 1) {
            block = new Block(id, new Date().getTime(), "A", "0", HashUtil.applySha256(String.valueOf("A")));
        } else {
            block = new Block(id, new Date().getTime(), "A", chain[id - 2].getCurrentHash(), HashUtil.applySha256(String.valueOf("A")));
        }
        chain[id - 1] = block;
        id++;
    }

    boolean validate() {
        for (Block block : chain) {
            if(!block.getCurrentHash().equals(HashUtil.applySha256(block.getMessage()))) {
                return false;
            }
        }
        return true;
    }
}
