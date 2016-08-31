package tacoMiner.merkle;

import tacoMiner.util.SHA256;

import javax.xml.bind.DatatypeConverter;

/**
 * TODO: Calculate Merkle root from a few transactions
 */
public class MerkleRoot {
    public String[] originalTrans;

    public MerkleRoot(String[] tx) {
        originalTrans = tx;
    }

    public static String[] convArray(String[][] z) {
        String[] t = new String[z.length];
        for (int i = 0; i < z.length; i++) {
            t[i] = z[i][0];
        }
        return t;
    }

    private String hasher(String a, String b) {
        a = SHA256.EndianReverse(a);
        b = SHA256.EndianReverse(b);//endian stuff

        String hash1 = DatatypeConverter.printHexBinary(SHA256.RawHash256(a + b));
        String hash2 = DatatypeConverter.printHexBinary(SHA256.RawHash256(hash1));

        return SHA256.EndianReverse(hash2);
    }

    public void calculateRoot() {
        String[] trans = originalTrans;

        while (!(trans.length == 1)) {
            boolean skip = false;
            String[] trans2 = new String[(int) Math.ceil((double) trans.length / 2)];

            boolean odd = (trans.length % 2 == 1);

            int z = 0;
            System.out.println(trans.length);
            for (int i = 0; i < trans.length; i++) {
                System.out.println(i);
                if (skip == false) {
                    if (i == trans.length - 1) break; //last one, this is a odd one so we do it manually
                    System.out.println("Merging:" + trans[i] + ":" + trans[i + 1]);
                    trans2[z] = hasher(trans[i], trans[i + 1]);
                }
                z = z + 1;
            }
            if (odd) {
                trans2[z] = hasher(trans[trans.length - 1], trans[trans.length - 1]);
            }

            trans = trans2;
        }

        System.out.println(trans[0]);
    }


}
