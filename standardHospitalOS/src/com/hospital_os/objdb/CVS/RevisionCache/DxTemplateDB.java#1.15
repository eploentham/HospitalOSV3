/*Source file: E:\\Job\\DrugReportSep\\code\\com\\hospital_os\\control\\PrescribeDB.java*/
package com.hospital_os.objdb;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;
import java.util.*;
import java.sql.*;
public class DxTemplateDB
{
    public ConnectionInf theConnectionInf;
    public DxTemplate dbObj;
    final public String idtable = "154";/*"146";*/
    /**
     * @param ConnectionInf db
     * @roseuid 3F65897F0326
     */
    public DxTemplateDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new DxTemplate();
        initConfig();
    }
    public DxTemplateDB()
    {
        theConnectionInf = null;
        dbObj = new DxTemplate();
        initConfig();
    }
 
    /*////////////////////////////////////////////////////////////////////////////*/
    
    public boolean initConfig()
    {
        
        dbObj.table="b_template_dx";
        dbObj.pk_field="b_template_dx_id";
        dbObj.description   ="template_dx_description";
        dbObj.icd_type   ="template_dx_icd_type";
        dbObj.icd_code   ="b_icd10_number";
        dbObj.guide_after_dx="template_dx_guideafterdx";
        dbObj.thaidescription = "template_dx_thaidescription"; 
        dbObj.clinic_code = "b_visit_clinic_id";
        //dbObj.setUseICD10("visit_diag_map_icd");
        return true;
    }
    
  
    public Vector selectByVid(String vid) throws Exception{
        StringBuffer sql = new StringBuffer( "select * from t_visit_diag_map,b_template_dx " ).append(
        		" where t_visit_diag_map.b_template_dx_id= b_template_dx.b_template_dx_id" ).append(
        		" and t_visit_diag_map.t_visit_id = '").append( vid ).append("'" ).append(
                        " and t_visit_diag_map.visit_diag_map_active = '1' limit 10");
        return this.eQuery(sql.toString());
    }
    public DxTemplate readByDes(String des)throws Exception
    {
        DxTemplate dxTemplate = null;
        String sql = "select * from "+dbObj.table +" where "+dbObj.description+" like '%"+des+"%' limit 1";
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (DxTemplate)v.get(0);

    }
    /**
     * @param cmd
     * @param o
     * @return int
     * @roseuid 3F6574DE0394
     */
    
    public int insert(DxTemplate o) throws Exception
    {
        DxTemplate p=o;
        p.generateOID(idtable);
        StringBuffer sql = new StringBuffer("insert into " ).append( dbObj.table ).append( " ("
        ).append( dbObj.pk_field
        ).append( " ,"	).append( dbObj.description
        ).append( " ,"  ).append( dbObj.icd_type
        ).append( " ,"  ).append( dbObj.icd_code
        ).append( " ,"  ).append( dbObj.guide_after_dx
        ).append( " ,"	).append( dbObj.thaidescription
        ).append( " ,"  ).append( dbObj.clinic_code
        ).append( " ) values ('"
        ).append( p.getObjectId()
        ).append( "','" ).append( p.description
        ).append( "','" ).append( p.icd_type
        ).append( "','" ).append( p.icd_code.toUpperCase()
        ).append( "','" ).append( p.guide_after_dx
        ).append( "','"	).append( p.thaidescription
        ).append( "','" ).append( p.clinic_code
        ).append( "')");
        return theConnectionInf.eUpdate(sql.toString());
    }
    /*////////////////////////////////////////////////////////////////////////////////////////////////////*/
    public int update(DxTemplate o) throws Exception
    {
        DxTemplate p=o;
        StringBuffer sql = new StringBuffer("update " ).append( dbObj.table ).append( " set "
        ).append( dbObj.description ).append( "='" ).append( p.description
        ).append( "', " ).append( dbObj.icd_type ).append( "='" ).append( p.icd_type
        ).append( "', " ).append( dbObj.icd_code ).append( "='" ).append( p.icd_code.toUpperCase()
        ).append( "', " ).append( dbObj.guide_after_dx ).append( "='" ).append( p.guide_after_dx
        ).append( "', " ).append( dbObj.thaidescription ).append( "='" ).append( p.thaidescription
        ).append( "', " ).append( dbObj.clinic_code ).append( "='" ).append( p.clinic_code
        ).append( "' where " ).append( dbObj.pk_field ).append( "='" ).append( p.getObjectId() ).append("'");
        return theConnectionInf.eUpdate(sql.toString());
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public int delete(DxTemplate o) throws Exception
    {
        StringBuffer sql = new StringBuffer("delete from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.pk_field ).append( "='" ).append( o.getObjectId() ).append("'");
        
        return theConnectionInf.eUpdate(sql.toString());
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public DxTemplate selectByPK(String pk) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.pk_field
        ).append( " = '" ).append( pk ).append( "'");
        
        Vector v=eQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return (DxTemplate)v.get(0);
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector selectAllByName(String pk) throws Exception
    {
        pk = Gutil.CheckReservedWords(pk);
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table);
        
        if(pk.trim().length() !=0)
        {
            sql .append( " where" ).append( "( UPPER(" ).append(  dbObj.pk_field
            ).append( ") like UPPER('" ).append( pk.toUpperCase() ).append( "')" ).append( " or UPPER("
            ).append( dbObj.description ).append( ") like UPPER('" ).append( pk.toUpperCase() ).append( "')" ).append( ") ");
            
        }
        
        sql .append(  " order by " ).append( dbObj.description );
        
        return eQuery(sql.toString());
    }
    
    /*////////////////////////////////////////////////////////////////////////////*/
    public DxTemplate selectByName(String pk) throws Exception
    {
        pk = Gutil.CheckReservedWords(pk);
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table ).append(
                   " where upper(" ).append(  dbObj.description ).append(
                   ") like upper('" ).append( pk ).append( "')");
        
 
        Vector v=eQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return (DxTemplate)v.get(0);
    }
    
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector selectAll() throws Exception
    {   Vector vc = new Vector();
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table ).append( " order by ").append(
        dbObj.description);
        vc = veQuery(sql.toString());
        if(vc.size()==0)
            return null;
        else
            return vc;
        
    }
    
    /*////////////////////////////////////////////////////////////////////////////*/
    
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
            p.name = rs.getString(dbObj.description);
            list.add(p);
        }
        rs.close();
        return list;
    }
    
    /**
     * @author henbe
     */
////////////////////////////////////////////////////////////////////////////*/
    public Vector eQuery(String sql) throws Exception
    {
        DxTemplate p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql.toString());
        while(rs.next())
        {
            p = new DxTemplate();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.description = rs.getString(dbObj.description);
            p.icd_type = rs.getString(dbObj.icd_type);
            p.icd_code = rs.getString(dbObj.icd_code);
            p.guide_after_dx = rs.getString(dbObj.guide_after_dx);
            p.thaidescription = rs.getString(dbObj.thaidescription);
            p.clinic_code = rs.getString(dbObj.clinic_code);
            //p.setUseICD10(rs.getString(dbObj.getUseICD10()));
            list.add(p);
        }
        rs.close();
        return list;
    }
    /**
     * @deprecated henbe ชื่อต้องขึ้นต้นด้วยคำว่า select กรณีที่เป็นการดึง่ข้อมูล
     * 
     */
    ////////////////////////////////////////////////////////////////////////////*/
    public Vector queryDxTemplate(String dx) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
        ).append( " where "
        ).append( dbObj.pk_field ).append( " = '" ).append( dx ).append( "'");
        return eQuery(sql.toString());
    }
    /*////////////////////////////////////////////////////////////////////////////*/    
}
