//Source file: E:\\Job\\DrugReportSep\\code\\com\\hospital_os\\control\\PrescribeDB.java

package com.hosv3.objdb;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.objdb.*;
import java.util.*;

public class ContractAdjust2DB extends ContractAdjustDB
{
    
    public ContractAdjust2DB(ConnectionInf db){
        super(db);
    }
    ///////////////////////////////////////////////////////////////////////////
    public int deleteByCid(String key) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.contract_id +" = '"+ key +"'";
        return theConnectionInf.eUpdate(sql);
    }   
    public Vector selectByCid(String key) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.contract_id +" = '"+ key +"'"
        + " order by " + dbObj.covered_id;
        return eQuery(sql);
    }       
    //////////////////////////////////////////////////////////////////////////////
}
