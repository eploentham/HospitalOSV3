
package com.hospital_os.objdb;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;
import java.util.*;
import java.sql.*;
public class PatientOldHnDB
{   
    public ConnectionInf theConnectionInf;
    public PatientOldHn dbObj;
    final public String idtable = "908";
    /**
     * @param ConnectionInf dbtest henbe_test
     * @roseuid 3F65897F0326
     * @ยังไม่ได้ใช้จริงนะ:amp:2/6/2549
     */
    public PatientOldHnDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new PatientOldHn();
        initConfig();
    }
    public PatientOldHnDB()
    {
        theConnectionInf = null;
        dbObj = new PatientOldHn();
        initConfig();
    }
    public boolean initConfig()
    {
        dbObj.table="t_patient_hn_history";
        dbObj.pk_field="t_patient_hn_history_id";

        dbObj.patient_id   ="t_patient_id";
        dbObj.hn   ="patient_hn_history_hn";
        dbObj.other_patient_id ="patient_hn_history_other_patient_id";
        dbObj.description ="patient_hn_history_description";
        return true;
    }
    
    
    /**
     * @param cmd
     * @param o
     * @return int
     * @roseuid 3F6574DE0394
     */
    public int insert(PatientOldHn o) throws Exception
    {
        String sql="";
        PatientOldHn p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"  + dbObj.patient_id
        + " ,"  + dbObj.hn
        + " ,"	+ dbObj.other_patient_id
        + " ,"	+ dbObj.description        
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.patient_id
        + "','" + p.hn
        + "','" + p.other_patient_id
        + "','" + p.description        
        + "')";
        
        return theConnectionInf.eUpdate(sql);
    }
    public int update(PatientOldHn o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        PatientOldHn p=o;
        String field =""
        + "', " + dbObj.patient_id + "='" + p.patient_id
        + "', " + dbObj.hn + "='" + p.hn
        + "', " + dbObj.description + "='" + p.description
        + "', " + dbObj.other_patient_id + "='" + p.other_patient_id
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(PatientOldHn o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public PatientOldHn selectByPK(String pk) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.pk_field
        + " = '" + pk + "'";
        Vector v = eQuery(sql);
        if(v.size()==0)    return null;
        else               return (PatientOldHn)v.get(0);
    }
    
    public Vector selectByPatientId(String patient_id) throws Exception
    {  
        String sql ="select * from " + dbObj.table
        + " where "+ dbObj.patient_id + " = '" + patient_id + "'"
        + " order by "+ dbObj.hn;
        return eQuery(sql);
    }
    
    
    public Vector eQuery(String sql) throws Exception
    {
        PatientOldHn p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new PatientOldHn();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.patient_id = rs.getString(dbObj.patient_id);
            p.hn = rs.getString(dbObj.hn);
            p.description = rs.getString(dbObj.description);
            p.other_patient_id = rs.getString(dbObj.other_patient_id);
            list.add(p);
        }
        rs.close();
        return list;
    }
}
