package blockchain;

public class Block {

    private int id;
    private long timestamp;
    private String previousHash;
    private String currentHash;
    private long magicNumber;
    private int generationTime;

    public Block(int id, long timestamp, long magicNumber, String previousHash, String currentHash, int generationTime) {
        this.id = id;
        this.timestamp = timestamp;
        this.magicNumber = magicNumber;
        this.previousHash = previousHash;
        this.currentHash = currentHash;
        this.generationTime = generationTime;
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
                "\nBlock was generating for " + generationTime + " seconds";
    }
}
