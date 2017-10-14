/*
 * ManagePPResp.java
 *
 * Created on 1 สิงหาคม 2548, 15:39 น.
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
