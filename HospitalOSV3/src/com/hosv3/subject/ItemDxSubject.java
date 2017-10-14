/*
 * ItemDxSubject.java
 *
 * Created on 9 สิงหาคม 2549, 14:18 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hosv3.subject;
import java.util.*;
import com.hosv3.usecase.setup.*;
/**
 *
 * @author henbe
 */
public class ItemDxSubject implements ManageItemDxReq
{
    Vector vSetTableItemDx;
    /** Creates a new instance of ItemDxSubject */
    public ItemDxSubject()
    {
        vSetTableItemDx = new Vector ();
    }
    public void removeAttach()
    {
        vSetTableItemDx.removeAllElements();
    }
    
   public void attachItemDx(ManageItemDxReq o){
        vSetTableItemDx.add(o);
    }

    public void notifySetTableItemDx(String str, int status)
    { for(int i=0,size=vSetTableItemDx.size();i<size;i++)
      {
          ((ManageItemDxReq)this.vSetTableItemDx.get(i)).notifySetTableItemDx(str,status);
      }      
    }
    
}
