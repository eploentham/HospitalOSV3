/*
 * HosControl.java
 *
 * Created on 27 ตุลาคม 2546, 19:26 น.
 */
package com.hosv3.control;

import com.hospital_os.usecase.connection.ConnectionInf;

//henbe said bad pattern import com.hosv3.gui.dialog.HosDialog;
import com.hosv3.gui.panel.transaction.HosPanel;
import com.hosv3.subject.*;
import com.hosv3.utility.connection.UpdateStatus;
import com.hosv3.utility.*;
import com.hosv3.object.*;

///
///
/**
 *
 * @author  henbe
 */
public class HosControl{

    
    public ConnectionInf theConnectionInf;
    public HosObject theHO;
    public LookupObject theLO;
    public HosSubject theHS;
    public UpdateStatus theUS;
    public HosDB theHosDB;
    
    public PatientControl thePatientControl;
    public LookupControl theLookupControl;
    public SystemControl theSystemControl;
    public VisitControl theVisitControl;
    public VitalControl theVitalControl;
    public DiagnosisControl theDiagnosisControl;
    public OrderControl theOrderControl;
    public SetupControl theSetupControl;
    public BillingControl theBillingControl;
    public PrintControl thePrintControl;
    public ResultControl theResultControl;
    public LabReferControl theLabReferControl;

    public GPatientSuit theGPS;
    public SmartCardControl theSmartCardControl;
    public HosPanel theHP;
    public LabControl theLabControl;
    // Somprasong add 20101007
    public NotifyNoteControl theNotifyNoteControl;
    //henbe comment 100253 ton bad pattern
    //public HosDialog theHD;
    //เอา theHD ออก
    //old version of HosControl
    /** Creates a new instance of HosControl */
    public HosControl() {
        String url = "jdbc:postgresql://localhost:5432/hos";
        String user = "postgres";
        String pass = "postgres";
        String dri = "org.postgresql.Driver";
        String type = "0"; //0 postgres 1 mysql 2 sqlserverT
        theConnectionInf = new ConnectionDBMgr(dri,url,user,pass,type);
        initControl(theConnectionInf);
    }
    public void setHosPanel(HosPanel hp)
    {
        theHP = hp;
    }
    public HosControl(String url,String uname,String passwd
    , int statusApp,String di,String typeDatabase) 
    {   
        theConnectionInf = new ConnectionDBMgr(di,url, uname,passwd,typeDatabase);
        initControl(theConnectionInf);
    }
    public HosControl(ConnectionInf c) 
    {   
        initControl(c);
    }
    /**
     *@deprecated henbe unused ใช้กรณีไหนนิ
     **/
    public static HosControl initFromConfigFile(String file) throws Exception
    {
        Config config = new Config();
//        config.setConfigFile(file);
//        if(config.readDataFromFile())
//            if(config.checkData())
                return new HosControl(config.getConnectionInf());
//        return null;
    }
    
    public HosDB getHosDB(){
        return theHosDB;
    }
    private void initControl(ConnectionInf c)
    {
        theConnectionInf = c;
        LookupObject lo = new LookupObject();
        HosObject ho = new HosObject(lo);
        HosSubject hs = new HosSubject();
        theHosDB = new HosDB(theConnectionInf);
        theHO = ho;
        theHS = hs;
        theLO = lo;
        theHO.local_db = DialogConfig.readDbFile();
        theLookupControl = new LookupControl(theConnectionInf,ho,theHosDB,hs,lo);
        thePatientControl = new PatientControl(theConnectionInf,ho,theHosDB,hs,lo);
        theSystemControl = new SystemControl(theConnectionInf,ho,theHosDB,hs);
        thePatientControl.setSystemControl(theSystemControl);
        theSystemControl.setDepControl(theLookupControl);
        theSetupControl = new SetupControl(theConnectionInf,ho,theHosDB,hs,lo);
        theSetupControl.setSystemControl(theSystemControl);
        theSetupControl.setDepControl(theLookupControl,thePatientControl);
        theDiagnosisControl = new DiagnosisControl(theConnectionInf,ho,theHosDB,hs);
        theDiagnosisControl.setSystemControl(theSystemControl);
        theOrderControl = new OrderControl(theConnectionInf,ho,theHosDB,hs);
        theOrderControl.setSystemControl(theSystemControl);
        theVisitControl = new VisitControl(theConnectionInf,ho,theHosDB,hs);
        ////dep fail//////////////////////////////////////////////////////////////////
        theVisitControl.setDepControl(theLookupControl,theOrderControl,thePatientControl,theSystemControl);
        theDiagnosisControl.setDepControl(theLookupControl,theVisitControl);
        theOrderControl.setDepControl(theDiagnosisControl,theVisitControl,theLookupControl,theSetupControl);
////////////////////////////////////////////////////////////////////////////////////        
        thePatientControl.setDepControl(theLookupControl,theVisitControl);
        theVitalControl = new VitalControl(theConnectionInf,ho,theHosDB,hs);
        theVitalControl.setSystemControl(theSystemControl);
        theVitalControl.setDepControl(theLookupControl,theVisitControl,theDiagnosisControl,theOrderControl); 
        theLabReferControl = new LabReferControl(theConnectionInf,ho,theHosDB,hs,lo);
        theLabReferControl.setDepControl(theLookupControl);
        theLabReferControl.setSystemControl(theSystemControl);
        theResultControl = new ResultControl(theConnectionInf,ho,theHosDB,hs);
        theResultControl.setDepControl(theLookupControl, theVisitControl, theOrderControl);
        theResultControl.setSystemControl(theSystemControl);
        theBillingControl = new BillingControl(theConnectionInf,ho,theHosDB,hs,lo);
        theBillingControl.setSystemControl(theSystemControl);
        theBillingControl.setDepControl(theLookupControl,theOrderControl,theVisitControl);
        thePrintControl = new PrintControl(theConnectionInf,ho,theHosDB,hs,lo);
        thePrintControl.setSystemControl(theSystemControl);
        thePrintControl.setDepControl(theLookupControl,theSetupControl
            ,theVisitControl,theOrderControl
            ,theVitalControl,theDiagnosisControl
            ,theResultControl,theBillingControl,thePatientControl);
        theLabControl = new LabControl(this);

        theNotifyNoteControl = new NotifyNoteControl(theConnectionInf, ho, theHosDB, hs, theLookupControl);
    }
    
    public ConnectionInf getConnecton()
    {
        return theConnectionInf;
    }
    
    public void setUpdateStatus(UpdateStatus us){
        theUS = us;
        theGPS = new GPatientSuit(theHO,theHosDB,theUS);
        theSmartCardControl = new SmartCardControl(theUS);
        theLookupControl.setUpdateStatus(theUS);
        theSystemControl.setUpdateStatus(theUS);
        thePatientControl.setUpdateStatus(theUS);
        theOrderControl.setUpdateStatus(theUS);
        theVisitControl.setUpdateStatus(theUS);
        theVitalControl.setUpdateStatus(theUS);
        thePrintControl.setUpdateStatus(theUS);
        theDiagnosisControl.setUpdateStatus(theUS);
        theBillingControl.setUpdateStatus(theUS);
        theResultControl.setUpdateStatus(theUS);
        theSetupControl.setUpdateStatus(theUS);
        theLabReferControl.setUpdateStatus(theUS);
        theNotifyNoteControl.setUpdateStatus(theUS);
    }
    
       
}
