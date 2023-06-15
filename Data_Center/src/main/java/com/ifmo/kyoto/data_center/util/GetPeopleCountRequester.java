package com.ifmo.kyoto.data_center.util;

import com.ifmo.kyoto.data_center.service.UnsanctionedAccessChecker;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Queue;

public class GetPeopleCountRequester implements Runnable {
    private String url = "http://localhost:5500/count";
    private Queue<String> timestamps;

    @Autowired
    UnsanctionedAccessChecker unsanctionedAccessChecker;

    public GetPeopleCountRequester(Queue<String> timestamps) {
        this.timestamps = timestamps;
    }


    @Override
    public void run() {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            URL url = new URL(this.url);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String jsonTimestamp = "{\"id\" : \""+ timestamps.poll() + "\"}";

            writer.write(jsonTimestamp);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                StringBuilder stringBuilder = new StringBuilder();
                String line = reader.readLine();
                stringBuilder.append(line);
                String response = stringBuilder.toString();
                Integer count = Integer.parseInt(response);
                unsanctionedAccessChecker.check(count);
            } else if (responseCode == 403) {

            } else {
                System.out.println("HTTP request failed with response code: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}