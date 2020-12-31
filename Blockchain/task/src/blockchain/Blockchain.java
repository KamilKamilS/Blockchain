package blockchain;

import blockchain.util.HashUtil;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Blockchain implements Serializable {

    private static final long serialVersionUID = 1L;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Block block : chain) {
            sb.append(block);
            sb.append("\n");
        }
        return sb.toString();
    }

    boolean validate() {
        for (int i = 0; i < chain.size(); i++) {
            if (i == 0) {
                if (!chain.get(i).getPreviousHash().equals("0")) {
                    return false;
                }
            } else {
                if (!chain.get(i).getPreviousHash().equals(chain.get(i - 1).getCurrentHash())) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void serialize(Object obj, String fileName) throws Exception {
        FileOutputStream fos = new FileOutputStream(fileName);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(obj);
        oos.close();
    }

    public static Object deserialize(String fileName) throws Exception {
        FileInputStream fis = new FileInputStream(fileName);
        BufferedInputStream bis = new BufferedInputStream(fis);
        ObjectInputStream ois = new ObjectInputStream(bis);
        Object obj = ois.readObject();
        ois.close();
        if (((Blockchain) obj).validate()) {
            return obj;
        }
        return null;
    }
}
