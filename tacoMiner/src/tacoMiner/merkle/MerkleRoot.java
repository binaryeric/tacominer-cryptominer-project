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

    private Merkle hasher(String a, String b) {
        a = SHA256.EndianReverse(a);
        b = SHA256.EndianReverse(b);//endian stuff

        String hash1 = DatatypeConverter.printHexBinary(SHA256.RawHash256(a + b));
        String hash2 = DatatypeConverter.printHexBinary(SHA256.RawHash256(hash1));

        return new Merkle(SHA256.EndianReverse(hash2));
    }

    private Merkle[] genMerkles(String[] hashes) {
        Merkle[] a = new Merkle[hashes.length];
        for (int i = 0; i < hashes.length; i++) {
            a[i] = new Merkle(hashes[i]);
        }
        return a;
    }

    public String calculateRoot() {
        Merkle[] merkles = genMerkles(originalTrans);
        boolean odd = (merkles.length % 2 == 1);
        int lastOne = -1;
        while (true) {

            Merkle[] newMerkles = new Merkle[(merkles.length / 2 + (odd ? 1 : 0))];

            if (merkles.length == 1) break;
            if (merkles[1] == null) break;

            if (merkles.length % 2 == 1) lastOne = merkles.length - 1;

            int counter = 0;

            for (int i = 0; i < merkles.length; i = i + 2) {
                if (i == lastOne) {
                    newMerkles[counter] = hasher(merkles[i].hash, merkles[i].hash);
                    //System.out.println("Last One Merging: " + merkles[i] + " and " + merkles[i] + " Result:" + newMerkles[counter]);
                    break;
                }
                newMerkles[counter] = hasher(merkles[i].hash, merkles[i + 1].hash);
                //System.out.println("Merging: " + merkles[i] + " and " + merkles[i + 1] + " Result:" + newMerkles[counter]);

                counter++;
            }
            //System.out.println("------------------------");
            merkles = newMerkles;

        }
        return merkles[0].toString();
    }


}
