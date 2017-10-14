/*
 * RPPcuPregnanceANCDB.java
 *
 * Created on 13 มีนาคม 2549, 17:22 น.
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
import com.hospital_os.utility.DateUtil;
/**
 *
 * @author pu
 */
public class RPPcuPregnanceANCDB 
{
    public ConnectionInf theConnectionInf;
    String SQL = "";
    Vector vc;
    ResultSet rs = null;
    ResultSetMetaData metadata;
    Vector vString;
    Vector vData;
    Vector vColumn;
    Vector vRowData;
    /** Creates a new instance of RPPcuPregnanceANCDB */
    public RPPcuPregnanceANCDB(ConnectionInf conf)
    {
        theConnectionInf = conf;
    }
    
    /**
     *ดึงข้อมูลรายชื่อหญิงตั้งครรภ์ ที่มารับบริการ ANC แยกตามหมู่บ้าน
     *@param village_id เป็น String ของรหัสหมู่บ้านที่ต้องการค้นหา
     *@param startdate เป็น String วันที่เริ่มต้นในการดึงข้อมูล
     *@param enddate เป็น String วันที่สิ้นสุดในการดึงข้อมูล
     *@return Vector ที่เก็บข้อมูลจำนวนประชากร แยกตามกลุ่มอายุ
     *@Date 13/03/2006
     *@Author pu
     */
    public Vector queryPregnanceANCByDate(String village_id,String startdate,String enddate)
    {
        SQL = "SELECT t_health_village.village_moo AS moo " +
                ", t_health_village.village_name AS village_name " +
                ", f_patient_prefix.patient_prefix_description || t_health_family.patient_name || ' ' || t_health_family.patient_last_name AS name" +
                ", t_health_home.health_home_house AS house " +
                ", t_health_anc.health_anc_no AS anc_no " +
                ", substring(t_health_anc.record_date_time,0,11) AS anc_date " +
              "FROM " +
                "t_health_village INNER JOIN t_health_home " +
                "       ON t_health_home.t_health_village_id = t_health_village.t_health_village_id " +
                "		INNER JOIN   t_health_family " +
                "	ON " +
                "           (t_health_family.t_health_home_id = t_health_home.t_health_home_id " +
                "		AND t_health_family.health_family_active = '1') " +
                "		INNER JOIN f_patient_prefix " +
                "	ON f_patient_prefix.f_patient_prefix_id = t_health_family.f_prefix_id " +
                "		INNER JOIN t_health_anc " +
                "	ON " +
                "           (t_health_anc.t_health_family_id = t_health_family.t_health_family_id " +
                "               AND t_health_anc.health_anc_active = '1') " +
              "WHERE ";
                if(!village_id.equals("0"))
                {
                    SQL = SQL + "t_health_village.t_health_village_id = '" + village_id + "' AND " ;
                }
                SQL = SQL + "substring(t_health_anc.record_date_time,0,11) BETWEEN '"+ startdate +"' AND '"+ enddate +"' " +
              "GROUP BY " +
                "t_health_village.village_moo " +
                ",t_health_village.village_name " +
                ",f_patient_prefix.patient_prefix_description " +
                ",t_health_family.patient_name " +
                ",t_health_family.patient_last_name " +
                ",t_health_home.health_home_house " +
                ",t_health_anc.health_anc_no " +
                ",anc_date " +
              "ORDER BY " +
                "t_health_village.village_moo";
        System.out.println("queryPregnanceANC " + SQL);
        try
        {
            this.rs = this.theConnectionInf.eQuery(SQL);
            this.vc = this.getData(this.rs);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            if(this.vc == null)
                this.vc= null;
        }
        return this.vc;
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
                        if(i==5)
                        {
                            rowdata[i] = rs.getString(i+1);
                            if(rowdata[i].trim().length() != 0)
                            {
                                rowdata[i] = DateUtil.getDateToString(DateUtil.getDateFromText(rs.getString(i+1)), false);
                            }
                        }
                        else
                        {
                            rowdata[i] = rs.getString(i+1);
                        }
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
