package tacoMiner.bcoin;


import tacoMiner.main.tacoInit;
import tacoMiner.util.SHA256;

import java.util.SplittableRandom;

public class Work extends Thread {

    public SplittableRandom random;
    public int nonce = 0;
    public Block b;
    byte[] target;
    byte[] hash;

    public Work(SplittableRandom _sr, Block _b, byte[] _t) {
        random = _sr;
        b = _b;
        target = _t;
    }

    public long StartThread() {
        this.start();
        return this.getId();
    }

    public void run() {
        while (true) {
            tacoInit.hashesCount++;

            nonce = random.nextInt(0, Integer.MAX_VALUE);
            try {
                hash = b.getHash(nonce);
            } catch (Exception e) {
                e.printStackTrace();
            }
            /*
            System.out.println("---- HASH -----");
            for(Byte a : hash){
                System.out.print(String.format("%02X ", a));
            }
            System.out.println("\n===========");
            System.out.println("TARGET: " + targetString);
            */

            if (b.SHA256.ArrayCompare(hash, target)) {
                System.out.println("WE MINED A BLOCK");
                System.out.println("WE MINED A BLOCK");
                System.out.println("WE MINED A BLOCK");
                System.out.println("WE MINED A BLOCK");
                System.out.println("WE MINED A BLOCK");
                System.out.println("WE MINED A BLOCK");
                System.out.println("Mined: Nonce: " + nonce);
                break;
            }
        }
    }
}
