/*
 * RPPatientInClinicDB.java
 *
 * Created on 5 �Զع�¹ 2549, 11:41 �.
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
public class RPPatientInClinicDB
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
    
    /** Creates a new instance of RPPatientInClinicDB */
    public RPPatientInClinicDB(ConnectionInf conf)
    {
        theConnectionInf = conf;
    }
    
    /**
     *��§ҹ�����·������Ѻ��ԡ�ä�Թԡ��ҧ �
     *@param startdate �� String ������ѹ����������㹡�ô֧������
     *@param enddate �� String ������ѹ�������ش㹡�ô֧������
     *@return Vector �ͧ�����·������Ѻ��ԡ�ä�Թԡ��ҧ �
     *@Author pu
     *@Date 05/06/2006
     */
    public Vector queryPatientInClinic(String startdate,String enddate)
    {
        try
        {
            SQL = "select q1.clinic AS clinic " +
                        ", count(q1.number) AS patient_number " +
                    "from " + 
                    "(    select " + 
                            "b_visit_clinic.visit_clinic_description AS clinic " +
                            ", count(t_diag_icd10.diag_icd10_number) AS number " +
                            ", t_diag_icd10.diag_icd10_vn " +
                        "from b_visit_clinic inner join t_diag_icd10 on " + 
                            "b_visit_clinic.b_visit_clinic_id = t_diag_icd10.b_visit_clinic_id " +
                        "where " +
                            "t_diag_icd10.diag_icd10_active = '1' " +
                            "and substring(diag_icd10_diagnosis_date,1,10) between '"+startdate+"' and '"+enddate+"' " +
                        "group by " + 
                            "b_visit_clinic.visit_clinic_description " +
                            ", t_diag_icd10.diag_icd10_vn " +
                    ") AS q1 " +
                    "group by " +
                        "q1.clinic";
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
