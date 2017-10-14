/*
 * DrugFrequencyLookup.java
 *
 * Created on 21 กรกฎาคม 2548, 10:55 น.
 */

package com.hosv3.control.lookup;

/*
 * PrescriberLookup.java
 *
 * Created on 9 พฤศจิกายน 2546, 9:23 น.
 */
import com.hospital_os.usecase.connection.*;
import com.hosv3.control.*;

/**
 *
 * @not deprecated because use henbe package
 * @author  henbe
 * วิธีการปรับแก้การค้น inactive ให้แสดงได้แต่เลือกให้ไม่ได้มีดังนี้
 * - รวมฟังชันค้นหาให้แสดงรวมศูนย์
 *      return thePC.listDrugFrequency();
 *      return thePC.listDrugFrequency(str);
 * - เพิ่ม listAll ขึ้นมากนอกเหนือจาก listActive โดยมี 
 *      listAll - return vAll
 *      listActive return vActive
 * - แก้ฟังขัน list(boolean active) ให้ค้น 2 แบบเก็บ 2 เวกเตอร์ return ตาม flag
 * - แก้ฟังขัน readById() ให้ค้นจาก list(false) คือ ListAll
 */
public class DrugFrequencyLookup implements LookupControlInf
    {
    
    private LookupControl thePC;
    /** Creates a new instance of PrescriberLookup */
    public DrugFrequencyLookup(LookupControl pc) {
        thePC = pc;
    }
    
    public java.util.Vector listData(String str) {
        return thePC.listDrugFrequency(str);
    }
    
    public CommonInf readData(String pk) {
        return thePC.readDrugFrequencyById(pk);
    }
    
    public CommonInf readHosData(String pk)
    {
        return thePC.readDrugFrequencyById(pk);
    }
}
