/*
 * ImagePoint.java
 *
 * Created on 16 ÁÔ¶Ø¹ÒÂ¹ 2549, 17:17 ¹.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hosv3.utility;
import java.awt.*;
/**
 *
 * @author henbe
 */
public class ImagePoint extends Point{
    Object theObject;
    Color theColor;
    int order;
    public ImagePoint (int x,int y,Color color)
    {
        super(x,y);
        theColor = color;
    }
    public Color getColor()
    {
        return theColor;
    }
    public void setObject(Object object)
    {
        theObject = object;
    }
    public Object getObject()
    {
        return theObject;
    }
    public void setOrder(int object)
    {
        order = object;
    }
    public int getOrder()
    {
        return order;
    }
    
    public void setColor(Color color)
    {
        theColor = color;
    }    
}
