/*
 * ManageDB.java
 *
 * Created on 3 ÁÔ¶Ø¹ÒÂ¹ 2549, 11:21 ¹.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportnan.objdb;
import com.hospital_os.usecase.connection.ConnectionInf;
/**
 *
 * @author pu
 */
public class ManageDB
{
    public ConnectionInf theConnectionInf;
    public RPAccedent19CauseNanDB theRPAccedent19CauseNanDB;
    public RPPatientOPDRemainDB theRPPatientOPDRemainDB;
    public RPDailyOPDPatientDB theRPDailyOPDPatientDB;
    public RPEmergencyPatientDB theRPEmergencyPatientDB;
    public RPPatientInClinicDB theRPPatientInClinicDB;
    public RPPatientOperatedDB theRPPatientOperatedDB;    
    public RPResidentDB theRPResidentDB;
    public SiteDB thSiteDB;
    public TambonDB theTambonDB;
    public GeneralDB theGeneralDB;
    public ServicePointNanDB theServicePointNanDB;
    public RPIPDPatientDB theRPIPDPatientDB;
    public RP505INClinicDB theRP505INClinicDB;
    public RPPatientNCDDB theRPPatientNCDDB;
    
    /** Creates a new instance of ManageDB */
    public ManageDB(ConnectionInf conf)
    {
        theConnectionInf = conf;
        theRPAccedent19CauseNanDB = new RPAccedent19CauseNanDB(theConnectionInf);
        theRPPatientOPDRemainDB = new RPPatientOPDRemainDB(theConnectionInf);
        theRPDailyOPDPatientDB = new RPDailyOPDPatientDB(theConnectionInf);
        theRPEmergencyPatientDB = new RPEmergencyPatientDB(theConnectionInf);
        theRPPatientInClinicDB = new RPPatientInClinicDB(theConnectionInf);
        theRPPatientOperatedDB = new RPPatientOperatedDB(theConnectionInf);
        theRPResidentDB = new RPResidentDB(theConnectionInf);
        thSiteDB = new SiteDB(theConnectionInf);
        theTambonDB = new TambonDB(theConnectionInf);
        theGeneralDB = new GeneralDB(theConnectionInf);
        theServicePointNanDB = new ServicePointNanDB(theConnectionInf); 
        theRPIPDPatientDB = new RPIPDPatientDB(theConnectionInf); 
        theRP505INClinicDB = new RP505INClinicDB(theConnectionInf); 
        theRPPatientNCDDB = new RPPatientNCDDB(theConnectionInf);
    }
    
}
