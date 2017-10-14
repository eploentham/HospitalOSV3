/*
 * EpiAgeGroupTypeDB.java
 *
 * Created on 29 มีนาคม 2549, 12:03 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.setupreport.objectdb;

import com.hospital_os.usecase.connection.ConnectionInf;
import com.setupreport.object.*;
import com.setupreport.utility.ComboFix;
import java.util.Vector;
import java.sql.*;
/**
 *
 * @author Ojika
 */
public class EpiAgeGroupTypeDB
{
    
    /** Creates a new instance of HealthyGroup */
    ConnectionInf theConnectionInf;
    String SQL = "";
    ResultSet rs = null;
    Vector vObject ;
    
    EpiAgeGroupType theEpiAgeGroupType;
    
    public EpiAgeGroupTypeDB(ConnectionInf connectionInf)
    {
        theConnectionInf = connectionInf;
    }    
    
    /**
     *  สำหรับค้นหา ประเภทของช่วงอายุ เพื่อเซ็ทเป็น Combobox
     *  @Author Ojika
     *  @Date 29/03/2549
     **/
    public Vector selectAll() throws Exception
    {
        SQL = " SELECT r_epi_age_group_type_id, epi_age_group_type_description " +
                " FROM r_epi_age_group_type ";
        
        return eComboQuery(SQL);
    }    
    
    public EpiAgeGroupType selectByPk(String pk) throws Exception
    {
        SQL = " SELECT * " +
                " FROM r_epi_age_group_type " +
                " WHERE r_epi_age_group_type_id = '"+pk+"' ";
        
        return eQuery(SQL);
    }
    
    private EpiAgeGroupType eQuery(String sql) throws Exception
    {
        EpiAgeGroupType theEpiAgeGroupType = new EpiAgeGroupType();
        
        rs = theConnectionInf.eQuery(sql);
        vObject = new Vector();
        try{
            while(rs.next()) 
            {
                theEpiAgeGroupType = new EpiAgeGroupType();
                
                theEpiAgeGroupType.setObjectId(rs.getString(1));
                
                theEpiAgeGroupType.r_epi_age_group_type_id = rs.getString(1);
                theEpiAgeGroupType.epi_age_group_type_number = rs.getString(2);
                theEpiAgeGroupType.epi_age_group_type_description = rs.getString(3);
                
                vObject.add(theEpiAgeGroupType);
                theEpiAgeGroupType = null;
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
                theEpiAgeGroupType = null;
        }
        
        if(vObject != null)
        {
           theEpiAgeGroupType = (EpiAgeGroupType)vObject.get(0);            
        }
        
        return theEpiAgeGroupType;
    }
    
    private Vector eComboQuery(String SQL) throws Exception
    {
        ComboFix theComboFix;
        Vector vEpiAgeGroupType = new Vector();
        
        rs = theConnectionInf.eQuery(SQL);
        
        while(rs.next())
        {
            theComboFix = new ComboFix(); 
            
            theComboFix.code = rs.getString(1);
            theComboFix.name = rs.getString(2);
            
            vEpiAgeGroupType.add(theComboFix);
        }    
        
        return vEpiAgeGroupType;
    }
}
