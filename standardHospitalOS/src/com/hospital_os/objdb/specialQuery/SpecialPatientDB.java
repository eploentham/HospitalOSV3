/*
 * SpecialPatientDB.java
 *
 * Created on 14 สิงหาคม 2547, 13:55 น.
 */
package com.hospital_os.objdb.specialQuery;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.object.specialQuery.*;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;
import java.util.*;
import java.sql.*;
/**
 *
 * @author  tong
 */
public class SpecialPatientDB
{
    /** Creates a new instance of SpecialPatientDB */
    public ConnectionInf theConnectionInf;
    public Patient dbObj;
    public SpecialPatientDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new Patient();
        initConfig();
    }
    public boolean initConfig()
    {
        dbObj.table = "t_patient";
        dbObj.pk_field = "t_patient_id";
        dbObj.hn  = "patient_hn";
        dbObj.fname = "patient_firstname";
        dbObj.lname = "patient_lastname";
        dbObj.birthday = "patient_birthday";
        dbObj.mother_fname = "patient_mother_firstname";
        dbObj.pid = "patient_pid";
        dbObj.house = "patient_house";
        dbObj.road = "patient_road";
        dbObj.village = "patient_moo";
        dbObj.tambon = "patient_tambon";
        dbObj.ampur = "patient_amphur";
        dbObj.changwat = "patient_changwat";
        dbObj.phone = "patient_phone_number";
        dbObj.active = "patient_active";
        return true;
    }
    /*////////////////////////////////////////////////////////////////////////////
*/
    public Vector queryByFLName(String fname,String lname) throws Exception
    {
        String sql="select "
        + dbObj.pk_field + ","
        + dbObj.hn + ","
        + dbObj.fname + ","
        + dbObj.lname + ","
        + dbObj.birthday + ","
        + dbObj.mother_fname + ","
        + dbObj.pid + ","        
        + dbObj.house + ","
        + dbObj.road + ","
        + dbObj.village + ","
        + dbObj.tambon  + ","
        + dbObj.ampur  + ","
        + dbObj.changwat  + ","
        + dbObj.phone  + ","
        + dbObj.active
        + " from " + dbObj.table
        + " where "
        + dbObj.fname + " like '" + fname + "' and "
        + dbObj.lname + " like '" + lname + "' and " 
        + dbObj.active + " ='1'";
        return eQuery(sql);
    }
    /*////////////////////////////////////////////////////////////////////////////
*/
    public Vector queryByPID(String pid) throws Exception
    {
        String sql="select "
        + dbObj.pk_field + ","
        + dbObj.hn + ","
        + dbObj.fname + ","
        + dbObj.lname + ","
        + dbObj.birthday + ","
        + dbObj.mother_fname + ","
        + dbObj.pid + ","        
        + dbObj.house + ","
        + dbObj.road + ","
        + dbObj.village + ","
        + dbObj.tambon  + ","
        + dbObj.ampur  + ","
        + dbObj.changwat  + ","
        + dbObj.phone  + ","
        + dbObj.active
        + " from " + dbObj.table
        + " where "
        + dbObj.pid + " like '" + pid + "'" 
        + " and " + dbObj.active + " ='1' " ;
        
        return eQuery(sql);
    }
    /*////////////////////////////////////////////////////////////////////////////
*/
    public Vector queryByFName(String fname) throws Exception
    {
        String sql="select "
        + dbObj.pk_field + ","
        + dbObj.hn + ","
        + dbObj.fname + ","
        + dbObj.lname + ","
        + dbObj.birthday + ","
        + dbObj.mother_fname + ","
        + dbObj.pid + ","        
        + dbObj.house + ","
        + dbObj.road + ","
        + dbObj.village + ","
        + dbObj.tambon  + ","
        + dbObj.ampur  + ","
        + dbObj.changwat  + ","
        + dbObj.phone  + ","
        + dbObj.active
        + " from " + dbObj.table
        + " where "
        + dbObj.fname + " like '" + fname + "' and "
        + dbObj.active + " = '1' "
        + "order by "+ dbObj.fname;
        
        return eQuery(sql);
    }
    /*////////////////////////////////////////////////////////////////////////////
*/
    public Vector queryBySName(String lname) throws Exception
    {
        String sql="select "
        + dbObj.pk_field + ","
        + dbObj.hn + ","
        + dbObj.fname + ","
        + dbObj.lname + ","
        + dbObj.birthday + ","
        + dbObj.mother_fname + ","
        + dbObj.pid + ","        
        + dbObj.house + ","
        + dbObj.road + ","
        + dbObj.village + ","
        + dbObj.tambon  + ","
        + dbObj.ampur  + ","
        + dbObj.changwat  + ","
        + dbObj.phone  + ","
        + dbObj.active
        + " from " + dbObj.table
        + " where "
        + dbObj.lname + " like '" + lname + "' and "
        + dbObj.active + " = '1' ";
        
        return eQuery(sql);
    }
    private Vector eQuery(String sql) throws Exception
    {
        Patient p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        int i = 0;
        while(rs.next())
        {
            p = new Patient();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.hn  = rs.getString(dbObj.hn);
            p.fname = rs.getString(dbObj.fname);
            p.lname =  rs.getString(dbObj.lname);
            p.birthday =  rs.getString(dbObj.birthday);
            p.mother_fname =  rs.getString(dbObj.mother_fname);
            p.pid =  rs.getString(dbObj.pid);
            p.house =  rs.getString(dbObj.house);
            p.road =  rs.getString(dbObj.road);
            p.village =  rs.getString(dbObj.village);
            p.tambon =  rs.getString(dbObj.tambon);
            p.ampur =  rs.getString(dbObj.ampur);
            p.changwat =  rs.getString(dbObj.changwat);
            p.phone =  rs.getString(dbObj.phone);
            p.active =  rs.getString(dbObj.active);
            list.add(p);            
        }
        rs.close();
        return list;
    }
}
