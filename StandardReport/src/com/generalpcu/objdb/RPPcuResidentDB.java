/*
 * RPPcuResidentDB.java
 *
 * Created on 4 �չҤ� 2549, 16:44 �.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.generalpcu.objdb;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.generalpcu.utility.Language;
import com.hospital_os.utility.DateUtil;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;
/**
 *
 * @author pu
 */
public class RPPcuResidentDB
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
    private int columnsize;
    private int str;
    /** Creates a new instance of RPPcuResidentDB */
    public RPPcuResidentDB(ConnectionInf conf)
    {
        theConnectionInf = conf;
    }
    
    /**
     *�ӹǹ��Ъҡ� �¡����� ��������ҹ
     *@param startdate �� String �ͧ�ѹ����������㹡�ô֧������
     *@param enddate �� String �ͧ�ѹ�������ش㹡�ô֧������
     *@return Vector ����红����Ũӹǹ��Ъҡ� �¡������������
     *@Date 03/04/2006
     *@Author pu
     */
    public Vector queryAllResidentByDate(String village_id)
    {
        str = 0;
        SQL = "SELECT  t_health_village.village_moo AS moo " +
                ",t_health_village.village_name AS village_name " +
                ",COUNT(t_health_family.t_health_family_id) AS TOTAL " +
                ",SUM(CASE  WHEN (t_health_family.f_sex_id = '1') " +
                " THEN 1 ELSE 0 END)AS male " +
                ",SUM(CASE  WHEN (t_health_family.f_sex_id = '2')" +
                " THEN 1 ELSE 0 END)  AS female " +
                ",SUM(CASE  WHEN ((t_health_family.f_sex_id <> '2' ) AND (t_health_family.f_sex_id <> '1' )) " +
                " THEN 1 ELSE 0 END)  AS  NONSPEC_SEX " +
              "FROM (t_health_village LEFT JOIN t_health_home " +
                "ON (t_health_home.t_health_village_id = t_health_village.t_health_village_id " +
                "  AND (t_health_village.village_moo <> '0' AND t_health_village.village_moo <> '00')) " +
                "LEFT JOIN   t_health_family " +
                "ON (t_health_family.t_health_home_id = t_health_home.t_health_home_id " +
                "AND t_health_family.health_family_active = '1' )) " ;
//                if(!village_id.equals("0"))
//                {
//                    SQL = SQL + "WHERE t_health_village.t_health_village_id = '" + village_id + "' " + 
//                }
                SQL = SQL + "WHERE (t_health_village.village_moo <> '0' AND t_health_village.village_moo <> '00')";
                SQL = SQL + "GROUP BY " +
                "t_health_village.village_moo " +
                ",t_health_village.village_name " +
               "ORDER BY " +
                "t_health_village.village_moo";
        
                System.out.println("queryAllResident --" + SQL);
                try
                {
                    rs = theConnectionInf.eQuery(SQL);
                    this.vc = getData(rs, "","");
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }
                finally
                {
                    if(this.vc == null)
                    {
                        this.vc = null;
                    }
                }
                return this.vc;       
    }
    /**
     *�ӹǹ��Ъҡ� �¡������������ ��ṡ��������ҹ 㹡óշ���кت�ǧ����
     *�д֧������੾�Ъ�ǧ���ع���
     *@param village_id �� String �ͧ���������ҹ����ͧ��ä���
     *@param startdate �� String �ͧ�ѹ����������㹡�ô֧������
     *@param enddate �� String �ͧ�ѹ�������ش㹡�ô֧������
     *@param agebegin �� String �ͧ��ǧ�����������
     *@param ageend �� String �ͧ��ǧ��������ش
     *@return Vector ����红����Ũӹǹ��Ъҡ� �¡������������
     *@Date 04/03/2006
     *@Author pu
     */
    public Vector queryResidentAgePortionByDate(String village_id,String startdate,String enddate,String agebegin,String ageend)
    {
        str = 0;
        SQL = "SELECT  t_health_village.village_moo AS moo " +
                ",t_health_village.village_name AS village_name " +
                ",COUNT(t_health_family.t_health_family_id) AS TOTAL " +
                ",SUM(CASE  WHEN (t_health_family.f_sex_id = '1') " +
                "	THEN  1 " +
                "	ELSE 0 " +
                "	END)  AS male " +
                ",SUM(CASE  WHEN (t_health_family.f_sex_id = '2') " +
                "	THEN  1 " +
                "	ELSE 0  " +
                "       END)  AS female " +
                ",SUM(CASE  WHEN ((t_health_family.f_sex_id <> '2' ) AND (t_health_family.f_sex_id <> '1' )) " +
                "	THEN  1 " +
                "	ELSE 0 " +
                "       END)  AS  NONSPEC_SEX " +
               "FROM (t_health_village LEFT JOIN t_health_home " +
                "	ON (t_health_home.t_health_village_id = t_health_village.t_health_village_id " +
                "		AND (t_health_village.village_moo <> '0' AND t_health_village.village_moo <> '00')) " +
                "			LEFT JOIN   t_health_family " +
                "	ON ( t_health_family.t_health_home_id = t_health_home.t_health_home_id " +
                "       	     AND t_health_family.health_family_active = '1' " +
                "                    AND  ((  to_number(substring(' ' || age(to_date('"+ startdate +"','YYYY-MM-DD'), " +
                "                             to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year'),'999') >= " + agebegin + " " +
                "                         AND to_number(substring(' ' || age(to_date('"+ enddate +"','YYYY-MM-DD'), " +
                "                             to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year'),'999') <= " + ageend + " " +
                "                           ) ";
                if(agebegin == null || agebegin.equals("0"))
                {
                   SQL = SQL +    " OR substring(' ' || age(to_date('"+ startdate +"','YYYY-MM-DD'), " +
                                  " to_date(t_health_family.patient_birthday,'YYYY-MM-DD'))  from '(...)year')  IS NULL " ;                                 
                }             
                SQL = SQL +  " ))) " ;
                if(!village_id.equals("0") && !village_id.equals("00"))
                {
                    SQL = SQL + "WHERE t_health_village.t_health_village_id = '" + village_id + "' ";
                }
                SQL = SQL + "GROUP BY " +
                "moo " +
                ",village_name " +
                "ORDER BY " +
                "t_health_village.village_moo";
        
        System.out.println("queryPatienNametByDate --" + SQL);
        try
        {
            rs = theConnectionInf.eQuery(SQL);
            this.vc = getData(rs, "","");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            if(this.vc == null)
            {
                this.vc = null;
            }
        }
        return this.vc;
    }
    
    /**
     *�ӹǹ��Ъҡ÷�����������ҹ 㹡óշ��֧�����ŷء��ǧ���� 
     *�й�仵�͡Ѻ�����Ţͧ��ǧ���ط�����
     *@param village_id �� String �ͧ���������ҹ����ͧ��ä���
     *@param startdate  �� String �ѹ����������㹡�ô֧������
     *@param enddate  �� String �ѹ�������ش㹡�ô֧������
     *@return Vector ����红����Ũӹǹ��Ъҡ÷�����������ҹ
     *@Date 04/03/2006
     *@Author pu
     */
    public Vector queryResidentAgePortionAllByDate(String village_id,String currentdate,String agebegin,String ageend,int columnsizes)
    {
        str = 0;
        columnsize = columnsizes;
        /*SQL = "SELECT  t_health_village.village_moo AS moo " +
                ",t_health_village.village_name AS village_name " +
                ",COUNT(t_health_family.t_health_family_id) AS TOTAL " +
                ",SUM(CASE  WHEN (t_health_family.f_sex_id = '1') " +
                " THEN 1 ELSE 0 END)AS male " +
                ",SUM(CASE  WHEN (t_health_family.f_sex_id = '2')" +
                " THEN 1 ELSE 0 END)  AS female " +
                ",SUM(CASE  WHEN ((t_health_family.f_sex_id <> '2' ) AND (t_health_family.f_sex_id <> '1' )) " +
                " THEN 1 ELSE 0 END)  AS  NONSPEC_SEX " +
              "FROM (t_health_village LEFT JOIN t_health_home " +
                "ON t_health_home.t_health_village_id = t_health_village.t_health_village_id " +
                "LEFT JOIN   t_health_family " +
                "ON (t_health_family.t_health_home_id = t_health_home.t_health_home_id " +
                "AND t_health_family.health_family_active = '1' )) " ;
                if(!village_id.equals("0"))
                {
                    SQL = SQL + "WHERE t_health_village.t_health_village_id = '" + village_id + "' ";
                }
                SQL = SQL + "GROUP BY " +
                "t_health_village.village_moo " +
                ",t_health_village.village_name " +
               "ORDER BY " +
                "to_number(t_health_village.village_moo,'99')";*/
         SQL = "SELECT t_health_village.village_moo AS moo " +
                ",t_health_village.village_name AS village_name " +
                ",COUNT(t_health_family.t_health_family_id) AS TOTAL " +
                ",SUM(CASE  WHEN (t_health_family.f_sex_id = '1') " +
                "	THEN  1 " +
                "	ELSE 0 " +
                "	END)  AS male " +
                ",SUM(CASE  WHEN (t_health_family.f_sex_id = '2') " +
                "	THEN  1 " +
                "	ELSE 0  " +
                "       END)  AS female " +
                ",SUM(CASE  WHEN ((t_health_family.f_sex_id <> '2' ) AND (t_health_family.f_sex_id <> '1' )) " +
                "	THEN  1 " +
                "	ELSE 0 " +
                "       END)  AS  NONSPEC_SEX " +
               "FROM (t_health_village " +
                "       LEFT JOIN t_health_home " +
                "           ON (t_health_home.t_health_village_id = t_health_village.t_health_village_id " +
                "              	AND (t_health_village.village_moo <> '0' AND t_health_village.village_moo <> '00')) " +
                "	LEFT JOIN   t_health_family " +
                "           ON ( t_health_family.t_health_home_id = t_health_home.t_health_home_id " +
                "       	     AND t_health_family.health_family_active = '1' " +
                "                    AND  ((  to_number(substring(' ' || age(to_date('"+ currentdate +"','YYYY-MM-DD'), " +
                "                             to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year'),'999') >= " + agebegin + " " +
                "                         AND to_number(substring(' ' || age(to_date('"+ currentdate +"','YYYY-MM-DD'), " +
                "                             to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year'),'999') <= " + ageend + " " +
                "                           ) ";
                if(agebegin.equals("0"))
                {
                   SQL = SQL +    " OR substring(' ' || age(to_date('"+ currentdate +"','YYYY-MM-DD'), " +
                                  " to_date(t_health_family.patient_birthday,'YYYY-MM-DD'))  from '(...)year')  IS NULL " ;                                 
                }             
                SQL = SQL +  " ))) " ;
//                if(!village_id.equals("0") && !village_id.equals("00"))
//                {
//                    SQL = SQL + "WHERE t_health_village.t_health_village_id = '" + village_id + "' " ;                
//                }       
                SQL = SQL + "WHERE (t_health_village.village_moo <> '0' AND t_health_village.village_moo <> '00')";
                SQL = SQL + "GROUP BY t_health_village.village_moo " +
                        ",t_health_village.village_name " +
                        "ORDER BY t_health_village.village_moo";
        
        System.out.println("queryResidentAgePortionAll --" + SQL);
        try
        {
            this.rs = theConnectionInf.eQuery(SQL);
            this.vc = this.ResidentAgePortionAllByDate(rs);//this.getData(rs);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            if(this.vc == null)
            {
                this.vc = null;
            }
        }
        return this.vc;
    }
    
    /**
     *�ӹǹ��Ъҡ� �¡������������ ��ṡ��������ҹ 㹡óշ���кت�ǧ����
     *�д֧������੾�Ъ�ǧ���ع���
     *@param village_id �� String ���������ҹ����ͧ��ä���
     *@param startdate  �� String �ѹ����������㹡�ô֧������
     *@param enddate  �� String �ѹ�������ش㹡�ô֧������
     *@param agebegin  �� String ��ǧ�����������
     *@param ageend  �� String ��ǧ��������ش
     *@param currentdate  ��  String �ѹ���Ѩ�غѹ
     *@return Vector ����红����Ũӹǹ��Ъҡ� �¡������������
     *@Date 07/03/2006
     *@Author pu
     */
    public Vector queryResidentAgeGroupByDate(String village_id,String currentdate,String startdate,String enddate,String agebegin,String ageend)
    {
          str = 1;
          SQL = "SELECT t_health_village.village_moo AS moo " +
                ",SUM(CASE  WHEN (t_health_family.f_sex_id = '1') " +
                "	THEN  1 " +
                "	ELSE 0 " +
                "	END)  AS male " +
                ",SUM(CASE  WHEN (t_health_family.f_sex_id = '2') " +
                "	THEN  1 " +
                "	ELSE 0  " +
                "       END)  AS female " +
                ",SUM(CASE  WHEN ((t_health_family.f_sex_id <> '2' ) AND (t_health_family.f_sex_id <> '1' )) " +
                "	THEN  1 " +
                "	ELSE 0 " +
                "       END)  AS  NONSPEC_SEX " +
               "FROM (t_health_village " +
                "       LEFT JOIN t_health_home " +
                "           ON (t_health_home.t_health_village_id = t_health_village.t_health_village_id " +
                "		AND (t_health_village.village_moo <> '0' AND t_health_village.village_moo <> '00')) " +
                "	LEFT JOIN   t_health_family " +
                "           ON ( t_health_family.t_health_home_id = t_health_home.t_health_home_id " +
                "       	     AND t_health_family.health_family_active = '1' " +
                "                    AND  ((  to_number(substring(' ' || age(to_date('"+ currentdate +"','YYYY-MM-DD'), " +
                "                             to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year'),'999') >= " + agebegin + " " +
                "                         AND to_number(substring(' ' || age(to_date('"+ currentdate +"','YYYY-MM-DD'), " +
                "                             to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year'),'999') <= " + ageend + " " +
                "                           ) ";
                if(agebegin.equals("0"))
                {
                   SQL = SQL +    " OR substring(' ' || age(to_date('"+ currentdate +"','YYYY-MM-DD'), " +
                                  " to_date(t_health_family.patient_birthday,'YYYY-MM-DD'))  from '(...)year')  IS NULL " ;                                 
                }             
                SQL = SQL +  " ))) " ;
                if(!village_id.equals("0") && !village_id.equals("00"))
                {
                    SQL = SQL + "WHERE t_health_village.t_health_village_id = '" + village_id + "' " ;                
                }        
                SQL = SQL + "GROUP BY t_health_village.village_moo " +
                        "ORDER BY t_health_village.village_moo";
        System.out.println("queryPatienNametByDate --" + SQL);
        try
        {
            this.rs = theConnectionInf.eQuery(SQL);
            this.vc = this.getData(rs,agebegin,ageend);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            if(this.vc == null)
            {
                this.vc = null;
            }
        }
        return this.vc;
    }
    
    /**
     *��ª�������������Թ 72 ��͹ (0-5 ��) ��ṡ��������ҹ
     *@param village_id �� String ���������ҹ����ͧ��ä���
     *@param startdate ��  String �ѹ����������㹡�ô֧������
     *@param enddate  �� String �ѹ�������ش㹡�ô֧������
     *@param  currentdate  �� String �ѹ���Ѩ�غѹ
     *@return Vector �������ª��ͼ����·�����ä���Դ���
     *@Date 07/03/2006
     *@Author pu
     */
    public Vector queryResidentAgeChildByDate(String village_id,String currentdate,String startdate,String enddate)
    {
        str = 0;
        SQL = "SELECT t_health_village.village_moo AS moo " +
                ", t_health_village.village_name AS village_name " +
                ", f_patient_prefix.patient_prefix_description || t_health_family.patient_name AS firstname " +
                ", t_health_family.patient_last_name AS lastname " +
                ", t_health_home.health_home_house AS house " +
                ", t_health_family.patient_birthday AS birthdate " +
                ", CASE WHEN (to_number(substring(' ' || age(to_date('"+currentdate+"','YYYY-MM-DD') " +
                "            ,to_date(t_health_family.patient_birthday,'YYYY-MM-DD'))  from '(...)year'),'999') IS NOT NULL) " +
                "  THEN trim(substring(' ' || age(to_date('"+currentdate+"','YYYY-MM-DD') " +
                "            ,to_date(t_health_family.patient_birthday,'YYYY-MM-DD'))  from '(...)year')) " +
                "  ELSE '0' END AS year " +
                ", CASE WHEN (to_number(substring(' ' || age(to_date('"+currentdate+"','YYYY-MM-DD') " +
                "            ,to_date(t_health_family.patient_birthday,'YYYY-MM-DD'))  from '(...)mon'),'999') IS NOT NULL) " +
                "  THEN trim(substring(' ' || age(to_date('"+currentdate+"','YYYY-MM-DD') " +
                "            ,to_date(t_health_family.patient_birthday,'YYYY-MM-DD'))  from '(...)mon')) " +
                "  ELSE '0' END AS month " +
                ", CASE WHEN (to_number(substring(' ' || age(to_date('"+currentdate+"','YYYY-MM-DD') " +
                "           ,to_date(t_health_family.patient_birthday,'YYYY-MM-DD'))  from '(...)day'),'999') IS NOT NULL) " +
                "  THEN trim(substring(' ' || age(to_date('"+currentdate+"','YYYY-MM-DD') " +
                "           ,to_date(t_health_family.patient_birthday,'YYYY-MM-DD'))  from '(...)day')) " +
                "  ELSE '0' END AS day " +
                ",t_health_family.patient_father_firstname || ' ' || t_health_family.patient_father_lastname AS fathername " +
                ",t_health_family.patient_mother_firstname || ' ' || t_health_family.patient_mother_lastname AS mothername " +
                "FROM " +
                "   t_health_village INNER JOIN t_health_home " +
                "		ON (t_health_home.t_health_village_id = t_health_village.t_health_village_id " +
                "			AND (t_health_village.village_moo <> '0' AND t_health_village.village_moo <> '00')) " ;
                if(!village_id.equals("0") && !village_id.equals("00"))
                {
                    SQL = SQL + " AND t_health_village.t_health_village_id = '" + village_id + "' ";
                }  
                SQL = SQL +" INNER JOIN   t_health_family " +
                "		ON t_health_family.t_health_home_id = t_health_home.t_health_home_id " +
                "                    INNER JOIN f_patient_prefix " +
                "		ON f_patient_prefix.f_patient_prefix_id = t_health_family.f_prefix_id " +
                "WHERE " +
                "   t_health_family.health_family_active = '1'" +
                "   AND((substring(' ' || age(to_date('"+currentdate+"','YYYY-MM-DD') " +
                "  ,to_date(t_health_family.patient_birthday,'YYYY-MM-DD'))  from '(...)year')) IS NULL " +
                "   OR  to_number(substring(' ' || age(to_date('"+currentdate+"','YYYY-MM-DD'), " +
                "   to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year'),'999') < 6 " +
                "   OR (to_number(substring(' ' || age(to_date('"+currentdate+"','YYYY-MM-DD'), " +
                "   to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year'),'999') = 6 " +
                "   AND to_number(substring(' ' || age(to_date('"+currentdate+"','YYYY-MM-DD'), " +
                "   to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)mon'),'999') IS NULL ))" +
                "GROUP BY " +
                "	moo " +
                "       ,village_name " +
                "	,firstname " +
                "	,lastname " +
                "	,house " +
                "	,birthdate " +
                "	,year " +
                "	,month " +
                "	,day " +
                "	,fathername " +
                "	,mothername " +
                "ORDER BY " +
                "t_health_village.village_moo";
        System.out.println("gueryResidentAgeChild --" + SQL);
        try
        {
            this.rs = theConnectionInf.eQuery(SQL);
            this.vc = getData(this.rs, "","");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            if(this.vc == null)
            {
                this.vc = null;
            }
        }
        return this.vc;        
    }
    /**
     *��ª��ͻ�Ъҡ÷�������ࢵ�Ѻ�Դ�ͺ ��˭ԧ ���� �ҡ���� 15 ��
     *@param String village_id  �� String ���������ҹ����ͧ��ä���
     *@param String startdate �� String �ѹ����������㹡�ô֧������
     *@param enddate  �� String �ѹ�������ش㹡�ô֧������
     *@param currentdate �� String �ѹ���Ѩ�غѹ
     *@return Vector �������ª��ͼ����·�����ä���Դ���
     *@Date 07/03/2006
     *@Author pu
     */  
    public Vector queryResidentWomenUpper15ByDate(String village_id,String currentdate,String startdate,String enddate)
    {
        str = 0;
        SQL = "SELECT t_health_village.village_moo AS moo " +
                ", t_health_village.village_name AS village_name " +
                ", f_patient_prefix.patient_prefix_description || t_health_family.patient_name AS firstname " +
                ", t_health_family.patient_last_name AS lastname " +
                ", t_health_family.patient_pid AS pid " +
                ", CASE WHEN to_number(substring(' ' || age(to_date('"+currentdate+"','YYYY-MM-DD'), " +
                "            to_date(t_health_family.patient_birthday,'YYYY-MM-DD'))  from '(...)year'),'999') IS NULL " +
                "  THEN '' ELSE substring(' ' || age(to_date('"+currentdate+"','YYYY-MM-DD'), " +
                "            to_date(t_health_family.patient_birthday,'YYYY-MM-DD'))  from '(...)year')  END AS year " +
                ",t_health_home.health_home_house AS house " +
               "FROM " +
                "t_health_village INNER JOIN t_health_home " +
                "   ON (t_health_home.t_health_village_id = t_health_village.t_health_village_id " +
                "	AND (t_health_village.village_moo <> '0' AND t_health_village.village_moo <> '00')) ";
                if(!village_id.equals("0") && !village_id.equals("00"))
                {
                    SQL = SQL + " AND t_health_village.t_health_village_id = '" + village_id + "' ";
                }
                SQL =SQL +" INNER JOIN   t_health_family " +
                "   ON t_health_family.t_health_home_id = t_health_home.t_health_home_id " +
                "       INNER JOIN f_patient_prefix " +
                "   ON f_patient_prefix.f_patient_prefix_id = t_health_family.f_prefix_id " +
               "WHERE " +
                "   t_health_family.f_patient_marriage_status_id = '2' " +
                "   AND t_health_family.f_sex_id  = '2' " +
                "   AND t_health_family.health_family_active = '1' " + 
                "   AND (t_health_village.village_moo <> '0' AND t_health_village.village_moo <> '00') " +
                "   AND to_number(substring('' || age(to_date('"+currentdate+"','YYYY-MM-DD'),to_date(t_health_family.patient_birthday,'YYYY-MM-DD'))  from '(...)year'),'999') >15 " +
               "GROUP BY " +
                "   moo " +
                "   ,village_name " +
                "   ,firstname " +
                "   ,lastname " +
                "   ,pid " +
                "   ,year " +
                "   ,house " +
               "ORDER BY " +
               " t_health_village.village_moo";
        System.out.println("queryResidentUpper15" + SQL);
        try
        {
            this.rs = this.theConnectionInf.eQuery(SQL);
            this.vc = this.getData(this.rs, "", "");
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
    /**
     *��§ҹ������ª��Ե�ͧ��Ъҡ� ���������ҹ ������к����˵ء�����ª��Ե
     *@param village_id ��  String  ���������ҹ����ͧ��ä���
     *@param startdate �� String �ѹ����������㹡�ô֧������
     *@param enddate �� String �ѹ�������ش㹡�ô֧������
     *@return Vector �������ª��ͼ����·�����ä���Դ���
     *@Date 07/03/2006
     *@Author pu
     */      
    public Vector queryResidentDeathByDate(String village_id,String startdate,String enddate)
    {
        str = 0;
        SQL = "SELECT t_health_village.village_moo AS moo " +
                ", t_health_village.village_name AS village_name " +
                ", f_patient_prefix.patient_prefix_description || t_health_family.patient_name AS firstname " +
                ", t_health_family.patient_last_name AS lastname " +
                ", t_death.death_primary_diagnosis AS cause_A " +
                ", t_death.death_comorbidity  AS cause_B " +
                ", t_death.death_complication  AS cause_C " +
                ", t_death.death_others  AS cause_D " +
                ", t_death.death_external_cause_of_injury AS cause_E " +
                ", t_death.death_cause AS cause_F " +
                "FROM " +
                "  t_health_village INNER JOIN t_health_home " +
                "	ON (t_health_home.t_health_village_id = t_health_village.t_health_village_id " +
                "       	AND (t_health_village.village_moo <> '0' AND t_health_village.village_moo <> '00')) ";
                if(!village_id.equals("0") && !village_id.equals("00"))
                {
                    SQL = SQL + " AND t_health_village.t_health_village_id = '" + village_id + "' ";
                }
                SQL = SQL + " INNER JOIN   t_health_family " +
                "	ON t_health_family.t_health_home_id = t_health_home.t_health_home_id " +
                "               INNER JOIN f_patient_prefix " +
                "	ON f_patient_prefix.f_patient_prefix_id = t_health_family.f_prefix_id " +
                "		INNER JOIN t_death " +
                "	ON t_death.t_health_family_id = t_health_family.t_health_family_id " +
                "WHERE " +
                "	t_death.death_active = '1' " +
                "   AND t_health_family.health_family_active = '1' " +
                "	AND (t_death.death_date_time BETWEEN '"+startdate+"' AND '"+enddate+"') " +
                "       AND (t_health_village.village_moo <> '0' AND t_health_village.village_moo <> '00') " +
                "GROUP BY " +
                "	moo " +
                "	,village_name " +
                "	,firstname " +
                "	,lastname " +
                "	,cause_A " +
                "	,cause_B " +
                "	,cause_C " +
                "	,cause_D " +
                "	,cause_E " +
                "	,cause_F " +
                "ORDER BY " +
                  " t_health_village.village_moo";
        System.out.println("queryResidentDeath " + SQL);
         try
        {
            this.rs = this.theConnectionInf.eQuery(SQL);
            this.vc = this.getData(this.rs,"","");             
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
    
    private Vector ResidentAgePortionAllByDate(ResultSet resultset) throws Exception
    {
        int column = 0;
        String[] rowdata = null;
        String[] columnname = null;
        vString =  null;
        vData = new Vector();
        //��Ǩ�ͺ��� resultset
        if(rs!= null)
        {
            //�ӡ���Ѻ��������ǹ��� field
            metadata = rs.getMetaData();
            //�Ѻ�ӹǹ column
            column = metadata.getColumnCount();
            //init array ����ըӹǹ ��ҡѺ column
            columnname = new String[columnsize];
            vString = new Vector();
            //�ӡ������Ҫ��� field ��� get ��
            for(int i = 0 ; i < columnsize;i++)
            {   
                if(i < column)
                {
                    columnname[i] = metadata.getColumnLabel(i+1);
                    columnname[i] = Language.getTextBundle(columnname[i].toUpperCase());
                }
                else
                {
                    columnname[i] = "-";
                }
            }
            //�ӡ������Ңͧ field �Ѻ������
            while(rs.next())
            {   rowdata = new String[columnsize];
                for(int i = 0 ; i < columnsize;i++)
                {   
                    if(i < column)
                    {
                        if(rs.getObject(i+1) != null)
                        {
                            rowdata[i] = rs.getString(i+1);
                        }
                        else
                        {
                            rowdata[i] = "-";
                        }
                    }
                    else
                    {
                        rowdata[i] = "-";
                    }
                }
                vString.add(rowdata);
            }            
            vData.add(columnname);
            vData.add(vString);
        }
        return vData;
    }

    private Vector getData(ResultSet resultset,String agebegin,String ageend) throws Exception
    {
        int column = 0;
        String[] rowdata = null;
        String[] columnname = null;
        vString =  null;
        vData = new Vector();
        //��Ǩ�ͺ��� resultset
        if(rs!= null)
        {
            //�ӡ���Ѻ��������ǹ��� field
            metadata = rs.getMetaData();
            //�Ѻ�ӹǹ column
            column = metadata.getColumnCount();
            //init array ����ըӹǹ ��ҡѺ column
            columnname = new String[column];
            vString = new Vector();
            //�ӡ������Ҫ��� field ��� get ��
            for(int i = 0 ; i < column;i++)
            {
                columnname[i] = metadata.getColumnLabel(i+1);
                if(this.str == 1)
                {
                    columnname[i] = agebegin +"-"+ ageend +"("+Language.getTextBundle(columnname[i].toUpperCase())+")";
                }
                else
                {                    
                    columnname[i] = Language.getTextBundle(columnname[i].toUpperCase());
                }
            }
            //�ӡ������Ңͧ field �Ѻ������
            while(rs.next())
            {   rowdata = new String[column];
                for(int i = 0 ; i < column;i++)
                {
                    if(rs.getObject(i+1) != null)
                    {
                        rowdata[i] = rs.getString(i+1);
                        if(i==5)
                        {
                            if(rowdata[i].trim().length() != 0)
                            {
                                rowdata[i] = DateUtil.getDateToString(DateUtil.getDateFromText(rowdata[i]),false);
                            }
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
