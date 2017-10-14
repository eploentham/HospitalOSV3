/*
 * GeneralPCU.java
 *
 * Created on 7 กุมภาพันธ์ 2549, 14:33 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.standardReport;
import com.hospital_os.usecase.connection.ModuleInfTool;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.hospital_os.utility.ConnectionDBMgr;
import com.hospital_os.object.Version;

import com.generalpcu.control.ManageControlSubject;
import com.generalpcu.gui.panel.PanelGeneralPCU;
import com.generalpcu.utility.Language;
import com.hosv3.control.HosControl;
import com.hosv3.utility.connection.UpdateStatus;
/**
 *
 * @author pu
 */
public class GeneralPCU implements ModuleInfTool
{
    ConnectionInf theConnectionInf;
    ManageControlSubject theMCS;
    PanelGeneralPCU thePanelGeneralPCU;
    /** Creates a new instance of GeneralPCU */
    public GeneralPCU()
    {
        
    }

    public javax.swing.JMenu getJMenu()
    {
        return null;
    }

    public javax.swing.JPanel getJPanel()
    {
         return thePanelGeneralPCU;
    }

    public String getNamePanel()
    {
         return Language.getTextBundle("GeneralPCU");
    }

    public Object getObjectVersion()
    {
         return thePanelGeneralPCU.getVersion();
    }

    public java.util.Vector getVectorJMenuItem(String str)
    {
         return null;
    }

    public java.util.Vector getVectorJPanel()
    {
         return null;
    }

    public java.util.Vector getVectorSetupModule()
    {
         return null;
    }

    public boolean isInMenuStandard()
    {
         return false;
    }

    public boolean isJMenuVisible(String str)
    {
         return false;
    }

    public boolean isJPanelVisible(String str)
    {
         return true;
    }

    public boolean isJTreeVisible(String str)
    {
         return false;
    }

    public void setHosControl(Object obj)
    {
        if(obj instanceof HosControl)
        {
            HosControl hc = (HosControl)obj;
            theConnectionInf = hc.theConnectionInf;
            UpdateStatus us = hc.theUS;
            theMCS = new ManageControlSubject(hc,theConnectionInf,us);
            thePanelGeneralPCU = new PanelGeneralPCU(theMCS);   
        }
    }
    
 /*  public static void main(String args[])
    {
        String server = "192.168.1.6";
        String database = "develop_report";
        String port = "5432";
        String username = "postgres";
        String password = "postgres";
        String url_1 = "jdbc:postgresql://"+ server +":" +port +"/" + database;
        
        String a[] = {"-module=com.generalpcu.GeneralPCU;"};
        ConnectionInf theConnectionInf = new ConnectionDBMgr("org.postgresql.Driver",url_1,username,password,"0");
        GeneralPCU cc = new GeneralPCU();
        cc.setHosControl(theConnectionInf);
    }
  **/
    
}
