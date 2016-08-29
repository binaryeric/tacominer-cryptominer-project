package tacoMiner.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

/*

 */
public class DifficultyExchange {
    public static String DifficultyToTarget(double difficulty) {
        return NBitsToTarget(DifficultyToNBits(difficulty));
    }

    public static BigInteger DifficultyToTargetViaDivison(double difficulty) {
        //max target / difficulty
        return new BigDecimal("110427941548649020598956093796432407239217743554726184882600387580788735").divide(BigDecimal.valueOf(difficulty), RoundingMode.HALF_UP).toBigInteger();
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

    public static String NBitsToTarget(int nBits) {
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
