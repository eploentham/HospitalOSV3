/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.report18file.gui;

import com.report18file.control.*;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.hosv3.utility.connection.UpdateStatus;
import com.report18file.objdb.EpiDB;
import com.report18file.objdb.Rp18DB;
import com.report18file.objectnh.*;

/**
 *
 * @author henbe
 */
public class DBSuitNh extends DBSuit{

    public DBSuitNh(UpdateStatus us,ConnectionInf con){
        theConnectionInf = con;
         init18FileNhso(us);
    }
    public static DBSuit getDBSuit(UpdateStatus us,ConnectionInf con){
        return new DBSuitNh(us,con);
    }
    private void init18FileNhso(UpdateStatus us) {
        theAncDB = new Rp18DB(theConnectionInf,new AncNh());
        theAppointDB = new Rp18DB(theConnectionInf,new AppointNh());
        theCardDB = new Rp18DB(theConnectionInf,new CardNh());
        theChronicDB = new Rp18DB(theConnectionInf,new ChronicNh());
        theDeathDB = new Rp18DB(theConnectionInf,new DeathNh());
        theDiagDB = new Rp18DB(theConnectionInf,new DiagNh());
        theDrugDB = new Rp18DB(theConnectionInf,new DrugNh());
        theEpiDB = new EpiDB(theConnectionInf,new EpiNh());
        theFpDB = new Rp18DB(theConnectionInf,new FpNh());
        theHomeDB = new Rp18DB(theConnectionInf,new HomeNh());
        MchNh theMCH = new MchNh();
        theMCH.setConnectionInf(theConnectionInf);
        theMchDB = new Rp18DB(theConnectionInf,theMCH);
        PpNh thePP = new PpNh();
        thePP.setConnectionInf(theConnectionInf);
        thePpDB = new Rp18DB(theConnectionInf,thePP);
        theNutritionDB = new Rp18DB(theConnectionInf,new NutritionNh());
        thePersonDB = new Rp18DB(theConnectionInf,new PersonNh());
        theProcedDB = new Rp18DB(theConnectionInf,new ProcedNh());
        theServeilDB = new Rp18DB(theConnectionInf,new ServeilNh());
        theServiceDB = new Rp18DB(theConnectionInf,new ServiceNh());
        theWomanDB = new Rp18DB(theConnectionInf,new WomanNh());
    }
}
