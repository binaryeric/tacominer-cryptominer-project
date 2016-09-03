package tacoMiner.process.cpu;

import java.util.SplittableRandom;

import tacoMiner.debug.Log;
import tacoMiner.process.*;

public class BlockThreads implements DeviceThreadManager {

    private static String _ver, pbhh, mrh, _time, _nBits;
    private Log logger;

    public void init(String vers, String pb, String mroot, String t, String bits) {
        _ver = vers;
        pbhh = pb;
        mrh = mroot;
        _time = t;
        _nBits = bits;
        logger = logger.getInstance();
    }

    public Miner[] threadGenerator(int numThreads) {
        Miner[] blocks = new Miner[numThreads];
        for (int i = 0; i < numThreads; i++) {
            logger.print("Initializing Block " + Integer.toString(i));
            blocks[i] = new BlockMiner(_ver, pbhh, mrh, _time, _nBits);
        }
        return blocks;
    }

    public void initBytes(Miner[] b) {
        for (Miner a : b) {
            a.convertValsToBytes();
        }
    }

    public void saveHeaders(Miner[] b) {
        for (Miner a : b) {
            a.saveHeader();
        }
    }

    public void startMiners(SplittableRandom sr, Miner[] b, byte[] tgt) {
        for (Miner a : b) {
            a.allocateByteArray();
            logger.print("Starting miner: " + Integer.toString(a.hashCode()));
            new MineThread(sr, a, tgt).start();
        }

    }


}