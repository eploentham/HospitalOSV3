/*
 * ManageControlSubject.java
 *
 * Created on 3 ÁÔ¶Ø¹ÒÂ¹ 2549, 11:22 ¹.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportnan.subject;
import com.reportnan.control.ManageControl;
import com.hospital_os.usecase.connection.ConnectionInf;
/**
 *
 * @author pu
 */
public class ManageControlSubject
{
    ConnectionInf theConnectionInf;
    public ManageControl theManageControl;
    public ManageSubject theManageSubject;
    
    /** Creates a new instance of ManageControlSubject */
    public ManageControlSubject(ConnectionInf conf)
    {
        theConnectionInf = conf;
        theManageControl = new ManageControl(theConnectionInf); 
        theManageSubject = new ManageSubject();
    }
    
}
