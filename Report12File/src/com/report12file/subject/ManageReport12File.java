/*
 * ManageReport12File.java
 *
 * Created on 19
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.report12file.subject;
import java.awt.Color;
/**
 *
 * @author tong(Padungrat)
 */
public interface ManageReport12File {
    public void onShowStatus(String str,Color color);
    public void onShowStatus(String str,Color color,boolean isFinish);
    public void onShowPicture(String pic,boolean isVisible);
    public void setEnabled(boolean setEnabled);
    
}
