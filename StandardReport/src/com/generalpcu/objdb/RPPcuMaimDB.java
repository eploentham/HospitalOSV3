/*
 * RPPcuMaimDB.java
 *
 * Created on 27 มีนาคม 2549, 14:53 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.generalpcu.objdb;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.generalpcu.utility.Language;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;
/**
 *
 * @author pu
 */
public class RPPcuMaimDB
{
    public ConnectionInf theConnectionInf;
    String SQL;
    Vector vc;
    ResultSet rs = null;
    ResultSetMetaData metadata;
    Vector vPateintInservicePoint ;
    Vector vString;
    Vector vData;
    
    /** Creates a new instance of RPPcuMaimDB */
    public RPPcuMaimDB(ConnectionInf conf)
    {
        theConnectionInf = conf;
        
    }
    
    /**
     *รายชื่อผู้พิการ(จำแนกตามความพิการ)
     *@param startdate เป็น String วันที่เริ่มต้นในการดึงข้อมูล
     *@param enddate เป็น String วันที่สิ้นสุดในการดึงข้อมูล
     *@param village_id เป็น String รหัสหมู่บ้านที่ต้องการค้นหา
     *@param maimtype_id เป็น String รหัสกลุ่มความพิการ
     *@param treatstr เป็น String รหัสสถานะการบำบัดรักษา
     *@param registrystr เป็น String รหัสสถานะการขึ้นทะเบียนผู้พิการ
     *@return Vector ของข้อมูลรายชื่อผู้พิการ(จำแนกตามความพิการ)
     *@Date 27/03/2006
     *@Author pu
     */
    public Vector queryPersonMaimByDate(String village_id,String maimtype_id,String treatstr,String registrystr,String startdate,String enddate)
    {
        SQL = "SELECT t_health_village.village_moo AS MOO " +
                ", t_health_village.village_name AS village_name " +
                ", b_health_maim.health_maim_description AS maimtype " +
                ", f_patient_prefix.patient_prefix_description || t_health_family.patient_name AS firstname " +
                ", t_health_family.patient_last_name AS lastname " +
                ", t_health_home.health_home_house AS house " +
                ", CASE WHEN (to_number(substring(' ' || age(to_date('"+ startdate +"','YYYY-MM-DD') " +
                "       ,to_date(t_health_family.patient_birthday,'YYYY-MM-DD'))  from '(...)year'),'999') IS NOT NULL) " +
                "  THEN trim(substring(' ' || age(to_date('"+ startdate +"','YYYY-MM-DD') " +
                "       ,to_date(t_health_family.patient_birthday,'YYYY-MM-DD'))  from '(...)year')) " +
                "  ELSE '0' END AS year " +
                ", CASE WHEN t_health_maim.health_maim_treat = '1' " +
                "  THEN 'treated' " +
                "  ELSE 'non-treat' END AS maimtreat " +
                ", CASE WHEN t_health_maim.health_maim_registry = '1' " +
                "  THEN 'registered' " +
                "  ELSE 'non-register' END AS maimregistry " +
                "FROM " +
                "t_health_village INNER JOIN t_health_home " +
                "	ON (t_health_home.t_health_village_id = t_health_village.t_health_village_id " +
				"			AND  t_health_village.village_moo <> '0') " +
                "       	INNER JOIN   t_health_family " +
                "	ON t_health_family.t_health_home_id = t_health_home.t_health_home_id " +
                "		INNER JOIN f_patient_prefix " +
                "	ON f_patient_prefix.f_patient_prefix_id = t_health_family.f_prefix_id " +
                "		INNER JOIN t_health_maim " +
                "	ON t_health_maim.t_health_family_id = t_health_family.t_health_family_id " +
                "		INNER JOIN b_health_maim " +
                "	ON t_health_maim.b_health_maim_id = b_health_maim.b_health_maim_id " +
                "WHERE " +
                "t_health_family.health_family_active = '1' " +
                "AND t_health_maim.health_maim_active = '1' " +
                "AND b_health_maim.health_maim_active = '1' " ;
        
        if(!treatstr.equals("2"))
        {
            SQL = SQL +  "AND t_health_maim.health_maim_treat = '"+treatstr+"' ";
        }
        if(!registrystr.equals("2"))
        {
            SQL = SQL + "AND t_health_maim.health_maim_registry = '"+registrystr+"' ";
        } 
                
        if(!village_id.equals("0"))
        {
            SQL = SQL + "AND t_health_village.t_health_village_id = '" + village_id + "' ";
        }
        if(!maimtype_id.equals("0"))
        {
            SQL = SQL + "AND b_health_maim.b_health_maim_id = '" + maimtype_id + "' ";
        }        
        
        SQL = SQL + "AND t_health_maim.health_maim_survey_date BETWEEN '"+ startdate +"' AND '"+ enddate +"' " +
                "GROUP BY " +
                "MOO " +
                ",village_name " +
                ",maimtype " +
                ",firstname " +
                ",lastname " +
                ",house " +
                ",year " +
                ",maimtreat " +
                ",maimregistry " +
                "ORDER BY " +
                "t_health_village.village_moo" +
                ",maimtype";
        System.out.println("PersonMaimByDate --" + SQL);
        try
        {
            rs = theConnectionInf.eQuery(SQL);
            vc = getData(rs);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            if(vc == null)
            {
                vc = null;
            }
        }
        return vc;
    }
    
    private Vector getData(ResultSet resultset) throws Exception
    {
        int column = 0;
        String[] rowdata = null;
        String[] columnname = null;
        vString =  null;
        vData = new Vector();
        //ตรวจสอบค่า resultset
        if(rs!= null)
        {
            //ทำการรับข้อมูลส่วนหัว field
            metadata = rs.getMetaData();
            //นับจำนวน column
            column = metadata.getColumnCount();
            //init array ให้มีจำนวน เท่ากับ column
            columnname = new String[column];
            vString = new Vector();
            //ทำการให้ค่าชื่อ field ที่ get มา
            for(int i = 0 ; i < column;i++)
            {
                columnname[i] = metadata.getColumnLabel(i+1);
                columnname[i] = Language.getTextBundle(columnname[i].toUpperCase());
            }
            //ทำการให้ค่าของ field กับข้อมูล
            while(rs.next())
            {   rowdata = new String[column];
                for(int i = 0 ; i < column;i++)
                {
                    if(rs.getObject(i+1) != null)
                    {
                        rowdata[i] = rs.getString(i+1);
                    }
                }
                vString.add(rowdata);
            }
            vData.add(columnname);
            vData.add(vString);
        }
        return vData;
    }
}
