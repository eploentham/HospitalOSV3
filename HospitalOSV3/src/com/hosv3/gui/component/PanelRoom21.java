/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hosv3.gui.component;

import com.hosv3.control.HosControl;
import com.hosv3.object.Bed;
import com.hosv3.object.Room;
import java.awt.Color;
import java.util.Vector;

/**
 *
 * @author LionHeartV
 */
public class PanelRoom21 extends javax.swing.JPanel {

    /**
     * Creates new form PanelRoom2
     */
    Bed theBed1;
    Bed theBed2;
    Bed theBed3;
    Bed theBed4;
    Bed theBed5;
    Bed theBed6;
    Bed theBed7;
    Bed theBed8;
    Bed theBed9;
    Bed theBed10;
    Bed theBed11;
    Bed theBed12;
    Bed theBed13;
    Bed theBed14;
    Bed theBed15;
    Bed theBed16;
    Bed theBed17;
    Bed theBed18;
    Bed theBed19;
    Bed theBed20;
    Bed theBed21;
    Bed theBed22;
    Bed theBed23;
    Bed theBed24;
    Bed theBed25;
    Bed theBed26;
    Bed theBed27;
    Bed theBed28;
    Bed theBed29;
    Bed theBed30;
    Bed theBed31;
    Bed theBed32;
    Room theRoom;
    boolean theBed1Available;
    boolean theBed2Available;
    boolean theBed3Available;
    boolean theBed4Available;
    boolean theBed5Available;
    boolean theBed6Available;
    boolean theBed7Available;
    boolean theBed8Available;
    boolean theBed9Available;
    boolean theBed10Available;
    boolean theBed11Available;
    boolean theBed12Available;
    boolean theBed13Available;
    boolean theBed14Available;
    boolean theBed15Available;
    boolean theBed16Available;
    boolean theBed17Available;
    boolean theBed18Available;
    boolean theBed19Available;
    boolean theBed20Available;
    boolean theBed21Available;
    boolean theBed22Available;
    boolean theBed23Available;
    boolean theBed24Available;
    boolean theBed25Available;
    boolean theBed26Available;
    boolean theBed27Available;
    boolean theBed28Available;
    boolean theBed29Available;
    boolean theBed30Available;
    boolean theBed31Available;
    boolean theBed32Available;
    private HosControl theHC;
    public PanelRoom21() {
        initComponents();
        jPanelRoom1.setVisible(false);
        jPanelRoom2.setVisible(false);
        jPanelRoom3.setVisible(false);
        jPanelRoom4.setVisible(false);
        jPanelRoom5.setVisible(false);
        jPanelRoom6.setVisible(false);
        jPanelRoom7.setVisible(false);
        jPanelRoom8.setVisible(false);
        jPanelRoom9.setVisible(false);
        jPanelRoom10.setVisible(false);
        jPanelRoom11.setVisible(false);
        jPanelRoom12.setVisible(false);
        jPanelRoom13.setVisible(false);
        jPanelRoom14.setVisible(false);
        jPanelRoom15.setVisible(false);
        jPanelRoom16.setVisible(false);
        jPanelRoom17.setVisible(false);
        jPanelRoom18.setVisible(false);
        jPanelRoom19.setVisible(false);
        jPanelRoom20.setVisible(false);
        jPanelRoom21.setVisible(false);
        jPanelRoom22.setVisible(false);
        jPanelRoom23.setVisible(false);
        jPanelRoom24.setVisible(false);
        jPanelRoom25.setVisible(false);
        jPanelRoom26.setVisible(false);
        jPanelRoom27.setVisible(false);
        jPanelRoom28.setVisible(false);
        jPanelRoom29.setVisible(false);
        jPanelRoom30.setVisible(false);
        jPanelRoom31.setVisible(false);
        jPanelRoom32.setVisible(false);
    }
    public void setControl(HosControl hc)
    {
        theHC = hc;
    }
    public void setRoom(Room room)
    {
        theRoom = room;
        jLabelTitle.setText(theRoom.visit_room_number);
        if(!theRoom.color.equals(""))
        {
            String arr[] = theRoom.color.split(",");
            Color color = new Color(Integer.parseInt(arr[0]),Integer.parseInt(arr[1]),Integer.parseInt(arr[2]));
            jPanel1.setOpaque(true);
            jPanel1.setBackground(color);
        }
    }
    public void repaintN()
    {
        if(jPanelRoom1.isVisible())
            setBed1(theBed1,theBed1Available);
        if(jPanelRoom2.isVisible())
            setBed2(theBed2,theBed2Available);
        if(jPanelRoom3.isVisible())
            setBed3(theBed3,theBed3Available);
        if(jPanelRoom4.isVisible())
            setBed4(theBed4,theBed4Available);
        if(jPanelRoom5.isVisible())
            setBed5(theBed5,theBed5Available);
        if(jPanelRoom6.isVisible())
            setBed6(theBed6,theBed6Available);
        if(jPanelRoom7.isVisible())
            setBed7(theBed7,theBed7Available);
        if(jPanelRoom8.isVisible())
            setBed8(theBed8,theBed8Available);
        if(jPanelRoom9.isVisible())
            setBed9(theBed9,theBed9Available);
        if(jPanelRoom10.isVisible())
            setBed10(theBed10,theBed10Available);
        if(jPanelRoom11.isVisible())
            setBed11(theBed11,theBed11Available);
        if(jPanelRoom11.isVisible())
            setBed12(theBed12,theBed12Available);
        if(jPanelRoom13.isVisible())
            setBed13(theBed13,theBed13Available);
        if(jPanelRoom14.isVisible())
            setBed14(theBed14,theBed14Available);
        if(jPanelRoom15.isVisible())
            setBed15(theBed15,theBed15Available);
        if(jPanelRoom16.isVisible())
            setBed16(theBed16,theBed16Available);
        if(jPanelRoom17.isVisible())
            setBed17(theBed17,theBed17Available);
        if(jPanelRoom18.isVisible())
            setBed18(theBed18,theBed18Available);
        if(jPanelRoom19.isVisible())
            setBed19(theBed19,theBed19Available);
        if(jPanelRoom20.isVisible())
            setBed20(theBed20,theBed20Available);
        if(jPanelRoom21.isVisible())
            setBed21(theBed21,theBed21Available);
        if(jPanelRoom22.isVisible())
            setBed22(theBed22,theBed22Available);
        if(jPanelRoom23.isVisible())
            setBed23(theBed23,theBed23Available);
        if(jPanelRoom24.isVisible())
            setBed24(theBed24,theBed24Available);
        if(jPanelRoom25.isVisible())
            setBed25(theBed25,theBed25Available);
        if(jPanelRoom26.isVisible())
            setBed26(theBed26,theBed26Available);
        if(jPanelRoom27.isVisible())
            setBed27(theBed27,theBed27Available);
        if(jPanelRoom28.isVisible())
            setBed28(theBed28,theBed28Available);
        if(jPanelRoom29.isVisible())
            setBed29(theBed29,theBed29Available);
        if(jPanelRoom30.isVisible())
            setBed30(theBed30,theBed30Available);
        if(jPanelRoom31.isVisible())
            setBed31(theBed31,theBed31Available);
        if(jPanelRoom32.isVisible())
            setBed32(theBed32,theBed32Available);
        
    }
    public void setBed1(Bed bed1,boolean available)
    {
        theBed1Available = available;
        theBed1 = bed1;
        jLabelRoom1.setText(theBed1.visit_bed_number);
        jPanelRoom1.setVisible(true);
        jPanelRoom1.setOpaque(true);
        if(!theBed1.visit_bed_book.equals("1"))
        {
            if(available)
                jPanelRoom1.setBackground(Color.GREEN);  
            else
            {
                jPanelRoom1.setToolTipText(theBed1.caption);
                jPanelRoom1.setBackground(Color.RED);  
            }
        }
        else
        {
            jPanelRoom1.setBackground(Color.ORANGE); 
        } 
    }
    public void setBed2(Bed bed2,boolean available)
    {
        theBed2Available = available;
        theBed2 = bed2;
        jLabelRoom2.setText(theBed2.visit_bed_number);
        jPanelRoom2.setVisible(true);
        jPanelRoom2.setOpaque(true);
        if(!theBed2.visit_bed_book.equals("1"))
        {
            if(available)
                jPanelRoom2.setBackground(Color.GREEN);  
            else
            {
                jPanelRoom2.setToolTipText(theBed2.caption);
                jPanelRoom2.setBackground(Color.RED);  
            }
        }
        else
        {
            jPanelRoom2.setBackground(Color.ORANGE); 
        }  
    }
    public void setBed3(Bed bed3,boolean available)
    {
        theBed3Available = available;
        theBed3 = bed3;
        jLabelRoom3.setText(theBed3.visit_bed_number);
        jPanelRoom3.setVisible(true);
        jPanelRoom3.setOpaque(true);
        if(!theBed3.visit_bed_book.equals("1"))
        {
            if(available)
                jPanelRoom3.setBackground(Color.GREEN);  
            else
            {
                jPanelRoom3.setToolTipText(theBed3.caption);
                jPanelRoom3.setBackground(Color.RED); 
            }
        }
        else
        {
            jPanelRoom3.setBackground(Color.ORANGE); 
        } 
    }
    public void setBed4(Bed bed4,boolean available)
    {
        theBed4Available = available;
        theBed4 = bed4;
        jLabelRoom4.setText(theBed4.visit_bed_number);
        jPanelRoom4.setVisible(true);
        jPanelRoom4.setOpaque(true);
        if(!theBed4.visit_bed_book.equals("1"))
        {
            if(available)
                jPanelRoom4.setBackground(Color.GREEN);  
            else
            {
                jPanelRoom4.setToolTipText(theBed4.caption);
                jPanelRoom4.setBackground(Color.RED);  
            }
        }
        else
        {
            jPanelRoom4.setBackground(Color.ORANGE); 
        }  
    }
    public void setBed5(Bed bed5,boolean available)
    {
        theBed5Available = available;
        theBed5 = bed5;
        jLabelRoom5.setText(theBed5.visit_bed_number);
        jPanelRoom5.setVisible(true);
        jPanelRoom5.setOpaque(true);
        if(!theBed5.visit_bed_book.equals("1"))
        {
            if(available)
                jPanelRoom5.setBackground(Color.GREEN);  
            else
            {
                jPanelRoom5.setToolTipText(theBed5.caption);
                jPanelRoom5.setBackground(Color.RED);  
            }
        }
        else
        {
            jPanelRoom5.setBackground(Color.ORANGE); 
        } 
    }
    public void setBed6(Bed bed6,boolean available)
    {
        theBed6Available = available;
        theBed6 = bed6;
        jLabelRoom6.setText(theBed6.visit_bed_number);
        jPanelRoom6.setVisible(true);
        jPanelRoom6.setOpaque(true);
        if(!theBed6.visit_bed_book.equals("1"))
        {
            if(available)
                jPanelRoom6.setBackground(Color.GREEN);  
            else
            {
                jPanelRoom6.setToolTipText(theBed6.caption);
                jPanelRoom6.setBackground(Color.RED);  
            }
        }
        else
        {
            jPanelRoom6.setBackground(Color.ORANGE); 
        } 
    }
    public void setBed7(Bed bed7,boolean available)
    {
        theBed7Available = available;
        theBed7 = bed7;
        jLabelRoom7.setText(theBed7.visit_bed_number);
        jPanelRoom7.setVisible(true);
        jPanelRoom7.setOpaque(true);
        if(!theBed7.visit_bed_book.equals("1"))
        {
            if(available)
                jPanelRoom7.setBackground(Color.GREEN);  
            else
            {
                jPanelRoom7.setToolTipText(theBed7.caption);
                jPanelRoom7.setBackground(Color.RED); 
            }
        }
        else
        {
            jPanelRoom7.setBackground(Color.ORANGE); 
        }  
    }
    public void setBed8(Bed bed8,boolean available)
    {
        theBed8Available = available;
        theBed8 = bed8;
        jLabelRoom8.setText(theBed8.visit_bed_number);
        jPanelRoom8.setVisible(true);
        jPanelRoom8.setOpaque(true);
        if(!theBed8.visit_bed_book.equals("1"))
        {
            if(available)
                jPanelRoom8.setBackground(Color.GREEN);  
            else
            {
                jPanelRoom8.setToolTipText(theBed8.caption);
                jPanelRoom8.setBackground(Color.RED);  
            }
        }
        else
        {
            jPanelRoom8.setBackground(Color.ORANGE); 
        } 
    }
    public void setBed9(Bed bed9,boolean available)
    {
        theBed9Available = available;
        theBed9 = bed9;
        jLabelRoom9.setText(theBed9.visit_bed_number);
        jPanelRoom9.setVisible(true);
        jPanelRoom9.setOpaque(true);
        if(!theBed9.visit_bed_book.equals("1"))
        {
            if(available)
                jPanelRoom9.setBackground(Color.GREEN);  
            else
            {
                jPanelRoom9.setToolTipText(theBed9.caption);
                jPanelRoom9.setBackground(Color.RED);  
            }
        }
        else
        {
            jPanelRoom9.setBackground(Color.ORANGE); 
        }   
    }
    public void setBed10(Bed bed10,boolean available)
    {
        theBed10Available = available;
        theBed10 = bed10;
        jLabelRoom10.setText(theBed10.visit_bed_number);
        jPanelRoom10.setVisible(true);
        jPanelRoom10.setOpaque(true);
        if(!theBed10.visit_bed_book.equals("1"))
        {
            if(available)
                jPanelRoom10.setBackground(Color.GREEN);  
            else
            {
                jPanelRoom10.setToolTipText(theBed10.caption);
                jPanelRoom10.setBackground(Color.RED);  
            }
        }
        else
        {
            jPanelRoom10.setBackground(Color.ORANGE); 
        } 
    }
    public void setBed11(Bed bed11,boolean available)
    {
        theBed11Available = available;
        theBed11 = bed11;
        jLabelRoom11.setText(theBed11.visit_bed_number);
        jPanelRoom11.setVisible(true);
        jPanelRoom11.setOpaque(true);
        if(!theBed11.visit_bed_book.equals("1"))
        {
            if(available)
                jPanelRoom11.setBackground(Color.GREEN);  
            else
            {
                jPanelRoom11.setToolTipText(theBed11.caption);
                jPanelRoom11.setBackground(Color.RED);
            }
        }
        else
        {
            jPanelRoom11.setBackground(Color.ORANGE); 
        } 
    }
    public void setBed12(Bed bed12,boolean available)
    {
        theBed12Available = available;
        theBed12 = bed12;
        jLabelRoom12.setText(theBed12.visit_bed_number);
        jPanelRoom12.setVisible(true);
        jPanelRoom12.setOpaque(true);
        if(!theBed12.visit_bed_book.equals("1"))
        {
            if(available)
                jPanelRoom12.setBackground(Color.GREEN);  
            else
            {
                jPanelRoom12.setToolTipText(theBed12.caption);
                jPanelRoom12.setBackground(Color.RED);
            }
        }
        else
        {
            jPanelRoom12.setBackground(Color.ORANGE); 
        }  
    }
    public void setBed13(Bed bed13,boolean available)
    {
        theBed13Available = available;
        theBed13 = bed13;
        jLabelRoom13.setText(theBed13.visit_bed_number);
        jPanelRoom13.setVisible(true);
        jPanelRoom13.setOpaque(true);
        if(!theBed13.visit_bed_book.equals("1"))
        {
            if(available)
                jPanelRoom13.setBackground(Color.GREEN);  
            else
            {
                jPanelRoom13.setToolTipText(theBed13.caption);
                jPanelRoom13.setBackground(Color.RED);  
            }
        }
        else
        {
            jPanelRoom13.setBackground(Color.ORANGE); 
        } 
    }
    public void setBed14(Bed bed14,boolean available)
    {
        theBed14Available = available;
        theBed14 = bed14;
        jLabelRoom14.setText(theBed14.visit_bed_number);
        jPanelRoom14.setVisible(true);
        jPanelRoom14.setOpaque(true);
        if(!theBed14.visit_bed_book.equals("1"))
        {
            if(available)
                jPanelRoom14.setBackground(Color.GREEN);  
            else
            {
                jPanelRoom14.setToolTipText(theBed14.caption);
                jPanelRoom14.setBackground(Color.RED);  
            }
        }
        else
        {
            jPanelRoom14.setBackground(Color.ORANGE); 
        } 
    }
    public void setBed15(Bed bed15,boolean available)
    {
        theBed15Available = available;
        theBed15 = bed15;
        jLabelRoom15.setText(theBed15.visit_bed_number);
        jPanelRoom15.setVisible(true);
        jPanelRoom15.setOpaque(true);
        if(!theBed15.visit_bed_book.equals("1"))
        {
            if(available)
                jPanelRoom15.setBackground(Color.GREEN);  
            else
            {
                jPanelRoom15.setToolTipText(theBed15.caption);
                jPanelRoom15.setBackground(Color.RED);  
            }
        }
        else
        {
            jPanelRoom15.setBackground(Color.ORANGE); 
        } 
    }
    public void setBed16(Bed bed16,boolean available)
    {
        theBed16Available = available;
        theBed16 = bed16;
        jLabelRoom16.setText(theBed16.visit_bed_number);
        jPanelRoom16.setVisible(true);
        jPanelRoom16.setOpaque(true);
        if(!theBed16.visit_bed_book.equals("1"))
        {
            if(available)
                jPanelRoom16.setBackground(Color.GREEN);  
            else
            {
                jPanelRoom16.setToolTipText(theBed16.caption);
                jPanelRoom16.setBackground(Color.RED);  
            }
        }
        else
        {
            jPanelRoom16.setBackground(Color.ORANGE); 
        }  
    }
    public void setBed17(Bed bed17,boolean available)
    {
        theBed17Available = available;
        theBed17 = bed17;
        jLabelRoom17.setText(theBed17.visit_bed_number);
        jPanelRoom17.setVisible(true);
        jPanelRoom17.setOpaque(true);
        if(!theBed17.visit_bed_book.equals("1"))
        {
            if(available)
                jPanelRoom17.setBackground(Color.GREEN);  
            else
            {
                jPanelRoom17.setToolTipText(theBed17.caption);
                jPanelRoom17.setBackground(Color.RED);  
            }
        }
        else
        {
            jPanelRoom17.setBackground(Color.ORANGE); 
        } 
    }
    public void setBed18(Bed bed18,boolean available)
    {
        theBed18Available = available;
        theBed18 = bed18;
        jLabelRoom18.setText(theBed18.visit_bed_number);
        jPanelRoom18.setVisible(true);
        jPanelRoom18.setOpaque(true);
        if(!theBed18.visit_bed_book.equals("1"))
        {
            if(available)
                jPanelRoom18.setBackground(Color.GREEN);  
            else
            {
                jPanelRoom18.setToolTipText(theBed18.caption);
                jPanelRoom18.setBackground(Color.RED);  
            }
        }
        else
        {
            jPanelRoom18.setBackground(Color.ORANGE); 
        }  
    }
    public void setBed19(Bed bed19,boolean available)
    {
        theBed19Available = available;
        theBed19 = bed19;
        jLabelRoom19.setText(theBed19.visit_bed_number);
        jPanelRoom19.setVisible(true);
        jPanelRoom19.setOpaque(true);
        if(!theBed19.visit_bed_book.equals("1"))
        {
            if(available)
                jPanelRoom19.setBackground(Color.GREEN);  
            else
            {
                jPanelRoom19.setToolTipText(theBed19.caption);
                jPanelRoom19.setBackground(Color.RED); 
            }
        }
        else
        {
            jPanelRoom19.setBackground(Color.ORANGE); 
        } 
    }
    public void setBed20(Bed bed20,boolean available)
    {
        theBed20Available = available;
        theBed20 = bed20;
        jLabelRoom20.setText(theBed20.visit_bed_number);
        jPanelRoom20.setVisible(true);
        jPanelRoom20.setOpaque(true);
        if(!theBed20.visit_bed_book.equals("1"))
        {
            if(available)
                jPanelRoom20.setBackground(Color.GREEN);  
            else
            {
                jPanelRoom20.setToolTipText(theBed20.caption);
                jPanelRoom20.setBackground(Color.RED);  
            }
        }
        else
        {
            jPanelRoom20.setBackground(Color.ORANGE); 
        }  
    }
    public void setBed21(Bed bed21,boolean available)
    {
        theBed21Available = available;
        theBed21 = bed21;
        jLabelRoom21.setText(theBed21.visit_bed_number);
        jPanelRoom21.setVisible(true);
        jPanelRoom21.setOpaque(true);
        if(!theBed21.visit_bed_book.equals("1"))
        {
            if(available)
                jPanelRoom21.setBackground(Color.GREEN);  
            else
            {
                jPanelRoom21.setToolTipText(theBed21.caption);
                jPanelRoom21.setBackground(Color.RED);  
            }
        }
        else
        {
            jPanelRoom21.setBackground(Color.ORANGE); 
        } 
    }
    public void setBed22(Bed bed22,boolean available)
    {
        theBed22Available = available;
        theBed22 = bed22;
        jLabelRoom22.setText(theBed22.visit_bed_number);
        jPanelRoom22.setVisible(true);
        jPanelRoom22.setOpaque(true);
        if(!theBed22.visit_bed_book.equals("1"))
        {
            if(available)
                jPanelRoom22.setBackground(Color.GREEN);  
            else
            {
                jPanelRoom22.setToolTipText(theBed22.caption);
                jPanelRoom22.setBackground(Color.RED);  
            }
        }
        else
        {
            jPanelRoom22.setBackground(Color.ORANGE); 
        } 
    }
    public void setBed23(Bed bed23,boolean available)
    {
        theBed23Available = available;
        theBed23 = bed23;
        jLabelRoom23.setText(theBed23.visit_bed_number);
        jPanelRoom23.setVisible(true);
        jPanelRoom23.setOpaque(true);
        if(!theBed23.visit_bed_book.equals("1"))
        {
            if(available)
                jPanelRoom23.setBackground(Color.GREEN);  
            else
            {
                jPanelRoom23.setToolTipText(theBed23.caption);
                jPanelRoom23.setBackground(Color.RED); 
            }
        }
        else
        {
            jPanelRoom23.setBackground(Color.ORANGE); 
        }  
    }
    public void setBed24(Bed bed24,boolean available)
    {
        theBed24Available = available;
        theBed24 = bed24;
        jLabelRoom24.setText(theBed24.visit_bed_number);
        jPanelRoom24.setVisible(true);
        jPanelRoom24.setOpaque(true);
        if(!theBed24.visit_bed_book.equals("1"))
        {
            if(available)
                jPanelRoom24.setBackground(Color.GREEN);  
            else
            {
                jPanelRoom24.setToolTipText(theBed24.caption);
                jPanelRoom24.setBackground(Color.RED);  
            }
        }
        else
        {
            jPanelRoom24.setBackground(Color.ORANGE); 
        } 
    }
    public void setBed25(Bed bed25,boolean available)
    {
        theBed25Available = available;
        theBed25 = bed25;
        jLabelRoom25.setText(theBed25.visit_bed_number);
        jPanelRoom25.setVisible(true);
        jPanelRoom25.setOpaque(true);
        if(!theBed25.visit_bed_book.equals("1"))
        {
            if(available)
                jPanelRoom25.setBackground(Color.GREEN);  
            else
            {
                jPanelRoom25.setToolTipText(theBed25.caption);
                jPanelRoom25.setBackground(Color.RED);  
            }
        }
        else
        {
            jPanelRoom25.setBackground(Color.ORANGE); 
        }   
    }
    public void setBed26(Bed bed26,boolean available)
    {
        theBed26Available = available;
        theBed26 = bed26;
        jLabelRoom26.setText(theBed26.visit_bed_number);
        jPanelRoom26.setVisible(true);
        jPanelRoom26.setOpaque(true);
        if(!theBed26.visit_bed_book.equals("1"))
        {
            if(available)
                jPanelRoom26.setBackground(Color.GREEN);  
            else
            {
                jPanelRoom26.setToolTipText(theBed26.caption);
                jPanelRoom26.setBackground(Color.RED);  
            }
        }
        else
        {
            jPanelRoom26.setBackground(Color.ORANGE); 
        } 
    }
    public void setBed27(Bed bed27,boolean available)
    {
        theBed27Available = available;
        theBed27 = bed27;
        jLabelRoom27.setText(theBed27.visit_bed_number);
        jPanelRoom27.setVisible(true);
        jPanelRoom27.setOpaque(true);
        if(!theBed27.visit_bed_book.equals("1"))
        {
            if(available)
                jPanelRoom27.setBackground(Color.GREEN);  
            else
            {
                jPanelRoom27.setToolTipText(theBed27.caption);
                jPanelRoom27.setBackground(Color.RED);
            }
        }
        else
        {
            jPanelRoom27.setBackground(Color.ORANGE); 
        } 
    }
    public void setBed28(Bed bed28,boolean available)
    {
        theBed28Available = available;
        theBed28 = bed28;
        jLabelRoom28.setText(theBed28.visit_bed_number);
        jPanelRoom28.setVisible(true);
        jPanelRoom28.setOpaque(true);
        if(!theBed28.visit_bed_book.equals("1"))
        {
            if(available)
                jPanelRoom28.setBackground(Color.GREEN);  
            else
            {
                jPanelRoom28.setToolTipText(theBed28.caption);
                jPanelRoom28.setBackground(Color.RED);
            }
        }
        else
        {
            jPanelRoom28.setBackground(Color.ORANGE); 
        }  
    }
    public void setBed29(Bed bed29,boolean available)
    {
        theBed29Available = available;
        theBed29 = bed29;
        jLabelRoom29.setText(theBed29.visit_bed_number);
        jPanelRoom29.setVisible(true);
        jPanelRoom29.setOpaque(true);
        if(!theBed29.visit_bed_book.equals("1"))
        {
            if(available)
                jPanelRoom29.setBackground(Color.GREEN);  
            else
            {
                jPanelRoom29.setToolTipText(theBed29.caption);
                jPanelRoom29.setBackground(Color.RED);  
            }
        }
        else
        {
            jPanelRoom29.setBackground(Color.ORANGE); 
        } 
    }
    public void setBed30(Bed bed30,boolean available)
    {
        theBed30Available = available;
        theBed30 = bed30;
        jLabelRoom30.setText(theBed30.visit_bed_number);
        jPanelRoom30.setVisible(true);
        jPanelRoom30.setOpaque(true);
        if(!theBed30.visit_bed_book.equals("1"))
        {
            if(available)
                jPanelRoom30.setBackground(Color.GREEN);  
            else
            {
                jPanelRoom30.setToolTipText(theBed30.caption);
                jPanelRoom30.setBackground(Color.RED);  
            }
        }
        else
        {
            jPanelRoom30.setBackground(Color.ORANGE); 
        } 
    }
    public void setBed31(Bed bed31,boolean available)
    {
        theBed31Available = available;
        theBed31 = bed31;
        jLabelRoom31.setText(theBed31.visit_bed_number);
        jPanelRoom31.setVisible(true);
        jPanelRoom31.setOpaque(true);
        if(!theBed31.visit_bed_book.equals("1"))
        {
            if(available)
                jPanelRoom31.setBackground(Color.GREEN);  
            else
            {
                jPanelRoom31.setToolTipText(theBed31.caption);
                jPanelRoom31.setBackground(Color.RED);  
            }
        }
        else
        {
            jPanelRoom31.setBackground(Color.ORANGE); 
        } 
    }
    public void setBed32(Bed bed32,boolean available)
    {
        theBed32Available = available;
        theBed32 = bed32;
        jLabelRoom32.setText(theBed32.visit_bed_number);
        jPanelRoom32.setVisible(true);
        jPanelRoom32.setOpaque(true);
        if(!theBed32.visit_bed_book.equals("1"))
        {
            if(available)
                jPanelRoom32.setBackground(Color.GREEN);  
            else
            {
                jPanelRoom32.setToolTipText(theBed32.caption);
                jPanelRoom32.setBackground(Color.RED);  
            }
        }
        else
        {
            jPanelRoom32.setBackground(Color.ORANGE); 
        }  
    }
    
    public void flushN()
    {
        for(int i=0;i<theHC.theHO.thePanelRoom2V.size();i++)
        {
            if(theHC.theHO.thePanelRoom2V.get(i) instanceof PanelRoom2)
            {
                PanelRoom2 tmp = (PanelRoom2) theHC.theHO.thePanelRoom2V.get(i);
                tmp.repaintN();
            }
            if(theHC.theHO.thePanelRoom2V.get(i) instanceof PanelRoom21)
            {
                PanelRoom21 tmp = (PanelRoom21) theHC.theHO.thePanelRoom2V.get(i);
                tmp.repaintN();
            }
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabelTitle = new javax.swing.JLabel();
        jPanelRoom2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabelRoom2 = new javax.swing.JLabel();
        jPanelRoom1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabelRoom1 = new javax.swing.JLabel();
        jPanelRoom3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabelRoom3 = new javax.swing.JLabel();
        jPanelRoom4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabelRoom4 = new javax.swing.JLabel();
        jPanelRoom5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabelRoom5 = new javax.swing.JLabel();
        jPanelRoom6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabelRoom6 = new javax.swing.JLabel();
        jPanelRoom7 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabelRoom7 = new javax.swing.JLabel();
        jPanelRoom8 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabelRoom8 = new javax.swing.JLabel();
        jPanelRoom9 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabelRoom9 = new javax.swing.JLabel();
        jPanelRoom10 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabelRoom10 = new javax.swing.JLabel();
        jPanelRoom11 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabelRoom11 = new javax.swing.JLabel();
        jPanelRoom12 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabelRoom12 = new javax.swing.JLabel();
        jPanelRoom13 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabelRoom13 = new javax.swing.JLabel();
        jPanelRoom14 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabelRoom14 = new javax.swing.JLabel();
        jPanelRoom15 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabelRoom15 = new javax.swing.JLabel();
        jPanelRoom16 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabelRoom16 = new javax.swing.JLabel();
        jPanelRoom17 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabelRoom17 = new javax.swing.JLabel();
        jPanelRoom18 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabelRoom18 = new javax.swing.JLabel();
        jPanelRoom19 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabelRoom19 = new javax.swing.JLabel();
        jPanelRoom20 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabelRoom20 = new javax.swing.JLabel();
        jPanelRoom21 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabelRoom21 = new javax.swing.JLabel();
        jPanelRoom22 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabelRoom22 = new javax.swing.JLabel();
        jPanelRoom23 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabelRoom23 = new javax.swing.JLabel();
        jPanelRoom24 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabelRoom24 = new javax.swing.JLabel();
        jPanelRoom25 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabelRoom25 = new javax.swing.JLabel();
        jPanelRoom26 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabelRoom26 = new javax.swing.JLabel();
        jPanelRoom27 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabelRoom27 = new javax.swing.JLabel();
        jPanelRoom28 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabelRoom28 = new javax.swing.JLabel();
        jPanelRoom29 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabelRoom29 = new javax.swing.JLabel();
        jPanelRoom30 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabelRoom30 = new javax.swing.JLabel();
        jPanelRoom31 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jLabelRoom31 = new javax.swing.JLabel();
        jPanelRoom32 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabelRoom32 = new javax.swing.JLabel();

        setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabelTitle.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel1.add(jLabelTitle, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        add(jPanel1, gridBagConstraints);

        jPanelRoom2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelRoom2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanelRoom2MouseReleased(evt);
            }
        });
        jPanelRoom2.setLayout(new java.awt.GridBagLayout());

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/bed.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelRoom2.add(jLabel2, gridBagConstraints);

        jLabelRoom2.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanelRoom2.add(jLabelRoom2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jPanelRoom2, gridBagConstraints);

        jPanelRoom1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelRoom1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanelRoom1MouseReleased(evt);
            }
        });
        jPanelRoom1.setLayout(new java.awt.GridBagLayout());

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/bed.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelRoom1.add(jLabel4, gridBagConstraints);

        jLabelRoom1.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanelRoom1.add(jLabelRoom1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jPanelRoom1, gridBagConstraints);

        jPanelRoom3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelRoom3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanelRoom3MouseReleased(evt);
            }
        });
        jPanelRoom3.setLayout(new java.awt.GridBagLayout());

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/bed.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelRoom3.add(jLabel3, gridBagConstraints);

        jLabelRoom3.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanelRoom3.add(jLabelRoom3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jPanelRoom3, gridBagConstraints);

        jPanelRoom4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelRoom4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanelRoom4MouseReleased(evt);
            }
        });
        jPanelRoom4.setLayout(new java.awt.GridBagLayout());

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/bed.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelRoom4.add(jLabel5, gridBagConstraints);

        jLabelRoom4.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanelRoom4.add(jLabelRoom4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jPanelRoom4, gridBagConstraints);

        jPanelRoom5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelRoom5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanelRoom5MouseReleased(evt);
            }
        });
        jPanelRoom5.setLayout(new java.awt.GridBagLayout());

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/bed.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelRoom5.add(jLabel6, gridBagConstraints);

        jLabelRoom5.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanelRoom5.add(jLabelRoom5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jPanelRoom5, gridBagConstraints);

        jPanelRoom6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelRoom6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanelRoom6MouseReleased(evt);
            }
        });
        jPanelRoom6.setLayout(new java.awt.GridBagLayout());

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/bed.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelRoom6.add(jLabel7, gridBagConstraints);

        jLabelRoom6.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanelRoom6.add(jLabelRoom6, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jPanelRoom6, gridBagConstraints);

        jPanelRoom7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelRoom7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanelRoom7MouseReleased(evt);
            }
        });
        jPanelRoom7.setLayout(new java.awt.GridBagLayout());

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/bed.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelRoom7.add(jLabel8, gridBagConstraints);

        jLabelRoom7.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanelRoom7.add(jLabelRoom7, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jPanelRoom7, gridBagConstraints);

        jPanelRoom8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelRoom8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanelRoom8MouseReleased(evt);
            }
        });
        jPanelRoom8.setLayout(new java.awt.GridBagLayout());

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/bed.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelRoom8.add(jLabel9, gridBagConstraints);

        jLabelRoom8.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanelRoom8.add(jLabelRoom8, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jPanelRoom8, gridBagConstraints);

        jPanelRoom9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelRoom9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanelRoom9MouseReleased(evt);
            }
        });
        jPanelRoom9.setLayout(new java.awt.GridBagLayout());

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/bed.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelRoom9.add(jLabel10, gridBagConstraints);

        jLabelRoom9.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanelRoom9.add(jLabelRoom9, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jPanelRoom9, gridBagConstraints);

        jPanelRoom10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelRoom10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanelRoom10MouseReleased(evt);
            }
        });
        jPanelRoom10.setLayout(new java.awt.GridBagLayout());

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/bed.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelRoom10.add(jLabel11, gridBagConstraints);

        jLabelRoom10.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanelRoom10.add(jLabelRoom10, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jPanelRoom10, gridBagConstraints);

        jPanelRoom11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelRoom11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanelRoom11MouseReleased(evt);
            }
        });
        jPanelRoom11.setLayout(new java.awt.GridBagLayout());

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/bed.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelRoom11.add(jLabel12, gridBagConstraints);

        jLabelRoom11.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanelRoom11.add(jLabelRoom11, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jPanelRoom11, gridBagConstraints);

        jPanelRoom12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelRoom12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanelRoom12MouseReleased(evt);
            }
        });
        jPanelRoom12.setLayout(new java.awt.GridBagLayout());

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/bed.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelRoom12.add(jLabel13, gridBagConstraints);

        jLabelRoom12.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanelRoom12.add(jLabelRoom12, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jPanelRoom12, gridBagConstraints);

        jPanelRoom13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelRoom13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanelRoom13MouseReleased(evt);
            }
        });
        jPanelRoom13.setLayout(new java.awt.GridBagLayout());

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/bed.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelRoom13.add(jLabel14, gridBagConstraints);

        jLabelRoom13.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanelRoom13.add(jLabelRoom13, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jPanelRoom13, gridBagConstraints);

        jPanelRoom14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelRoom14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanelRoom14MouseReleased(evt);
            }
        });
        jPanelRoom14.setLayout(new java.awt.GridBagLayout());

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/bed.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelRoom14.add(jLabel15, gridBagConstraints);

        jLabelRoom14.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanelRoom14.add(jLabelRoom14, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jPanelRoom14, gridBagConstraints);

        jPanelRoom15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelRoom15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanelRoom15MouseReleased(evt);
            }
        });
        jPanelRoom15.setLayout(new java.awt.GridBagLayout());

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/bed.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelRoom15.add(jLabel16, gridBagConstraints);

        jLabelRoom15.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanelRoom15.add(jLabelRoom15, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jPanelRoom15, gridBagConstraints);

        jPanelRoom16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelRoom16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanelRoom16MouseReleased(evt);
            }
        });
        jPanelRoom16.setLayout(new java.awt.GridBagLayout());

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/bed.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelRoom16.add(jLabel17, gridBagConstraints);

        jLabelRoom16.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanelRoom16.add(jLabelRoom16, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jPanelRoom16, gridBagConstraints);

        jPanelRoom17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelRoom17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanelRoom17MouseReleased(evt);
            }
        });
        jPanelRoom17.setLayout(new java.awt.GridBagLayout());

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/bed.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelRoom17.add(jLabel18, gridBagConstraints);

        jLabelRoom17.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanelRoom17.add(jLabelRoom17, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jPanelRoom17, gridBagConstraints);

        jPanelRoom18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelRoom18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanelRoom18MouseReleased(evt);
            }
        });
        jPanelRoom18.setLayout(new java.awt.GridBagLayout());

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/bed.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelRoom18.add(jLabel19, gridBagConstraints);

        jLabelRoom18.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanelRoom18.add(jLabelRoom18, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jPanelRoom18, gridBagConstraints);

        jPanelRoom19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelRoom19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanelRoom19MouseReleased(evt);
            }
        });
        jPanelRoom19.setLayout(new java.awt.GridBagLayout());

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/bed.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelRoom19.add(jLabel20, gridBagConstraints);

        jLabelRoom19.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanelRoom19.add(jLabelRoom19, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jPanelRoom19, gridBagConstraints);

        jPanelRoom20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelRoom20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanelRoom20MouseReleased(evt);
            }
        });
        jPanelRoom20.setLayout(new java.awt.GridBagLayout());

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/bed.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelRoom20.add(jLabel21, gridBagConstraints);

        jLabelRoom20.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanelRoom20.add(jLabelRoom20, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jPanelRoom20, gridBagConstraints);

        jPanelRoom21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelRoom21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanelRoom21MouseReleased(evt);
            }
        });
        jPanelRoom21.setLayout(new java.awt.GridBagLayout());

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/bed.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelRoom21.add(jLabel22, gridBagConstraints);

        jLabelRoom21.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanelRoom21.add(jLabelRoom21, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jPanelRoom21, gridBagConstraints);

        jPanelRoom22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelRoom22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanelRoom22MouseReleased(evt);
            }
        });
        jPanelRoom22.setLayout(new java.awt.GridBagLayout());

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/bed.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelRoom22.add(jLabel23, gridBagConstraints);

        jLabelRoom22.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanelRoom22.add(jLabelRoom22, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jPanelRoom22, gridBagConstraints);

        jPanelRoom23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelRoom23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanelRoom23MouseReleased(evt);
            }
        });
        jPanelRoom23.setLayout(new java.awt.GridBagLayout());

        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/bed.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelRoom23.add(jLabel24, gridBagConstraints);

        jLabelRoom23.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanelRoom23.add(jLabelRoom23, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jPanelRoom23, gridBagConstraints);

        jPanelRoom24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelRoom24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanelRoom24MouseReleased(evt);
            }
        });
        jPanelRoom24.setLayout(new java.awt.GridBagLayout());

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/bed.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelRoom24.add(jLabel25, gridBagConstraints);

        jLabelRoom24.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanelRoom24.add(jLabelRoom24, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jPanelRoom24, gridBagConstraints);

        jPanelRoom25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelRoom25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanelRoom25MouseReleased(evt);
            }
        });
        jPanelRoom25.setLayout(new java.awt.GridBagLayout());

        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/bed.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelRoom25.add(jLabel26, gridBagConstraints);

        jLabelRoom25.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanelRoom25.add(jLabelRoom25, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jPanelRoom25, gridBagConstraints);

        jPanelRoom26.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelRoom26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanelRoom26MouseReleased(evt);
            }
        });
        jPanelRoom26.setLayout(new java.awt.GridBagLayout());

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/bed.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelRoom26.add(jLabel27, gridBagConstraints);

        jLabelRoom26.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanelRoom26.add(jLabelRoom26, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jPanelRoom26, gridBagConstraints);

        jPanelRoom27.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelRoom27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanelRoom27MouseReleased(evt);
            }
        });
        jPanelRoom27.setLayout(new java.awt.GridBagLayout());

        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/bed.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelRoom27.add(jLabel28, gridBagConstraints);

        jLabelRoom27.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanelRoom27.add(jLabelRoom27, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jPanelRoom27, gridBagConstraints);

        jPanelRoom28.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelRoom28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanelRoom28MouseReleased(evt);
            }
        });
        jPanelRoom28.setLayout(new java.awt.GridBagLayout());

        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/bed.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelRoom28.add(jLabel29, gridBagConstraints);

        jLabelRoom28.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanelRoom28.add(jLabelRoom28, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jPanelRoom28, gridBagConstraints);

        jPanelRoom29.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelRoom29.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanelRoom29MouseReleased(evt);
            }
        });
        jPanelRoom29.setLayout(new java.awt.GridBagLayout());

        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/bed.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelRoom29.add(jLabel30, gridBagConstraints);

        jLabelRoom29.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanelRoom29.add(jLabelRoom29, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jPanelRoom29, gridBagConstraints);

        jPanelRoom30.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelRoom30.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanelRoom30MouseReleased(evt);
            }
        });
        jPanelRoom30.setLayout(new java.awt.GridBagLayout());

        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/bed.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelRoom30.add(jLabel31, gridBagConstraints);

        jLabelRoom30.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanelRoom30.add(jLabelRoom30, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jPanelRoom30, gridBagConstraints);

        jPanelRoom31.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelRoom31.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanelRoom31MouseReleased(evt);
            }
        });
        jPanelRoom31.setLayout(new java.awt.GridBagLayout());

        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/bed.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelRoom31.add(jLabel32, gridBagConstraints);

        jLabelRoom31.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanelRoom31.add(jLabelRoom31, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jPanelRoom31, gridBagConstraints);

        jPanelRoom32.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelRoom32.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanelRoom32MouseReleased(evt);
            }
        });
        jPanelRoom32.setLayout(new java.awt.GridBagLayout());

        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/bed.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelRoom32.add(jLabel33, gridBagConstraints);

        jLabelRoom32.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanelRoom32.add(jLabelRoom32, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jPanelRoom32, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void jPanelRoom1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelRoom1MouseReleased
        if(!theBed1Available)
            return;
        flushN();
        jPanelRoom1.setOpaque(true);
        jPanelRoom1.setBackground(Color.LIGHT_GRAY);  
        theHC.theHO.theRoomSelected = theRoom;
        theHC.theHO.theBedSelected = theBed1;
    }//GEN-LAST:event_jPanelRoom1MouseReleased

    private void jPanelRoom2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelRoom2MouseReleased
        if(!theBed2Available)
            return;
        flushN();
        jPanelRoom2.setOpaque(true);
        jPanelRoom2.setBackground(Color.LIGHT_GRAY);  
        theHC.theHO.theRoomSelected = theRoom;
        theHC.theHO.theBedSelected = theBed2;
    }//GEN-LAST:event_jPanelRoom2MouseReleased

    private void jPanelRoom3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelRoom3MouseReleased
        if(!theBed3Available)
            return;
        flushN();
        jPanelRoom3.setOpaque(true);
        jPanelRoom3.setBackground(Color.LIGHT_GRAY);  
        theHC.theHO.theRoomSelected = theRoom;
        theHC.theHO.theBedSelected = theBed3;
    }//GEN-LAST:event_jPanelRoom3MouseReleased

    private void jPanelRoom4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelRoom4MouseReleased
        if(!theBed4Available)
            return;
        flushN();
        jPanelRoom4.setOpaque(true);
        jPanelRoom4.setBackground(Color.LIGHT_GRAY);  
        theHC.theHO.theRoomSelected = theRoom;
        theHC.theHO.theBedSelected = theBed4;
    }//GEN-LAST:event_jPanelRoom4MouseReleased

    private void jPanelRoom5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelRoom5MouseReleased
        if(!theBed5Available)
            return;
        flushN();
        jPanelRoom5.setOpaque(true);
        jPanelRoom5.setBackground(Color.LIGHT_GRAY);  
        theHC.theHO.theRoomSelected = theRoom;
        theHC.theHO.theBedSelected = theBed5;
    }//GEN-LAST:event_jPanelRoom5MouseReleased

    private void jPanelRoom6MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelRoom6MouseReleased
        if(!theBed6Available)
            return;
        flushN();
        jPanelRoom6.setOpaque(true);
        jPanelRoom6.setBackground(Color.LIGHT_GRAY);  
        theHC.theHO.theRoomSelected = theRoom;
        theHC.theHO.theBedSelected = theBed6;
    }//GEN-LAST:event_jPanelRoom6MouseReleased

    private void jPanelRoom7MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelRoom7MouseReleased
        if(!theBed7Available)
            return;
        flushN();
        jPanelRoom7.setOpaque(true);
        jPanelRoom7.setBackground(Color.LIGHT_GRAY);  
        theHC.theHO.theRoomSelected = theRoom;
        theHC.theHO.theBedSelected = theBed7;
    }//GEN-LAST:event_jPanelRoom7MouseReleased

    private void jPanelRoom8MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelRoom8MouseReleased
        if(!theBed8Available)
            return;
        flushN();
        jPanelRoom8.setOpaque(true);
        jPanelRoom8.setBackground(Color.LIGHT_GRAY);  
        theHC.theHO.theRoomSelected = theRoom;
        theHC.theHO.theBedSelected = theBed8;
    }//GEN-LAST:event_jPanelRoom8MouseReleased

    private void jPanelRoom9MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelRoom9MouseReleased
        if(!theBed9Available)
            return;
        flushN();
        jPanelRoom9.setOpaque(true);
        jPanelRoom9.setBackground(Color.LIGHT_GRAY);  
        theHC.theHO.theRoomSelected = theRoom;
        theHC.theHO.theBedSelected = theBed9;
    }//GEN-LAST:event_jPanelRoom9MouseReleased

    private void jPanelRoom10MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelRoom10MouseReleased
        if(!theBed10Available)
            return;
        flushN();
        jPanelRoom10.setOpaque(true);
        jPanelRoom10.setBackground(Color.LIGHT_GRAY);  
        theHC.theHO.theRoomSelected = theRoom;
        theHC.theHO.theBedSelected = theBed10;
    }//GEN-LAST:event_jPanelRoom10MouseReleased

    private void jPanelRoom11MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelRoom11MouseReleased
        if(!theBed11Available)
            return;
        flushN();
        jPanelRoom11.setOpaque(true);
        jPanelRoom11.setBackground(Color.LIGHT_GRAY);  
        theHC.theHO.theRoomSelected = theRoom;
        theHC.theHO.theBedSelected = theBed11;
    }//GEN-LAST:event_jPanelRoom11MouseReleased

    private void jPanelRoom12MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelRoom12MouseReleased
        if(!theBed12Available)
            return;
        flushN();
        jPanelRoom12.setOpaque(true);
        jPanelRoom12.setBackground(Color.LIGHT_GRAY);  
        theHC.theHO.theRoomSelected = theRoom;
        theHC.theHO.theBedSelected = theBed12;
    }//GEN-LAST:event_jPanelRoom12MouseReleased

    private void jPanelRoom13MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelRoom13MouseReleased
        if(!theBed13Available)
            return;
        flushN();
        jPanelRoom13.setOpaque(true);
        jPanelRoom13.setBackground(Color.LIGHT_GRAY);  
        theHC.theHO.theRoomSelected = theRoom;
        theHC.theHO.theBedSelected = theBed13;
    }//GEN-LAST:event_jPanelRoom13MouseReleased

    private void jPanelRoom14MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelRoom14MouseReleased
        if(!theBed14Available)
            return;
        flushN();
        jPanelRoom14.setOpaque(true);
        jPanelRoom14.setBackground(Color.LIGHT_GRAY);  
        theHC.theHO.theRoomSelected = theRoom;
        theHC.theHO.theBedSelected = theBed14;
    }//GEN-LAST:event_jPanelRoom14MouseReleased

    private void jPanelRoom15MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelRoom15MouseReleased
        if(!theBed15Available)
            return;
        flushN();
        jPanelRoom15.setOpaque(true);
        jPanelRoom15.setBackground(Color.LIGHT_GRAY);  
        theHC.theHO.theRoomSelected = theRoom;
        theHC.theHO.theBedSelected = theBed15;
    }//GEN-LAST:event_jPanelRoom15MouseReleased

    private void jPanelRoom16MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelRoom16MouseReleased
        if(!theBed16Available)
            return;
        flushN();
        jPanelRoom16.setOpaque(true);
        jPanelRoom16.setBackground(Color.LIGHT_GRAY);  
        theHC.theHO.theRoomSelected = theRoom;
        theHC.theHO.theBedSelected = theBed16;
    }//GEN-LAST:event_jPanelRoom16MouseReleased

    private void jPanelRoom17MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelRoom17MouseReleased
        if(!theBed17Available)
            return;
        flushN();
        jPanelRoom17.setOpaque(true);
        jPanelRoom17.setBackground(Color.LIGHT_GRAY);  
        theHC.theHO.theRoomSelected = theRoom;
        theHC.theHO.theBedSelected = theBed17;
    }//GEN-LAST:event_jPanelRoom17MouseReleased

    private void jPanelRoom18MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelRoom18MouseReleased
        if(!theBed18Available)
            return;
        flushN();
        jPanelRoom18.setOpaque(true);
        jPanelRoom18.setBackground(Color.LIGHT_GRAY);  
        theHC.theHO.theRoomSelected = theRoom;
        theHC.theHO.theBedSelected = theBed18;
    }//GEN-LAST:event_jPanelRoom18MouseReleased

    private void jPanelRoom19MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelRoom19MouseReleased
        if(!theBed19Available)
            return;
        flushN();
        jPanelRoom19.setOpaque(true);
        jPanelRoom19.setBackground(Color.LIGHT_GRAY);  
        theHC.theHO.theRoomSelected = theRoom;
        theHC.theHO.theBedSelected = theBed19;
    }//GEN-LAST:event_jPanelRoom19MouseReleased

    private void jPanelRoom20MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelRoom20MouseReleased
        if(!theBed20Available)
            return;
        flushN();
        jPanelRoom20.setOpaque(true);
        jPanelRoom20.setBackground(Color.LIGHT_GRAY);  
        theHC.theHO.theRoomSelected = theRoom;
        theHC.theHO.theBedSelected = theBed20;
    }//GEN-LAST:event_jPanelRoom20MouseReleased

    private void jPanelRoom21MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelRoom21MouseReleased
        if(!theBed21Available)
            return;
        flushN();
        jPanelRoom21.setOpaque(true);
        jPanelRoom21.setBackground(Color.LIGHT_GRAY);  
        theHC.theHO.theRoomSelected = theRoom;
        theHC.theHO.theBedSelected = theBed21;
    }//GEN-LAST:event_jPanelRoom21MouseReleased

    private void jPanelRoom22MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelRoom22MouseReleased
        if(!theBed22Available)
            return;
        flushN();
        jPanelRoom22.setOpaque(true);
        jPanelRoom22.setBackground(Color.LIGHT_GRAY);  
        theHC.theHO.theRoomSelected = theRoom;
        theHC.theHO.theBedSelected = theBed22;
    }//GEN-LAST:event_jPanelRoom22MouseReleased

    private void jPanelRoom23MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelRoom23MouseReleased
        if(!theBed23Available)
            return;
        flushN();
        jPanelRoom23.setOpaque(true);
        jPanelRoom23.setBackground(Color.LIGHT_GRAY);  
        theHC.theHO.theRoomSelected = theRoom;
        theHC.theHO.theBedSelected = theBed23;
    }//GEN-LAST:event_jPanelRoom23MouseReleased

    private void jPanelRoom24MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelRoom24MouseReleased
        if(!theBed24Available)
            return;
        flushN();
        jPanelRoom24.setOpaque(true);
        jPanelRoom24.setBackground(Color.LIGHT_GRAY);  
        theHC.theHO.theRoomSelected = theRoom;
        theHC.theHO.theBedSelected = theBed24;
    }//GEN-LAST:event_jPanelRoom24MouseReleased

    private void jPanelRoom25MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelRoom25MouseReleased
        if(!theBed25Available)
            return;
        flushN();
        jPanelRoom25.setOpaque(true);
        jPanelRoom25.setBackground(Color.LIGHT_GRAY);  
        theHC.theHO.theRoomSelected = theRoom;
        theHC.theHO.theBedSelected = theBed25;
    }//GEN-LAST:event_jPanelRoom25MouseReleased

    private void jPanelRoom26MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelRoom26MouseReleased
        if(!theBed26Available)
            return;
        flushN();
        jPanelRoom26.setOpaque(true);
        jPanelRoom26.setBackground(Color.LIGHT_GRAY);  
        theHC.theHO.theRoomSelected = theRoom;
        theHC.theHO.theBedSelected = theBed26;
    }//GEN-LAST:event_jPanelRoom26MouseReleased

    private void jPanelRoom27MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelRoom27MouseReleased
        if(!theBed27Available)
            return;
        flushN();
        jPanelRoom27.setOpaque(true);
        jPanelRoom27.setBackground(Color.LIGHT_GRAY);  
        theHC.theHO.theRoomSelected = theRoom;
        theHC.theHO.theBedSelected = theBed27;
    }//GEN-LAST:event_jPanelRoom27MouseReleased

    private void jPanelRoom28MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelRoom28MouseReleased
        if(!theBed28Available)
            return;
        flushN();
        jPanelRoom28.setOpaque(true);
        jPanelRoom28.setBackground(Color.LIGHT_GRAY);  
        theHC.theHO.theRoomSelected = theRoom;
        theHC.theHO.theBedSelected = theBed28;
    }//GEN-LAST:event_jPanelRoom28MouseReleased

    private void jPanelRoom29MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelRoom29MouseReleased
        if(!theBed29Available)
            return;
        flushN();
        jPanelRoom29.setOpaque(true);
        jPanelRoom29.setBackground(Color.LIGHT_GRAY);  
        theHC.theHO.theRoomSelected = theRoom;
        theHC.theHO.theBedSelected = theBed29;
    }//GEN-LAST:event_jPanelRoom29MouseReleased

    private void jPanelRoom30MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelRoom30MouseReleased
        if(!theBed30Available)
            return;
        flushN();
        jPanelRoom30.setOpaque(true);
        jPanelRoom30.setBackground(Color.LIGHT_GRAY);  
        theHC.theHO.theRoomSelected = theRoom;
        theHC.theHO.theBedSelected = theBed30;
    }//GEN-LAST:event_jPanelRoom30MouseReleased

    private void jPanelRoom31MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelRoom31MouseReleased
        if(!theBed31Available)
            return;
        flushN();
        jPanelRoom31.setOpaque(true);
        jPanelRoom31.setBackground(Color.LIGHT_GRAY);  
        theHC.theHO.theRoomSelected = theRoom;
        theHC.theHO.theBedSelected = theBed31;
    }//GEN-LAST:event_jPanelRoom31MouseReleased

    private void jPanelRoom32MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelRoom32MouseReleased
        if(!theBed32Available)
            return;
        flushN();
        jPanelRoom32.setOpaque(true);
        jPanelRoom32.setBackground(Color.LIGHT_GRAY);  
        theHC.theHO.theRoomSelected = theRoom;
        theHC.theHO.theBedSelected = theBed32;
    }//GEN-LAST:event_jPanelRoom32MouseReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelRoom1;
    private javax.swing.JLabel jLabelRoom10;
    private javax.swing.JLabel jLabelRoom11;
    private javax.swing.JLabel jLabelRoom12;
    private javax.swing.JLabel jLabelRoom13;
    private javax.swing.JLabel jLabelRoom14;
    private javax.swing.JLabel jLabelRoom15;
    private javax.swing.JLabel jLabelRoom16;
    private javax.swing.JLabel jLabelRoom17;
    private javax.swing.JLabel jLabelRoom18;
    private javax.swing.JLabel jLabelRoom19;
    private javax.swing.JLabel jLabelRoom2;
    private javax.swing.JLabel jLabelRoom20;
    private javax.swing.JLabel jLabelRoom21;
    private javax.swing.JLabel jLabelRoom22;
    private javax.swing.JLabel jLabelRoom23;
    private javax.swing.JLabel jLabelRoom24;
    private javax.swing.JLabel jLabelRoom25;
    private javax.swing.JLabel jLabelRoom26;
    private javax.swing.JLabel jLabelRoom27;
    private javax.swing.JLabel jLabelRoom28;
    private javax.swing.JLabel jLabelRoom29;
    private javax.swing.JLabel jLabelRoom3;
    private javax.swing.JLabel jLabelRoom30;
    private javax.swing.JLabel jLabelRoom31;
    private javax.swing.JLabel jLabelRoom32;
    private javax.swing.JLabel jLabelRoom4;
    private javax.swing.JLabel jLabelRoom5;
    private javax.swing.JLabel jLabelRoom6;
    private javax.swing.JLabel jLabelRoom7;
    private javax.swing.JLabel jLabelRoom8;
    private javax.swing.JLabel jLabelRoom9;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelRoom1;
    private javax.swing.JPanel jPanelRoom10;
    private javax.swing.JPanel jPanelRoom11;
    private javax.swing.JPanel jPanelRoom12;
    private javax.swing.JPanel jPanelRoom13;
    private javax.swing.JPanel jPanelRoom14;
    private javax.swing.JPanel jPanelRoom15;
    private javax.swing.JPanel jPanelRoom16;
    private javax.swing.JPanel jPanelRoom17;
    private javax.swing.JPanel jPanelRoom18;
    private javax.swing.JPanel jPanelRoom19;
    private javax.swing.JPanel jPanelRoom2;
    private javax.swing.JPanel jPanelRoom20;
    private javax.swing.JPanel jPanelRoom21;
    private javax.swing.JPanel jPanelRoom22;
    private javax.swing.JPanel jPanelRoom23;
    private javax.swing.JPanel jPanelRoom24;
    private javax.swing.JPanel jPanelRoom25;
    private javax.swing.JPanel jPanelRoom26;
    private javax.swing.JPanel jPanelRoom27;
    private javax.swing.JPanel jPanelRoom28;
    private javax.swing.JPanel jPanelRoom29;
    private javax.swing.JPanel jPanelRoom3;
    private javax.swing.JPanel jPanelRoom30;
    private javax.swing.JPanel jPanelRoom31;
    private javax.swing.JPanel jPanelRoom32;
    private javax.swing.JPanel jPanelRoom4;
    private javax.swing.JPanel jPanelRoom5;
    private javax.swing.JPanel jPanelRoom6;
    private javax.swing.JPanel jPanelRoom7;
    private javax.swing.JPanel jPanelRoom8;
    private javax.swing.JPanel jPanelRoom9;
    // End of variables declaration//GEN-END:variables
}
