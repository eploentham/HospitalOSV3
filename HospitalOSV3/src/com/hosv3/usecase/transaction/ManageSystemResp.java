/*
 * ManageVitalSignResp.java
 *
 * Created on 17 ����Ҥ� 2548, 18:55 �.
 */

package com.hosv3.usecase.transaction;

/**
 *
 * @author  americus
 */
public interface ManageSystemResp {
    
    public void notifyLogin(String str,int status);
    public void notifyLoginSetup(String str,int status);
    public void notifyLogout(String str,int status);
    public void notifyExit(String str,int status);
}
