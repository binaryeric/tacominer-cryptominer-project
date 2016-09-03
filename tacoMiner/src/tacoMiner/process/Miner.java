package tacoMiner.process;

public interface Miner {

    public void mine();

    public byte[] getHash(int nonce);

    public void saveHeader();

    public void allocateByteArray();

    public void convertValsToBytes();

}
