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
import com.report18file.objectpp.*;

/**
 *
 * @author henbe
 */
public class DBSuitPp extends DBSuit{

    public DBSuitPp(UpdateStatus us,ConnectionInf con){
        theConnectionInf = con;
         init18FilePP(us);
    }

    public static DBSuit getDBSuit(UpdateStatus us,ConnectionInf con){
        return new DBSuitPp(us,con);
    }
    private void init18FilePP(UpdateStatus us) {
        theAncDB = new Rp18DB(theConnectionInf,new AncPp());
        theEpiDB = new EpiDB(theConnectionInf,new EpiPp());
        theFpDB = new Rp18DB(theConnectionInf,new FpPp());
        MchPp theMCH = new MchPp();
        theMCH.setConnectionInf(theConnectionInf);
        theMchDB = new Rp18DB(theConnectionInf,theMCH);
        PpPp thePP = new PpPp();
        thePP.setConnectionInf(theConnectionInf);
        thePpDB = new Rp18DB(theConnectionInf,thePP);
        theNutritionDB = new Rp18DB(theConnectionInf,new NutritionPp());
        theServeilDB = new Rp18DB(theConnectionInf,new ServeilPp());
        theWomanDB = new Rp18DB(theConnectionInf,new WomanPp());

        theHomeDB = null;
        thePersonDB = null;
        theServiceDB = null;
        theAppointDB = null;
        theCardDB = null;
        theChronicDB = null;
        theDeathDB = null;
        theDiagDB = null;
        theDrugDB = null;
        theProcedDB = null;
    }
}
