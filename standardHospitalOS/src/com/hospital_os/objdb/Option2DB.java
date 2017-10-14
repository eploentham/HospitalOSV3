//Source file: E:\\Job\\DrugReportSep\\code\\com\\hospital_os\\control\\PrescribeDB.java

package com.hospital_os.objdb;

import com.hospital_os.object.*;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.hospital_os.objdb.*;

import java.util.*;
import java.sql.*;

public class Option2DB extends OptionDB
{
    OptionDetailDB theOptionDetailDB;
    public Option2DB(ConnectionInf db,OptionDetailDB oddb)
    {
        theConnectionInf = db;
        setDepDB(oddb);
    }
    public void setDepDB(OptionDetailDB oddb){
        theOptionDetailDB = oddb;
    }
    
    /**
     * @authen  Henbe pongtorn
     * @date  24/05/06
     * @แก้ให้เป็นฟังชันที่สามารถบันทึก option ที่หลากหลายได้
     */
    public int insert(Option o) throws Exception
    {
        Vector odv = o.getOptionDetailV();
        int ret = 0;
        ret += theOptionDetailDB.deleteAll();
        for(int i=0;i<odv.size();i++)
        {
            OptionDetail od = (OptionDetail)odv.get(i);
            ret += theOptionDetailDB.insert(od);
        }
        return ret;
    }
    public int update(Option o) throws Exception
    {
        return insert(o);
    }
    //////////////////////////////////////////////////////////////////////////////
    public int delete(Option o) throws Exception
    {
        return theOptionDetailDB.deleteAll();
    }
    //////////////////////////////////////////////////////////////////////////////
    public Option select()
    {
        try{
            Vector odv = theOptionDetailDB.selectAll();
            Option opt = new Option(odv);
            return opt;
        }
        catch(Exception e){
            return new Option();
        }
    }
    //////////////////////////////////////////////////////////////////////////////    
}
