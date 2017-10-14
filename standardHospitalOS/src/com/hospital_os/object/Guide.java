/*
 * Guide.java
 *
 * Created on 4 สิงหาคม 2549, 16:39 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hospital_os.object;
import com.hospital_os.usecase.connection.Persistent;
import com.hospital_os.utility.Constant;
import java.util.Vector;
/**
 *
 * @author sumo
 */
public class Guide extends Persistent
{
    
    public String number = "";
    public String description = "";
    public String active = "1";
    
    /** Creates a new instance of Guide */
    public Guide() {
    }
    public String toString(){
        return description;
    }
    public static String toString(Vector vGuide)
    {
        Constant.println("vGuide==null"+vGuide==null);
        if(vGuide==null)
            return "";
        Constant.println("vGuide.size()"+vGuide.size());
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<vGuide.size();i++){
            Guide guide = (Guide)vGuide.get(i);
            sb.append(guide.toString());
            sb.append(" ");
        }
        return sb.toString();
    }
}
