/*
 * PrintDrugRx2.java
 *
 * Created on 1 กรกฎาคม 2551, 14:18 น.
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hosv3.object.printobject;

import com.hosv3.utility.Constant;
import com.printing.object.DrugRx.PrintDrugRx;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Pu
 */ 
public class PrintDrugRx2 extends PrintDrugRx
{
    public String pprint_date = "print_date";
    public String pprint_time = "print_time";
    public Map PrintDrugRx2;
    /** Creates a new instance of PrintDrugRx2 */
    public PrintDrugRx2()
    {
        PrintDrugRx2 = new HashMap();
    }
    
    public void setPrintDate(String name)
    {
        setMap(pprint_date,name);
    }
    public void setPrintTime(String name)
    {
        setMap(pprint_time,name);
    }
       public void setTime(String name){
        setMap(ptime,name);
    }
    public void setStartDateCard(String name){
        setMap(pstartdatecard,name);
    }
    public void setCardID(String name){
        setMap(pcardid,name);
    }
    public void setExpireDateCard(String name){
        setMap(pexpiredatecard,name);
    }
    public void setHospital(String name){
        setMap(phospital,name);
    }
    public void setName(String name){
        setMap(pname,name);
    }
    public void setAge(String name){
        setMap(page,name);
    }
    public void setHn(String name){
        setMap(phn,name);
    }
    public void setVn(String name){
        setMap(pvn,name);
    }
    public void setDateVisit(String name){
        setMap(pdateVisit,name);
    }
    public void setPatientType(String name){
        setMap(ppatientType,name);
    }
    public void setDx(String name){
        setMap(pdx,name);
    }
    public void setDoctor(String name){
        setMap(pdoctor,name);
    }
    public void setPlan(String name){
        setMap(pplan,name);
    }
    public void setAddress(String name){
        setMap(pAddress,name);
    }
    public void setHeight(String name){
        setMap(pheight,name);
    }
    public void setWeight(String name){
        setMap(pweight,name);
    }
    
      /**สถานพยาบาลหลัก
     *@param name เป็นข้อมูลที่ต้องการจะให้ map กับ parameter ที่กำหนดใน xml
     *@author padungrat(tong)
     *@date 15/03/49,14:42
     */
    public void setMainHospital(String name)
    {
        setMap(pMainHospital,name);
    }
    /**สถานพยาบาลรอง
     *@param name เป็นข้อมูลที่ต้องการจะให้ map กับ parameter ที่กำหนดใน xml
     *@author padungrat(tong)
     *@date 15/03/49,14:42
     */
    public void setSubHospital(String name)
    {
        setMap(pSubHospital,name);
    }
    /**เลขบัตรประชาชน
     *@param name เป็นข้อมูลที่ต้องการจะให้ map กับ parameter ที่กำหนดใน xml
     *@author padungrat(tong)
     *@date 15/03/49,14:42
     */
    public void setPid(String name)
    {
        setMap(pPid,name);
    }
    /**ค่าใช้จ่ายรวมทั้งหมด
     *@param name เป็นข้อมูลที่ต้องการจะให้ map กับ parameter ที่กำหนดใน xml
     *@author padungrat(tong)
     *@date 15/03/49,14:42
     */
    public void setTotalPrice(String name)
    {
        setMap(total_price,name);
    }
    /**เลขบัตรประชาขนที่แยกเป็นแต่ละตัว
     *@param name เป็นข้อมูลที่ต้องการจะให้ map กับ parameter ที่กำหนดใน xml
     *@author padungrat(tong)
     *@date 15/03/49,14:42
     */
    public void setArrayPid(String name){
        int size = aPid.length;
        if(name.trim().length() == 13){
            for(int i=0;i<size;i++){
                setMap(aPid[i],new String().valueOf(name.charAt(i)));
            }
        }
        else{
            for(int i=0;i<size;i++){
                setMap(aPid[i],"");
            }
            Constant.println("ข้อมูลไม่ถูกตอ้ง  Data is incorrect");
        }
    }

    /**เลขบัตรประชาขนที่แยกเป็นแต่ละตัว
     *@param name เป็นข้อมูลที่ต้องการจะให้ map กับ parameter ที่กำหนดใน xml
     *@author pongtorn(henbe)
     *@date 15/05/49,14:42
     */
    public void setPersonId(String name){
        setMap(p_person_id,name);
    }
    public void setDiagIcd10(String name){
        setMap(p_diag_icd10,name);
    }
    public void setDxNote(String name){
        setMap(p_dx_note,name);
    }
    /**Dx ภาษาไทย
     *@param name เป็นข้อมูลที่ต้องการจะให้ map กับ parameter ที่กำหนดใน xml
     *@author sumo
     *@date 14/08/2549
     */
    public void setDiseaseThai(String name){
        setMap(p_disease_th,name);
    }
    public Map getData(){
        return PrintDrugRx2;
    }
    public void setMap(String Param, String Data)
    {
        PrintDrugRx2.put(Param,Data);
    }
}
