/*
 * QueryManage.java
 *
 * Created on 6 กันยายน 2548, 13:06 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportquery.usecase.gui;
import java.awt.Color;
/**
 *
 * @author tong(Padungrat)
 */
public interface QueryManage {
    
    public void notifyQueryDataNoDate(String[] columnname, java.util.Vector vString,String exceptions);
    public void notifyQueryDataDate(String[] columnname, java.util.Vector vString, int exception, String exceptions);
    
    
    public void onShowStatus(String str,Color color);
    public void onShowStatus(String str,Color color,boolean isFinish);
    public void onShowPicture(String pic,boolean isVisible);
    public void setEnabled(boolean setEnabled);
}
