package tacoMiner.bcoin;

public class BcoinDaemon {
    public static boolean STARTED = false;
    public static String DaemonPath = "C:\\Program Files\\Bitcoin\\daemon\\bitcoind.exe";
    private static Process process;

    public static void Start() {
        try {
            if (STARTED == false) {
                STARTED = true;
                process = new ProcessBuilder(DaemonPath).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void Stop() {
        try {
            if (STARTED) {
                STARTED = false;
                process.destroy();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
