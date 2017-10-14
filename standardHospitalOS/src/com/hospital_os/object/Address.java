/*
 * Address.java
 *
 * Created on 11 ตุลาคม 2546, 14:05 น.
 */

package com.hospital_os.object;

import com.hospital_os.usecase.connection.Persistent;
import com.hospital_os.usecase.connection.*;
/**
 *
 * @author  tong
 */
public class Address  extends Persistent implements CommonInf
{
    
    /** Creates a new instance of Address */
    public String fullname; 
    public String description;
    public String tmbtype; 
    public String ampcode;
    public String cgwcode ;
    public String region ;

    public Address() {
        this.fullname = new String();
        this.description = new String(); 
        this.tmbtype = new String(); 
        this.ampcode = new String(); 
        this.cgwcode = new String(); 
        this.region  = new String();
    }
    public String getCode(){return getObjectId();}
    public String getName(){return description;}
    public String toString(){return description;}
    
}
