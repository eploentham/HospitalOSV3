/*
 * PanelSetup.java
 *
 * Created on 28 ¡’π“§¡ 2549, 12:59 π.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hosv3.gui.component;

import com.hosv3.control.*;
import com.hosv3.utility.connection.UpdateStatus;
import com.hospital_os.usecase.connection.Persistent;
import java.util.Vector;
/**
 *
 * @author henbe
 */
public interface PanelSetupImp {
    
    public void clearAll();
    public void setEnabled(boolean b);
    
    public Persistent getXPer();
    public void setXPer(Persistent x);
    
    public void setLanguage();
    
    public void setControl(HosControl hc,UpdateStatus us);
    public void setupLookup();
    public boolean deleteXPer(Persistent x);
    public boolean saveXPer(Persistent x);
    public Vector listXPer(String key,String active,int offset);
    public boolean isActiveVisible();
    public String getTitle();
}
