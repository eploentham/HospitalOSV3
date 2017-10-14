/*
 * ICD10ChronicDB.java
 *
 * Created on 10 เมษายน 2549, 14:55 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.healthy.objdb;
import com.healthy.object.*;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;
import com.hospital_os.objdb.*;
import java.util.*;
import java.sql.*;
/**
 *
 * @author hospitalos5
 */
public class ICD10ChronicDB extends ICD10DB {
    
    /** Creates a new instance of ICD10ChronicDB */
    public ICD10ChronicDB(ConnectionInf db) {
        super(db);
    }
    public Vector selectChronicComboFix() throws Exception
    {   Vector vc = new Vector();
        String sql ="select b_icd10_id, icd10_number, icd10_description, icd10_other_description " +
        "from " + dbObj.table + ", b_group_chronic, b_group_icd10 " +
        "where substr(" + dbObj.icd10_id + ",1,3) = group_icd10_number " + 
            "and group_icd10_b_group_chronic_id  = group_chronic_number " +
            "order by " + dbObj.pk_field;
        return veQueryCF(sql);
    }
    public Vector veQueryCF(String sql) throws Exception
    {
        ComboFix p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        
        while(rs.next())
        {
            p = new ComboFix();
            p.code = rs.getString(dbObj.pk_field);
            p.name = rs.getString(dbObj.icd10_id) + "  " + rs.getString(dbObj.description);
            list.add(p);
        }
        rs.close();
        return list;
    }
    
}
