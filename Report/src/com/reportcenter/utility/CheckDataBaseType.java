/*
 * CheckDataBaseType.java
 *
 * Created on 30 กรกฎาคม 2548, 14:14 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportcenter.utility;
import com.reportcenter.object.DatabaseType;
/**
 *
 * @author  Administrator
 */
public class CheckDataBaseType {
    
    /** Creates a new instance of CheckDataBaseType */
    DatabaseType theDatabaseType;// = new DatabaseType();
    
    public CheckDataBaseType() {
    }
    
    public int getDataBaseType(String databaseType)
    {   theDatabaseType = new DatabaseType();
        int type =0;
        for(int i=0; i < theDatabaseType.databasetype.length ; i++)
        {
             if(databaseType.equals(theDatabaseType.databasetype[i]))
             {  type = i;
             }
        }
        
        databaseType = null;
        theDatabaseType = null;
        return type;
    }
}

