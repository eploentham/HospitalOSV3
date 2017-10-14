/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.report18file.gui;

import com.hosv3.utility.connection.UpdateStatus;
import com.report18file.control.Rp18Control;

/**
 *
 * @author henbe
 */
public class PanelReport8 extends PanelReport51 {
 
    public void setConnection(Rp18Control rc,UpdateStatus us){
        super.setConnection(rc,us);
        dbs = DBSuitPp.getDBSuit(theUS,theConnectionInf);
        index = Rp18Control.STD_PP;
    }
}
