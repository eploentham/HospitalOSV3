/*
 * ManagePersonResp.java
 *
 * Created on 29 ¡’π“§¡ 2549, 14:14 π.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hosv3.usecase.transaction;

/**
 *
 * @author Jao
 *@deprecated henbe unused
 */
public interface ManagePersonResp {
    
    public void notifySavePersonPayment(String str ,int status);
    public void notifyDeletePersonPayment(String str ,int status);
    
}
