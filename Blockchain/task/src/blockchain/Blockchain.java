package blockchain;

import blockchain.util.HashUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Blockchain {

    private List<Block> chain;
    private int requiredZeros;

    public Blockchain(int requiredZeros) {
        this.chain = new ArrayList<>();
        this.requiredZeros = requiredZeros;
    }

    public List<Block> getChain() {
        return chain;
    }

    void generateNewBlock() {
        Block block;
        if (this.chain.size() == 0) {
            block = new Block(null, requiredZeros);
        } else {
            block = new Block(chain.get(chain.size() - 1), requiredZeros);
        }
        this.chain.add(block);
    }


    boolean validate() {
        for (Block block : chain) {
            if(!block.getCurrentHash().equals(HashUtil.applySha256(String.valueOf(block.getMagicNumber() + block.getId() + block.getTimestamp())))) {
                return false;
            }
        }
        return true;
    }
}
