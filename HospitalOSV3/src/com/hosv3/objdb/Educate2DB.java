/*
 * Educate2DB.java
 *
 * Created on 21 กันยายน 2548, 15:53 น.
 */

package com.hosv3.objdb;
import java.util.Vector;

import com.hospital_os.objdb.EducateDB;
import com.hospital_os.usecase.connection.ConnectionInf;


/**
 *
 * @author  kingland
 */
public class Educate2DB extends EducateDB{
    
    /** Creates a new instance of Educate2DB */
    public Educate2DB(ConnectionInf db) {
        super(db);
    }
    public Vector selectAll() throws Exception
    {   Vector vc = new Vector();
        String sql ="select * from " + dbObj.table + " order by "+
        "patient_education_type_sort_index";
        vc = veQuery(sql);
        if(vc.size()==0)
            return null;
        else
            return vc;
    }
}
