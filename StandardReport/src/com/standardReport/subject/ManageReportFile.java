/*
 * ManageReportFile.java
 *
 * Created on 19 สิงหาคม 2548, 11:13 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.standardReport.subject;
import java.awt.Color;
/**
 *
 * @author Noom
 */
public interface ManageReportFile {
    public void onShowStatus(String str,Color color);
    public void onShowPicture(String pic,boolean isVisible);
}
