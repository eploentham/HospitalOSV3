//Source file: E:\\Job\\DrugReportSep\\code\\com\\hospital_os\\control\\PrescribeDB.java

package com.hosv3.objdb;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;
import com.hospital_os.objdb.*;
import java.util.*;
import java.text.*;

/**
 *@deprecated use in com.hospital_os.objdb.*;
 */
public class SequenceData2DB extends SequenceDataDB
{
    
    public SequenceData2DB(ConnectionInf db)
    {
        super(db);
    }

    public Vector selectLikePattern(String data) throws Exception
    {
        String sql="select * from " + dbObj.table ;
        if(data.length() != 0)
           sql = sql + " Where upper(" + dbObj.pattern + ") like upper('" + data + "')";

        Vector v=eQuery(sql);
        if(v.size()==0)         return null;
        else                    return v;
    }    
    
    public String updateSequence(String str,boolean hn_runing) throws Exception
    {
        //เอาค่าเก่ามาเก็บไว้ แล้วก็ update sequence ให้เป็นเลขถัดไป
        SequenceData sd = selectByPK(str);
        int value = Integer.parseInt(sd.value);
        sd.value = String.valueOf(value + 1);
        update(sd);
        String a = "";
        if(!sd.equals(""))
             a = getSeqPattern(sd.pattern,sd.value);
        else
            a = getSeqPattern("000000000",sd.value);
            
        return a;
    }
    
    public String getSeqPattern(String pattern,String value){
        String patt = pattern;
        String start = "";
        String year = "";
        if(pattern.indexOf("yy")!=-1){
            patt = pattern.substring(pattern.lastIndexOf("yy")+2);
            start = pattern.substring(0,pattern.indexOf("yy"));
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(System.currentTimeMillis());
            //year = String.valueOf(c.get(c.YEAR)).substring(2);
            year = Timing.getYear2Digit(theConnectionInf);
        }
        if(pattern.indexOf(".")!=-1){
            patt = pattern.substring(pattern.lastIndexOf(".")+1);
            start = pattern.substring(0,pattern.indexOf("."));
        }
        DecimalFormat d = new DecimalFormat();
        d.applyPattern(patt);
        String show = start + year + d.format(Integer.parseInt(value));
        return show; 
    }
}
