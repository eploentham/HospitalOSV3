//Source file: E:\\Job\\DrugReportSep\\code\\com\\hospital_os\\control\\PrescribeDB.java

package com.hosv3.objdb;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.objdb.*;
import java.util.*;

public class ItemPrice2DB extends ItemPriceDB
{
    
    public ItemPrice2DB(ConnectionInf db)
    {
        super(db);
    }
    //////////////////////////////////////////////////////////////////////////////
    public Vector selectByItemDate(String pk,String date) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.item_id
        + " = '" + pk + "' and " + dbObj.active_date + " <= '" + date + "'"
        + " order by " + dbObj.active_date + " DESC";
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }    
}
