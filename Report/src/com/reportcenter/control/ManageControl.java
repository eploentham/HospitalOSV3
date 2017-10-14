/*
 * ManageControl.java
 *
 * Created on 2 ¾ÄÈ¨Ô¡ÒÂ¹ 2548, 16:56 ¹.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportcenter.control;
import com.reportcenter.control.MainControl;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.reportcenter.control.HosDB;
/**
 *
 * @author tong(Padungrat)
 */
public class ManageControl {
    
    ConnectionInf theConnectionInf;
    public MainControl theMainControl;
    HosDB theHosDB;
    
    public ManageControl(ConnectionInf connectionInf)
    {
        theConnectionInf = connectionInf;
        theHosDB = new HosDB(theConnectionInf);
        theMainControl = new MainControl(theHosDB);
    }
    
}
