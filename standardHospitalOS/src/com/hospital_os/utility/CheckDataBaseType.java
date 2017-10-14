/*
 * CheckDataBaseType.java
 *
 * Created on 20 ธันวาคม 2546, 10:39 น.
 */

package com.hospital_os.utility;

import com.hospital_os.object.*;
/**
 * 
 * @author Administrator
 * @deprecated ทั้ง class มีฟังชันเดียวเก็บไว้ทำไม่เอาออกดีกว่า
 */
public class CheckDataBaseType {
    
    /** Creates a new instance of CheckDataBaseType */
    DatabaseType theDatabaseType;/* = new DatabaseType();
*/

    public CheckDataBaseType() {
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
