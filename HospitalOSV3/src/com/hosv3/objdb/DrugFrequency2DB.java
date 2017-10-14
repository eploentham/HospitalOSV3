//Source file: E:\\Job\\DrugReportSep\\code\\com\\hospital_os\\control\\PrescribeDB.java

package com.hosv3.objdb;
import com.hosv3.object.*;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.objdb.*;
import java.util.*;
import java.sql.*;

public class DrugFrequency2DB extends DrugFrequencyDB
{
    
    public DrugFrequency2DB(ConnectionInf db)
    {
        super(db);
    }
    
    //////////////////////////////////////////////////////////////////////////////
    public Vector selectAll() throws Exception
    {
        Vector vc = new Vector();
        String sql="select * from " + dbObj.table
        + "  order by " + dbObj.description;
        vc = eQuery(sql);
        if(vc.size()==0)
            return null;
        else
            return vc;
    }
    //////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////
    public Vector selectActive() throws Exception
    {
        Vector vc = new Vector();
        String sql="select * from " + dbObj.table
        + " where "+ dbObj.active +" = '1' order by " + dbObj.description;
        vc = eQuery(sql);
        if(vc.size()==0)
            return null;
        else
            return vc;
    }
 //////////////////////////////////////////////////////////////////////////////
    public Vector selectByCN(String key) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where (UPPER(" + dbObj.drug_frequency_id
        + ") like UPPER('" + key+ "') "
        + " or UPPER(" + dbObj.description
        + ") like UPPER('" + key+ "')) and "
//        + dbObj.active +" = '1' order by " + dbObj.description;
        + dbObj.active +" = '1' order by item_drug_frequency_number";
        
        return eQuery(sql);
    }   
    //////////////////////////////////////////////////////////////////////////////
    public Vector eQuery(String sql) throws Exception
    {
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            DrugFrequency2 p = new DrugFrequency2();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.drug_frequency_id = rs.getString(dbObj.drug_frequency_id);
            p.factor = rs.getString(dbObj.factor);
            p.description = rs.getString(dbObj.description);
            if(p.description!=null)
                p.description = p.description.replace('\n',' ');
            p.active = rs.getString(dbObj.active);
            list.add(p);
        }
        rs.close();
        return list;
    }    
}
