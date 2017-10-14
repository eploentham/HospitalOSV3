/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pcu.utility;

import java.util.Vector;
import javax.swing.JTable;

/**
 *
 * @author henbe
 */
public interface PanelObj {
    public void setObject(Object obj);

    public Object getObject();

    public void refreshList();

    public void selectList();

}
