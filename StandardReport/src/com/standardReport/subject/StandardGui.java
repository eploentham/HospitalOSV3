/*
 * StandardGui.java
 *
 * Created on 7 กันยายน 2548, 15:53 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.standardReport.subject;

/**
 *
 * @author nu_ojika
 */
public interface StandardGui {
    
    public void setQueryReport(String startDate,String endDate);
    
    public void setQueryReport(String startDate,String endDate,String treatStatus);
    
    public void setExportFile(String fileType,String path);
    
    public String getCardName();
    
}
