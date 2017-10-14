/*
 * Irf.java
 *
 * Created on 8 กันยายน 2548, 12:05 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.report12file.objectnh53;
import com.linuxense.javadbf.DBFField;
import com.report12file.object.Rp12OI2;
import com.reportcenter.object.RpField;
import com.report12file.utility.Report12FileData;
import com.reportcenter.utility.IOStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author tong(Padungrat)
 */
public class IrfNh53 extends Rp12OI2{

    /** character width 9 */
    public String an;
    /** character width 5 */
    public String refer;
    /** character width 1 */
    public String refertype;
    
    public IrfNh53() {
    }
    public Rp12OI2 initInstant() {
        return new IrfNh53();
    }

    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {
        String sql = IOStream.readInputDefault("config/rp_12file53/12file53_irf.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        return pm;
    }


 public String[] getValueArray() {
        return new String[]{
an	,
refer	,
refertype
};
    }

 public boolean setValue(ResultSet rs) throws Exception {
        IrfNh53 p = this;
	p.	an	 = Report12FileData.getRsString(rs,	1	);
	p.	refer	 = Report12FileData.getRsString(rs,	2	);
	p.	refertype	 = Report12FileData.getRsString(rs,	3	);
            return true;
    }



    public String getFileName() {
        return "IRF";
    }


    public RpField[] getRpFields() {
        if(theRPF!=null)
            return theRPF;
        theRPF = new RpField[]{
        RpField.initData("	an	","	เลข AN	",	9	,	false	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C)
        ,RpField.initData("	refer	","	สถานพยาบาลส่งต่อ	",	5	,	false	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C)
        ,RpField.initData("	refertype	","	ประเภทการส่งต่อ	",	1	,	false	,	true	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C)
        };
        return theRPF;
    }

}
