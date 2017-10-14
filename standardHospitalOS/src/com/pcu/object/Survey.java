/*
 * Survey.java
 *
 * Created on 6 ¡’π“§¡ 2549, 14:30 π.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.pcu.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author kingland
 */
public class Survey extends Persistent{
    private static String init = "";
    public String survey_id = init;    
    public String survey_description = init;
    public String survey_active =init;
    /** Creates a new instance of Survey */
    public Survey() {
    }
    
}
