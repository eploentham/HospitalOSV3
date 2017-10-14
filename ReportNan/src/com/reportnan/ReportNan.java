/*
 * ReportNan.java
 *
 * Created on 3 ÁÔ¶Ø¹ÒÂ¹ 2549, 11:03 ¹.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportnan;
import com.hospital_os.usecase.connection.ModuleInfTool;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.hospital_os.utility.ConnectionDBMgr;
import com.hosv3.control.HosControl;
import com.reportnan.control.Rp18Control;
import com.reportnan.gui.panelsetup.PanelMainSetup;
import com.reportnan.subject.ManageControlSubject;
import com.reportnan.gui.panel.PanelReportNan;
import com.reportnan.subject.ManageControlSubjectSetup;
import com.reportnan.utility.Language;

/**
 *
 * @author pu
 */
public class ReportNan implements ModuleInfTool
{
    HosControl theHC;
    ManageControlSubject theMCS;
    PanelReportNan thePanelReportNan;

    private ManageControlSubjectSetup theManageControlSubject;

    private PanelMainSetup thePanelMainSetup;
    /** Creates a new instance of ReportNan */
    public ReportNan()
    {       
    }
    
    public void setHosControl(Object obj)
    {
        if(obj instanceof HosControl)
        {
            theHC = (HosControl)obj;
            System.out.println("Init instance of ReportNan");
            theMCS = new ManageControlSubject(theHC.theConnectionInf);
            theManageControlSubject = new ManageControlSubjectSetup(theHC.theConnectionInf);
            Rp18Control rc = new Rp18Control(theHC.theConnectionInf,theHC.theUS,theHC.theHO);
            thePanelReportNan = new PanelReportNan(theMCS);
            thePanelReportNan.setControl(theHC,rc);
            thePanelMainSetup = new PanelMainSetup(theManageControlSubject);
        }
    }
    public static void main(String agrs[])
    {
        String server = "192.168.1.6";
        String database = "develop_report";
        String port = "5432";
        String username = "postgres";
        String password = "postgres";
        String url_1 = "jdbc:postgresql://"+ server +":" +port +"/" + database;
        
        String a[] = {"-module=com.reportnan.ReportNan;"};
        ConnectionInf theConnectionInf = new ConnectionDBMgr("org.postgresql.Driver",url_1,username,password,"0");
        ReportNan cc = new ReportNan();
        cc.setHosControl(theConnectionInf);
    }

    public javax.swing.JMenu getJMenu()
    {
        return null;
    }

    public javax.swing.JPanel getJPanel()
    {
        return this.thePanelReportNan;
    }

    public String getNamePanel()
    {
       return Language.getTextBundle("ReportNan");
    }

    public Object getObjectVersion()
    {
        return this.thePanelReportNan.getVersion();
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
        System.out.println("public java.util.Vector getVectorSetupModule() SetupReportNan");
        return thePanelMainSetup.getVectorSetupPanel();
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
        return true;
    }
}
