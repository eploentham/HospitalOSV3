/*
 * MapQueueVisit.java
 *
 * Created on 22 æƒ…¿“§¡ 2547, 21:17 π.
 */

package com.hospital_os.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author  tong
 */
public class MapQueueVisit  extends Persistent {
    
    /** Creates a new instance of MapQueueVisit */
    public String map_queue_visit_id;
    public String visit_id ;
    public String queue; 
    public String queue_visit;
    public String active ="1";

    public MapQueueVisit() {
    }
    
}
