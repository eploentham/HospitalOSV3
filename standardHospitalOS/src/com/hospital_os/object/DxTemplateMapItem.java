/*
 * DxTemplateMapItem.java
 *
 * Created on 8 �ԧ�Ҥ� 2549, 18:04 �.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hospital_os.object;
import com.hospital_os.usecase.connection.Persistent;
import com.hospital_os.usecase.connection.CommonInf;

/**
 *
 * @author Administrator
 */
public class DxTemplateMapItem extends Persistent 
{
    /**������¡�� Item ��� map �Ѻ Dx */
    public String b_template_dx_map_item_id ="";
    /**���� Template �ͧ Dx ���١ Map*/
    public String template_dx_id ="";
    /**����  Item ��� map �Ѻ Dx*/
    public String item_id="";    
    /**������¡�� Item*/    
    public String code = "";
    /**������¡�� Item*/
    public String description = "";
    
    /** Creates a new instance of DxTemplateMapItem */
    public DxTemplateMapItem()
    {
    }    
}
