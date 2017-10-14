/*
 * AfterMchSubject.java
 *
 * Created on 29 กรกฎาคม 2548, 15:56 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.pcu.subject;
import java.util.Vector;
import com.pcu.object.BornMch;
import com.pcu.subject.ManageAfterMchResp;
/**
 *
 * @author tong(Padungrat)
 */
public class AfterMchSubject {
    private Vector vAfterMch;
    /** Creates a new instance of AfterMchSubject */
    public AfterMchSubject() {
        vAfterMch = new Vector();
    }
    public void attachManageAfterBornMch(ManageAfterMchResp o)
    {
        vAfterMch.add(o);
    }
    
    public void notifyCallAfterMchMother(BornMch bornmch)
    {
        for(int i = 0 ; i < vAfterMch.size() ; i++)
            ((ManageAfterMchResp)vAfterMch.elementAt(i)).notifyCallAfterMchMother(bornmch);   
    }
    
    public void notifyCallAfterBornMchMother()
    {
        for(int i = 0 ; i < vAfterMch.size() ; i++)
            ((ManageAfterMchResp)vAfterMch.elementAt(i)).notifyCallAfterBornMchMother();   
    }
    
    public void notifyGetDataBornMchMotherToAfterMchMother(BornMch bornmch)
    {
        for(int i = 0 ; i < vAfterMch.size() ; i++)
            ((ManageAfterMchResp)vAfterMch.elementAt(i)).notifyGetDataBornMchMotherToAfterMchMother(bornmch);   
    }
    
    public void notifyCallAfterBornMchMotherService(boolean inFirst)
    {
        for(int i = 0 ; i < vAfterMch.size() ; i++)
            ((ManageAfterMchResp)vAfterMch.elementAt(i)).notifyCallAfterBornMchMotherService(inFirst);   
    }
}
