package com.example.currencyconverterjavafx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONObject;

public class APIConsumer {
    public APIConsumer() {
    }
    public double sendHttpGETRequest(String from_code, String to_code, double amount) throws IOException {
        String GET_URL = "https://v6.exchangerate-api.com/v6/099f87b4ee74f691d62a62d5/pair/" + from_code + "/" + to_code;
        URL url = new URL(GET_URL);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        int responseCode = httpURLConnection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            JSONObject obj = new JSONObject(response.toString());
            double exchangeRate = obj.getDouble("conversion_rate");
            System.out.println(exchangeRate);
            return amount * exchangeRate;
        }
        else {
            return 0.0;
        }
    }
}
