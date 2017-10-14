/*
 * HosDialog.java
 *
 * Created on 6 กรกฎาคม 2548, 15:10 น.
 */

package com.pcu.gui.dialog;

import com.hosv3.gui.dialog.DialogSearchPatient;
import com.pcu.control.PCUObject;
import javax.swing.*;
import com.pcu.control.HosManage;
import com.pcu.object.*;

import com.hospital_os.object.Office;
import com.hospital_os.object.Appointment;

import com.hosv3.control.HosControl;
import com.hosv3.gui.dialog.DialogAppointment;
import com.hosv3.utility.connection.UpdateStatus;
import com.pcu.gui.panelpcu.PanelANC;

/**
 *
 * @author amp
 */
public class HosDialog extends com.hosv3.gui.dialog.HosDialog
{
    
    public DialogSetupSearchPcu theDialogSetupSearchPcu;
    public DialogApgarScore theDialogApgarScore;
    public DialogOffice theDialogOffice;
    public DialogHomePatient theDialogHomePatient;
    public DialogAppointment theDialogAppointment;
    public DialogPersonStatus theDialogPersonStatus;
    public DialogChangeHome theDialogChangeHome;
    public PanelAfterMch thePanelAfterMch;
    public PanelEditLMP thePanelEditLMP;
    JFrame theJFrame;
    
    public DialogSearchPatient theDialogSearchPatient;
    
    private DialogSearchPatientPCU theDialogSearchPatientPCU;
    /** Creates a new instance of HosDialog */
    public HosDialog(JFrame jf,HosControl hc,UpdateStatus us)
    {
        super(hc,us);
        theJFrame = jf;
    }
    public void setJFrame(JFrame jf)
    {
        theJFrame = jf;
    }
    /**
     *@deprecated dialog ไม่ควรเรียก dialog
     */
    public void showDialogSetupSearchPCU(HosManage hm,int sh)
    {
        if(theDialogSetupSearchPcu == null)
        {
            theDialogSetupSearchPcu = new DialogSetupSearchPcu(theJFrame, true,hm);
        }
        theDialogSetupSearchPcu.showSearch(sh);
    }
    
    /**
     *@deprecated dialog เรียก dialog ด้วยกันไม่ได้
     *
     */
    public void showDialogPPApgarScore(HosManage hm,int minute,String[] arrayApgarScore)
    {
        if(theDialogApgarScore == null)
        {
            theDialogApgarScore = new DialogApgarScore(theJFrame, true,hm);
        }
        theDialogApgarScore.showDialog(minute,arrayApgarScore);
    }
    
    public boolean showDialogOffice(HosManage hm,Office theOffice/*,PCUObject thePCUObject*/)
    {
        if(theDialogOffice == null)
        {
            theDialogOffice = new DialogOffice(hm,theOffice/*,thePCUObject*/);
        }
        return theDialogOffice.showDialog(theOffice);
    }
    
    
    public void showDialogBornMch(HosManage hm,int sh,PCUObject thePCUObject)
    {
        if(theDialogSetupSearchPcu == null)
        {
            theDialogSetupSearchPcu = new DialogSetupSearchPcu(theJFrame, true,hm);
        }
        theDialogSetupSearchPcu.setPatientID(null);
        if(thePCUObject != null && thePCUObject.getPatient() != null)
        {
            theDialogSetupSearchPcu.setPatientID(thePCUObject.getPatient().getObjectId());
        }
        theDialogSetupSearchPcu.showSearch(sh);
        
    }
    
    public void showDialogHomePatient(HosManage hm,PCUObject pcuobject)
    {
        if(theDialogHomePatient == null)
        {
            theDialogHomePatient = new DialogHomePatient(theJFrame, true,hm);
        }
        theDialogHomePatient.showDialog();
    }
    
    public void showDialogSearchPatientPCU(HosManage hm,String fname,String lname,String hn)
    {
        String pid = "";
        if(theDialogSearchPatient==null)
            theDialogSearchPatient = new DialogSearchPatient(hm.theHC,hm.theHosControl.theUS,1);
        if(!fname.equals(""))
        {
            theDialogSearchPatient.setFnameLname(fname,lname);
        }
        else if(!hn.equals("")|| !pid.equals(""))
        {
            theDialogSearchPatient.setHNPID(hn,pid);
        }
        theDialogSearchPatient.showDialog();
    }
    public String showSearchFamily(HosManage hm)
    {
        if(theDialogSearchPatient==null)
            theDialogSearchPatient = new DialogSearchPatient(hm.theHC,hm.theHosControl.theUS,1);

        return theDialogSearchPatient.showDialogFamily();
    }
    
    public void showDialogAppointment(HosManage hm,PCUObject pcuobject)
    {
        if(theDialogAppointment == null)
        {
            theDialogAppointment = new DialogAppointment(hm.theHC,hm.theHC.theUS,false);
        }
        theDialogAppointment.showDialog(pcuobject.getPatient(), pcuobject.getVisit(),false);
    }
    public void showDialogAppointment(HosManage hm,PCUObject pcuobject,Appointment appoint)
    {
        if(theDialogAppointment == null)
        {
            theDialogAppointment = new DialogAppointment(hm.theHC,hm.theHC.theUS,false);
        }
        theDialogAppointment.showDialog(pcuobject.getPatient(), pcuobject.getVisit(),false,appoint);
    }
    public boolean showDialogPersonStatus(HosManage hm,Family family,JFrame frm)
    {
        if(theDialogPersonStatus == null)
        {
            theDialogPersonStatus = new DialogPersonStatus(hm,frm);
        }
        return theDialogPersonStatus.showDialog(family);
    }
    public void showDialogChangeHome(HosManage hm,Family family,JDialog frame)
    {
        if(theDialogChangeHome == null)
        {
            theDialogChangeHome = new DialogChangeHome(frame,true,hm);
        }
        theDialogChangeHome.showDialog(family);
    }
    public void showPanelAfterMch(HosManage hm,AfterMchMother afterMchMother,String num)
    {
        if(thePanelAfterMch==null)
        {
            thePanelAfterMch = new PanelAfterMch();
            thePanelAfterMch.setControl(hm);
        }
        thePanelAfterMch.setAfterMchMother(afterMchMother, num);
        thePanelAfterMch.showDialog();
    }
    public void showPanelEditLMP(HosManage hm,Pregnancy pregnancy)
    {
        if(thePanelEditLMP == null)
        {
            thePanelEditLMP = new PanelEditLMP();
            thePanelEditLMP.setControl(hm);
        }
        thePanelEditLMP.setPregnancy(pregnancy);
//        thePanelEditLMP.setPanelANC(panelANC1);
//        thePanelEditLMP.setAncPcu(ancPcu);
        thePanelEditLMP.showDialog();
    }
}
