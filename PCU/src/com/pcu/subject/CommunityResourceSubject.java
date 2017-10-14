/*
 * CommunityResourceSubject.java
 *
 * Created on 23 สิงหาคม 2548, 21:07 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.pcu.subject;

import java.util.Vector;
import com.pcu.subject.ManageCommunityResourceResp;
/**
 *
 * @author amp
 */
public class CommunityResourceSubject {
    
    private Vector vVector;
    
    /** Creates a new instance of CommunityResourceSubject */
    public CommunityResourceSubject() 
    {
        vVector = new Vector();
    }
    
    public void attachCommunityResource(ManageCommunityResourceResp o)
    {
        vVector.add(o);
    }
    
    public void notifyCommunityResource(String communityResourceId)
    {
        for(int i = 0 ; i < vVector.size() ; i++)
            ((ManageCommunityResourceResp)vVector.elementAt(i)).notifyCommunityResource(communityResourceId);   
    }
    
}
