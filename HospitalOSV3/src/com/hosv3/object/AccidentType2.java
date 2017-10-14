/*
 * AccidentType2.java
 *
 * Created on 2 ÁÔ¶Ø¹ÒÂ¹ 2549, 13:34 ¹.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hosv3.object;
import com.hospital_os.object.AccidentType;
import com.hospital_os.usecase.connection.CommonInf;
/**
 *
 * @author Padungrat(tong)
 */
public class AccidentType2   extends AccidentType implements CommonInf {
    
    static final long serialVersionUID = 0;
    /** Creates a new instance of AccidentType2 */
    public AccidentType2() {
    }
     public String getCode() {
        return this.getObjectId();
    }
    
    public String getName() {
        return this.accident_type_trauma +":" +this.accident_type_description;
    }
    public String toString(){
        return getName();
    }
}
