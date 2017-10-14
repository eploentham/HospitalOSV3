/*
 * GeneralPCUControl.java
 *
 * Created on 11 กุมภาพันธ์ 2549, 9:56 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.generalpcu.control;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.generalpcu.objdb.ManageDB;
import com.hosv3.control.HosControl;
import java.util.Vector;
import java.util.Hashtable;
import com.generalpcu.object.ResidentAgeGroup;
import com.generalpcu.object.RPRescidentPCU;
import com.hospital_os.utility.DateUtil;
import com.hosv3.object.UseCase;
import com.hosv3.utility.Config;
import javax.swing.JFrame;
/**
 *
 * @author pu
 */
public class GeneralPCUControl
{
    private ConnectionInf theConnectionInf;
    private ManageDB theManageDB;
    private Vector vcData;
    private Vector vTemp;
    private Vector vAgeGroup;
    private String startDate;
    private String finishDate;
    private String ageBegin;
    private String ageEnd;
    private ResidentAgeGroup theResidentAgeGroup;
    private RPRescidentPCU theRPRescidentPCU;
    private String[] headColumn;
    private String[] column;
    private String[] dataAge;
    private Vector vDataTemp;
    private Vector vData;
    private Vector vDataQuery;
    private int currenti = 0;
    private int columnsize;
    private String start;
    private String end;
    private HosControl theHC;
    
    /** Creates a new instance of GeneralPCUControl */
    public GeneralPCUControl(HosControl hc,ManageDB mdb)
    {
        theHC = hc;
        theManageDB = mdb;
        theConnectionInf = theManageDB.theConnectionInf; 
        theConnectionInf.open();
        try
        {
            this.vAgeGroup = theManageDB.theResidentAgeGroupDB.selectAll();            
            if(this.vAgeGroup != null)
            {
                this.columnsize = (this.vAgeGroup.size() * 3) + 6;
            }
            else
            {
                this.columnsize = 6;
            }
            //this.theManageDB.theRPPcuResidentDB.setVectorAgeGroup(this.vAgeGroup); 
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally{
            theConnectionInf.close();
        }
    }
    
    /**
     *กำหนดค่าให้กับวันที่เริ่มต้น และสิ้นสุด
     *@Author pu
     *@Date 11/02/2006
     */
    public boolean setDateForQuery(String startDate, String endDate)
    {
        this.startDate = startDate;
        this.finishDate = endDate;
        return true;
    }
    
    /**
     *ค้นหาจำนวนเด็ก 0-6 ปี ทั้งหมดที่อยู่ในเขต ตามวันที่เริ่มต้น และสิ้นสุด 
     *สำหรับรายงานโภชนาการ
     *@return Vector ของจำนวนเด็ก 0-6ปี
     *@Author pu
     *@Date 11/02/2006
     */
    public Vector queryNutritionByDate(boolean dbBackup)
    {
        Vector vLevelSome = new Vector();
        if(dbBackup){
            theConnectionInf = Config.getConnectionInfDBBackupFromFile(new JFrame());
        }else{
            theConnectionInf = theManageDB.theConnectionInf;
        }
        theConnectionInf.open();
        vcData = new Vector();
        Vector vctemp = null;
        try
        {
            theManageDB.theRPPcuNutritionDB.theConnectionInf = theConnectionInf;
           this.vTemp = theManageDB.theRPPcuNutritionDB.queryChildAllNutritionByDate(this.startDate, this.finishDate);
           if(this.vTemp != null)
           {
                vctemp = new Vector();
                vLevelSome = theManageDB.theRPPcuNutritionDB.queryNutritrionByCriteria(this.startDate, this.finishDate);
           }
           this.vcData = getJoinedVectorNutrtion(vLevelSome);
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "Nutritrion",
                    "ฐานข้อมูล : "+
                    theConnectionInf.getURL()+
                    " ช่วงเวลา "+
                    startDate+"-"+
                    finishDate,
                    theHC.theUS.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "Nutritrion", ex, theHC.theUS.ERROR);
            theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vcData;
    }
    
    /**
     *ค้นหาจำนวนเด็ก 0-6 ปีที่มีข้อมูลการลง Vitalsign การชั่งน้ำหนัก และค่า BMI 
     *และ นับจำนวนเด็ก 0-6 ปี แยกตามระดับโภชนาการ ตามวันที่เริ่มต้น และสิ้นสุด 
     *สำหรับรายงานโภชนาการ
     *@return Vector ของจำนวนเด็ก 0-6ปี
     *@Author pu
     *@Date 28/04/2006
     */
    public Vector queryNutritrionByCriteria()
    {
        vcData = new Vector();
        theConnectionInf.open();
        try
        {
            vcData = theManageDB.theRPPcuNutritionDB.queryNutritrionByCriteria(this.startDate, this.finishDate);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vcData;
    }
    
    
    /**
     * สำหรับตรวจสอบข้อมูลรายงานว่าต้องการดึงข้อมูลรายงานแบบไหน
     * @Param selectReport1 คือ เลือกว่าออกรายงานแบบไหน 1 คือ จำนวน , 2 คือ รายชื่อ 
     * @Param selectReport2 คือ การเลือกว่าผู้ป่วยเสียชีวิตหรือไม่ 1 คือ ยังมีชีวิต , 2 คือ เสียชีวิต
     * @Param selectReport3 คือ เลือกว่าผู้ป่วยในเขต หรือทั้งหมด 1 คือ ในเขต , 2 คือ ทั้งหมด(ในเขต+นอกเขต) 
     * @Param selectVillage คือ รหัสบ้านที่เลือก 
     * @Return Vector ของ หัวตารางและข้อมูลจำนวนผู้ป่วยที่ได้จกการค้นหา
     * @Author Ojika
     * @Date 11/02/2549
     */
    public Vector selectChronicPcu(int selectReport1, int selectReport2, int selectReport3, String selectVillage, String endDate, boolean isDbBackup)
    {
        vcData = new Vector();
        
        if(selectReport1 == 1)
        {
            // ค้นจำนวน
            vcData = queryPatientAmount(selectReport2, selectReport3, selectVillage, endDate, isDbBackup);
        }
        else if(selectReport1 == 2)
        {
            // ค้นรายชื่อ
            vcData = queryPatientName(selectReport2, selectReport3, selectVillage, endDate, isDbBackup);
        }
        return vcData;
    }
    
    /*
     * ค้นหาจำนวนผู้ป่วยตามเงื่อนไขที่กำหนดมา
     * @Param selectReport2 คือ การเลือกว่าผู้ป่วยเสียชีวิตหรือไม่ 1 คือ ยังมีชีวิต , 2 คือ เสียชีวิต
     * @Param selectReport3 คือ เลือกว่าผู้ป่วยในเขต หรือทั้งหมด 1 คือ ในเขต , 2 คือ ทั้งหมด(ในเขต+นอกเขต) 
     * @Param selectVillage คือ รหัสบ้านที่เลือก 
     * @Return Vector ของ หัวตารางและข้อมูลจำนวนผู้ป่วยที่ได้จกการค้นหา
     * @Author Ojika
     * @Date 11/02/2549
     **/
    public Vector queryPatientAmount(int selectReport2, int selectReport3, String selectVillage, String endDate, boolean isDbBackup)
    {
        Vector vc = new Vector();
        if(isDbBackup){
            theConnectionInf = Config.getConnectionInfDBBackupFromFile(new JFrame());
        }else{
            theConnectionInf = theManageDB.theConnectionInf;
        }
        theConnectionInf.open();
        try
        {
            theManageDB.theRPPcuChronicDB.theConnectionInf = theConnectionInf;
           vc = theManageDB.theRPPcuChronicDB.queryPatientAmount(selectReport2, selectReport3, selectVillage, endDate);
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "PatientAmount",
                    "ฐานข้อมูล : "+
                    theConnectionInf.getURL()+
                    " ช่วงเวลา "+
                    startDate+"-"+
                    finishDate,
                    theHC.theUS.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "PatientAmount", ex, theHC.theUS.ERROR);
            theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vc;
    }
    
    /*
     * ค้นหารายชื่อผู้ป่วยตามเงื่อนไขที่กำหนดมา
     * @Param selectReport2 คือ การเลือกว่าผู้ป่วยเสียชีวิตหรือไม่ 1 คือ ยังมีชีวิต , 2 คือ เสียชีวิต
     * @Param selectReport3 คือ เลือกว่าผู้ป่วยในเขต หรือทั้งหมด 1 คือ ในเขต , 2 คือ ทั้งหมด(ในเขต+นอกเขต) 
     * @Param selectVillage คือ รหัสบ้านที่เลือก 
     * @Return Vector ของ หัวตารางและข้อมูลจำนวนผู้ป่วยที่ได้จกการค้นหา
     * @Author Ojika
     * @Date 11/02/2549
     **/
    public Vector queryPatientName(int selectReport2, int selectReport3, String selectVillage, String endDate, boolean isDbBackup)
    {
        Vector vc = new Vector();
        if(isDbBackup){
            theConnectionInf = Config.getConnectionInfDBBackupFromFile(new JFrame());
        }else{
            theConnectionInf = theManageDB.theConnectionInf;
        }
        theConnectionInf.open();
        try
        {
            theManageDB.theRPPcuChronicDB.theConnectionInf = theConnectionInf;
           vc = theManageDB.theRPPcuChronicDB.queryPatientName(selectReport2, selectReport3, selectVillage, endDate);
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "PatientName",
                    "ฐานข้อมูล : "+
                    theConnectionInf.getURL()+
                    " ช่วงเวลา "+
                    startDate+"-"+
                    finishDate,
                    theHC.theUS.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "PatientName", ex, theHC.theUS.ERROR);
            theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vc;
    }
    
    /**
     *ค้นหาข้อมูลรายงานจำนวนผู้ป่วยโรคไม่ติดต่อ ตามวันที่เริ่มต้น และสิ้นสุด
     *@param village เป็น String ของรหัสหมู่บ้านที่ต้องการค้นหา
     *@Param disease เป็น String ของรหัสโรคไม่ติดต่อที่ต้องการค้นหา
     *@return Vector ของข้อูลจำนวนผู้ป่วยโรคไม่ติดต่อ
     *@Author pu
     *@Date 23/02/2006
     */
    public Vector queryUncontagiousPatientAmount(String village,String disease, boolean isDbBackup)
    {
         vcData = new Vector();
        if(isDbBackup){
            theConnectionInf = Config.getConnectionInfDBBackupFromFile(new JFrame());
        }else{
            theConnectionInf = theManageDB.theConnectionInf;
        }
        theConnectionInf.open();
        try
        {
            theManageDB.theRPPcuUncontagiousDB.theConnectionInf = theConnectionInf;
           vcData = theManageDB.theRPPcuUncontagiousDB.queryPatientAmountByDate(village,disease,this.startDate, this.finishDate);
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "PatientAmountByDate",
                    "ฐานข้อมูล : "+
                    theConnectionInf.getURL()+
                    " ช่วงเวลา "+
                    startDate+"-"+
                    finishDate,
                    theHC.theUS.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "PatientAmountByDate", ex, theHC.theUS.ERROR);
            theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vcData;
    }
    
    /**
     *ค้นหาข้อมูลรายชื่อผู้ป่วยโรคไม่ติดต่อ ตามวันที่เริ่มต้น และสิ้นสุด
     *@param village เป็น String ของรหัสหมู่บ้านที่ต้องการค้นหา
     *@Param disease เป็น String ของรหัสโรคไม่ติดต่อที่ต้องการค้นหา
     *@return Vector ของข้อมูลจำนวนผู้ป่วยโรคไม่ติดต่อ ตามวันที่เริ่มต้น และสิ้นสุด
     *@Author pu
     *@Date 23/02/2006
     */
    public Vector queryUncontagiousPatientName(String village,String disease, boolean isDbBackup)
    {
        vcData = new Vector();
        if(isDbBackup){
            theConnectionInf = Config.getConnectionInfDBBackupFromFile(new JFrame());
        }else{
            theConnectionInf = theManageDB.theConnectionInf;
        }
        theConnectionInf.open();
        try
        {
            theManageDB.theRPPcuUncontagiousDB.theConnectionInf = theConnectionInf;
            vcData = theManageDB.theRPPcuUncontagiousDB.queryPatienNametByDate(village,disease,this.startDate, this.finishDate);
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "PatienNametByDate",
                    "ฐานข้อมูล : "+
                    theConnectionInf.getURL()+
                    " ช่วงเวลา "+
                    startDate+"-"+
                    finishDate,
                    theHC.theUS.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "PatienNametByDate", ex, theHC.theUS.ERROR);
            theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vcData;
    }
    
    /**
     *ค้นหาข้อมูลรายงานจำนวนประชากร แยกตามกลุ่มอายุ จำแนกตามหมู่บ้าน
     *@param village เป็น String ของรหัสหมู่บ้านที่ต้องการค้นหา
     *@param StartAge เป็น String ของอายุเริ่มต้นที่ต้องการดึงข้อมูล
     *@param EndAge เป็น String ของอายุสิ้นสุดที่ต้องการดึงข้อมูล
     *@param AgePortion เป็น String ของรหัสของช่วงอายุที่ต้องการดึงข้อมูล 
     *@param isPortion เป็น Integer ของสถานะการดึงข้อมูล ว่าเป็นการระบุช่วงอายุเอง หรือเลือกจาก ComboBox 
     *                        ถ้า 0 เป็นการระบุช่วงอายุโดยการเลือกจาก ComboBox 
     *                        ถ้า 1 เป็นการระบุช่วงอายุเอง 
     *@return Vector ของข้อมูลจำนวนประชากร แยกตามกลุ่มอายุ จำแนกตามหมู่บ้าน
     *@Author pu
     *@Date 04/03/2006
     */
   public Vector queryResidentAgePortion(String village, String startAge,String endAge
           ,String agePortion,int isSpecifyAge,int isPortion,Vector vStartEndAge, boolean isDbBackup)
    {
        Vector vAgePortionSome = new Vector();
        if(isDbBackup){
            theConnectionInf = Config.getConnectionInfDBBackupFromFile(new JFrame());
        }else{
            theConnectionInf = theManageDB.theConnectionInf;
        }
        theConnectionInf.open();
        Vector vctemp = null;
        try
        {   //เลือกช่วงอายุจาก ComboBox 
            if(isPortion == 1)
            {
                //เลือกช่วงอายุทั้งหมด
                if(agePortion.equals("0"))
                {
                    this.currenti = 0;                  
                    if(setStartEndAge(vStartEndAge))
                    {
                        theManageDB.theRPPcuResidentDB.theConnectionInf = theConnectionInf;
                        this.vTemp = theManageDB.theRPPcuResidentDB.queryResidentAgePortionAllByDate(
                                village
                                , DateUtil.getTextCurrentDate(theConnectionInf)
                                , this.start
                                , this.end
                                , this.columnsize);
                        ////rp1
                        if(this.vAgeGroup != null)
                        {
                            int size = this.vAgeGroup.size();
                            vctemp = new Vector();
                            for(int i=0;i<size;i++)
                            {
                                startAge = ((ResidentAgeGroup)this.vAgeGroup.get(i)).begin;
                                endAge = ((ResidentAgeGroup)this.vAgeGroup.get(i)).end;
                                ////rp2

                                vAgePortionSome = theManageDB.theRPPcuResidentDB.queryResidentAgeGroupByDate(village, DateUtil.getTextCurrentDate(this.theConnectionInf),this.startDate, this.finishDate, startAge, endAge);
                                vctemp.add(vAgePortionSome);
                            }
                            this.vcData = getJoinedVectorResident(vctemp);
                        }
                    }
                    else
                    {                        
                        this.vcData= null;// queryAllResident(village);
                    }
                }
                //เลือกช่วงอายุ
                else
                {
                    setAgePortionData(agePortion);
                    theManageDB.theRPPcuResidentDB.theConnectionInf = theConnectionInf;
                    this.vcData = theManageDB.theRPPcuResidentDB.queryResidentAgePortionByDate(village,this.startDate, this.finishDate, this.ageBegin, this.ageEnd);
                }
            }
            //ระบุช่วงอายุเอง
            else
            {
                theManageDB.theRPPcuResidentDB.theConnectionInf = theConnectionInf;
                this.vcData = theManageDB.theRPPcuResidentDB.queryResidentAgePortionByDate(village, this.startDate , this.finishDate, startAge, endAge);
            }
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "ResidentAgePortion",
                    "ฐานข้อมูล : "+
                    theConnectionInf.getURL()+
                    " ช่วงเวลา "+
                    startDate+"-"+
                    finishDate,
                    theHC.theUS.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "ResidentAgePortion", ex, theHC.theUS.ERROR);
            theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vcData;
    }
   
   /**
    *โปรแกรมจะนำ Vector ของข้อมูลตั้งแต่ 2 Vector ขึ้นไปมาต่อกัน 
    *โดยจะมี Vector ตัวหลัก ที่เป็น Vector ที่เก็บข้อมูลทั้งหมด แล้วนำ Vector ย่อยมาต่อรวมกับ Vector หลัก
    *วิธีการต่อ  จะต่อหัว Column ให้เสร็จก่อน ค่อยนำข้อมูลมาต่อกัน
    *@param vc เป็น Vector ที่ต้องการนำมาต่อ
    *@Author pu + Ojika
    *@Date 10/03/2006
    */
   private Vector getJoinedVectorResident(Vector vc)
   {
       int size = 0;
       Vector vcData = new Vector();
       Vector vcAge = new Vector();
       if(vc != null && vc.size() >0)
       {
           size = vc.size();
           Vector vcage = null;
           int round =0;
           String[] head,head1 ;
           Vector data,data1;
           //หัว
           head = (String[])vTemp.get(0);
           //ข้อมูล
           data = (Vector)vTemp.get(1);       
           String[] dataarray=null,dataarray1 ;
           int currentm = 0; 
           int currentx =0;

                //array 0 check is dataarray[0]
               //จำนวนช่วงอายุ
               for(int j=0 ; j< size;j++)
               {               
                   vcage = (Vector)vc.get(j);
                   //หัว
                   head1 = (String[])vcage.get(0);                   
                   int n = 0;
                   for(int m=currentm; m<(3+currentm) ; m++)
                   {                       
                       head[m+6] = head1[n+1];
                       n++;
                   }       
                   currentm = currentm + 3;
               }
           
                   int position = 0;
                   for(int k=0; k<data.size()-1;k++)  // Loop ตามหมู่บ้าน
                   {    
                       dataarray = (String[])data.get(k); 
                                              
                       for(int x=0; x<3; x++) // เพศ
                       {
                           position = x;                         
                           for(int z=0;z<size;z++)//ช่วงอายุ
                           {         
                                vcage = (Vector)vc.get(z);
                                data1 = (Vector)vcage.get(1);
                                dataarray1 = (String[])data1.get(k);
                                dataarray[position+6] = dataarray1[x+1]; 
                                position = position + 3;
                           }
                           dataarray1 = null;
                       }        
                       
                       vcAge.add(dataarray);
                       dataarray = null;
                   }
                   currentx = currentx + 3;
                   vcage =null;
                   head1 =null;
                   data1 = null;
                   dataarray =null;
                   vcData.add(head);
                   vcData.add(vcAge);
       }       
        return vcData;       
   }
   
   /**
    *โปรแกรมจะนำ Vector ของข้อมูลตั้งแต่ 2 Vector ขึ้นไปมาต่อกัน 
    *โดยจะมี Vector ตัวหลัก ที่เป็น Vector ที่เก็บข้อมูลทั้งหมด แล้วนำ Vector ย่อยมาต่อรวมกับ Vector หลัก
    *วิธีการต่อ  จะต่อหัว Column ให้เสร็จก่อน ค่อยนำข้อมูลมาต่อกัน
    *@param vc เป็น Vector ที่ต้องการนำมาต่อ
    *@Author pu
    *@Date 28/04/2006
    */   
   private Vector getJoinedVectorNutrtion(Vector vc)
   {
       Vector vcData = new Vector();
       Vector vcLevel = new Vector();
       int size = 0;
       int size_head = 0;
       int size_head1 = 0; 
       int size_head2 = 0;

       if(vc != null && vc.size() >0)
       {
           size = vc.size();
           
           String[] head,head1;
           Vector vdata,vdata1;
           String[] dataarray=null,dataarray1 ;
           //หัว                 
           head = (String[])this.vTemp.get(0);//หัวตัวหลัก
           head1 = (String[])vc.get(0);//หัวตัวย่อย   
           size_head = head.length;
           size_head1 = head1.length;
          
           //ข้อมูล
           vdata = (Vector)vTemp.get(1);//ข้อมูลตัวหลัก
           vdata1 = (Vector)vc.get(1);//ข้อมูลตัวย่อย
           
           String[] head2  = new String[head.length+(head1.length-1)];
           size_head2 = head2.length;
           //นำหัวของข้อมูลหลักมาเก็บใน array ใหม่ที่ใหญ่กว่าเดิม         
           for(int i=0;i<size_head;i++)
           {
               head2[i] = head[i];
           }  
           //ต่อหัว column   
           for(int j=0;j<size_head1-1;j++)
           {
               head2[j+size_head] = head1[j+1];
           }
           
           //ต่อข้อมูลหลักกับข้อมูลย่อย
           String[] dataarray2;
           int size2 = 0;
           for(int j=0; j<vdata.size();j++)  // Loop ตามหมู่บ้าน
           {
               dataarray = (String[])vdata.get(j); //ข้อมูลหลักแถวที่ j     
               dataarray1 = (String[])vdata1.get(j); //ข้อมูลย่อยแถวที่ j
               dataarray2 = new String[dataarray.length+(dataarray1.length-1)];
               size2 = dataarray1.length;
               
               for(int k=0;k<dataarray.length;k++)
               {
                   dataarray2[k] = dataarray[k];
               }    
               
               for(int z=0;z<size2-1;z++)// Loop ตาม Level
               {
                 dataarray2[z+dataarray.length] = dataarray1[z+1];      
               }
               vcLevel.add(dataarray2);
               dataarray2 = null;
           }        
           head1 =null;
           vdata1 = null;
           dataarray =null;
           dataarray1 = null;
           vcData.add(head2);
           vcData.add(vcLevel);           
       }
       return vcData;
   }


   /**
    *ในกรณีผู้ใช้เลือกช่วงอายุจาก ComboBox โปรแกรมจะทำการหาว่า
    *ช่วงอายุที่ส่งมา มีอายุเริ่มต้นที่เท่าไร และอายุสิ้นสุดที่เท่าไร
    *โดยการค้นหาจาก Key id ของช่วงอายุที่ส่งมา เปรียบเทียบกับ Key id ของช่วงอายุทั้งหมด
    *ถ้า key id ตรงกัน โปรแกรมจะนำอายุเริ่มต้น มาเก็บไว้ในตัวแปร ageBegin และสิ้นสุด มาเก็บไว้ในตัวแปร ageEnd
    *@param agePortion เป็น String ของ Key id ของช่วงอายุที่ถูกเลือก
    *@Author pu
    *@Date 03/03/2006
    */
   private void setAgePortionData(String agePortion)
   {
       String pk = "";
       if(this.vAgeGroup != null)
       {
           for(int i=0;i<this.vAgeGroup.size();i++)
           {
               pk = ((ResidentAgeGroup)this.vAgeGroup.get(i)).getObjectId();
               if(pk.equals(agePortion))
               {
                   this.ageBegin = ((ResidentAgeGroup)this.vAgeGroup.get(i)).begin;
                   this.ageEnd = ((ResidentAgeGroup)this.vAgeGroup.get(i)).end;
               }
           }
       }
   }
   
   /**
    *อายุเริ่มต้น และอายุที่มากที่สุดของช่วงอายุทั้งหมด
    *@Author pu
    *@Date 03/04/2006
    */
   private boolean setStartEndAge(Vector vc)
   {
       boolean haveAge = false;
       if(vc != null && vc.size() != 0)
       {
           this.start = (String)vc.get(0);
           this.end = (String)vc.get(1);
           haveAge = true;
       }
       else
       {
           this.start = "";
           this.end = "";
           haveAge = false;
       }
       return haveAge;
   }
   
   /**
    *รายชื่อเด็กอายุไม่เกิน 72 เดือน (0-5 ปี) จำแนกตามหมู่บ้าน
    *@param String village รหัสหมู่บ้านที่ต้องการค้นหา
    *@return Vector ของข้อมูลเด็กอายุไม่เกิน 72 เดือน (0-5 ปี) จำแนกตามหมู่บ้าน
    *@Author pu
    *@Date 07/03/2006
    */
   public Vector queryResidentChild0_5(String village, boolean isDbBackup)
   {
       vcData = new Vector();
       if(isDbBackup){
            theConnectionInf = Config.getConnectionInfDBBackupFromFile(new JFrame());
        }else{
            theConnectionInf = theManageDB.theConnectionInf;
        }
       theConnectionInf.open();
       try
       {
           theManageDB.theRPPcuResidentDB.theConnectionInf = theConnectionInf;
           vcData = theManageDB.theRPPcuResidentDB.queryResidentAgeChildByDate(village,DateUtil.getTextCurrentDate(this.theConnectionInf), this.startDate, this.finishDate);

            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "ResidentAgeChildByDate",
                    "ฐานข้อมูล : "+
                    theConnectionInf.getURL()+
                    " ช่วงเวลา "+
                    startDate+"-"+
                    finishDate,
                    theHC.theUS.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "ResidentAgeChildByDate", ex, theHC.theUS.ERROR);
           theConnectionInf.rollback();
       }
       finally
       {
           theConnectionInf.close();
       }
       return vcData;
   }
   
   /**
    *ค้นหาข้อมูลรายงานประชากรที่อยู่ในเขตรับผิดชอบ เพศหญิง อายุ มากกว่า 15 ปี
    *@param String village รหัสหมู่บ้านที่ต้องการค้นหา
    *@return Vector ของข้อมูลประชากรเพศหญิง อายุ มากกว่า 15 ปี
    *@Author pu
    *@Date 07/03/2006
    */
   public Vector queryResidentWomenUpper15(String village, boolean isDbBackup)
   {
       vcData = new Vector();
       if(isDbBackup){
            theConnectionInf = Config.getConnectionInfDBBackupFromFile(new JFrame());
        }else{
            theConnectionInf = theManageDB.theConnectionInf;
        }
       theConnectionInf.open();
       try
       {
           theManageDB.theRPPcuResidentDB.theConnectionInf = theConnectionInf;
           vcData = theManageDB.theRPPcuResidentDB.queryResidentWomenUpper15ByDate(village, DateUtil.getTextCurrentDate(this.theConnectionInf),this.startDate, this.finishDate);
       
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "ResidentWomenUpper15ByDate",
                    "ฐานข้อมูล : "+
                    theConnectionInf.getURL()+
                    " ช่วงเวลา "+
                    startDate+"-"+
                    finishDate,
                    theHC.theUS.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "ResidentWomenUpper15ByDate", ex, theHC.theUS.ERROR);
           theConnectionInf.rollback();
       }
       finally
       {
           theConnectionInf.close();
       }
       return vcData;
   }
   
   /**
    *ค้นหาข้อมูลรายงานการเสียชีวิตของประชากร แต่ละหมู่บ้าน พร้อมระบุสาเหตุการเสียชีวิต
    *@param String village รหัสหมู่บ้านที่ต้องการค้นหา
    *@return Vector ของข้อมูลจำนวนประชากรที่เสียชีวิต
    *@Author pu
    *@Date 07/03/2006
    */
   public Vector queryResidentDeath(String village, boolean isDbBackup)
   {
       vcData = new Vector();
       if(isDbBackup){
            theConnectionInf = Config.getConnectionInfDBBackupFromFile(new JFrame());
        }else{
            theConnectionInf = theManageDB.theConnectionInf;
        }
       theConnectionInf.open();
       try
       {
           theManageDB.theRPPcuResidentDB.theConnectionInf = theConnectionInf;
           vcData = theManageDB.theRPPcuResidentDB.queryResidentDeathByDate(village, this.startDate, this.finishDate);
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "ResidentDeathByDate",
                    "ฐานข้อมูล : "+
                    theConnectionInf.getURL()+
                    " ช่วงเวลา "+
                    startDate+"-"+
                    finishDate,
                    theHC.theUS.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "ResidentDeathByDate", ex, theHC.theUS.ERROR);
           theConnectionInf.rollback();
       }
       finally
       {
           theConnectionInf.close();
       }
       return vcData;
   }
   
   /**
    *ค้นหาข้อมูลรายชื่อหญิงตั้งครรภ์ ที่มารับบริการ ANC แยกตามหมู่บ้าน
    *@param village เป็น String รหัสหมู่บ้านที่ต้องการค้นหา
    *@return Vector ของรายชื่อหญิงตั้งครรภ์ 
    *@Author pu
    *@Date 13/03/2006
    */
   public Vector queryPregnanceANC(String village, boolean isDbBackup)
   {
       vcData = new Vector();
       if(isDbBackup){
            theConnectionInf = Config.getConnectionInfDBBackupFromFile(new JFrame());
        }else{
            theConnectionInf = theManageDB.theConnectionInf;
        }
       theConnectionInf.open();
       try
       {
           theManageDB.theRPPcuPregnanceANCDB.theConnectionInf = theConnectionInf;
           vcData = theManageDB.theRPPcuPregnanceANCDB.queryPregnanceANCByDate(village, this.startDate, this.finishDate);
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "PregnanceANCByDate",
                    "ฐานข้อมูล : "+
                    theConnectionInf.getURL()+
                    " ช่วงเวลา "+
                    startDate+"-"+
                    finishDate,
                    theHC.theUS.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "PregnanceANCByDate", ex, theHC.theUS.ERROR);
           theConnectionInf.rollback();
       }
       finally
       {
           theConnectionInf.close();
       }
       return vcData;
   }
   
   /**
    *ค้นหาข้อมูลรายชื่อผู้พิการ แยกกลุ่มความพิการ และหมู่บ้าน
    *@param village เป็น String รหัสหมู่บ้านที่ต้องการค้นหา
    *@param maimtype เป็น String รหัสกลุ่มความพิการ
    *@param treatstr เป็น String รหัสสถานะการบำบัดรักษา
    *@param registrystr เป็น String รหัสสถานะการขึ้นทะเบียนผู้พิการ
    *@return Vector ของรายชื่อผู้พิการ
    *@Author pu
    *@Date 27/03/2006
    */
   public Vector queryPersonMaim(String village,String maimtype,String treatstr,String registrystr, boolean isDbBackup)
   {
       vcData = new Vector();
       if(isDbBackup){
            theConnectionInf = Config.getConnectionInfDBBackupFromFile(new JFrame());
        }else{
            theConnectionInf = theManageDB.theConnectionInf;
        }
       theConnectionInf.open();
       try
       {
           theManageDB.theRPPcuMaimDB.theConnectionInf = theConnectionInf;
           vcData = theManageDB.theRPPcuMaimDB.queryPersonMaimByDate(village,maimtype, treatstr, registrystr,this.startDate, this.finishDate);
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "PersonMaimByDate",
                    "ฐานข้อมูล : "+
                    theConnectionInf.getURL()+
                    " ช่วงเวลา "+
                    startDate+"-"+
                    finishDate,
                    theHC.theUS.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "PersonMaimByDate", ex, theHC.theUS.ERROR);
           theConnectionInf.rollback();
       }
       finally
       {
           theConnectionInf.close();
       }
       return vcData;
   }

   /**
    *ค้นหาจำนวนประชากรทั้งหมด แยกตามเพศและหมู่บ้าน
    *@param village เป็น String รหัสหมู่บ้านที่ต้องการค้นหา
    *@return Vector ของจำนวนประชากรทั้งหมด
    *@Author pu
    *@Date 03/04/2006
    */
   public Vector queryAllResident(String village, boolean isDbBackup)
   {
       vcData = new Vector();
       if(isDbBackup){
            theConnectionInf = Config.getConnectionInfDBBackupFromFile(new JFrame());
        }else{
            theConnectionInf = theManageDB.theConnectionInf;
        }
       theConnectionInf.open();
       try
       {
           theManageDB.theRPPcuResidentDB.theConnectionInf = theConnectionInf;
           vcData = theManageDB.theRPPcuResidentDB.queryAllResidentByDate(village);
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "AllResidentByDate",
                    "ฐานข้อมูล : "+
                    theConnectionInf.getURL()+
                    " ช่วงเวลา "+
                    startDate+"-"+
                    finishDate,
                    theHC.theUS.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "AllResidentByDate", ex, theHC.theUS.ERROR);
           theConnectionInf.rollback();
       }
       finally
       {
           theConnectionInf.close();
       }
       return vcData;
   }
   
   /**
     * สำหรับตรวจสอบข้อมูลรายงานว่าต้องการดึงข้อมูลรายงานแบบไหน
     * @Param selectReport คือ เลือกว่าออกรายงานแบบไหน 1 คือ รายชื่อ , 2 คือ จำนวน wellbaby, 3 คือ จำนวน TT
     * @Param VillageId คือ รหัสบ้านที่เลือก
     * @Param EpiAgeGroupId คือ รหัสช่วงอายุ
     * @Param startDate คือ วันที่เริ่มต้นค้นหา
     * @Param endDate คือ วันที่สิ้นสุดการค้นหา
     * @Return Vector ของ หัวตารางและข้อมูลจำนวนผู้ป่วยที่ได้จกการค้นหา
     * @Author Ojika
     * @Date 31/03/2549
     */
    public Vector selectEpiPcu(int selectReport, String selectVillage, String EpiAgeGroupId, String startDate, String endDate,boolean isDbBackup)
    {
        vcData = new Vector();
        String[] headColumn = new String[] {""};
        Vector vcDataQuery = new Vector();
        if(selectReport == 1)
        {
            // ค้นรายชื่อ
            vcData = queryListEpiPerson(selectVillage,startDate,endDate,isDbBackup);
            
            if(vcData != null)
            {     
                // ดึงค่าจาก vector เพื่อแยกส่วนข้อมูลไปตรวจสอบ
                headColumn = (String[])vcData.get(0);
                vcDataQuery = (Vector)vcData.get(1);
                // ส่งข้อมูลไปตรวจสอบ ให้แสดงเฉพาะ Record แรกในข้อมูลเดียวกันเท่านั้น
                this.checkDataForShowReport(vcDataQuery);
                
                vcData = new Vector();
                vcData.add(headColumn);
                vcData.add(vcDataQuery);
            }
                        
        }
        else if(selectReport == 2)
        {
            // ค้นจำนวน Wellbaby
            vcData = queryAmountEpiWellbaby(selectVillage,EpiAgeGroupId,startDate,endDate,isDbBackup);
        }
        else if(selectReport == 3)
        {
            // ค้นจำนวน TT
            vcData = queryAmountEpiTT(selectVillage,startDate,endDate,isDbBackup);
        }
        
        headColumn = null;
        vcDataQuery = null;
        
        return vcData;
    }
    
    /**
     * ดึงข้อมูล รายชื่อเด็กที่ได้รับวัคซีน
     * @Author Ojika
     * @Date 31/03/2549
     **/
    public Vector queryListEpiPerson(String selectVillage, String startDate, String endDate, boolean isDbBackup)
   {
       vcData = new Vector();
       if(isDbBackup){
            theConnectionInf = Config.getConnectionInfDBBackupFromFile(new JFrame());
        }else{
            theConnectionInf = theManageDB.theConnectionInf;
        }
       theConnectionInf.open();
       try
       {
           theManageDB.theRPPcuEpiDB.theConnectionInf = theConnectionInf;
          vcData = theManageDB.theRPPcuEpiDB.queryListEpiPerson(selectVillage, startDate, endDate);
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "ListEpiPerson",
                    "ฐานข้อมูล : "+
                    theConnectionInf.getURL()+
                    " ช่วงเวลา "+
                    startDate+"-"+
                    finishDate,
                    theHC.theUS.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "ListEpiPerson", ex, theHC.theUS.ERROR);
           theConnectionInf.rollback();
       }
       finally
       {
           theConnectionInf.close();
       }
       return vcData;
   }
    
    /**
     * ดึงข้อมูล จำนวนผู้ที่ไดรับวัคซีนจาก Wellbaby ตามช่วงที่กำหนด
     * @Author Ojika
     * @Date 31/03/2549
     **/
    public Vector queryAmountEpiWellbaby(String selectVillage, String EpiAgeGroupId, String startDate, String endDate, boolean isDbBackup)
   {
       vcData = new Vector();
       if(isDbBackup){
            theConnectionInf = Config.getConnectionInfDBBackupFromFile(new JFrame());
        }else{
            theConnectionInf = theManageDB.theConnectionInf;
        }
       theConnectionInf.open();
       try
       {
           theManageDB.theRPPcuEpiDB.theConnectionInf = theConnectionInf;
          vcData = theManageDB.theRPPcuEpiDB.queryAmountEpiWellbaby(selectVillage, EpiAgeGroupId, startDate, endDate);
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "AmountEpiWellbaby",
                    "ฐานข้อมูล : "+
                    theConnectionInf.getURL()+
                    " ช่วงเวลา "+
                    startDate+"-"+
                    finishDate,
                    theHC.theUS.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "AmountEpiWellbaby", ex, theHC.theUS.ERROR);
           theConnectionInf.rollback();
       }
       finally
       {
           theConnectionInf.close();
       }
       return vcData;
   }
    
    /**
     * จำนวนผู้ได้รับวัคซีน ของคลินิกบาดทะยัก (TT)
     * @Author Ojika
     * @Date 31/03/2549
     **/
    public Vector queryAmountEpiTT(String selectVillage, String startDate, String endDate, boolean isDbBackup)
   {
       vcData = new Vector();
       if(isDbBackup){
            theConnectionInf = Config.getConnectionInfDBBackupFromFile(new JFrame());
        }else{
            theConnectionInf = theManageDB.theConnectionInf;
        }
       theConnectionInf.open();
       try
       {
           theManageDB.theRPPcuEpiDB.theConnectionInf = theConnectionInf;
          vcData = theManageDB.theRPPcuEpiDB.queryAmountEpiTT(selectVillage, startDate, endDate); 
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "AmountEpiTT",
                    "ฐานข้อมูล : "+
                    theConnectionInf.getURL()+
                    " ช่วงเวลา "+
                    startDate+"-"+
                    finishDate,
                    theHC.theUS.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "AmountEpiTT", ex, theHC.theUS.ERROR);
           theConnectionInf.rollback();
       }
       finally
       {
           theConnectionInf.close();
       }
       return vcData;
   }
    
    /**
     * สำหรับตรวจสอบข้อมูลที่เป็นข้อมูลเดียวกัน ให้แสดงเฉพาะ Record แรกเท่านั้น
     * @Author Ojika
     * @Date 03/04/2549
     **/
    public Vector checkDataForShowReport(Vector vc)
    {
        Vector vRowData = new Vector();
        String[] rowdataCheck = null;
        if(vc != null && vc.size() > 0)
        {
            for(int i=0,size=vc.size();i<size;i++)
            {
                String[] rowdata = (String[])vc.get(i);
                if(i == 0)
                {
                    rowdataCheck = (String[])vc.get(i);                                        
                }
                else
                {        
                        // ตรวจสอบข้อมูลประชากร
                        if(rowdata[2].equalsIgnoreCase(rowdataCheck[2]) 
                            && rowdata[3].equalsIgnoreCase(rowdataCheck[3])
                            && rowdata[4].equalsIgnoreCase(rowdataCheck[4])
                            && rowdata[5].equalsIgnoreCase(rowdataCheck[5])
                            && rowdata[6].equalsIgnoreCase(rowdataCheck[6])
                            && rowdata[7].equalsIgnoreCase(rowdataCheck[7])
                            && rowdata[8].equalsIgnoreCase(rowdataCheck[8])
                            && rowdata[9].equalsIgnoreCase(rowdataCheck[9]))
                        {
                            rowdata[0] = "";
                            rowdata[1] = "";
                            rowdata[2] = "";
                            rowdata[3] = "";
                            rowdata[4] = "";
                            rowdata[5] = "";
                            rowdata[6] = "";
                            rowdata[7] = "";
                            rowdata[8] = "";
                            rowdata[9] = "";                                                         
                        }
                        else
                        {                            
                            rowdataCheck = (String[])vc.get(i);
                        }
                }
                
                // นำข้อมูลมา add ลงใน Vector ใหม่                                
                vRowData.add(rowdata);
                rowdata = null;
            }
        }
        
        return vRowData;
    }
    
    /**
     * สำหรับตรวจสอบข้อมูลรายงานว่าต้องการดึงข้อมูลรายงานแบบไหน
     * @Param 
     * selectReport คือ เลือกว่าออกรายงานแบบไหน 
     *      1 คือ รายงานแสดงจำนวนและอัตราการวางแผนครอบครัว จำแนกรายหมู่บ้าน จาก บัญชี 6 หญิงวัยเจริญพันธุ์
     *      2 คือ รายงานแสดงจำนวนและอัตราการวางแผนครอบครัว จำแนกรายหมู่บ้าน
     *      3 คือ รายงานแสดงรายชื่อของหญิงที่มีการวางแผนครอบครัว
     *      4 คือ รายชื่อหญิงวัยเจริญพันธุ์(อายุ 15-44 ปี;สมรส)ที่ไม่ได้รับการวางแผนครอบครัวในปัจจุบัน 
     * VillageId คือ รหัสบ้านที่เลือก
     * isQueryByDate คือ ต้องการดึงข้อมูลตามช่วงวันที่หรือไม่ 1 คือ ดึงตามช่วงวันที่ 0 คือ ดึงข้อมูลถึงวันที่สิ้นสุด
     * startDate คือ วันที่เริ่มต้นค้นหา
     * endDate คือ วันที่สิ้นสุดการค้นหา
     * @Return Vector ของ หัวตารางและข้อมูลจำนวนผู้ป่วยที่ได้จกการค้นหา
     * @Author Ojika
     * @Date 03/04/2549
     */
    public Vector selectFpPcu(int selectReport, String selectVillage, int isQueryByDate, String startDate, String endDate, boolean isDbBackup)
    {
        vcData = new Vector();
        
        if(selectReport == 1)
        {
            vcData = queryAmountByVillageAndMarry(selectVillage, startDate, endDate, isQueryByDate, isDbBackup);
        }
        else if(selectReport == 2)
        {
            vcData = queryAmountByVillage(selectVillage, startDate, endDate, isQueryByDate, isDbBackup);
        }
        else if(selectReport == 3)
        {
            vcData = queryListFpByVillage(selectVillage, startDate, endDate, isQueryByDate, isDbBackup);
        }
        else if(selectReport == 4)
        {
            vcData = queryListNotFp(selectVillage, startDate, endDate, isQueryByDate, isDbBackup);
        }
        
        return vcData;
    }
    
    /**
     * รายงานแสดงจำนวนและอัตราการวางแผนครอบครัว จำแนกรายหมู่บ้าน จาก บัญชี 6 หญิงวัยเจริญพันธุ์
     * @Author Ojika
     * @Date 03/04/2549
     **/
    public Vector queryAmountByVillageAndMarry(String selectVillage, String startDate, String endDate, int isQueryByDate, boolean isDbBackup)
   {
       vcData = new Vector();
       if(isDbBackup){
            theConnectionInf = Config.getConnectionInfDBBackupFromFile(new JFrame());
        }else{
            theConnectionInf = theManageDB.theConnectionInf;
        }
       theConnectionInf.open();
       try
       {
           theManageDB.theRPPcuFamilyPlaningDB.theConnectionInf = theConnectionInf;
           vcData = theManageDB.theRPPcuFamilyPlaningDB.queryAmountByVillageAndMarry(selectVillage, startDate, endDate, isQueryByDate);
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "AmountByVillageAndMarry",
                    "ฐานข้อมูล : "+
                    theConnectionInf.getURL()+
                    " ช่วงเวลา "+
                    startDate+"-"+
                    finishDate,
                    theHC.theUS.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "AmountByVillageAndMarry", ex, theHC.theUS.ERROR);
           theConnectionInf.rollback();
       }
       finally
       {
           theConnectionInf.close();
       }
       return vcData;
   }
    
   /**
     * รายงานแสดงจำนวนและอัตราการวางแผนครอบครัว จำแนกรายหมู่บ้าน
     * @Author Ojika
     * @Date 03/04/2549
     **/
    public Vector queryAmountByVillage(String selectVillage, String startDate, String endDate, int isQueryByDate, boolean isDbBackup)
   {
       vcData = new Vector();
       if(isDbBackup){
            theConnectionInf = Config.getConnectionInfDBBackupFromFile(new JFrame());
        }else{
            theConnectionInf = theManageDB.theConnectionInf;
        }
       theConnectionInf.open();
       try
       {
           theManageDB.theRPPcuFamilyPlaningDB.theConnectionInf = theConnectionInf;
           vcData = theManageDB.theRPPcuFamilyPlaningDB.queryAmountByVillage(selectVillage, startDate, endDate, isQueryByDate);
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "AmountByVillage",
                    "ฐานข้อมูล : "+
                    theConnectionInf.getURL()+
                    " ช่วงเวลา "+
                    startDate+"-"+
                    finishDate,
                    theHC.theUS.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "AmountByVillage", ex, theHC.theUS.ERROR);
           theConnectionInf.rollback();
       }
       finally
       {
           theConnectionInf.close();
       }
       return vcData;
   }
    
    /**
     * รายงานแสดงรายชื่อของหญิงที่มีการวางแผนครอบครัว
     * @Author Ojika
     * @Date 03/04/2549
     **/
    public Vector queryListFpByVillage(String selectVillage, String startDate, String endDate, int isQueryByDate, boolean isDbBackup)
   {
       vcData = new Vector();
       if(isDbBackup){
            theConnectionInf = Config.getConnectionInfDBBackupFromFile(new JFrame());
        }else{
            theConnectionInf = theManageDB.theConnectionInf;
        }
       theConnectionInf.open();
       try
       {
           theManageDB.theRPPcuFamilyPlaningDB.theConnectionInf = theConnectionInf;
           vcData = theManageDB.theRPPcuFamilyPlaningDB.queryListFpByVillage(selectVillage, startDate, endDate, isQueryByDate);
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "ListFpByVillage",
                    "ฐานข้อมูล : "+
                    theConnectionInf.getURL()+
                    " ช่วงเวลา "+
                    startDate+"-"+
                    finishDate,
                    theHC.theUS.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "ListFpByVillage", ex, theHC.theUS.ERROR);
           theConnectionInf.rollback();
       }
       finally
       {
           theConnectionInf.close();
       }
       return vcData;
   }
    
    /**
     * รายชื่อหญิงวัยเจริญพันธุ์(อายุ 15-44 ปี;สมรส)ที่ไม่ได้รับการวางแผนครอบครัวในปัจจุบัน 
     * @Author Ojika
     * @Date 03/04/2549
     **/
    public Vector queryListNotFp(String selectVillage, String startDate, String endDate, int isQueryByDate, boolean isDbBackup)
   {
       vcData = new Vector();
       if(isDbBackup){
            theConnectionInf = Config.getConnectionInfDBBackupFromFile(new JFrame());
        }else{
            theConnectionInf = theManageDB.theConnectionInf;
        }
       theConnectionInf.open();
       try
       {
           theManageDB.theRPPcuFamilyPlaningDB.theConnectionInf = theConnectionInf;
           vcData = theManageDB.theRPPcuFamilyPlaningDB.queryListNotFp(selectVillage, startDate, endDate, isQueryByDate);
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "ListNotFp",
                    "ฐานข้อมูล : "+
                    theConnectionInf.getURL()+
                    " ช่วงเวลา "+
                    startDate+"-"+
                    finishDate,
                    theHC.theUS.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "ListNotFp", ex, theHC.theUS.ERROR);
           theConnectionInf.rollback();
       }
       finally
       {
           theConnectionInf.close();
       }
       return vcData;
   }
    
}
