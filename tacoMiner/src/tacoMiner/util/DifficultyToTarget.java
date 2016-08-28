package tacoMiner.util;

import java.math.BigDecimal;
import java.math.BigInteger;

/*






EVERYTHING HERE IS DEPRECREATED, TOMMOROW I NEED TO DO THIS:

CONVERT DIFFICULTY TO NBITS

NBITS CAN THEN BE TURNED INTO TARGET

TODO: DO ABOVE



 */
public class DifficultyToTarget {
    public static BigDecimal MAX_TARGET;

    public static BigInteger DifficultyToTarget(double difficulty) {
        return (MAX_TARGET.multiply(BigDecimal.valueOf(difficulty))).toBigInteger();
    }

    public static void InitMaxTarget() {
        /*
        int z = (256-32);
        MAX_TARGET = MAX_TARGET.valueOf(2);
        MAX_TARGET = MAX_TARGET.pow(z);
        MAX_TARGET = MAX_TARGET.subtract(BigInteger.valueOf(1));
        */

        //real form is 2^(256-32) - 1 : as 0x00000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
        //however, bitcoin target is floating point and truncated as 0x00000000FFFF0000000000000000000000000000000000000000000000000000
        //so let's hard code the number!
        MAX_TARGET = new BigDecimal(new BigInteger("FFFF0000000000000000000000000000000000000000000000000000", 16));
    }
}
