package blockchain;

import blockchain.util.HashUtil;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class Block implements Serializable {

    private int id;
    private long timestamp;
    private String previousHash;
    private String currentHash;
    private long magicNumber;
    private int generationTime;

    public Block(Block previousBlock, int requiredZeros) {
        this.timestamp = new Date().getTime();
        this.previousHash = previousBlock == null? "0" : previousBlock.currentHash;
        this.id = previousBlock == null? 0 : previousBlock.id + 1;
        generateHash(requiredZeros);
        this.generationTime = calculateGenerationTimeInSeconds(timestamp);
    }


    private void generateHash(int requiredZeros) {
        do {
            this.magicNumber = ThreadLocalRandom.current().nextLong();
            this.currentHash = HashUtil.applySha256(String.valueOf(magicNumber + id + timestamp));
        } while(!validHash(requiredZeros));
    }

    private boolean validHash(int requiredZeros) {
        return currentHash.substring(0, requiredZeros).replaceAll("0", "").isBlank();
    }

    private int calculateGenerationTimeInSeconds(long startTime) {
        long endTime = System.currentTimeMillis();
        return (int)(endTime - startTime) / 1000;
    }

    public int getId() {
        return id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public String getCurrentHash() {
        return currentHash;
    }

    public long getMagicNumber() {
        return magicNumber;
    }

    @Override
    public String toString() {
        return "Block:" +
                "\nId: " + id +
                "\nTimestamp: " + timestamp +
                "\nMagic number: " + magicNumber +
                "\nHash of the previous block:\n" + previousHash +
                "\nHash of the block:\n" + currentHash +
                "\nBlock was generating for " + generationTime + " seconds\n";
    }
}
