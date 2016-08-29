package tacoMiner.bcoin;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BcoinCLI {
    public static boolean STARTED = false;
    public static String CLIPath = "C:\\Program Files\\Bitcoin\\daemon\\bitcoin-cli.exe ";
    private static Process process;

    public static String Run(String args) {
        if (STARTED == false) {
            StringBuilder z = new StringBuilder();
            try {
                STARTED = true;
                process = Runtime.getRuntime().exec(CLIPath + args);
                process.waitFor();

                String line;
                BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
                while ((line = input.readLine()) != null) {
                    z.append(line);
                }

                input.close();
                return z.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}
