/*
 * CommonInf.java
 *
 * Created on 20 ���Ҥ� 2546, 10:05 �.
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
    /**Object ��� HosControl ������ա�����¡�� �е�ͧ cast �� HosControl ��͹*/
    public void setHosControl(Object hc); /*call after initialise*/
}
