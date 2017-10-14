/*Source file: E:\\Job\\DrugReportSep\\code\\com\\hospital_os\\control\\PrescribeDB.java*/
package com.hosv3.objdb;

import com.hospital_os.usecase.connection.*;
import com.hospital_os.object.*;
import com.hospital_os.objdb.*;
import com.hosv3.object.*;
import java.util.*;
import java.sql.*;

public class VisitPatientDB
{
    
    public ConnectionInf theConnectionInf;
    public Visit dbObj;
    final public String idtable = "255";/*"218";*/
    /**
     * @param ConnectionInf db
     * @roseuid 3F65897F0326
     */
    public VisitPatientDB(ConnectionInf db)
    {   
        theConnectionInf=db;
        dbObj = VisitDB.getMapObject();
    }
    
    /*///////////////////////////////////////////////////////*/
    public Vector eQuery(String sql) throws Exception
    {
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            VisitPatient vp = new VisitPatient();
            VisitDB.getObject(VisitDB.getMapObject(), vp, rs);
            PatientDB.getObject(PatientDB.getMapObject(), vp.thePatient, rs);
            list.add(vp);
        }
        rs.close();
        return list;
    }
   /*////////////////////////////////////////////////////////////////////////////*/
    public Vector selectLocking(String str1,String str2) throws Exception
    {
        String sql = "select * from " + dbObj.table
        + " left join t_patient on t_patient.t_patient_id = t_visit.t_patient_id"
        + " where (" + dbObj.visit_status + " like '" + str1 + "'" +
        " or " + dbObj.visit_status + " like '" + str2 + "')" +
        " and " + dbObj.locking + " = '1' " + 
        " order by " + dbObj.begin_visit_time + " desc  limit 500";
        
        Vector v=eQuery(sql);
        if(v.size()==0)         return null;
        else                    return v;
    }
     /*////////////////////////////////////////////////////////////////////////////*/
    public Vector selectLocking() throws Exception
    {
        String sql = "select * from " + dbObj.table
        + " left join t_patient on t_patient.t_patient_id = t_visit.t_patient_id"
        + " where " + dbObj.visit_status + " like '%' and "
        + dbObj.locking + " = 1 "
        + " order by " + dbObj.begin_visit_time + " desc  limit 100";
        
        Vector v=eQuery(sql);
        if(v.size()==0)         return null;
        else                    return v;
    }
     /*////////////////////////////////////////////////////////////////////////////*/
    public Vector selectLocking(String hn) throws Exception
    {
        // SOmprasong fix bug query 110809
        String sql = "select * from " + dbObj.table
        + " left join t_patient on t_patient.t_patient_id = t_visit.t_patient_id"
        + " where " + dbObj.locking + " = '1' and "
        + dbObj.hn +" like '%" + hn + "%'"
        + " order by " + dbObj.vn + " desc ";
        
        Vector v=eQuery(sql);
        if(v.size()==0)         return null;
        else                    return v;
    }
}
