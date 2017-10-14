/*
 * ManageDB.java
 *
 * Created on 9 กุมภาพันธ์ 2549, 17:43 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.generalpcu.objdb;

import com.hospital_os.usecase.connection.ConnectionInf;
/**
 *
 * @author pu
 */
public class ManageDB
{    
    public ConnectionInf theConnectionInf;
    
    public VillageDB theVillageDB; 
    public DiseaseDB theDiseaseDB;
    public MaimTypeDB theMaimTypeDB;
    public RPPcuNutritionDB theRPPcuNutritionDB;
    public RPPcuChronicDB theRPPcuChronicDB;
    public RPPcuUncontagiousDB theRPPcuUncontagiousDB;
    public ResidentAgeGroupDB theResidentAgeGroupDB;
    public RPPcuResidentDB theRPPcuResidentDB;
    public RPPcuPregnanceANCDB theRPPcuPregnanceANCDB;  
    public RPPcuMaimDB theRPPcuMaimDB;
    public EpiAgeGroupDB theEpiAgeGroupDB;
    public RPPcuEpiDB theRPPcuEpiDB;
    public RPPcuFamilyPlaningDB theRPPcuFamilyPlaningDB;

    /** Creates a new instance of ManageDB */
    public ManageDB(ConnectionInf conf)
    {
        theConnectionInf = conf;  
        theVillageDB = new VillageDB(theConnectionInf);   
        theDiseaseDB = new DiseaseDB(theConnectionInf);
        theMaimTypeDB = new MaimTypeDB(theConnectionInf);
        theRPPcuNutritionDB = new RPPcuNutritionDB(theConnectionInf);    
        theRPPcuChronicDB = new RPPcuChronicDB(theConnectionInf);
        theRPPcuUncontagiousDB = new RPPcuUncontagiousDB(theConnectionInf);
        theResidentAgeGroupDB = new ResidentAgeGroupDB(theConnectionInf);
        theRPPcuResidentDB = new RPPcuResidentDB(theConnectionInf);
        theRPPcuPregnanceANCDB = new RPPcuPregnanceANCDB(theConnectionInf);
        theRPPcuMaimDB = new RPPcuMaimDB(theConnectionInf);
        theEpiAgeGroupDB = new EpiAgeGroupDB(theConnectionInf);
        theRPPcuEpiDB = new RPPcuEpiDB(theConnectionInf);
        theRPPcuFamilyPlaningDB = new RPPcuFamilyPlaningDB(theConnectionInf);

    }
    
}
