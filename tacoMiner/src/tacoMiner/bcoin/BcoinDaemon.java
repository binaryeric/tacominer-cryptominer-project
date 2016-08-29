package tacoMiner.bcoin;

public class BcoinDaemon {
    public static boolean STARTED = false;
    public static String DaemonPath = "C:\\Program Files\\Bitcoin\\daemon\\bitcoind.exe";
    private static Process process;

    public static void Start() throws Exception {
        if (STARTED == false) {
            STARTED = true;
            process = new ProcessBuilder(DaemonPath).start();
        }
    }

    public static void Stop() throws Exception {
        if (STARTED) {
            STARTED = false;
            process.destroy();
        }
    }
}
