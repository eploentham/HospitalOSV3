/*
 * VisitYear.java
 *
 * Created on 10 ����Ҥ� 2548, 14:30 �.
 */

package com.hospital_os.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 * �繵��ҧ����������������㹡���� �վ�. ������㹡�õ�Ǩ�ͺ �Ţ VN
 * @author tong
 * @ since V2.0 b8
 */
public class VisitYear extends Persistent {
    
    /** Creates a new instance of VisitYear */
    public String visit_year;
    public VisitYear() {
        visit_year = "00";
    }
    
}
