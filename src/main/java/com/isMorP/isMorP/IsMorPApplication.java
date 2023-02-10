package com.isMorP.isMorP;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@SpringBootApplication
public class IsMorPApplication {

    public static void main(String[] args) throws IOException {
//        SpringApplication.run(IsMorPApplication.class, args);

        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=jKBOA98qDuKUlkDBQTFWMlJ6RTDFExOV1iXcdTpHYS9%2BWbYXnvVSYFpbR6tHbK2Lqg%2BgmbkiKBcEbLnsD9%2FiCA%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("50", "UTF-8")); /*페이지번호*/
        URL url = new URL(urlBuilder.toString());

        System.out.println(urlBuilder.toString());

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }

        String firstData = sb.toString();



        firstData = firstData.replaceAll("</", "");

        firstData = firstData.replaceAll("&lt;p", "");
        firstData = firstData.replaceAll("&lt;/p", "");
        firstData = firstData.replaceAll("&lt;", "");
        firstData = firstData.replaceAll("&lt;/", "");
        firstData = firstData.replaceAll("&gt;", "");

        String[] firstDataArr = firstData.split("<");

        rd.close();
        conn.disconnect();

        for (int i=0; i < firstDataArr.length; i++) {
            System.out.println(firstDataArr[i]);
        }

    }

}
