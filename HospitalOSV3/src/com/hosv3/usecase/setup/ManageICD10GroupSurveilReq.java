/*
 * ManageMapICD10Req.java
 *
 * Created on 9 กันยายน 2551, 15:33 น.
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hosv3.usecase.setup;

/**
 *
 * @author iPu
 */
public interface ManageICD10GroupSurveilReq
{
    public void notifySetTableICD10GSGroup(String str, int status);
    public void notifySetTableICD10GSSpecifyCode(String str, int status);
    
}
