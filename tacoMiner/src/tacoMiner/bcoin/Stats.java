package tacoMiner.bcoin;

import tacoMiner.main.tacoInit;

public class Stats extends Thread {
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(tacoInit.hashesCount + " : H/pS");
            tacoInit.hashesCount = 0;
        }
    }
}
