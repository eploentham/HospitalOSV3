//Source file: E:\\Job\\DrugReportSep\\code\\com\\hospital_os\\control\\PrescribeDB.java

package com.hosv3.objdb;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.objdb.*;
import java.util.*;

/**
 *@deprecated henbe unused
 */
public class ICD102DB extends ICD10DB
{
    
    public ICD102DB(ConnectionInf db)
    {
        super(db);
    }
   //////////////////////////////////////////////////////////////////////////////
    public Vector selectByIdUseGroup(String icd,String des,String odes,String group) throws Exception
    {
        String sql="select * from " + dbObj.table + " where"  + " upper(" + dbObj.icd10_id + ") like upper('"+ icd +"')";
        if(des.equals(""))
            sql+= " or upper("  + dbObj.description + ") like " + " upper('" + des + "')" +
            " or upper("  + dbObj.other_description + ") like " + " upper('" + odes + "')";
        if(!group.equals(""))
            sql += " and " + dbObj.generate_code + " = '" + group + "'";
        
        sql += " order by " + dbObj.icd10_id ;
        return eQuery(sql);
    }    
  
}
