//Source file: E:\\Job\\DrugReportSep\\code\\com\\hospital_os\\control\\PrescribeDB.java

package com.hosv3.objdb;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.objdb.*;
import java.util.*;

public class Sex2DB extends SexDB
{
    
    public Sex2DB(ConnectionInf db)
    {
        super(db);
    }
    

    //////////////////////////////////////////////////////////////////////////////
    public Vector selectAll() throws Exception
    {
        Vector vc = new Vector();
        String sql="select * from " + dbObj.table
        + " order by " + dbObj.description;
        vc = eQuery(sql);
        if(vc.size()==0)
            return null;
        else
            return vc;
    }
}
