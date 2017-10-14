/*
 * HealthySurveyDB.java
 *
 * Created on 9 ¡’π“§¡ 2549, 15:30 π.
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
public class HealthySurveyDB
{
    
    /** Creates a new instance of HealthySurveyDB */
    ConnectionInf theConnectionInf;
    String SQL = "";
    ResultSet rs = null;
    Vector vObject ;
    HealthySurvey theHealthySurvey;
    
    public HealthySurveyDB(ConnectionInf connectionInf)
    {
        theConnectionInf = connectionInf;
        theHealthySurvey = new HealthySurvey();
    }
    
    public Vector selectByKeyword(String keyword) throws Exception
    {
        SQL = " select " +
                " b_health_survey_id " +
                " ,health_survey_description " +
                " from " +
                " b_health_survey " +
                " where " +
                " UPPER(health_survey_description)like '%"+keyword+"%' " +
                " and health_survey_active = '1' " ;

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
                theHealthySurvey = new HealthySurvey();
                
                theHealthySurvey.setObjectId(rs.getString(1));  
                theHealthySurvey.b_health_survey_id  = rs.getString(1);  
                theHealthySurvey.health_survey_desceiption = rs.getString(2);  
                         
                vObject.add(theHealthySurvey);
                theHealthySurvey = null;
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
