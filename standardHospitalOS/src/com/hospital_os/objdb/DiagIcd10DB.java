/*Source file: E:\\Job\\DrugReportSep\\code\\com\\hospital_os\\control\\PrescribeDB.java
*/
package com.hospital_os.objdb;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.object.*;
import java.util.*;
public class DiagIcd10DB extends X39DB
{
    protected DiagIcd10 dbObj;
    /**
     * @param ConnectionInf db
     * @roseuid 3F65897F0326
     */
    public DiagIcd10DB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = DiagIcd10.initConfig();
        dbObjX = dbObj;
    }
    public int insert(DiagIcd10 dx) throws Exception{
        return super.insert(dx);
    }
    /**
     * @deprecated henbe �ó��˹
     * @return
     * @throws Exception
     */
    public Vector selectAll() throws Exception
    {
        String sql="select * from " + dbObj.table;
        return eQuery(sql);
    }
    /**
     * @param pk
     * @return
     * @throws Exception
     */
    public DiagIcd10 selectByPK(String pk) throws Exception
    {
        return (DiagIcd10)this.selectPK(pk);
    }
    /**
     *
     * @deprecated henbe ���ͼԴ��ͧ�� visit_id
     */
    public Vector selectByVN(String pk) throws Exception
    {
        return selectByVisitId(pk);
    }
    /**
     *@author henbe 
     *just move from com.hosv3
     */
    public Vector<X39Persistent> selectByVNNoSort(String pk) throws Exception
    {
        String sql="select * from " + dbObj.table +
                " where " + dbObj.visit_id + " = ? " +
                " AND " + dbObj.diag_icd10_active + "=?" +
                " order by " + dbObj.type + "," + dbObj.diag_icd10_record_date_time + " ASC" ;
        return eQueryX(sql,new String[]{pk,Active.isEnable()});
    }
    
    /**
     *@author henbe 
     *just move from com.hosv3
     */
    public Vector<X39Persistent> selectByVidSort(String pk) throws Exception
    {
        String sql="select * from " + dbObj.table +
                " where " + dbObj.visit_id + " = ? " +
                " AND " + dbObj.diag_icd10_active + "=?" +
                " order by " + dbObj.type + "," + dbObj.diag_icd10_record_date_time ;
        return eQueryX(sql,new String[]{pk,Active.isEnable()});
    } 
    /**
     * ��㹡��ź������ ICD10 ����Ţ visit_id ��� �Ţ ICD10 
     * @param o �� Object �ͧ DiagICD10
     * @deprecated henbe unused ��ͧ���� update Object ��� ���¡��� db.update(x) ᷹��÷�Ẻ���
     */
    public int deleteByVidIcd10(String vid,String icd10,String date_time,String eid) throws Exception
    {
        String sql = "UPDATE " + dbObj.table +
              " SET " + dbObj.diag_icd10_active + " ='0'" +
               "," + dbObj.diag_icd10_cancel_date_time + " ='"  + date_time + "'" +  
               "," + dbObj.diag_icd10_staff_cancel + " ='"  + eid + "'" +
               " WHERE "  + dbObj.visit_id + " ='" + vid + "'" +
               " AND " +dbObj.icd10_code + " ='" + icd10 + "'";
        return theConnectionInf.eUpdate(sql);
    }
    /**
     *
     * @param visit_id
     * @param icd_code
     * @param date_time
     * @param emp_id
     * @return
     * @throws Exception
     */
    public int deleteByIcdCode(String visit_id,String icd_code,String date_time,String emp_id) throws Exception
    {
        String sql = "UPDATE " + dbObj.table +
              " SET " + dbObj.diag_icd10_active + " =?" +
               "," + dbObj.diag_icd10_cancel_date_time + " =?" +
               "," + dbObj.diag_icd10_staff_cancel + " =?" +
               " WHERE "  + dbObj.visit_id + " =?" +
               " AND " +dbObj.icd10_code + " =?";
        //'0''"  + date_time + "''"  + emp_id + "''" + visit_id + "''" + icd_code + "'
        return eUpdate(sql,new String[]{Active.isDisable(),date_time,emp_id,visit_id,icd_code});
    }
    /**
     *
     * @deprecated henbe unused ��ͧ���� update Object ��� ���¡��� db.update(x) ᷹��÷�Ẻ���
     * @param o
     * @return
     * @throws Exception
     */
    public int delete(DiagIcd10 o) throws Exception
    {
        String sql = "UPDATE " + dbObj.table +
              " SET " + dbObj.diag_icd10_active + " ='"  + o.diag_icd10_active + "'" +
               "," + dbObj.diag_icd10_cancel_date_time + " ='"  + o.diag_icd10_cancel_date_time + "'" +
               "," + dbObj.diag_icd10_staff_cancel + " ='"  + o.diag_icd10_staff_cancel + "'" +
               " WHERE " + dbObj.pk_field + " ='" + o.getObjectId() + "'";
                 
        return theConnectionInf.eUpdate(sql);
    }
    /**
     *@see: ������ key id �����ʹ� active
     */
    public DiagIcd10 selectByPKNotActive(String pk) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.pk_field
        + " = ?";
        return (DiagIcd10)eQueryX1(sql,new String[]{pk});
    }
    /**
     *default active =1
     */
    public DiagIcd10 selectByVisitIdAndCode(String visit_id,String code) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.visit_id+ " = ?" +
        " AND " + dbObj.icd10_code  + " = ?" +
        " AND " + dbObj.diag_icd10_active + " = ?";
        return (DiagIcd10)eQueryX1(sql,new String[]{visit_id,code,Active.isEnable()});
    }
    
    /*////////////////////////////////////////////////////////////////////////////
*/
    /**
     * @author henbe
     * ����¹������������ѹ�Դ�Ӿٴ
     */
    public Vector<X39Persistent> selectByVisitId(String pk) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.visit_id + " = ? "+
        " AND " + dbObj.diag_icd10_active + " =?";
        sql = sql +  " order by " + dbObj.type ;
        return eQueryX(sql,new String[]{pk,Active.isEnable()});
    }

}
