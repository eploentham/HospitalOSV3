//Source file: E:\\Job\\DrugReportSep\\code\\com\\hospital_os\\control\\PrescribeDB.java

package com.hosv3.objdb.squery;

import com.hosv3.objdb.*;
import com.hospital_os.usecase.connection.*;
import java.util.*;

public class SpecialQueryItem2DB extends Item2DB
{
    
    public SpecialQueryItem2DB(ConnectionInf db)
    {
        super(db);
    }
    //////////////////////////////////////////////////////////////////////////////
  
    public Vector selectItemByGroup(String groupid) throws Exception{
        String sql = "select * from b_item where item_active = '1' " +
                    "and b_item_id in (select b_item_id  from b_item_set " +
                    "where b_item_group_id = '"+groupid+"') order by item_common_name";
        return eQuery(sql);
    }
}
