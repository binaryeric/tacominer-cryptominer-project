package tacoMiner.util;

import java.security.MessageDigest;

public class SHA256 {

    private MessageDigest md;

    public static void reverseArray(byte[] array) {
        if (array == null) {
            return;
        }
        int i = 0;
        int j = array.length - 1;
        byte tmp;
        while (j > i) {
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
            i++;
        }
    }

    public byte[] SHA2562ARGS(byte[] a, byte[] b) {
        try {
            md.update(a);
            md.update(b);
            return md.digest();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public byte[] SHA2561ARGS(byte[] a) {
        try {
            md.update(a);
            return md.digest();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void InitMD(MessageDigest _md) {
        System.out.println(_md.hashCode());
        md = _md;
    }

    public byte[] hasher(byte[] a) {
        a = SHA2561ARGS(SHA2561ARGS(a));
        reverseArray(a);
        return a;
    }

    public boolean ArrayCompare(byte[] a, byte[] target) {
        int offset = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != 0) {
                offset = i;
                break;
            }
        }
        if ((a.length - offset) > target.length) return false;

        int z = 0;
        for (int i = offset; i < a.length; i++) {
            System.out.println(a[z + offset] + " : " + z + offset);
            System.out.println(target[z]);
            if ((a[z + offset] & 0xFF) > target[z]) {
                return false;
            } else if (a[z + offset] != target[z]) {
                return true;
            }
            z++;
        }
        return true;
    }

}