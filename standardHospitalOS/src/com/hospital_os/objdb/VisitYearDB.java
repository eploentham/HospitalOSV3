/*
 * VisitYear.java
 *
 * Created on 10 พฤษภาคม 2548, 14:23 น.
 */

package com.hospital_os.objdb;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;
import com.hospital_os.*;
import java.util.*;
import java.sql.*;
/**
 * เป็นตารางที่เพิ่มขึ้นมาใหม่ในการเป็น ปีพศ. เพื่อใช้ในการตรวจสอบ เลข VN
 * @author tong
 * @ since V2.0 b8
 */
public class VisitYearDB {
    
    /** Creates a new instance of VisitYear */
    public ConnectionInf theConnectionInf;
    private static VisitYear dbObj;
    private static VisitYear Obj;
    final public String idtable = "273";/*"218";
*/
    private static String SQL = "";
    private static Vector vVisitYear;
    public VisitYearDB(ConnectionInf db) {
        theConnectionInf=db;
        dbObj = new VisitYear();
        vVisitYear = new Vector();
        initConfig();
    }
    
    public Object initConfig()
    {
        
        
        dbObj.table="t_visit_year";
        
        dbObj.pk_field="t_visit_year_id";
        dbObj.visit_year = "visit_year";
        
        return dbObj;
    }
    
    public boolean createTable() 
    {   boolean result = false;
        try{
            SQL = "CREATE TABLE t_visit_year (" +
                    "t_visit_year_id character varying(255) NOT NULL," +
                    "visit_year character varying(255));";
            this.theConnectionInf.eUpdate(SQL);
            result = true;
        
        }
        catch(Exception ex)
        {
           
        }
        
        try{
            SQL = "ALTER TABLE ONLY t_visit_year ADD CONSTRAINT t_visit_year_id PRIMARY KEY (t_visit_year_id);";
            this.theConnectionInf.eUpdate(SQL);
        
        }
        catch(Exception ex)
        {
           
        }
        try{
            SQL = "CREATE INDEX visit_year ON t_visit_year USING btree (visit_year);";
            this.theConnectionInf.eUpdate(SQL);
        
        }
        catch(Exception ex)
        {
           
        }
        
        return result;
    }
    
    public int insert(VisitYear visityear) throws Exception
    {   visityear.generateOID(idtable);
        SQL = "INSERT INTO " + dbObj.table + "(" +
                "" + dbObj.pk_field +
                "," + dbObj.visit_year + 
                ") " + 
                " VALUES(" +
                "'" + visityear.getObjectId() + 
                "','" + visityear.visit_year +
                "')";
        
       return this.theConnectionInf.eUpdate(SQL);         
             
    }
    
    public int updateCurrenctYear(String year) throws Exception
    {
        SQL = "UPDATE " + dbObj.table + " SET " +" " + dbObj.visit_year + " ='" +  year + "'" ;
        return this.theConnectionInf.eUpdate(SQL);
    }
    
    public String selectCurrenctYear() throws Exception
    {
        SQL = "SELECT * FROM " + dbObj.table + " ORDER BY " + dbObj.visit_year + " DESC";
        
        vVisitYear = vQuery(SQL);
        if(vVisitYear!=null)
        {
            if(vVisitYear.size() >0)
            {
                return ((VisitYear)vVisitYear.get(0)).visit_year;
            }
            else
            {
                return null;
            }
        }
        else
        {
            return null;
        }
        
        
    }
    /**
     *  ใช้ในการ แปลงข้อมูลลง Vector
     * @author  tong
     * @since V2.0 b8
     * @return Vector ของ Object VisitYear
     */
    private Vector vQuery(String sql) throws Exception
    {
        if(vVisitYear == null)
            vVisitYear = new Vector();
        vVisitYear.clear();
        
        ResultSet rs = theConnectionInf.eQuery(sql);
        Obj = new VisitYear();
        while(rs.next())
        {
            Obj = new VisitYear();
            Obj.setObjectId(rs.getString(dbObj.pk_field));
            Obj.visit_year = rs.getString(dbObj.visit_year);
         
            vVisitYear.add(Obj);
            Obj = null;;
        }
        
        return vVisitYear;
        
        
    }
}
