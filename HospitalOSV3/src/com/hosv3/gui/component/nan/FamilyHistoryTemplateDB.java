//Source file: E:\\Job\\DrugReportSep\\code\\com\\hospital_os\\control\\PrescribeDB.java

package com.hosv3.gui.component.nan;

import com.hospital_os.usecase.connection.*;
//import com.hosv3.object.nan.*;
//import com.hosv3.utility.connection.*;
import java.util.*;

//import java.sql.*;

public class FamilyHistoryTemplateDB extends DiseaseTemplateDB
{
    final public static String idtable = "905";

    /**
     * @param ConnectionInf db
     * @roseuid 3F65897F0326
     */
    public FamilyHistoryTemplateDB(ConnectionInf db)
    {
        super(db);
        initConfig();
    }
    public boolean initConfig()
    {
        dbObj.table="b_template_family_history";
        dbObj.pk_field="b_template_family_history_id";
        dbObj.name   ="template_family_history_name";
        dbObj.note   ="template_family_history_note";
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
        for(int i=0,size=v.size();i<size;i++){
            DiseaseTemplate rft = (DiseaseTemplate)v.get(i);
            vv.add(new FamilyHistoryTemplate(rft));
        }
        return vv;
    }
        
}
