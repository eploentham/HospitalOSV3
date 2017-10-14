//Source file: E:\\Job\\DrugReportSep\\code\\com\\hospital_os\\control\\PrescribeDB.java

package com.hosv3.gui.component.nan;

import com.hospital_os.usecase.connection.*;
//import com.hosv3.object.nan.*;
//import com.hosv3.utility.connection.*;
import java.util.*;

//import java.sql.*;

public class RiskFactorTemplateDB extends DiseaseTemplateDB
{
    final public static String idtable = "905";

    /**
     * @param ConnectionInf db
     * @roseuid 3F65897F0326
     */
    public RiskFactorTemplateDB(ConnectionInf db)
    {
        super(db);
        initConfig();
    }
    public boolean initConfig()
    {
        dbObj.table="b_template_risk_factor";
        dbObj.pk_field="b_template_risk_factor_id";
        dbObj.name   ="template_risk_factor_name";
        dbObj.note   ="template_risk_factor_note";
        return true;
    }
     //////////////////////////////////////////////////////////////////////////////
    public Vector selectAll() throws Exception
    {
        return getVector(super.selectAll());
    } 
    //////////////////////////////////////////////////////////////////////////////
    public Vector selectByName(String str) throws Exception
    {
        return getVector(super.selectByName(str));
    }
    //////////////////////////////////////////////////////////////////////////////
    public Vector getVector(Vector v)
    {
        Vector vv = new Vector();
        if(v==null)
            v = new Vector();
        for(int i=0,size=v.size();i<size;i++){
            DiseaseTemplate rft = (DiseaseTemplate)v.get(i);
            vv.add(new RiskFactorTemplate(rft));
        }
        return vv;
    }
}
