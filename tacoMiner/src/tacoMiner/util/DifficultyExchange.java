package tacoMiner.util;

/*






EVERYTHING HERE IS DEPRECREATED, TOMMOROW I NEED TO DO THIS:

CONVERT DIFFICULTY TO NBITS

NBITS CAN THEN BE TURNED INTO TARGET

TODO: DO ABOVE

 */
public class DifficultyExchange {

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

}
