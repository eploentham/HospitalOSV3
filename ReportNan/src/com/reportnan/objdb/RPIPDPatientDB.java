/*
 * RPIPDPatientDB.java
 *
 * Created on 16 �Զع�¹ 2549, 9:45 �.
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
public class RPIPDPatientDB
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
    /**
     * Creates a new instance of RPIPDPatientDB 
     */
    public RPIPDPatientDB(ConnectionInf conf)
    {
        theConnectionInf = conf;
    }
    
    /**
     *��§ҹ��Ш��ѹ �ҹ�����¹͡
     *@param start_date �� String ������ѹ����������㹡�ô֧������
     *@param end_date �� String ������ѹ�������ش㹡�ô֧������
     *@param discharge �� Integer �����ʶҹ�����繴֧�����Ŵ����ѹ����˹��·ҧ���ᾷ�� ���ͨ�˹��·ҧ����Թ
     *1 �ѹ����˹��·ҧ����Թ 0 ���ѹ����˹��·ҧ���ᾷ��
     *@return Vector �ͧ�������
     *@Author pu
     *@Date 16/06/2006
     *@Modified 04/09/2006
     */
    public Vector queryIPDPatientByDate(String startdate,String enddate,int discharge)
    {
        System.out.println(" ��Ңͧ����� ��˹��� : " + discharge);
        try
        {
            SQL = "select " +
                            "q1.des AS ward " +
                            ",sum(q1.admit) AS admission " +
                            ",sum(q1.disc) AS discharge" +
                            ",sum(q1.day_stay) AS day_stay " +
                    "from " +
                    "(select  " +
                            "b_visit_ward.visit_ward_description as des " +
                            ", sum(1) as admit " +
                            ", sum(0) as disc " +
                            ", sum(0) as day_stay " +
                    "from  " +
                            "t_visit INNER JOIN b_visit_ward " +
                            "ON t_visit.b_visit_ward_id = b_visit_ward.b_visit_ward_id " +
                    "where " +
                            "t_visit.f_visit_type_id = '1' " +
                            "and t_visit.f_visit_status_id <> '4' " +
                            "and t_visit.visit_doctor_discharge_status = '1' " +
                            "and (substring(t_visit.visit_begin_admit_date_time ,1,10) Between '"+ startdate +"' And '"+ enddate +"') " +
                    "group by " + 
                            "b_visit_ward.visit_ward_description " +

                    "UNION " +

                    "select " + 
                            "b_visit_ward.visit_ward_description " +
                            ", sum(0) as admit " +
                            ", sum(1) as disc " +
                            ", sum(case when ( to_date(substring(t_visit.visit_financial_discharge_time,1,10),'YYYY-MM-DD')  " +
                                    "- to_date(substring(t_visit.visit_begin_admit_date_time ,1,10) ,'YYYY-MM-DD') )  > 0 " +
                                    "then ( to_date(substring(t_visit.visit_financial_discharge_time,1,10),'YYYY-MM-DD')  " +
                                    "- to_date(substring(t_visit.visit_begin_admit_date_time ,1,10) ,'YYYY-MM-DD') ) " +
                                    "else 1 " +
                            "end) as day_stay " +

                    "from  " +
                            "t_visit INNER JOIN b_visit_ward " +
                            "ON t_visit.b_visit_ward_id = b_visit_ward.b_visit_ward_id " +
                    "where " +
                            "t_visit.f_visit_type_id = '1' " +
                            "and t_visit.f_visit_status_id <> '4' " +
                            "and t_visit.visit_money_discharge_status  = '1' ";
                     if(discharge ==0)//��˹��·ҧ���ᾷ��
                            SQL = SQL +  "and t_visit.visit_doctor_discharge_status = '1' and (substring(t_visit.visit_staff_doctor_discharge_date_time,1,10) Between '"+ startdate +"' And '"+ enddate +"') " ;
                     else//��˹��·ҧ����Թ
                         SQL = SQL +  "and t_visit.visit_money_discharge_status = '1' and (substring(t_visit.visit_financial_discharge_time,1,10) Between '"+ startdate +"' And '"+ enddate +"') " ;

                    SQL = SQL + "group by  " +
                            "b_visit_ward.visit_ward_description) AS q1 " +

                    "group by " +
                            "q1.des";
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
                columnname[i] = Language.getTextBundle(columnname[i].toUpperCase());
            }
            //�ӡ������Ңͧ field �Ѻ������
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
