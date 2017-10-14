/*
 * AutenticationReq.java
 *
 * Created on 16 พฤษภาคม 2547, 10:01 น.
 */

package com.hospital_os.usecase.connection;

/**
 *
 * @author  tong
 *ใช้กำหนด Menu ของผู้ใช้งาน
 */
public interface AutenticationReq {
    
    /** Creates a new instance of AutenticationReq */

    public void  Register(boolean b);
    public void  Screen(boolean b);
    public void  Doctor(boolean b);
    public void  Pharm(boolean b);
    public void  Cash(boolean b);
    public void  Lab(boolean b);
    public void  Xray(boolean b);
    public void  Stat(boolean b);
    public void  OneStopService(boolean b);
    public void  Health(boolean b);
    public void  All(boolean b);
    public void  Insure(boolean b);
  /*  public void  Register();*/
  /*  public void  Register();    */

    
}
