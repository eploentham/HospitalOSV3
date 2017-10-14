/*
 * NutritionTypeDB.java
 *
 * Created on 16 �չҤ� 2549, 14:45 �.
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
public class NutritionTypeDB
{
    
    /** Creates a new instance of NutritionTypeDB */
    ConnectionInf theConnectionInf;
    String SQL = "";
    ResultSet rs = null;
    Vector vObject ;
    NutritionType theNutritionType;
    
    public NutritionTypeDB(ConnectionInf connectionInf)
    {
        theConnectionInf = connectionInf;
        theNutritionType = new NutritionType();
    }
    
    /**
     *  ����Ѻ���Ҫ�Դ�ͧ��������ҡ�� �ҡ�Ӥ鹷������
     *  @Param keyword �� ����âͧ�Ӥ� �¨й�令��Ҩҡ��������´
     *  @Author Ojika
     *  @Date 16/03/2549
     **/
    public Vector selectByKeyword(String keyword) throws Exception
    {
        SQL = " SELECT * FROM r_nutrition_type " +
                " WHERE UPPER(nutrition_type_number) like UPPER('%"+ keyword  +"%') " +
                " OR UPPER(nutrition_type_description) like UPPER('%"+ keyword  +"%') ";
        
        return eQuery(SQL);
    }
    
    private Vector eQuery(String sql) throws Exception
    {
        rs = theConnectionInf.eQuery(sql);
        vObject = new Vector();
        try{
            while(rs.next()) 
            {
                theNutritionType = new NutritionType();
                
                theNutritionType.setObjectId(rs.getString(1));
                theNutritionType.r_nutrition_type_id = rs.getString(1);
                theNutritionType.nutrition_type_number = rs.getString(2);
                theNutritionType.nutrition_type_description = rs.getString(3);
                
                vObject.add(theNutritionType);
                theNutritionType = null;
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
