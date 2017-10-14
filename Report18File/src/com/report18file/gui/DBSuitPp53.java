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
import com.report18file.objectpp53.AncPp53;
import com.report18file.objectpp53.ChronicPp53;
import com.report18file.objectpp53.EpiPp53;
import com.report18file.objectpp53.FpPp53;
import com.report18file.objectpp53.MchPp53;
import com.report18file.objectpp53.NutritionPp53;
import com.report18file.objectpp53.PpPp53;
import com.report18file.objectpp53.ServeilPp53;
import com.report18file.objectpp53.WomanPp53;

/**
 *
 * @author henbe
 */
public class DBSuitPp53 extends DBSuit{

    public DBSuitPp53(UpdateStatus us,ConnectionInf con){
        theConnectionInf = con;
        init18FilePP(us);
    }

    public static DBSuit getDBSuit(UpdateStatus us,ConnectionInf con){
        return new DBSuitPp53(us,con);
    }
    private void init18FilePP(UpdateStatus us) {
        theAncDB = new Rp18DB(theConnectionInf,new AncPp53());
        theEpiDB = new EpiDB(theConnectionInf,new EpiPp53());
        theFpDB = new Rp18DB(theConnectionInf,new FpPp53());
        theMchDB = new Rp18DB(theConnectionInf,new MchPp53(theConnectionInf));
        thePpDB = new Rp18DB(theConnectionInf,new PpPp53(theConnectionInf));
        theNutritionDB = new Rp18DB(theConnectionInf,new NutritionPp53());
        theServeilDB = new Rp18DB(theConnectionInf,new ServeilPp53());
        theChronicDB = new Rp18DB(theConnectionInf,new ChronicPp53());
        theWomanDB = new Rp18DB(theConnectionInf,new WomanPp53());

        theHomeDB = null;
        thePersonDB = null;
        theServiceDB = null;
        theAppointDB = null;
        theCardDB = null;
        theDeathDB = null;
        theDiagDB = null;
        theDrugDB = null;
        theProcedDB = null;
    }
}
