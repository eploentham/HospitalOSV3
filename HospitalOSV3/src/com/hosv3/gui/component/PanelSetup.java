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
/**
 *
 * @author henbe
 */
public interface PanelSetup {
    public void setTitleLabel(String str);
    public void setupLookup();
    public void setControl(HosControl hc,UpdateStatus us);
}
