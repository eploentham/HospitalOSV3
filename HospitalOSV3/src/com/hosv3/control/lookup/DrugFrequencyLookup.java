/*
 * DrugFrequencyLookup.java
 *
 * Created on 21 �á�Ҥ� 2548, 10:55 �.
 */

package com.hosv3.control.lookup;

/*
 * PrescriberLookup.java
 *
 * Created on 9 ��Ȩԡ�¹ 2546, 9:23 �.
 */
import com.hospital_os.usecase.connection.*;
import com.hosv3.control.*;

/**
 *
 * @not deprecated because use henbe package
 * @author  henbe
 * �Ըա�û�Ѻ���ä� inactive ����ʴ��������͡���������մѧ���
 * - ����ѧ�ѹ��������ʴ�����ٹ��
 *      return thePC.listDrugFrequency();
 *      return thePC.listDrugFrequency(str);
 * - ���� listAll ����ҡ�͡�˹�ͨҡ listActive ���� 
 *      listAll - return vAll
 *      listActive return vActive
 * - ��ѧ�ѹ list(boolean active) ���� 2 Ẻ�� 2 �ǡ���� return ��� flag
 * - ��ѧ�ѹ readById() ���鹨ҡ list(false) ��� ListAll
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
