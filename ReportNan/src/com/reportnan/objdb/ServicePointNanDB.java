/*
 * ServicePointNanDB.java
 *
 * Created on 14 มิถุนายน 2549, 17:28 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportnan.objdb;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.hospital_os.objdb.ServicePointDB;
import com.hospital_os.object.ServicePoint;
import java.util.Vector;
import java.sql.ResultSet;
import com.reportnan.utility.ComboFix;

/**
 *
 * @author pu
 */
public class ServicePointNanDB extends ServicePointDB
{
    ConnectionInf theConnectionInf;
    Vector vServicePoint;
    String SQL;
    ComboFix theComboFix;
    ResultSet rs;
    /**
     * Creates a new instance of ServicePointNanDB 
     */
    public ServicePointNanDB(ConnectionInf conf)
    {
        theConnectionInf = conf;
    }
    
    public Vector selectServicePointAll() 
    {
        vServicePoint = new Vector();
        try
        {
            SQL ="select " + dbObj.table +"."+dbObj.pk_field + ", "
                    + dbObj.table +"."+dbObj.name +" from " + dbObj.table ;
            SQL = SQL +  " order by "+ dbObj.name;
            vServicePoint = evQuery(SQL);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return vServicePoint;
    }  
    
    
    
    private Vector evQuery(String sql) throws Exception
    {
        ComboFix theComboFix;
        Vector vData = new Vector();
        
        rs = theConnectionInf.eQuery(sql);
        
        while(rs.next())
        {
            theComboFix = new ComboFix();
            
            theComboFix.code = rs.getString(1);
            theComboFix.name = rs.getString(2);
            
            vData.add(theComboFix);
        }        
        return vData;
    }
        /**Select ข้อมูลที่อยู่ทั้งหมดในตาราง*/
    public Object selectBySearch(String search,String active) throws Exception
    {
        SQL = "SELECT * FROM " +
                " " + dbObj.table +   
                " WHERE ((UPPER(" + dbObj.table + "." + dbObj.service_point_id + ") like UPPER('%" + search + "%') " +
                " OR UPPER("  + dbObj.table + "." + dbObj.name + ") like UPPER('%" + search + "%') " +
                " OR UPPER("  + dbObj.table + "." + dbObj.service_type_id + ") like UPPER('%" + search + "%') " +
                " OR UPPER("  + dbObj.table + "." + dbObj.service_sub_type_id + ") like UPPER('%" + search + "%')) " +
                
                " AND " + dbObj.table + "." + dbObj.active + " = '" + active + "') " +
                " ORDER BY " + dbObj.table + "." + dbObj.service_point_id;
        System.out.println("SQL PlanGroup : selectByKeyID : " + SQL);
        return eQuery(SQL);
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        ServicePoint p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new ServicePoint();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.service_point_id = rs.getString(dbObj.service_point_id);
            p.name = rs.getString(dbObj.name);
            p.service_type_id = rs.getString(dbObj.service_type_id);
            p.service_sub_type_id = rs.getString(dbObj.service_sub_type_id);
            p.active = rs.getString(dbObj.active);
            list.add(p);
        }
        rs.close();
        return list;
    }

}
