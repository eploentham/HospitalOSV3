/*
 * DrugInteractionTypeDB.java
 *
 * Created on 14 ¡’π“§¡ 2549, 18:06 π.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hospital_os.objdb;

import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;
import java.util.*;
import java.sql.*;

/**
 *
 * @author amp
 */
public class DrugInteractionTypeDB
{
    public ConnectionInf theConnectionInf;
    public DrugInteractionType dbObj;
    
    /** Creates a new instance of DrugInteractionTypeDB */
    public DrugInteractionTypeDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new DrugInteractionType();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table="f_drug_interaction";
        dbObj.pk_field="f_drug_interaction_number";
        dbObj.description="f_drug_interaction_description";        
        return true;
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        DrugInteractionType p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new DrugInteractionType();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.description = rs.getString(dbObj.description);
            list.add(p);
        }
        rs.close();
        return list;
    }
    
}
