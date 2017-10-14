/*
 * Rp115Group4DiseaseDB.java
 *
 * Created on 14 ¡’π“§¡ 2549, 18:27 π.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.setupreport.objectdb;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.hosv3.utility.DBPersist;
import com.hosv3.utility.StandardDB;
import com.setupreport.object.Rp115Group4Disease;
import java.sql.ResultSet;
import java.util.Vector;
/**
 *
 * @author pu
 */
public class Rp115Group4DiseaseDB extends DBPersist implements StandardDB
{
    ConnectionInf theConnectionInf;
    Rp115Group4Disease theRp115Group4Disease,ObjectRp115Group4Disease;
    String SQL = "";
    ResultSet rs = null;
    Vector vObject ;
    int count =0;
    boolean bresult = false;
    /** Creates a new instance of Rp115Group4DiseaseDB */
    public Rp115Group4DiseaseDB(ConnectionInf conf)
    {
        theConnectionInf = conf;
        theRp115Group4Disease = new Rp115Group4Disease();
        theRp115Group4Disease.setInitDataFieldName();
    }

    public Object selectByKeyword(String search) throws Exception
    {
        SQL = "SELECT * FROM " +
                " " + theRp115Group4Disease.tableName +
                " WHERE " + theRp115Group4Disease.tableName + "." + theRp115Group4Disease.rp11_disease_number + " like '%" + search + "%' " +
                " OR " + theRp115Group4Disease.tableName + "." + theRp115Group4Disease.rp11_disease_description + " like '%" + search + "%' " +
                " ORDER BY " + theRp115Group4Disease.tableName + "." + theRp115Group4Disease.rp11_disease_number;
        System.out.println("SQL theResidentAgeGroup : selectBySearch : " + SQL);
        return eQuery(SQL);
    }   
    
    public int deleteByKeyID(String key_id) throws Exception
    {
        return 0;
    }

    public int insertData(Object object) throws Exception
    {
        return 0;
    }

    public Object selectByKeyID(String key_id) throws Exception
    {
        return null;
    }

    public int updateByKeyID(String key_id) throws Exception
    {
        return 0;
    }
    
    private java.util.Vector eQuery(String sql) throws Exception
    {
        rs = theConnectionInf.eQuery(sql);
        vObject = new Vector();
        try
        {
            while(rs.next())
            {
                ObjectRp115Group4Disease = new Rp115Group4Disease();
                ObjectRp115Group4Disease.setInitData();
                
                ObjectRp115Group4Disease.setObjectId(rs.getString(theRp115Group4Disease.pk_table));                
                ObjectRp115Group4Disease.rp11_disease_number = rs.getString(theRp115Group4Disease.rp11_disease_number);
                System.out.println("---------------3333-------------" + ObjectRp115Group4Disease.rp11_disease_number);
                ObjectRp115Group4Disease.rp11_disease_description = rs.getString(theRp115Group4Disease.rp11_disease_description);
                System.out.println("-------------4444---------------" + ObjectRp115Group4Disease.rp11_disease_description);
                
                vObject.add(ObjectRp115Group4Disease);
                ObjectRp115Group4Disease = null;
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
