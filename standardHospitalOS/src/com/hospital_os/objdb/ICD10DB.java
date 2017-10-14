/*Source file: E:\\Job\\DrugReportSep\\code\\com\\hospital_os\\control\\PrescribeDB.java*/
package com.hospital_os.objdb;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;
import java.util.*;
import java.sql.*;
public class ICD10DB
{
    public ConnectionInf theConnectionInf;
    public ICD10 dbObj;
    final public String idtable = "169";/*"158";*/
    /**
     * @param ConnectionInf db
     * @roseuid 3F65897F0326
     */
    public ICD10DB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new ICD10();
        initConfig();
    }
    
    public ICD10DB()
    {
        theConnectionInf = null;
        dbObj = new ICD10();
        initConfig();
    }
    
    public boolean initConfig()
    {
        Vector result = new Vector();
        dbObj.table="b_icd10";
        dbObj.pk_field="b_icd10_id";
        dbObj.icd10_id   ="icd10_number";
        dbObj.description   ="icd10_description";
        dbObj.other_description   ="icd10_other_description";
        dbObj.generate_code="icd10_generate_code";
        dbObj.active53="active53";
        return true;
    }
    
    /**
     * @param cmd
     * @param o
     * @return int
     * @roseuid 3F6574DE0394
     */
    public int insert(ICD10 o) throws Exception
    {
        ICD10 p=o;
        p.generateOID(idtable);
        StringBuffer sql = new StringBuffer("insert into " ).append( dbObj.table ).append( " ("
        ).append( dbObj.pk_field
        ).append( " ,"	).append( dbObj.icd10_id
        ).append( " ,"	).append( dbObj.description
        ).append( " ,"	).append( dbObj.other_description
        ).append( " ,"  ).append( dbObj.generate_code
        ).append( " ) values ('"
        ).append( p.getObjectId()
        ).append( "','" ).append( p.icd10_id
        ).append( "','" ).append( p.description
        ).append( "','" ).append( p.other_description
        ).append( "','" ).append( p.generate_code
        ).append( "')");
        return theConnectionInf.eUpdate(sql.toString());
    }

    public ICD10 selectActive53(String code) throws Exception {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.icd10_id).append( " ilike '" ).append( code ).append( "'"
        ).append( " and active53='1'");

        Vector v=eQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return (ICD10)v.get(0);
    }
    public int update(ICD10 o) throws Exception
    {
        ICD10 p=o;
        StringBuffer sql = new StringBuffer("update " ).append( dbObj.table ).append( " set "
        ).append( dbObj.icd10_id ).append( "='" ).append( p.icd10_id
        ).append( "', " ).append( dbObj.description ).append( "='" ).append( p.description
        ).append( "', " ).append( dbObj.other_description ).append( "='" ).append( p.other_description
        ).append( "', " ).append( dbObj.generate_code ).append( "='" ).append( p.generate_code
        ).append( "' where " ).append( dbObj.pk_field ).append( "='" ).append( p.getObjectId() ).append("'");
        return theConnectionInf.eUpdate(sql.toString());
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public int delete(ICD10 o) throws Exception
    {
        StringBuffer sql = new StringBuffer("delete from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.pk_field ).append( "='" ).append( o.getObjectId() ).append("'");
        return theConnectionInf.eUpdate(sql.toString());
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public ICD10 selectByPK(String pk) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.pk_field
        ).append( " = '" ).append( pk ).append( "'");
        
        Vector v=eQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return (ICD10)v.get(0);
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector selectAll() throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table);
        
        Vector v=eQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return v;
    }
    public ICD10 selectEqCode(String code) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.icd10_id).append( " = '" ).append( code ).append( "'");
        Vector v=eQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return (ICD10)v.get(0);
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public ICD10 selectByCode(String code) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
        ).append( " where upper(" ).append( dbObj.icd10_id
        ).append( ") = upper('" ).append( code ).append( "')");
        
        Vector v=eQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return (ICD10)v.get(0);
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector selectById(String pk) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
        ).append( " where UPPER(" ).append( dbObj.icd10_id
        ).append( ") like UPPER('%" ).append( pk.toUpperCase() ).append( "%') ");
        
        Vector v=eQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return v;
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public ICD10 selectByIdGroupOnly(String pk) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.icd10_id
        ).append( " ilike '" ).append( pk ).append( "'");
        
        Vector v=eQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return (ICD10)v.get(0);
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector selectByIdNameNotGroup(String pk) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table);
        
        if(pk.trim().length() !=0)
        {
            sql.append( " where" ).append( "("
            ).append( dbObj.description ).append( " ilike " ).append( "'%").append(  pk ).append( "%' or "
            ).append( dbObj.icd10_id ).append( " ilike " ).append( "'" ).append( pk ).append( "%'"
            ).append( ")" );
        }
        
        sql.append(  " order by " ).append( dbObj.icd10_id );
        
        
        Vector v=eQuery(sql.toString());
        
        if(v.size()==0)
            return null;
        else
            return v;
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector selectByIdUseGroup(String pk,String group) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table);
        
        if(pk.trim().length() !=0)
        {
            sql .append( " where"  ).append( "("
            ).append( "((" ).append( dbObj.icd10_id ).append( " ilike " ).append( "'").append( pk ).append("%'" ).append( ") or  ( upper("
            ).append( dbObj.description ).append( ") ilike " ).append( "'%" ).append( pk ).append( "%'))"
            ).append( " and ( " ).append( dbObj.generate_code ).append( " = '" ).append( group ).append( "'))" );
            
        }
        
        sql .append(  " order by " ).append( dbObj.icd10_id );
        
        
        Vector v=eQuery(sql.toString());
        
        if(v.size()==0)
            return null;
        else
            return v;
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector selectByNameUseGroup(String pk,String group) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table);
        
        if(pk.trim().length() !=0)
        {
            sql .append( " where"  ).append( "("
            ).append( "(" ).append( dbObj.description ).append( " ilike " ).append( "'%").append( pk ).append("%'" ).append( ")"
            ).append( " and ( " ).append( dbObj.generate_code ).append( " = '" ).append( group ).append( "'))" );
            
        }
        
        sql .append(  " order by " ).append( dbObj.icd10_id );
        
        
        Vector v=eQuery(sql.toString());
        
        if(v.size()==0)
            return null;
        else
            return v;
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    
    /**
     * ค้าหารหัสโรคที่อยู่ในกลุ่มของ การตั้งครรภ์ การคลอด และระยะหลังคลอด โดนจะใช้เฉพาะ
     * รหัส 'O00' ถึง 'O08'  และ 'O80' ถึง 'O84' เท่านั้น
     * จะใช้ในส่วนของ ข้อมูลการคลอด
     */
    public Vector selectByPregnant() throws Exception
    {
        StringBuffer sql = new StringBuffer( "select * from " ).append( dbObj.table
        ).append(  " where ( " ).append( dbObj.icd10_id
        ).append( " >= 'O00' and " ).append( dbObj.icd10_id
        ).append( " <= 'O08' ) or  (" ).append( dbObj.icd10_id
        ).append( " >= 'O80' and " ).append( dbObj.icd10_id
        ).append( " <= 'O84') ORDER BY " ).append( dbObj.icd10_id
        ).append( " ASC ");
        
        Vector v=veQuery(sql.toString());
        
        if(v.size()==0)
            return null;
        else
            return v;
        
    }
    public static void main(String[] argc){
        StringBuffer sb = new StringBuffer("henbe_test");
        sb.append(" test1 ");
        System.out.println(sb.toString());
        sb.append(" test2 ");
        System.out.println(sb.toString());
    }
    /** ใชได้เฉพาะ ที่ต้องการให้ค่าเป็น ICD 10*/

    /**
     *@deprecated henbe unused
     *
     */    
    public Vector veQuery(String sql) throws Exception
    {
        ComboFix p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql.toString());
        
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
    /**
     *ค้นหาเพื่อแสดงเฉพาะกลุ่ม ICD10 คือแสดงเฉพาะ ICD10 3 หลักแรก หรือแสดงเฉพาะรหัสที่มีจุดทศนิยม
    * เพิ่มเงื่อนไขการค้นหาให้ค้นจาก active53 คือรหัสใหม่ที่ใช้ได้ในปี 53 นี้เท่านั้น
     *@param code ตรวจสอบว่าแบบรหัส true ค้นหาแบบรหัส false ค้นหาแบบกลุ่มรหัส
     *@author pu 09/09/08
     */
    public Vector selectByIdGroup(String icd,String des,String odes,String group,boolean code) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table ).append( " where " );
        if(code)
            sql .append(  " length(" ).append( dbObj.icd10_id ).append( ") <> 3 ");//แสดงเฉพาะ ICD10 มากกว่า 3 หลัก : pu
        else
            sql .append(  " length(" ).append( dbObj.icd10_id ).append( ") = 3 ");//แสดงเฉพาะ ICD10 3 หลักแรก : pu
        if(!des.equals(""))
        {
            sql .append(  " and (upper(" ).append( dbObj.icd10_id ).append( ") like upper('").append( icd ).append("')");
            if(!group.equals(""))   sql .append( " and ");
            else                    sql .append( " or ");
            sql.append( " (upper("  ).append( dbObj.description ).append( ") like " ).append( " upper('" ).append( des ).append( "')" ).append(
                    " or upper("  ).append( dbObj.other_description ).append( ") like " ).append( " upper('" ).append( odes ).append( "')))");
        }
        else
           sql .append(  " and upper(" ).append( dbObj.icd10_id ).append( ") like upper('").append( icd ).append("')");
        if(!group.equals(""))
            sql .append( " and " ).append( dbObj.generate_code ).append( " = '" ).append( group ).append( "'");
        
        //ให้ค้นได้ทั้งหมด เพื่อให้ยังใช้รหัสเดิมได้ sql .append( " and active53='1' ");
        sql .append( " order by " ).append( dbObj.icd10_id );
        return eQuery(sql.toString());
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        ICD10 p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql.toString());
        int i=0;
        while(rs.next())
        {
            p = new ICD10();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.icd10_id = rs.getString(dbObj.icd10_id);
            p.description = rs.getString(dbObj.description);
            p.other_description = rs.getString(dbObj.other_description);
            p.generate_code = rs.getString(dbObj.generate_code);
            p.active53 = rs.getString(dbObj.active53);
            if(p.active53==null)
                p.active53 = "";
            list.add(p);
            i++;
            if(i>100) break;
        }
        rs.close();
        return list;
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector selectByIdName(String pk) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table);
        
        if(pk.trim().length() !=0)
        {
            sql.append( " where" ).append( "(" ).append(  dbObj.icd10_id
            ).append( " ilike '%" ).append( pk ).append( "%'" ).append( " or upper ("
            ).append( dbObj.description ).append( ") ilike '%" ).append( pk ).append( "%'" ).append( ") ");
            
        }
        
        sql.append(  " order by " ).append( dbObj.icd10_id );
        
        
        Vector v=eQuery(sql.toString());
        
        if(v.size()==0)
            return null;
        else
            return v;
    }

   /**
    * เพิ่มเงื่อนไขการค้นหาให้ค้นจาก active53 คือรหัสใหม่ที่ใช้ได้ในปี 53 นี้เท่านั้น
    * @param icd
    * @param des
    * @param odes
    * @param group
    * @return
    * @throws java.lang.Exception
    */
    public Vector selectByIdUseGroup(String icd,String des,String odes,String group) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table ).append( " where"  ).append( " upper(" ).append( dbObj.icd10_id ).append( ") like upper('").append( icd ).append("')");
        if(!des.equals("")){
            if(!group.equals(""))   sql .append( " and ");
            else                    sql .append( " or ");
            sql.append( " (upper("  ).append( dbObj.description ).append( ") like " ).append( " upper('" ).append( des ).append( "')" ).append(
            " or upper("  ).append( dbObj.other_description ).append( ") like " ).append( " upper('" ).append( odes ).append( "'))");
        }
        if(!group.equals(""))
            sql .append( " and " ).append( dbObj.generate_code ).append( " = '" ).append( group ).append( "'");

        //ให้ค้นได้ทั้งหมด เพื่อให้ยังใช้รหัสเดิมได้ sql .append( " and active53='1'");
        sql .append( " order by " ).append( dbObj.icd10_id );
        return eQuery(sql.toString());
    } 
    /*///////////////////////////////////////////////////*/
}
