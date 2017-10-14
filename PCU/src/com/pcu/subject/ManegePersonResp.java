/*
 * ManegePersonResp.java
 *
 * Created on 25 กุมภาพันธ์ 2549, 13:43 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.pcu.subject;

import java.util.*;
import com.pcu.object.Family;
/**
 *
 * @author Jao
 */
public interface ManegePersonResp {
     public void notifysaveFamily (Family family);
     public void notifyselectFamily(Family family);
}
