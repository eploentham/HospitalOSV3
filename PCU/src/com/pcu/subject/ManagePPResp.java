/*
 * ManagePPResp.java
 *
 * Created on 1 �ԧ�Ҥ� 2548, 15:39 �.
 */

package com.pcu.subject;

import com.pcu.object.PP;

/**
 *
 * @author amp
 */
public interface ManagePPResp
{    
    public void notifyApgarScore(String[] arrayApgarScoreNotify);
    public void notifySavePP(PP pp);
}
