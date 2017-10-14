/*
 * NutritionMapDB.java
 *
 * Created on 16 มีนาคม 2549, 14:44 น.
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
public class NutritionMapDB
{
    
    /** Creates a new instance of NutritionDB */
    ConnectionInf theConnectionInf;
    String SQL = "";
    ResultSet rs = null;
    Vector vObject ;
    NutritionMap theNutritionMap;
    
    public NutritionMapDB(ConnectionInf connectionInf)
    {
        theConnectionInf = connectionInf;
        theNutritionMap = new NutritionMap();
    }
    
    /**
     *  สำหรับลบรายการ Map ภาวะโภชนาการ
     *  @Author Ojika
     *  @Date 16/03/2549
     **/
    public int deleteByKeyID(String key_id) throws Exception
    {
        SQL = " DELETE FROM r_nutrition_map WHERE r_nutrition_map_id = '"+ key_id +"'";
        
        return theConnectionInf.eUpdate(SQL);
    }

    /**
     *  สำหรับบันทึกรายการ Map ภาวะโภชนาการ
     *  @Author Ojika
     *  @Date 16/03/2549
     **/
    public int insertData(Object object) throws Exception
    {
        theNutritionMap = (NutritionMap)object;
        theNutritionMap.generateOID(theNutritionMap.idTable);
        
        SQL = " INSERT INTO r_nutrition_map (" +
                "r_nutrition_map_id " +
                ",f_visit_nutrition_level_id " +
                ",nutrition_map_description " +
                ",r_nutrition_type_id " +
                ") values (" +
                "'" + theNutritionMap.getObjectId() + "' " +
                ",'" + theNutritionMap.f_visit_nutrition_level_id + "' " +
                ",'" + theNutritionMap.nutrition_map_description + "' " +
                ",'" + theNutritionMap.r_nutrition_type_id + "' " +
                ")";
        
        return theConnectionInf.eUpdate(SQL);
    }

    /**
     *  สำหรับค้นหารายการ Map ภาวะโภชนาการ จากตำค้นที่ส่งมา
     *  @Param nutritionTypeId เป็น รหัสของกลุ่มชนิดภาวะโภชนาการ
     *  @Author Ojika
     *  @Date 16/03/2549
     **/
    public Vector selectByNutritionTypeId(String nutritionTypeId) throws Exception
    {
        SQL = " SELECT * FROM r_nutrition_map " +
                " WHERE r_nutrition_type_id like '%"+ nutritionTypeId  +"%' ";
        
        return eQuery(SQL);
    }
    
    private java.util.Vector eQuery(String sql) throws Exception
    {
        rs = theConnectionInf.eQuery(sql);
        vObject = new Vector();
        try{
            while(rs.next()) 
            {
                theNutritionMap = new NutritionMap();
                
                theNutritionMap.setObjectId(rs.getString(1));
                theNutritionMap.r_nutrition_map_id = rs.getString(1);
                
                theNutritionMap.f_visit_nutrition_level_id = rs.getString(2);
                theNutritionMap.nutrition_map_description = rs.getString(3);
                theNutritionMap.r_nutrition_type_id = rs.getString(4);
                
                vObject.add(theNutritionMap);
                theNutritionMap = null;
            }
            
            rs.close();
        }
        catch(Exception ex)
        {
        }
        finally
        {
            if(vObject.size() == 0)
                vObject = null;
        }
        return vObject;
    }
    
}
