/*
 * SpecialQueryPatient2DB.java
 *
 * Created on 17 ธันวาคม 2548, 14:22 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hosv3.objdb.squery;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.objdb.specialQuery.*;
import com.hospital_os.object.specialQuery.*;
import java.util.*;
import java.sql.*;
/**
 *@deprecated henbe unused 05/02/07 
 * Use in search patient from dialog search
 */
public class SpecialQueryPatient2DB extends SpecialPatientDB{
    
    /** Creates a new instance of SpecialQueryPatient2DB */
    public SpecialQueryPatient2DB(ConnectionInf db){
        super(db);
    }
    
    public Vector selectByXn(String xn) throws Exception{
        String sql = "select * from t_patient where patient_xn like '%"+xn+"%'";
        return this.eQuery(sql);
    }
     private Vector eQuery(String sql) throws Exception
    {
        SpecialPatient p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new SpecialPatient();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.hn  = rs.getString(dbObj.hn);
            p.fname = rs.getString(dbObj.fname);
            p.lname =  rs.getString(dbObj.lname);
            p.birthday =  rs.getString(dbObj.birthday);
            p.mother_fname =  rs.getString(dbObj.mother_fname);
            p.active =  rs.getString(dbObj.active);
            list.add(p);            
        }
        rs.close();
        return list;
    }
}
