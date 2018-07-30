/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.altyjp.webChangeDetector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author altyjp
 */
public class HtmlGetter {

    private static String targetURL;

    /**
     * コンストラクタ
     * 文字列として取得するURLを指定する。
     * @param target_url 
     */
    public HtmlGetter(String target_url) {
        this.targetURL = target_url;
    }

    public static String executeGet() {
        System.out.println("掲示板へ通信...OK");

        StringBuilder sb = new StringBuilder();

        try {
            URL url = new URL(targetURL);

            HttpURLConnection connection = null;

            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    try (InputStreamReader isr = new InputStreamReader(connection.getInputStream(),
                            "Shift-JIS");
                            BufferedReader reader = new BufferedReader(isr)) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                                //System.out.println(line);
                                sb.append(line);
                        }
                    }
                }
            } finally {
                if (connection != null) {
                    connection.disconnect();
                    System.out.println("データ取得...OK");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("通信エラー:掲示板とのリンクを確立できません。");
            System.out.println("システムをロックします。");
        }

        System.out.println("掲示板との通信切断...OK");
        return new String(sb);
    }
}
