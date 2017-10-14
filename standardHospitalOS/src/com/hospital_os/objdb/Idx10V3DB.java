/*Source file: E:\\Job\\DrugReportSep\\code\\com\\hospital_os\\control\\PrescribeDB.java*/
package com.hospital_os.objdb;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;
import java.util.*;
import java.sql.*;
public class Idx10V3DB
{
    public ConnectionInf theConnectionInf;
    public Idx10V3 dbObj;
    final public String idtable = "172";/*"227";*/
    /**
     * @param ConnectionInf db
     * @roseuid 3F65897F0326
     */
    public Idx10V3DB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new Idx10V3();
        initConfig();
    }
    public boolean initConfig()
    {
        dbObj.table="f_icd10V3";
        dbObj.pk_field="f_icd10V3_id";
        dbObj.code1   ="icd10V3_number1";
        dbObj.code2   ="icd10V3_number1";
        dbObj.description="icd10V3_description";
        dbObj.see="icd10V3_see";
        dbObj.char4="icd10V3_char4";
        dbObj.char5="icd10V3_char5";
        /*
        dbObj.table = "idx10v3";
        dbObj.code1 = "code1";
        dbObj.code2 = "code2";
        dbObj.description = "description";
        dbObj.see = "see";
        dbObj.char4 = "char4";
        dbObj.char5 = "char5";
         */
        
        return true;
    }
    
    
    /**
     * @param cmd
     * @param o
     * @return int
     * @roseuid 3F6574DE0394
     */
    
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector selectByDescription(String pk) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where upper(" + dbObj.description
        + ") like '%" + pk + "%' ";
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector selectAll() throws Exception
    {
        String sql="select * from " + dbObj.table;
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    /*////////////////////////////////////////////////////////////////////////////////*/
    public Vector eQuery(String sql) throws Exception
    {
        Idx10V3 p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        int i = 0;
        while(rs.next())
        {
            p = new Idx10V3();
            p.code1 = rs.getString(dbObj.code1);
            p.code2 = rs.getString(dbObj.code2);
            p.description = rs.getString(dbObj.description);
            p.see = rs.getString(dbObj.see);
            p.char4 = rs.getString(dbObj.char4);
            p.char5 = rs.getString(dbObj.char5);
            i++;
            
            if(i > 150)
                break;
            list.add(p);
        }
        rs.close();
        return list;
    }
    /*////////////////////////////////////////////////////////////////////////////*/
}
