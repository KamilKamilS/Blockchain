package blockchain;

import blockchain.util.HashUtil;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class Blockchain {

    private Block[] chain;
    private int id;
    private int startZeros;

    public Blockchain(int startZeros) {
        this.id = 1;
        chain = new Block[5];
        this.startZeros = startZeros;
    }

    public Block[] getChain() {
        return chain;
    }

    void generateNewBlock() {
        long startTime = new Date().getTime() ;
        String hash = "";
        long magicNumber = 0l;
        do {
            magicNumber = ThreadLocalRandom.current().nextLong();
            hash = HashUtil.applySha256(String.valueOf(magicNumber));
        } while (!validHash(hash));

        long endTime = new Date().getTime();

        int generationTime = (int)(endTime - startTime) / 1000;

        Block block;
        if (id == 1) {
            block = new Block(id, new Date().getTime(), magicNumber, "0", hash, generationTime);
        } else {
            block = new Block(id, new Date().getTime(), magicNumber, chain[id - 2].getCurrentHash(), hash, generationTime);
        }
        chain[id - 1] = block;
        id++;
    }

    private boolean validHash(String hash) {
        for (int i = 0; i < startZeros; i++) {
            if(hash.charAt(i)!='0') {
                return false;
            }
        }
        return true;
    }

    boolean validate() {
        for (Block block : chain) {
            if(!block.getCurrentHash().equals(HashUtil.applySha256(String.valueOf(block.getMagicNumber())))) {
                return false;
            }
        }
        return true;
    }
}
