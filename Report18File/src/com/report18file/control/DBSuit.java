/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.report18file.control;

import com.hospital_os.usecase.connection.ConnectionInf;
import com.report18file.objdb.Rp18DB;
import java.io.IOException;

/**
 *
 * @author henbe
 */
public abstract class DBSuit {

    public ConnectionInf theConnectionInf;
    public Rp18DB theAncDB;
    public Rp18DB theAppointDB;
    public Rp18DB theCardDB;
    public Rp18DB theChronicDB;
    public Rp18DB theDeathDB;
    public Rp18DB theDiagDB;
    public Rp18DB theDrugDB;
    public Rp18DB theEpiDB;
    public Rp18DB theFpDB;
    public Rp18DB theHomeDB;
    public Rp18DB theMchDB;
    public Rp18DB theNutritionDB;
    public Rp18DB thePersonDB;
    public Rp18DB thePpDB;
    public Rp18DB theProcedDB;
    public Rp18DB theServeilDB;
    public Rp18DB theServiceDB;
    public Rp18DB theWomanDB;
/**
 *
 * @return
 */
    public static void main(String[] argc){
        try {
            Runtime.getRuntime().exec("nautilus /data/Job/rp1853");
            //Runtime.getRuntime().exec("/opt/ooo-dev3/program/soffice /data/Job/rp1853/Datadict_structure18file53.xls");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public String getResult() {
        StringBuffer sb = new StringBuffer();
            if(thePersonDB!=null) sb.append(thePersonDB.getResult());
            if(theDeathDB!=null) sb.append(theDeathDB.getResult());
            if(theChronicDB!=null) sb.append(theChronicDB.getResult() );
            if(theCardDB!=null) sb.append(theCardDB.getResult() );
            if(theServiceDB!=null) sb.append(theServiceDB.getResult() );
            if(theDiagDB!=null) sb.append(theDiagDB.getResult() );
            if(theAppointDB!=null) sb.append(theAppointDB.getResult() );
            if(theServeilDB!=null) sb.append(theServeilDB.getResult() );
            if(theDrugDB!=null) sb.append(theDrugDB.getResult() );
            if(theProcedDB!=null) sb.append(theProcedDB.getResult() );
            if(theWomanDB!=null) sb.append(theWomanDB.getResult() );
            if(theFpDB!=null) sb.append(theFpDB.getResult() );
            if(theEpiDB!=null) sb.append(theEpiDB.getResult() );
            if(theNutritionDB!=null) sb.append(theNutritionDB.getResult() );
            if(theAncDB!=null) sb.append(theAncDB.getResult() );
            if(thePpDB!=null) sb.append(thePpDB.getResult() );
            if(theMchDB!=null) sb.append(theMchDB.getResult() );
            if(theHomeDB!=null) sb.append(theHomeDB.getResult());
            return sb.toString();
    }

    public boolean isFinish() {
        boolean finish = true;
        if(theAncDB!=null)        finish = (finish && theAncDB.isFinish());
        if(theAppointDB!=null)        finish = (finish && theAppointDB.isFinish());
        if(theCardDB!=null)        finish = (finish && theCardDB.isFinish());
        if(theChronicDB!=null)        finish = (finish && theChronicDB.isFinish());
        if(theDeathDB!=null)        finish = (finish && theDeathDB.isFinish());
        if(theDiagDB!=null)        finish = (finish && theDiagDB.isFinish());
        if(theDrugDB!=null)        finish = (finish && theDrugDB.isFinish());
        if(theEpiDB!=null)        finish = (finish && theEpiDB.isFinish());
        if(theFpDB!=null)        finish = (finish && theFpDB.isFinish());
        if(theHomeDB!=null)        finish = (finish && theHomeDB.isFinish());
        if(theMchDB!=null)        finish = (finish && theMchDB.isFinish());
        if(theNutritionDB!=null)        finish = (finish && theNutritionDB.isFinish());
        if(thePersonDB!=null)        finish = (finish && thePersonDB.isFinish());
        if(thePpDB!=null)        finish = (finish && thePpDB.isFinish());
        if(theProcedDB!=null)        finish = (finish && theProcedDB.isFinish());
        if(theServeilDB!=null)        finish = (finish && theServeilDB.isFinish());
        if(theServiceDB!=null)        finish = (finish && theServiceDB.isFinish());
        if(theWomanDB!=null)        finish = (finish && theWomanDB.isFinish());
        return finish;
    }

    void stop() {
        if(theAncDB!=null)        theAncDB.stop();
        if(theAppointDB!=null)        theAppointDB.stop();
        if(theCardDB!=null)        theCardDB.stop();
        if(theChronicDB!=null)        theChronicDB.stop();
        if(theDeathDB!=null)        theDeathDB.stop();
        if(theDiagDB!=null)        theDiagDB.stop();
        if(theDrugDB!=null)        theDrugDB.stop();
        if(theEpiDB!=null)        theEpiDB.stop();
        if(theFpDB!=null)        theFpDB.stop();
        if(theHomeDB!=null)        theHomeDB.stop();
        if(theMchDB!=null)        theMchDB.stop();
        if(theNutritionDB!=null)        theNutritionDB.stop();
        if(thePersonDB!=null)        thePersonDB.stop();
        if(thePpDB!=null)        thePpDB.stop();
        if(theProcedDB!=null)        theProcedDB.stop();
        if(theServeilDB!=null)        theServeilDB.stop();
        if(theServiceDB!=null)        theServiceDB.stop();
        if(theWomanDB!=null)        theWomanDB.stop();
    }
 
}
