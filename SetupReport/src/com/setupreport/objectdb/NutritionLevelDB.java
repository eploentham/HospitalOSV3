/*
 * NutritionLevelDB.java
 *
 * Created on 16 ¡’π“§¡ 2549, 14:13 π.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.setupreport.objectdb;

import com.hospital_os.usecase.connection.ConnectionInf;
import com.setupreport.object.*;
import java.util.Vector;
import java.sql.*;
/**
 *
 * @author Ojika
 */
public class NutritionLevelDB
{
    
    /** Creates a new instance of NutritionLevelDB */
    ConnectionInf theConnectionInf;
    String SQL = "";
    ResultSet rs = null;
    Vector vObject ;
    NutritionLevel theNutritionLevel;
    
    public NutritionLevelDB(ConnectionInf connectionInf)
    {
        theConnectionInf = connectionInf;
        theNutritionLevel = new NutritionLevel();
    }
    
    public Vector selectByKeyword(String keyword) throws Exception
    {
        SQL = " select " +
                " f_visit_nutrition_level_id " +
                " ,visit_nutrition_level_description " +
                " from " +
                " f_visit_nutrition_level " +
                " where " +
                " UPPER(visit_nutrition_level_description) like UPPER('%"+keyword+"%') " +
                " and visit_nutrition_level_standard = '1'" ;

        System.out.println("SQL : selectBySearch : " + SQL);
        return eQuery(SQL);
    }
    
    private Vector eQuery(String sql) throws Exception
    {
        rs = theConnectionInf.eQuery(sql);
        vObject = new Vector();
        
        try{
            while(rs.next()) 
            {
                theNutritionLevel = new NutritionLevel();
                
                theNutritionLevel.setObjectId(rs.getString(1));  
                theNutritionLevel.f_visit_nutrition_level_id  = rs.getString(1);  
                theNutritionLevel.visit_nutrition_level_description = rs.getString(2);  
                         
                vObject.add(theNutritionLevel);
                theNutritionLevel = null;
            }
            
            rs.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            if(vObject.size() == 0)
                vObject = null;
        }
        return vObject;
    }
    
}
