/*
 * HtDB.java
 *
 * Created on 7 เมษายน 2549, 16:09 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.healthy.objdb;

import com.healthy.objdb.AGRTypeDB;
import com.pcu.objdb.objdbclass.*;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.objdb.*;
import com.healthy.object.*;

/**
 *
 * @author hospitalos5
 */
public class HtDB {
    
    public ConnectionInf theConnectionInf;
    public AGRTypeDB theAGRTypeDB;
    public SexDB theSexDB;
    public BehaviorHxTypeDB theBehaviorHxTypeDB;
    public CigaBehaviorTypeDB theCigaDB;
    public AlcoholDB theAlcoholDB;
    public SexBehaviorTypeDB theSexBehaviorDB;
    public MoodBehaviorTypeDB theMoodBehaviorDB;
    public AccidentProtectTypeDB theAccidentProtectTypeDB;
    public ICD10ChronicDB theICD10ChronicDB;
    public SchoolDB theSchoolDB;
    public ElderAgrDB theElderAgrDB;
    public BehaviorDB theBehaviorDB;
    public ToBeOneDB theToBeOneDB;
    public DiabetesDB theDiabetesDB;
    public ExerciseDB theExerciseDB;
    public PressureDB thePressureDB;
    public ReasonTypeDB theReasonTypeDB;
    
    /** Creates a new instance of HtDB */
    public HtDB(ConnectionInf theCon) {
        theConnectionInf = theCon;
        theAGRTypeDB = new AGRTypeDB(theConnectionInf);
        theSexDB = new SexDB(theConnectionInf);
        theBehaviorHxTypeDB = new BehaviorHxTypeDB(theConnectionInf);
        theCigaDB = new CigaBehaviorTypeDB(theConnectionInf);
        theAlcoholDB = new AlcoholDB(theConnectionInf);
        theSexBehaviorDB = new SexBehaviorTypeDB(theConnectionInf);
        theMoodBehaviorDB = new MoodBehaviorTypeDB(theConnectionInf);
        theAccidentProtectTypeDB = new AccidentProtectTypeDB(theConnectionInf);
        theICD10ChronicDB = new ICD10ChronicDB(theConnectionInf);
        theSchoolDB = new SchoolDB(theConnectionInf);
        theElderAgrDB = new ElderAgrDB(theConnectionInf);
        theBehaviorDB = new BehaviorDB(theConnectionInf);
        theToBeOneDB = new ToBeOneDB(theConnectionInf);
        theDiabetesDB = new DiabetesDB(theConnectionInf);
        theExerciseDB = new ExerciseDB(theConnectionInf);
        thePressureDB = new PressureDB(theConnectionInf);
        theReasonTypeDB = new ReasonTypeDB(theConnectionInf);
    }
}
