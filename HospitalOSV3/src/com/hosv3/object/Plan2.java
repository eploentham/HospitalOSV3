/*
 * Plan2.java
 * �����ӡ�������ҡ������Ѻ͹حҵ� ���������Ŵ��������� Plan �����
 * Created on 27 �á�Ҥ� 2548, 14:54 �.
 */

package com.hosv3.object;
import com.hospital_os.object.Plan;
import com.hospital_os.usecase.connection.*;
/**
 *
 * @author  kingland
 * @deprecated henbe unused
 */
public class Plan2 extends Plan implements CommonInf{
    
	static final long serialVersionUID = 0;

    /** Creates a new instance of Plan2 */
    public Plan2() {}
    public Plan2(Plan p) {
        plan_id=p.plan_id;
        description=p.description;
        active_from=p.active_from;
        active_to=p.active_to;
        active=p.active;
        pttype=p.pttype;
        money_limit=p.money_limit;
        payer_id=p.payer_id;
        contract_id=p.contract_id;  
        sort_index=p.sort_index;   
        setObjectId(p.getObjectId());
    }
    public String getCode() {
        return this.getObjectId();
    }
    
    public String getName() {
        return this.description;
    }
    public String toString(){
        return getName();
    }
}
