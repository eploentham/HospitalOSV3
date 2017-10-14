/*
 * ResultXrayPosition.java
 *
 * Created on 25 æƒ…¿“§¡ 2547, 15:42 π.
 */

package com.hospital_os.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author  tong
 */
public class ResultXrayPosition  extends Persistent{
    
    /** Creates a new instance of ResultXrayPosition */
 /*   public String result_xray_position_id*/
    public String xray_leteral_id;
    public String xray_position_id;
    public String order_result_xray_id;
    public String visit_id;
    public String order_item_id;
    public String xray_result_size_id; /*¢π“¥ø‘≈¡Ï*/
    public String kv = "0";
    public String ma = "0";
    public String second = "0";
    public String mas = "0";
    public String ffd = "0";
    public String active = "1";

    public ResultXrayPosition() {
    }
    
}
