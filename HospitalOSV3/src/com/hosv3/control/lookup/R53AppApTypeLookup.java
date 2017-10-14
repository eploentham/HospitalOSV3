/*
 * AccidentTypeLookup.java
 *
 * Created on 2 มิถุนายน 2549, 13:28 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hosv3.control.lookup;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.ComboFix;
import java.sql.ResultSet;
import java.util.Vector;

/**
 *
 * @not deprecated because use henbe package
 * @author Padungrat(tong)
 */
public class R53AppApTypeLookup implements LookupControlInf{
    private final ConnectionInf theConnectionInf;
    private Vector vret;
    /** Creates a new instance of AccidentTypeLookup */
    public R53AppApTypeLookup(ConnectionInf con){
        theConnectionInf = con;
    } 
    public java.util.Vector listData(String str) {
        try {
            theConnectionInf.open();
            ResultSet rs = theConnectionInf.eQuery(
                    "select * from (select id,name || ' '||description as des " +
                    "from r_rp1853_aptype) instype " +
                    "where id ilike '"+str+"' or des ilike '%"+str+"%' order by des");
            vret = new Vector();
                    ComboFix c = new ComboFix();
                    c.code = "";
                    c.name = "ไม่ระบุ";
                    vret.add(c);
            while (rs.next()) {
                ComboFix cf = new ComboFix();
                cf.code = rs.getString(1);
                cf.name = rs.getString(2);
                vret.add(cf);
            }
            return vret;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally{
            theConnectionInf.close();
        }
    }
    public CommonInf readHosData(String str) {
        Vector v = listData(str);
        if(v.isEmpty())
            return null;
        return (ComboFix)v.get(0);
    }
}
