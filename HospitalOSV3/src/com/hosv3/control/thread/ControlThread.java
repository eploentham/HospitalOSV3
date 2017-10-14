/*
 * ControlThread.java
 *
 * Created on 16 กรกฎาคม 2550, 8:54 น.
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hosv3.control.thread;

import com.hospital_os.usecase.connection.ConnectionInf;
import com.hospital_os.usecase.connection.UpdateStatus;
import com.hosv3.control.HosDB;
import com.hosv3.object.HosObject;

/**
 *
 * @author Aut
 */
public abstract class ControlThread extends Thread {
    protected ConnectionInf theConnectionInf;
    protected HosDB theHosDB;
    protected HosObject theHO;
    protected UpdateStatus theUS;
    
    /** Creates a new instance of ControlThread */
    public ControlThread() {        
    }
    
    public abstract void setControl(ConnectionInf con, HosDB hdb, HosObject ho, UpdateStatus us, Object control);
    protected abstract void runTask();
    
    public void run() {
        runTask();
    }        
}
