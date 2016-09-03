package tacoMiner.process.cpu;

import tacoMiner.process.*;
import tacoMiner.mineResource.*;
import tacoMiner.util.*;
import tacoMiner.debug.*;
import tacoMiner.main.Settings;
import tacoMiner.merkle.MerkleRoot;

import java.nio.ByteBuffer;
import java.time.Instant;
import java.util.SplittableRandom;
import org.json.JSONException;


public class MinerController implements SubSystem {

    private static byte[] timeBytes;
    private Processor cpu;
    private DeviceThreadManager blockthreads;
	//
	private Log logger;
	private double difficulty;
	private String vers, prev, merkle, time, nbits;
	private Miner[] blocks;

    public static synchronized byte[] getTime() {
        return timeBytes;
    }

    private synchronized void setTime(byte[] t) {
        timeBytes = t;
    }

    public void start() {
        cpu = CPU.getInstance();
        logger = Log.getInstance();
        difficulty = Blocksetup.getDifficulty();

        // Init hasher
        SHA256old.InitMD();
        GetUnconfTX tx = new GetUnconfTX((short) 8);
        MerkleRoot merk = new MerkleRoot(new String[]{"1", "2"});
        //
        try {
            merk = new MerkleRoot(MerkleRoot.convArray(tx.getTXArray()));
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        SplittableRandom random = new SplittableRandom();

        logger.printHighlight("Calculating Target...");

        byte[] tgt = Blocksetup.getTarget(difficulty);

        //
        logger.printHighlight("Calculating Merkle Root...");


        vers = SHA256old.EndianReverse(Integer.toString(Settings.PROTOCOL_VERSION));
        prev = SHA256old.EndianReverse(BTCinfo.getPreviousHash());
        merkle = SHA256old.EndianReverse(merk.calculateRoot());
        time = SHA256old.EndianReverse(Long.toString(Instant.now().getEpochSecond()));
        nbits = SHA256old.EndianReverse(Integer.toHexString(DifficultyExchange.DifficultyToNBits(difficulty)));

        //Init block threads:
        blockthreads = new BlockThreads();
        blockthreads.init(vers, prev, merkle, time, nbits);
        //
        blocks = blockthreads.threadGenerator(Settings.THREAD_GENERATE);
        // Start clock:
        startClock();

        logger.printHighlight("Initializing header prefix");
        blockthreads.saveHeaders(blocks);

        logger.printHighlight("Initializing headers into bytes");
        blockthreads.initBytes(blocks);
        //
        logger.printHighlight("Starting to mine...");
        //
        blockthreads.startMiners(random, blocks, tgt);
    }

    private void startClock() {
		new Thread(new Runnable() {
			public void run() {
				while(cpu.isAlive()) {
					setTime(ByteBuffer.allocate(4).putInt((int) Instant.EPOCH.getEpochSecond()).array());
					try {
						Thread.sleep(950);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
}
