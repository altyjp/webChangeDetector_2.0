# webChangeDetector_2.0
CSTSCお知らせbot2.0で実際に動作しているプログラムのソースです。    
以下は映画「コマンドー」観ながら書きました。  

1. コンパイルには以下のライブラリが必要です。筋肉で解決して下さい。
- twitter4j 4.0.4
- jsoup 1.10.1
  
2. ライブラリを揃えたって？ビールでも飲んでリラックスしな。  
`TweetStrings.java`の中身にある、以下の部分にえらい長いtwitter APIキーを入力します。  
twitter APIキーが無いって？だったらググれば良いだろ！

```
    //ここは各自設定すること
    private final String ConsumerKey= "";
    private final String ConsumerSecret = "";
    private final String AccessToken = "";
    private final String AccessTokenSecret = "";
```

3. APIキーはどこだ？  
まぁまぁ、落ち着けよ。  
そんなに焦ってこられちゃびびって話しも出来やしねぇ。  
APIキーが無くても、ロジックは無事だ。少なくとも今のところはな。  
APIキー無しで動作させたかったら、  
`WebChangeDetector.java`にあるこの部分をコメントアウトしろ。OK?
```
//ツイートを行う。 
tweetStr.tweetString("理工シューティングの掲示板が更新されました。\n"
    + "チェックしてくださいね！ http://cstsc.webdeki-bbs.com \n"
    + "【更新されたスレッド】\n"
    + tweetStr.genTweetStr(newBBSdata));
```
