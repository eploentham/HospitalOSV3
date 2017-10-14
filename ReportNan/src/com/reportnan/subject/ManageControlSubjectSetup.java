/*
 * ManageControlSubject.java
 *
 * Created on 13 �Զع�¹ 2549, 11:21 �.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportnan.subject;

import com.hospital_os.usecase.connection.ConnectionInf;

import com.setupreportnan.control.ManageControl;
import com.setupreportnan.subject.ManageSubject;
/**
 *
 * @author pu
 */
public class ManageControlSubjectSetup
{
    ConnectionInf theConnectionInf;
        public ManageControl theManageControl;
        public ManageSubject theManageSubject;
        
        /** Creates a new instance of ManageControlSubject */
        public ManageControlSubjectSetup(ConnectionInf conf)
        {
            theConnectionInf = conf;
            theManageControl = new ManageControl(theConnectionInf);
            theManageSubject = new ManageSubject();
        }
    
}
