/*
 * ApgarScoreSubject.java
 *
 * Created on 1 สิงหาคม 2548, 15:26 น.
 */

package com.pcu.subject;

import com.pcu.object.PP;
import java.util.Vector;
import com.pcu.subject.ManagePPResp;
/**
 *
 * @author amp
 */
public class PPSubject
{
    private Vector vVector;
    /** Creates a new instance of ApgarScoreSubject */
    public PPSubject()
    {
        vVector = new Vector();
    }
    
    public void attachApgarScore(ManagePPResp o)
    {
        vVector.add(o);
    }
    
    public void notifyApgarScore(String[] arrayApgarScore)
    {
        for(int i = 0 ; i < vVector.size() ; i++)
            ((ManagePPResp)vVector.elementAt(i)).notifyApgarScore(arrayApgarScore);   
    }
    public void notifySavePP(PP pp)
    {
        for(int i = 0 ; i < vVector.size() ; i++)
            ((ManagePPResp)vVector.elementAt(i)).notifySavePP(pp);
    }
}
