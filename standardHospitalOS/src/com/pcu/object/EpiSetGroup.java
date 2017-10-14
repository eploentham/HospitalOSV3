/*
 * EpiSetGroup.java
 *
 * Created on 24 มิถุนายน 2548, 16:31 น.
 */

package com.pcu.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author Administrator
 */
public class EpiSetGroup extends Persistent
{
    
    public String other_description = "";
    public String description = "";    
    public String active = "1";
    
    /** Creates a new instance of EpiSetGroup */
    public EpiSetGroup()
    {
    }
    
    public boolean getActive()
    {
        if(active.equals("1"))  return true;
        else    return false;
    }
    
    public void setActive(boolean act)
    {
        if(act)    active = "1";
        else       active = "0";
    }
    
}
