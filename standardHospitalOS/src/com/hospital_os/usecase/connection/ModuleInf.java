/*
 * CommonInf.java
 *
 * Created on 20 ตุลาคม 2546, 10:05 น.
 */

package com.hospital_os.usecase.connection;

import java.util.*;
import javax.swing.*;

/**
 *
 * @author  Administrator
 */
public interface ModuleInf {
    public Vector getPanelV();  /* ModulePanelInf*/
    public JMenu getJMenu();

    public void setEmployee(Object us);
    public void setSite(Object us);

    public Vector getObserverV();  /* ModuleObserverInf*/
    
    public void saveConnection(String str);
    public void startRun(boolean str);
    public String getVersion();
    /**Object คือ HosControl เมื่อมีการเรียกใช้ จะต้อง cast เป็น HosControl ก่อน*/
    public void setHosControl(Object hc); /*call after initialise*/
}
