package tacoMiner.mineResource;

import tacoMiner.util.*;

import java.math.BigInteger;

public class Blocksetup {
	
	public static double getDifficulty() {
		double difficulty = Double.valueOf(HTTP.getAsync(HTTP.formatURL("https://blockchain.info/q/getdifficulty")));
		return difficulty;
	}

	public static byte[] getTarget(double diff) {
		String tgtStr = new BigInteger(DifficultyExchange.DifficultyToTarget(diff)).toString(16);
		int tgtLen = tgtStr.length();
		for (int i = 0; i < ((tgtLen % 2 == 1 ? 1 : 0) + tgtLen - tgtLen); i++) {
			tgtStr = "0" + tgtStr;
			//pad hex: f -> becomes -> 0f
		}
		return hexStringToByteArray(tgtStr);
	}
	
	// 
	
    protected static byte[] hexStringToByteArray(String s) {
        System.out.println(s);
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }
    

    protected static boolean checkByteArray(byte[] a, byte[] b) {
        for (int i = 0; i < b.length; i++) {
            System.out.println(a[i]);
            System.out.println(b[i]);
            if (a[i] > b[i]) {
                return false;
            }
        }
        return true;
    }

	
}
