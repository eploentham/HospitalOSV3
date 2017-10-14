/*
 * SQLTemplateParam.java
 *
 * Created on 21 มิถุนายน 2547, 11:36 น.
 */

package com.hospital_os.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author  Administrator
 */
public class SQLTemplateParam extends Persistent  {
    
    /** Creates a new instance of SQLTemplate */
    public String code;
    public String description;
    public String sql;
    public String active = "1";
    public SQLTemplateParam() {
    }
    
}
