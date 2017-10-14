/*
 * PatientPCUDB.java
 *
 * Created on 26 กันยายน 2548, 15:31 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.pcu.objdb.objdbclass;
import com.hospital_os.usecase.connection.*;

import com.hospital_os.utility.*;
import com.hospital_os.object.*;
import com.hospital_os.objdb.*;
//import com.hosv3.objdb.*;
import com.pcu.object.*;
import java.util.*;
import java.sql.*;
/**
 *
 * @author jao
 *@deprecated henbe unused
 */
public class PatientPCUDB extends PatientDB {
    
    /** Creates a new instance of PatientPCUDB */
    public PatientPCUDB(ConnectionInf db) {
         super(db);
    }
    
    public Vector selectByHomeNumber(String number) throws Exception
    {
         String sql="select * from " + dbObj.table
        + " where " + dbObj.house
        + " like '" + number + "%'"
        + " and " + dbObj.active + " ='1' " ;
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    
}
