/*
 * SpecialQueryPatient2DB.java
 *
 * Created on 17 ธันวาคม 2548, 14:22 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hosv3.objdb.squery;
import com.hospital_os.objdb.*;
import com.hospital_os.usecase.connection.*;
import java.util.*;
/**
 *
 * @author kingland
 * @deprecated henbe กำลังจะเลิกใช้งานแล้ว ให้ไปเรียกใช้ได้จาก DxTemplateDB
 */
public class SpecialQueryDxTemplate2DB extends DxTemplateDB{
    
    /** Creates a new instance of SpecialQueryPatient2DB */
    public SpecialQueryDxTemplate2DB(ConnectionInf db){
        super(db);
    }
    
    public Vector selectByVid(String vid) throws Exception{
        String sql = "select * from t_visit_diag_map,b_template_dx " +
        		" where t_visit_diag_map.b_template_dx_id= b_template_dx.b_template_dx_id" +
        		" and t_visit_diag_map.t_visit_id like '"+ vid +"%'" +
                        " and t_visit_diag_map.visit_diag_map_active = '1'";
        return null;//this.spQuery(sql);
    }
}
