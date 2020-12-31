package blockchain;

import miner.Miner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AppRunner {



    static void createMainers(int number) {
        ExecutorService executor = Executors.newFixedThreadPool(number);
        executor.submit(new Miner());

        if (Blockchain.getInstance().getSize() >= 5) {
            try {
                executor.awaitTermination(10, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static void printBlockchain() {
        System.out.println(Blockchain.getInstance());
    }
}
