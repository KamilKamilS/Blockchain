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
            executor.shutdownNow();
        }
    }

    static void printBlockchain() {
        System.out.println(Blockchain.getInstance());
    }
}
