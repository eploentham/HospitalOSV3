/*
 * RP115Group4_2549.java
 *
 * Created on 22 มีนาคม 2549, 16:17 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.standardReport.object;
/**
 *
 * @author pu
 */
public class RP115Group4_2549 
{
     public String plan_type;
     
     /**ข้อมูลการคัดกรอง*/
     public String womb_cancer;
     public String breast_cancer;
     public String thalassemia;
     public String thalassemia_mom;
     public String iodine_thiroid;
     
     /**ยืนยันการตรวจคัดกรอง thalassemia และไทรอยด์*/
     public String thalassemia_infant;
     public String thiroid;
     
     /**การสิ้นสุดการตั้งครรภ์*/
     public String terminated_pregnance;
     
     /**เด็ก 0-1 เดือนได้รับการรักษาภาวะบกพร่องไอโอดีนและไทรอยด์*/
     public String treat_thiroid;
     
     /**จำนวนครั้งในการให้บริการฟื้นฟูสมรรถภาพ*/
     public String efficiency;
     
     /**จำนวนผู้รับบริการการเยี่ยมบ้าน*/
     public String person;
     
    /** Creates a new instance of RP115Group4_2549 */
    public RP115Group4_2549()
    {
        
    }
    public void setInitData()
    {
        plan_type= "";
        
        womb_cancer= "";
        breast_cancer= "";
        thalassemia= "";
        thalassemia_mom= "";
        iodine_thiroid= "";
        
        thalassemia_infant= "";
        thiroid= "";
        
        terminated_pregnance= "";
        
        treat_thiroid= "";
        
        efficiency= "";
        
        person= "";
    }
    
    
}
