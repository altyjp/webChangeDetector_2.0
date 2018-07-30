/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.altyjp.webChangeDetector;

import java.util.List;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author altyjp
 */
public class TweetStrings {

    //ここは各自設定すること
    private final String ConsumerKey= "";
    private final String ConsumerSecret = "";
    private final String AccessToken = "";
    private final String AccessTokenSecret = "";
    
    /**
     * 文字列をツイートする
     *
     * @param str_tweet
     * @throws TwitterException
     */
    public void tweetString(String str_tweet) throws TwitterException {

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(ConsumerKey)
                .setOAuthConsumerSecret(ConsumerSecret)
                .setOAuthAccessToken(AccessToken)
                .setOAuthAccessTokenSecret(AccessTokenSecret);
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        System.out.println("ツイッターとの接続...OK");
        System.out.println("ツイート準備");
        System.out.println(str_tweet);
        //User user = twitter.verifyCredentials();
        //ユーザ情報取得
        //System.out.println("なまえ　　　：" + user.getName());
        //System.out.println("ひょうじ名　：" + user.getScreenName());
        //System.err.println("ふぉろー数　：" + user.getFriendsCount());
        //System.out.println("ふぉろわー数：" + user.getFollowersCount());
        //ツイートしてみる
        Status status = twitter.updateStatus(str_tweet);
        System.out.println("ツイッターとの切断...OK");
    }
    
    public String genTweetStr(List<bbsRow> bbsRowData){
        String ans = "";
        int cntNum = 0;
        int maxRowNum = 3;
        int maxStrLength = 10;
        
        for(bbsRow temp : bbsRowData){
            //更新の上位3件
            if(temp.isUpdate && cntNum < maxRowNum){
                //maxStrLength以上の長さがあった場合
                if (temp.title.length() > maxStrLength){
                    ans += temp.title.substring(0, maxStrLength);
                }else{
                    ans += temp.title;
                }
                ans += "\n";
                cntNum++;
            }else if(temp.isUpdate && cntNum >= maxRowNum){
                cntNum++;
            }
        }
        
        if(cntNum > maxRowNum){
            ans+= "他" + (cntNum - maxRowNum) + "件";
        }
        
        return ans;
    }
    
}
