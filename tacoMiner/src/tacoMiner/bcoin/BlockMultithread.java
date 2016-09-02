package tacoMiner.bcoin;

import java.util.SplittableRandom;

public class BlockMultithread {
    static String _ver;
    static String pbhh;
    static String mrh;
    static String _time;
    static String _nBits;

    public static void init(String a, String b, String c, String d, String e) {
        _ver = a;
        pbhh = b;
        mrh = c;
        _time = d;
        _nBits = e;
    }

    public static Block[] multiThreadBlocksGenerate(int threads) {
        Block[] b = new Block[threads];
        for (int i = 0; i < threads; i++) {
            System.out.println("Generating Block Object #" + i);
            b[i] = new Block(_ver, pbhh, mrh, _time, _nBits);
        }
        return b;
    }

    public static void saveHeaders(Block[] b) {
        for (Block a : b) {
            a.saveHeader();
        }
    }

    public static void initBytes(Block[] b) {
        for (Block a : b) {
            a.convertValsToBytes();
        }
    }

    public static void startMine(SplittableRandom sr, Block[] b, byte[] target) {
        for (Block a : b) {
            System.out.println("Starting Miner Thread: " + a.hashCode());
            System.out.println("Thread ID: " + (new Work(sr, a, target)).StartThread());
        }
    }
}
