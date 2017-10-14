/*Source file: E:\\Job\\DrugReportSep\\code\\com\\hospital_os\\control\\PrescribeDB.java*/
package com.setupreport.objectdb;
import com.hospital_os.usecase.connection.*;
//import com.hospital_os.utility.*;+
import com.setupreport.object.*;
import com.setupreport.utility.Language;
import com.hosv3.utility.DBPersist;
import com.hosv3.utility.StandardDB;
import java.util.*;
import java.sql.*;

public class ICD10DB extends DBPersist implements StandardDB 
{
    public ConnectionInf theConnectionInf;
    public ICD10 theICD10 ,ObjectICD10;
    String SQL = "";
    ResultSet rs = null;
    java.util.Vector vObject ;
    int count =0;
    boolean bresult = false;

    /**
     * @param ConnectionInf db
     * @roseuid 3F65897F0326
     */
    public ICD10DB(ConnectionInf db)
    {
        theConnectionInf=db;
        
        theICD10 = new ICD10();
        theICD10.setInitDataFieldName();
    }
    
    /**
     * @param cmd
     * @param o
     * @return int
     * @roseuid 3F6574DE0394
     */
 /*   public int insert(ICD10 o) throws Exception
    {
        String sql="";
        ICD10 p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"	+ dbObj.icd10_id
        + " ,"	+ dbObj.description
        + " ,"	+ dbObj.other_description
        + " ,"  + dbObj.generate_code
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.icd10_id
        + "','" + p.description
        + "','" + p.other_description
        + "','" + p.generate_code
        + "')";
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
  */
  /*  public int update(ICD10 o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        ICD10 p=o;
        String field =""
        + "', " + dbObj.icd10_id + "='" + p.icd10_id
        + "', " + dbObj.description + "='" + p.description
        + "', " + dbObj.other_description + "='" + p.other_description
        + "', " + dbObj.generate_code + "='" + p.generate_code
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
   */
    /*////////////////////////////////////////////////////////////////////////////*/
   /* public int delete(ICD10 o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    **/
    /*////////////////////////////////////////////////////////////////////////////*/
/*    public ICD10 selectByPK(String pk) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.pk_field
        + " = '" + pk + "'";
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (ICD10)v.get(0);
    }
 */
    /*////////////////////////////////////////////////////////////////////////////*/
 /*   public Vector selectAll() throws Exception
    {
        String sql="select * from " + dbObj.table;
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
  */
    /*////////////////////////////////////////////////////////////////////////////*/
  /*  public ICD10 selectByCode(String code) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.icd10_id
        + " = '" + code + "'";
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (ICD10)v.get(0);
    }
   **/
    /*////////////////////////////////////////////////////////////////////////////*/
  /*  public Vector selectById(String pk) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where UPPER(" + dbObj.icd10_id
        + ") like UPPER('%" + pk.toUpperCase() + "%') ";
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
   */
    /*////////////////////////////////////////////////////////////////////////////*/
  /*  public ICD10 selectByIdGroupOnly(String pk) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.icd10_id
        + " like '" + pk + "'";
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (ICD10)v.get(0);
    }
   **/
    /*////////////////////////////////////////////////////////////////////////////*/
  /*  public Vector selectByIdNameNotGroup(String pk) throws Exception
    {
        String sql="select * from " + dbObj.table;
        
        if(pk.trim().length() !=0)
        {
            sql = sql + " where" + "("
            + dbObj.description + " like " + "'%"+  pk + "%' or "
            + dbObj.icd10_id + " like " + "'" + pk + "%'"
            + ")" ;
        }
        
        sql = sql +  " order by " + dbObj.icd10_id ;
        
        
        Vector v=eQuery(sql);
        
        if(v.size()==0)
            return null;
        else
            return v;
    }
   */
    /*////////////////////////////////////////////////////////////////////////////*/
   /* public Vector selectByIdUseGroup(String pk,String group) throws Exception
    {
        String sql="select * from " + dbObj.table;
        
        if(pk.trim().length() !=0)
        {
            sql = sql + " where"  + "("
            + "((" + dbObj.icd10_id + " like " + "'"+ pk +"%'" + ") or  ( upper("
            + dbObj.description + ") like " + "'%" + pk + "%'))"
            + " and ( " + dbObj.generate_code + " = " + "'" + group + "'" + ")" + ")" ;
            
        }
        
        sql = sql +  " order by " + dbObj.icd10_id ;
        
        
        Vector v=eQuery(sql);
        
        if(v.size()==0)
            return null;
        else
            return v;
    }
    **/
    /*////////////////////////////////////////////////////////////////////////////*/
   /** public Vector selectByNameUseGroup(String pk,String group) throws Exception
    {
        String sql="select * from " + dbObj.table;
        
        if(pk.trim().length() !=0)
        {
            sql = sql + " where"  + "("
            + "(" + dbObj.description + " like " + "'%"+ pk +"%'" + ")"
            + " and ( " + dbObj.generate_code + " = " + "'" + group + "'" + ")" + ")" ;
            
        }
        
        sql = sql +  " order by " + dbObj.icd10_id ;
        
        
        Vector v=eQuery(sql);
        
        if(v.size()==0)
            return null;
        else
            return v;
    }
    **/
    /*////////////////////////////////////////////////////////////////////////////*/
    
    /**
     * ค้าหารหัสโรคที่อยู่ในกลุ่มของ การตั้งครรภ์ การคลอด และระยะหลังคลอด โดนจะใช้เฉพาะ
     * รหัส 'O00' ถึง 'O08'  และ 'O80' ถึง 'O84' เท่านั้น
     * จะใช้ในส่วนของ ข้อมูลการคลอด
     */
 /*   public Vector selectByPregnant() throws Exception
    {
        String sql = "select * from " + dbObj.table
        +  " where ( " + dbObj.icd10_id
        + " >= 'O00' and " + dbObj.icd10_id
        + " <= 'O08' ) or  (" + dbObj.icd10_id
        + " >= 'O80' and " + dbObj.icd10_id
        + " <= 'O84') ORDER BY " + dbObj.icd10_id
        + " ASC ";
        
        Vector v=veQuery(sql);
        
        if(v.size()==0)
            return null;
        else
            return v;
        
    }
  **/
    /** ใชได้เฉพาะ ที่ต้องการให้ค่าเป็น ICD 10*/
    /*public Vector veQuery(String sql) throws Exception
    {
        ComboFix p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        
        while(rs.next())
        {
            p = new ComboFix();
            p.code = rs.getString(dbObj.pk_field);
            p.name = rs.getString(dbObj.icd10_id) + "  " + rs.getString(dbObj.description);
            list.add(p);
        }
        rs.close();
        return list;
    }
     **/
    
    
    /*public Vector eQuery(String sql) throws Exception
    {
        ICD10 p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        int i=0;
        while(rs.next())
        {
            p = new ICD10();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.icd10_id = rs.getString(dbObj.icd10_id);
            p.description = rs.getString(dbObj.description);
            p.other_description = rs.getString(dbObj.other_description);
            p.generate_code = rs.getString(dbObj.generate_code);
            list.add(p);
            i++;
            if(i>100) break;
        }
        rs.close();
        return list;
    }
     **/
    /*////////////////////////////////////////////////////////////////////////////*/
   /* public Vector selectByIdName(String pk) throws Exception
    {
        String sql="select * from " + dbObj.table;
        
        if(pk.trim().length() !=0)
        {
            sql = sql + " where" + "(" +  dbObj.icd10_id
            + " like '%" + pk + "%'" + " or upper ("
            + dbObj.description + ") like '%" + pk + "%'" + ") ";
            
        }
        
        sql = sql +  " order by " + dbObj.icd10_id ;
        
        
        Vector v=eQuery(sql);
        
        if(v.size()==0)
            return null;
        else
            return v;
    }
    **/
    /*///////////////////////////////////////////////////*/
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
    /*Select รหัสโรคต้องการค้นหา
     *@param search รหัสโรค
     *@return Object เป็นรหัสโรคที่ต้องการค้นหา 
     */
    public Object selectBySearch(String search) throws Exception
    {
        SQL = "SELECT * FROM " +
                " " + theICD10.tableName +
                " WHERE " + theICD10.tableName + "." + theICD10.icd10_number + " = '" + search + "' " +
                " ORDER BY " + theICD10.tableName + "." + theICD10.icd10_number ;
        System.out.println("SQL ICD10 : selectByKeyID : " + SQL);
        return eQuery(SQL);
    }
    
    public boolean checkHaveICD10(String icd10code,String key_id)
    {   count =0;
        bresult = true;
        
        if(key_id.equalsIgnoreCase(""))
        {
            SQL = "SELECT COUNT( " +
                theICD10.tableName + "." + theICD10.pk_table  +
                ")"   +
                " FROM " + theICD10.tableName +
                " WHERE " +
                " UPPER(" + theICD10.tableName + "." + theICD10.icd10_number + ")=UPPER('" + icd10code + "') ";
        }
        else
        {
            SQL = "SELECT COUNT( " +
                theICD10.tableName + "." + theICD10.pk_table  +
                ")"   +
                " FROM " + theICD10.tableName +
                " WHERE " +
                " (UPPER(" + theICD10.tableName + "." + theICD10.icd10_number + ")=UPPER('" + icd10code + "')) " +
                " AND " + theICD10.tableName + "." + theICD10.pk_table  + " <> " + key_id ;
        }
        
        System.out.println("SQL EyeGroup : checkSameCode : " + SQL);
        
        try
        {
            rs = theConnectionInf.eQuery(SQL);
            while(rs.next())
            {
                count = rs.getInt(1);
            }
            rs.close();
        }
        catch(Exception ex)
        {
        }
        finally
        {
            if(count >0)
            {
                bresult = false;
            }  
        }        
        return bresult;
    }
    
    public int updateByKeyID(String key_id)
    {
        return 0;
    }
    
    private java.util.Vector eQuery(String sql) throws Exception
    {
        rs = theConnectionInf.eQuery(sql);
        vObject = new java.util.Vector();
        ObjectICD10 = new ICD10();
        try
        {
            while(rs.next())
            {
                ObjectICD10 = new ICD10();
                ObjectICD10.setInitData();
                ObjectICD10.setObjectId(rs.getString(theICD10.pk_table));
                ObjectICD10.icd10_number = rs.getString(theICD10.icd10_number);
                ObjectICD10.icd10_description = rs.getString(theICD10.icd10_description);
                ObjectICD10.icd10_other_description = rs.getString(theICD10.icd10_other_description);
                ObjectICD10.icd10_generate_code = rs.getString(theICD10.icd10_generate_code);
                
                vObject.add(ObjectICD10);
                ObjectICD10 = null;
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
