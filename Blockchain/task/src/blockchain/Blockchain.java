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
    private static Blockchain instance = new Blockchain();
    private List<Block> chain;
    private int requiredZeros;

    private Blockchain() {
        this.chain = new ArrayList<>();
        this.requiredZeros = 1;
    }

    public List<Block> getChain() {
        return chain;
    }

    public int getSize() {
        return this.chain.size();
    }

    public static Blockchain getInstance() {
        return instance;
    }

    public int getRequiredZeros() {
        return requiredZeros;
    }

    public Block getPreviousBlock() {
        return chain.get(chain.size() - 1);
    }

    public void addToChain(Block block) {
        chain.add(block);
        System.out.println(block);
        calculateMiningLevel(block);
        System.out.println();
        if (getSize() == 5) {
            System.exit(0);
        }
    }

    private void calculateMiningLevel(Block block) {
        if (block.getGenerationTime() > 60) {
            this.requiredZeros--;
            System.out.println("N was decreased to " + requiredZeros);
        } else if (block.getGenerationTime() < 5) {
            requiredZeros++;
            System.out.println("N was increased to " + requiredZeros);
        } else {
            System.out.println("N stays the same");
        }
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

    public boolean validate() {
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
