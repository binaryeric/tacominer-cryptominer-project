package tacoMiner.util;

import java.math.BigInteger;

/*

 */
public class DifficultyExchange {
    public static String DifficultyToTarget(double difficulty) {
        return NBitsToTargetMath(DifficultyToNBits(difficulty));
    }

    public static int DifficultyToNBits(double difficulty) {
        int shift = 29;

        double ftarg = (double) 0x0000ffff / difficulty;
        while (ftarg < (double) 0x00008000) {
            shift--;
            ftarg *= 256.0;
        }

        while (ftarg >= (double) 0x00800000) {
            shift++;
            ftarg /= 256.0;
        }
        return (int) ftarg + (shift << 24);
        //return nBits;
    }

    public static String NBitsToTargetMath(int nBits) {
        String hex = Integer.toHexString(nBits);

        int numbytes = Integer.decode("0x" + hex.substring(0, 2));
        String significand = Integer.decode("0x" + hex.substring(2, hex.length())).toString();

        int a = (numbytes - (significand.length() / 2));

        BigInteger b = new BigInteger("256").pow(a).multiply(new BigInteger(significand));


        return b.toString();
    }

    public static String NBitsToTarget(int nBits) {
        /*
        Use math because it's better :)
         */
        String hex = Integer.toHexString(nBits);
        int numbytes = Integer.decode("0x" + hex.substring(0, 2));
        String prefix = hex.substring(3, hex.length());
        int toPad = numbytes - prefix.length();
        StringBuilder str = new StringBuilder();
        str.append(prefix);
        for (int i = 0; i < toPad; i++) {
            str.append("0");
        }
        return str.toString();
    }

}
