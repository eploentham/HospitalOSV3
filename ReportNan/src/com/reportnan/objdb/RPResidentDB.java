/*
 * RPResidentDB.java
 *
 * Created on 3 มิถุนายน 2549, 16:26 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportnan.objdb;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.reportnan.utility.Language;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;
/**
 *
 * @author pu
 */
public class RPResidentDB
{
    ConnectionInf theConnectionInf;
    String SQL = "";
    Vector vc;
    ResultSet rs = null;
    ResultSetMetaData metadata;
    Vector vString;
    Vector vData;
    private int columnsize;
    private int str;
    /** Creates a new instance of RPResidentDB */
    public RPResidentDB(ConnectionInf conf)
    {
        theConnectionInf = conf;
    }
    /**
     *รายงานจำนวนประชากร หลังคาเรือน ครอบครัว แยกตามหมู่บ้าน
     *@param tambon เป็น String ที่เก็บรหัสของหมู่บ้านที่ต้องการดึงข้อมูล
     *@return Vector ของประชากร
     *@Author pu
     *@Date 02/06/2006
     */
    public Vector queryResidentByTambon(String tambon)
    {
        try
        {
            SQL = "SELECT  ( 'บ้าน'|| ( CASE WHEN (query1.village_name IS NOT NULL AND " +
                                    "query1.village_name <> '' AND " +
                                    "query1.village_name <> 'null' ) " +  
                                    "THEN query1.village_name " +
                                    "ELSE '' " +
                    "END ) " + 
                    "|| '  หมู่ที่ '|| ( CASE WHEN (query1.village_moo IS NOT NULL AND " +
                                             "query1.village_moo <> '' AND " +
                                             "query1.village_moo <> 'null' ) " + 
                                    "THEN query1.village_moo " + 
                                    "ELSE '' " +
                    "END ) " +
                    "|| '  ตำบล '  ||  (CASE WHEN (f_address_1.address_description IS NOT NULL AND " +  
                                            "f_address_1.address_description <> '' AND " + 
                                            "f_address_1.address_description <> 'null' ) " +
                                    "THEN f_address_1.address_description " + 
                                    "ELSE '' " +
                    "END ) " +
                    "|| ' อำเภอ ' ||   (CASE WHEN (f_address_2.address_description IS NOT NULL AND " +
                                            "f_address_2.address_description <> '' AND " +
                                            "f_address_2.address_description <> 'null' ) " + 
                                    "THEN f_address_2.address_description " + 
                                    "ELSE '' " +
                    "END ) " +
                    "|| ' จังหวัด ' ||   (CASE WHEN (f_address.address_description IS NOT NULL AND " +
                                            "f_address.address_description <> '' AND " +
                                            "f_address.address_description <> 'null' ) " +  
                                    "THEN f_address.address_description " +
                                    "ELSE '' " +
                    "END )) AS ADDRESS " +
                    ", COUNT(query1.home_id) AS COUNT_HOME " + 
                    ", SUM(CASE WHEN (query1.health_home_family IS NOT NULL AND " +
                                    "query1.health_home_family <> '' AND " +
                                    "query1.health_home_family <> 'null') " +                          
                           "THEN to_number(query1.health_home_family,'99999') " +
                               "WHEN query1.health_home_family = '0' " +
                           "THEN 1 " +
                           "ELSE 0 " +
                           "END ) AS COUNT_FAMILY " +
                    ",SUM(query1.male) AS MALE " +
                    ",SUM(query1.female) AS FEMALE " +
                    ",SUM(query1.NONSPEC_SEX) AS NONSPEC_SEX " +
                    ",SUM(query1.TOTAL) AS TOTAL " +
            "FROM    ( " +
                       "SELECT " +
                            "t_health_village.t_health_village_id AS village_id " +
                            ",t_health_home.t_health_home_id AS home_id " + 
                            ",t_health_home.health_home_family AS health_home_family " +
                            ",t_health_village.village_name AS village_name " +
                            ",t_health_village.village_moo AS village_moo " +
                            ",t_health_village.village_tambon AS village_tambon " +
                            ",t_health_village.village_ampur AS village_ampur " +
                            ",t_health_village.village_changwat AS village_changwat " +
                            ",SUM(CASE WHEN t_health_family.t_health_family_id IS NOT NULL " +
                                    "THEN 1 " +
                                    "ELSE 0 END) AS TOTAL " +
                            ",SUM(CASE  WHEN (t_health_family.f_sex_id = '1') " + 
                                    "THEN 1 " +
                                    "ELSE 0 END) AS male " +
                            ",SUM(CASE  WHEN (t_health_family.f_sex_id = '2') " +
                                    "THEN 1 " +
                                    "ELSE 0 END) AS female " +
                            ",SUM(CASE  WHEN ((t_health_family.f_sex_id <> '2' ) AND (t_health_family.f_sex_id <> '1' )) " +
                                    "THEN 1 " +
                                    "ELSE 0 END) AS  NONSPEC_SEX " +
                            "FROM ( " +
                                    "t_health_village LEFT JOIN t_health_home " +
                                    "ON (t_health_home.t_health_village_id = t_health_village.t_health_village_id " +
                                  /*  "/*AND t_health_village.village_moo <> '0' */
                                   " ) " +
                                    "LEFT JOIN   t_health_family " +
                                    "ON (t_health_family.t_health_home_id = t_health_home.t_health_home_id " +
                                    "AND t_health_family.health_family_active = '1' )) " +
                            "GROUP BY " +
                                "t_health_village.t_health_village_id " +
                                ",t_health_home.t_health_home_id " +
                                ",t_health_home.health_home_family " +
                                ",t_health_village.village_name " +
                                ",t_health_village.village_moo " +
                                ",t_health_village.village_tambon " +
                                ",t_health_village.village_ampur " +
                                ",t_health_village.village_changwat) AS query1 " +                     
                    "LEFT JOIN f_address ON (query1.village_changwat = f_address.f_address_id) " +
                    "LEFT JOIN f_address AS f_address_2 ON (query1.village_ampur = f_address_2.f_address_id) " +
                    "LEFT JOIN f_address AS f_address_1 ON (query1.village_tambon = f_address_1.f_address_id ) ";
            if(!tambon.equals("0"))
            {
                SQL = SQL + "WHERE query1.village_tambon = '"+ tambon +"' ";
            }            
            SQL = SQL + "GROUP BY " +
                            "query1.village_name " +
                        ",   query1.village_moo " +
                        ",   f_address_1.address_description " +
                        ",   f_address_2.address_description " +
                        ",   f_address.address_description ";
            System.out.println(SQL);
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
