/*
 * DatabaseType.java
 *
 * Created on 14 �ѹ�Ҥ� 2546, 15:57 �.
 */

package com.hospital_os.object;

/**
 *
 * @author  tong
 * �纪�Դ�ͧ�ҹ���������
 */
public class DatabaseType {
    
    /** Creates a new instance of DatabaseType */
    final public String databasetype[];
    final private int num = 3;
    public DatabaseType() {
        databasetype = new String[num];
        
        databasetype[0] = "postgreSQL";
        databasetype[1] = "SQL Server 2000";
        databasetype[2] = "My SQL";
    }
    
    public static int getDataBaseType(String databaseType)
    {   
        DatabaseType theDatabaseType = new DatabaseType();
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
