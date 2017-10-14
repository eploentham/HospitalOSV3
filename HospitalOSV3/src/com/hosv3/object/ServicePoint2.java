/*
 *
 ไม่ใช้แล้วจะเอาไปไว้ใน service point เฉยๆ 
 ServicePoint2.java
 *
 * Created on 1 สิงหาคม 2548, 15:57 น.
 */
package com.hosv3.object;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.object.*;

/**
 *
 * @author  sumo
 */
public class ServicePoint2 extends ServicePoint implements CommonInf
{

	static final long serialVersionUID = 0;
    /** Creates a new instance of ServicePoint2 */
    public ServicePoint2(ServicePoint sp) {
        active = sp.active;
        name = sp.name;
        service_point_id = sp.service_point_id;
        service_sub_type_id = sp.service_sub_type_id;
        service_type_id = sp.service_type_id;
        setObjectId(sp.getObjectId());
    }
    public String toString(){
        String str = getName();
        return str;    
    }
}
