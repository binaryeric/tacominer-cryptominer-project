package tacoMiner.process.cpu;

import java.util.SplittableRandom;

import tacoMiner.debug.Log;
import tacoMiner.main.Settings;
import tacoMiner.process.Miner;
import tacoMiner.process.Processor;
import tacoMiner.util.SHA256;

public class MineThread extends Thread {

    private SplittableRandom rand;
    private int nonce = 0;
    private Miner mine;
    private byte[] tgt;
    private byte[] hash;

    private SHA256 sha;

    private int ns, ne;

    private Processor cpu;
    private Log logger;

    public MineThread(SplittableRandom _sr, Miner m, byte[] t) {
        rand = _sr;
        mine = m;
        tgt = t;
        cpu = CPU.getInstance();
        logger = Log.getInstance();
        //
        ns = Settings.nonceStart;
        ne = Settings.nonceMax;

        //
        sha = new SHA256();
    }

    public long Mine() {
        mine.allocateByteArray();
        this.start();
        return this.getId();
    }

    public void run() {
        while (cpu.isAlive()) {
            cpu.hash();
            nonce = rand.nextInt(ns, ne);
            hash = mine.getHash(nonce);
            //
            /*for(Byte a : hash) {
				System.out.println(hash);
			}*/
            //System.out.println("Hash: "+hash);
            if (sha.ArrayCompare(hash, tgt)) {
                logger.printHighlight("Target hash found!\n");
                cpu.stop();
            }
        }
    }

}
