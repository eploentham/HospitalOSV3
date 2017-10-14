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
import com.report18file.objectnh53.*;

/**
 *
 * @author henbe
 */
public class DBSuitNh53 extends DBSuit{

    public DBSuitNh53(UpdateStatus us,ConnectionInf con,String current_date){
        theConnectionInf = con;
         init18FileNhso53(us,current_date);
    }

    public static DBSuit getDBSuit(UpdateStatus us,ConnectionInf con,String current_date){
        return new DBSuitNh53(us,con,current_date);
    }
    private void init18FileNhso53(UpdateStatus us,String current_date) {
        theAncDB = new Rp18DB(theConnectionInf,new AncNh53());
        theAppointDB = new Rp18DB(theConnectionInf,new AppointNh53());
        theCardDB = new Rp18DB(theConnectionInf,new CardNh53());
        theChronicDB = new Rp18DB(theConnectionInf,new ChronicNh53());
        theDeathDB = new Rp18DB(theConnectionInf,new DeathNh53());
        theDiagDB = new Rp18DB(theConnectionInf,new DiagNh53());
        theDrugDB = new Rp18DB(theConnectionInf,new DrugNh53());
        theEpiDB = new EpiDB(theConnectionInf,new EpiNh53());
        theFpDB = new Rp18DB(theConnectionInf,new FpNh53());
        theHomeDB = new Rp18DB(theConnectionInf,new HomeNh53());
        MchNh53 theMCH = new MchNh53();
        theMCH.setConnectionInf(theConnectionInf);
        theMchDB = new Rp18DB(theConnectionInf,theMCH);
        PpNh53 thePP = new PpNh53();
        thePP.setConnectionInf(theConnectionInf);
        thePpDB = new Rp18DB(theConnectionInf,thePP);
        theNutritionDB = new Rp18DB(theConnectionInf,new NutritionNh53());
        thePersonDB = new Rp18DB(theConnectionInf,new PersonNh53(current_date));
        theProcedDB = new Rp18DB(theConnectionInf,new ProcedNh53());
        theServeilDB = new Rp18DB(theConnectionInf,new ServeilNh53());
        theServiceDB = new Rp18DB(theConnectionInf,new ServiceNh53());
        theWomanDB = new Rp18DB(theConnectionInf,new WomanNh53());
    }
}
