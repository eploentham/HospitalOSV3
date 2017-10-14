/*
 * Resource.java
 *
 * Created on 15 กรกฎาคม 2548, 15:37 น.
 */

package com.pcu.object;

import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author amp
 */
public class CommunityResource extends Persistent{
    private static String init = "";
    public String number = init;
    public String description = init;
    public String active = init;
    
    public String max_communityresource_id;
    
    /** Creates a new instance of Resource */
    public CommunityResource() {
    }
    
}
