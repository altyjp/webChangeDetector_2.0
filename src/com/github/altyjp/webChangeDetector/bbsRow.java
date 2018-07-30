/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.altyjp.webChangeDetector;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author altyjp
 */
public class bbsRow {
    
    String title = "";
    int recCounts = 0;
    String codeName = "";
    String lastUpdate = "";
    
    boolean isUpdate = false;

    public bbsRow(){
        
    }
    
    /**
     * toStingしたデータを使用してコンスト
     * @param toStringed 
     */
    public bbsRow(String toStringed){
        String[] strAry = toStringed.split("<TAB>");
        this.title = strAry[0];
        this.codeName = strAry[1];
        this.recCounts = Integer.parseInt(strAry[2]);
        this.lastUpdate = strAry[3];
    }
    
    
    /**
     * 現在の状態をコンソールに出力する。
     */
    public void output(){
        
        System.out.println();
        System.out.println("title:" + this.title);
        System.out.println("codeName:" + this.codeName);
        System.out.println("recCounts:" + this.recCounts);
        System.out.println("lastUpdate:" + this.lastUpdate);
        System.out.println();
        
    }
    
    @Override
    public boolean equals(Object o){
        if(o.getClass() != this.getClass()) {
            return false;
        } else {
            return this.toString().equals(o.toString());
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.title);
        hash = 29 * hash + Objects.hashCode(this.codeName);
        hash = 29 * hash + this.recCounts;
        hash = 29 * hash + Objects.hashCode(this.lastUpdate);
        hash = 29 * hash + (this.isUpdate ? 1 : 0);
        return hash;
    }
    
    @Override
    public String toString(){
        StringBuilder ans = new StringBuilder();
        ans.append(this.title.replaceAll("<TAB>", ""));
        ans.append("<TAB>");
        ans.append(this.codeName.replaceAll("<TAB>", ""));
        ans.append("<TAB>");
        ans.append(this.recCounts);
        ans.append("<TAB>");
        ans.append(this.lastUpdate);
        return ans.toString();
    }
    
}
