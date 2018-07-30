/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.altyjp.webChangeDetector;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author altyjp
 */
public class WebChangeDetector {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            System.out.println("＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝");
            System.out.println("お知らせボット[CSTSC版_VER2.0]：起動");
            System.out.println(new Date());
            System.out.println("＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝");
            
            //HTTP通信でデータを取得する。
            HtmlGetter htmlGetter
                    = new HtmlGetter("http://cstsc.webdeki-bbs.com");
            String str_xml = HtmlGetter.executeGet();

            System.out.println("＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝");
            
            //取得した部分に対してマスクし、注目する部分を作成する。
            MaskXml maskXml = new MaskXml();
            List<bbsRow> newBBSdata = maskXml.xmlMasks(str_xml);

            System.out.println("＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝");
            
            //セーブデータを取得する。
            CompareAndFiles comp = new CompareAndFiles();
            //セーブデータを作成する。
            //新規の場合はデータを更新して終了。
            if (comp.createSaveData()) {
                comp.wirteSaveData(newBBSdata);
                System.out.println("初回起動:セーブデータ作成のみ");
                return;
            }

            //前の情報を取得する。
            List<bbsRow> oldBBSdata = comp.readSaveData();

            //比較する
            boolean isUpdate = comp.compareBBSdata(newBBSdata, oldBBSdata);
            
            if (isUpdate) {
                System.out.println("スキャン結果:更新を検知");
                //ツイッターへのアクセス準備 
                TweetStrings tweetStr = new TweetStrings();
                
                //ツイートを行う。 
                tweetStr.tweetString("理工シューティングの掲示板が更新されました。\n"
                        + "チェックしてくださいね！ http://cstsc.webdeki-bbs.com \n"
                        + "【更新されたスレッド】\n"
                        + tweetStr.genTweetStr(newBBSdata));
                
                //セーブデータを更新する。
                comp.wirteSaveData(newBBSdata);
            }else{
                System.out.println("スキャン結果:更新なし");
            }

        } catch (Exception ex) {
            Logger.getLogger(WebChangeDetector.
                    class.getName()).log(Level.SEVERE, null, ex);
        }
        
            System.out.println("＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝");
            System.out.println("お知らせボット[CSTSC版]：システム終了");
            System.out.println(new Date());
            System.out.println("＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝");
    }

}
