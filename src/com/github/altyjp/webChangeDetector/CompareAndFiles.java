/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.altyjp.webChangeDetector;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author altyjp
 */
public class CompareAndFiles {

    private String dataFilePath = "./Datafiles/data.txt";


    private File dataFile = null;

    /**
     * セーブデータの新規作成を試みる. 
     * 新規作成をした場合はtrueを返す。
     * しなかった場合はfalse 例外発生時はその例外をスローする。
     *
     * @return
     * @throws java.io.IOException
     */
    public boolean createSaveData() throws IOException {
        dataFile = new File(dataFilePath);
        return dataFile.createNewFile();
    }

    /**
     * セーブデータの更新を行う。 
     * 戻り値は特になし。
     * 例外発生時にはその例外をスローする
     *
     * @param str_data
     * @throws IOException
     */
    public void wirteSaveData(List<bbsRow> bbsRows) throws IOException {

        FileWriter filewriter;
        filewriter = null;

        try {

            filewriter = new FileWriter(dataFile, false);
            
            for(bbsRow row : bbsRows){
                filewriter.write(row.toString());
                filewriter.write(System.lineSeparator());
            }
            

        } catch (IOException ex) {
            Logger.getLogger(CompareAndFiles.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (filewriter != null) {
                filewriter.close();
            }
        }
    }

    /***
     * セーブデータからデータを読み込む
     * その結果をPojoに変換する。
     * @return
     * @throws IOException 
     */
    public List<bbsRow> readSaveData() throws IOException {
        StringBuilder builder = new StringBuilder();
        List<bbsRow> oldData = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(dataFilePath))) {
            String string = reader.readLine();
            while (string != null) {
                bbsRow oldRow = new bbsRow(string);
                oldData.add(oldRow);
                string = reader.readLine();
            }
        }

        return oldData;
    }
    
    /**
     * 更新状態を調べる
     * @param newData
     * @param oldData
     * @return 
     */
    public boolean compareBBSdata
        (List<bbsRow> newData,List<bbsRow> oldData){
        
        bbsRow temp = null;
        boolean ans = false;
        
        for(int i=0;i < newData.size();i++){
            temp = newData.get(i);
             //含まれていない場合（更新された場合）
            if(!oldData.contains(temp)){
                newData.get(i).isUpdate = true;
                ans = true;
            }
        }
        
        return ans;
    }
    
    //デバッグ用、セーブデータファイルを取得する
    public String getDataFilePath() {
        System.out.println("注意：テスト用のメソッドが動作しています。");
        return dataFilePath;
    }

    //デバッグ用、セーブデータファイルを変更する
    public void setDataFilePath(String dataFilePath) {
        System.out.println("注意：テスト用のメソッドが動作しています。");
        this.dataFilePath = dataFilePath;
    }         
}
