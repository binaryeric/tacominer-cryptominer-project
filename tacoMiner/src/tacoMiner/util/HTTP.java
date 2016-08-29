package tacoMiner.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTP {

    public static URL formatURL(String urlToEncode) {
        try {
            return new URL(urlToEncode);
        } catch (Exception E) {
            System.out.println("URL Encoding Exception: ");
            E.printStackTrace();
            return null;
        }
    }


    public static String getAsync(URL obj) {
        try {
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));


            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return response.toString();

        } catch (Exception E) {
            E.printStackTrace();
        }
        return null;
    }
}
