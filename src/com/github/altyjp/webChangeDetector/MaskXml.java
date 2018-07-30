/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.altyjp.webChangeDetector;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 *
 * @author altyjp
 */
public class MaskXml {
    /**
     * xmlをマスクする
     * @param str_xml
     * @return
     * @throws Exception 
     */
    public List<bbsRow> xmlMasks(String str_xml) throws Exception {

        System.out.println("掲示板の解析開始...OK");
        
        String ignoreStr = "<span class=\"new\">NEW</span>";
        str_xml = str_xml.replace(ignoreStr, "");
        
        Document doc = Jsoup.parse(str_xml);
        
        List<bbsRow> topicData = new ArrayList<>();
        int rowCount = 1;
        
        Elements topicTeble = doc.select("TABLE.topic_table");
        //Elements topicTables = topicTeble.children().select("table");
        Elements rows = topicTeble.select("tr");
        
        System.out.println("解析結果を表示");
        System.out.println();
        
        for( Element row : rows ) {
            //タイトル行はスキップする。
            if(rowCount == 1){
                rowCount++;
                continue;
            }
            
            bbsRow rowData = getBBSrowData(row);
            System.out.println(rowData.toString());
            topicData.add(rowData);
            rowCount++;
        }
        
        System.out.println();
        System.out.println("掲示板の解析終了...OK");
        
        return topicData;
    }
    
    private bbsRow getBBSrowData(Element row){
        
        bbsRow rowData = new bbsRow();
        Locale.setDefault(Locale.ENGLISH);
        
        try{
            rowData.title = row.select("td").get(0).text();
            rowData.recCounts = Integer.parseInt
                                    (row.select("td").get(1).text());
            rowData.codeName = row.select("td").get(2).text();
            rowData.lastUpdate = row.select("td").get(3).text();
        } catch (NumberFormatException e) {
            rowData.codeName = "件数の解析エラー";
            System.out.println(e);
        }
        
        return rowData;
    }
    
}
