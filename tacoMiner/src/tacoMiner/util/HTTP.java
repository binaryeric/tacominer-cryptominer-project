package tacoMiner.util;

import java.net.URLEncoder;

public class HTTP {
    //TODO make this do stuff
    public static String formatURL(String urlToEncode){
        try {
            return URLEncoder.encode(urlToEncode, "UTF-8");
        }catch(Exception E){
            System.out.println("URL Encoding Exception: ");
            E.printStackTrace();
            return null;
        }
    }
    public static String getAsync(){

        return null;
    }
}
