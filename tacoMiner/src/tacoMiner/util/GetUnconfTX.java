package tacoMiner.util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;

/**
 * Gets a bunch of unconfirmed transactions that the user specifies and returns array of hashes.
 * Returns back a array of strings in format [hash, size]
 */
public class GetUnconfTX {
    private static final String API_URL = "https://blockchain.info/unconfirmed-transactions?format=json";
    private static URL apiURLObj;
    private short numOfTX;

    public GetUnconfTX(short nt) {
        numOfTX = nt;
        initAPIURL();
    }

    public String[][] getTXArray() {
        String[][] txArray = new String[numOfTX][2];
        JSONObject obj = new JSONObject(getDataTX());

        JSONArray jsonTXArray = obj.getJSONArray("txs");
        for (int i = 0; i < numOfTX; i++) {
            txArray[i][0] = jsonTXArray.getJSONObject(i).getString("hash");
            txArray[i][1] = jsonTXArray.getJSONObject(i).get("size").toString();
        }
        return txArray;
    }

    private void initAPIURL() {
        apiURLObj = HTTP.formatURL(API_URL);
    }

    private String getDataTX() {
        return HTTP.getAsync(apiURLObj);
    }
}
