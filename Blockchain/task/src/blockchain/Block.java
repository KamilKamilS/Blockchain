package blockchain;

public class Block {

    private int id;
    private long timestamp;
    private String previousHash;
    private String currentHash;
    private String message;

    public Block(int id, long timestamp, String message, String previousHash, String currentHash) {
        this.id = id;
        this.timestamp = timestamp;
        this.message = message;
        this.previousHash = previousHash;
        this.currentHash = currentHash;
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

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Block:" +
                "\nId: " + id +
                "\nTimestamp: " + timestamp +
                "\nHash of the previous block:\n" + previousHash +
                "\nHash of the block:\n" + currentHash;
    }
}
