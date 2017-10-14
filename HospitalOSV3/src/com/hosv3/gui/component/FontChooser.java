/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hosv3.gui.component;

import com.hospital_os.gui.font.DefaultFont;
import com.hospital_os.utility.IOStream;
import com.hosv3.utility.GuiLang;
import com.hosv3.utility.connection.UpdateStatus;
 import java.awt.*;
 import java.awt.event.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
 import javax.swing.*;
 import javax.swing.event.*;

 public class FontChooser extends JPanel
 {
        // all variables, aside from the final public Font are simply for the UI of the class
         private JLabel Preview;
         private JComboBox FontSize;
         private JList FontList;
         private JScrollPane jScrollPane1;
         private JList Style;
         private JScrollPane jScrollPane2;
         public  JButton OK_Button;
         private JButton Cancel_Button;
         // the SelectedFont attribute can be accessed by other classes
         public  Font SelectedFont;
         private FontDialog Parent;
    private UpdateStatus theUS;

         public FontChooser(FontDialog parent,UpdateStatus us) {
                 super();
                 theUS = us;
                 initialiseComponent();
                 String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
                 this.FontList.setListData(fonts);
                 for (int i = 8; i <= 72; i += 2)
                         this.FontSize.addItem(String.valueOf(i));

                 String[] styles = {"Regular", "Italic", "Bold", "Bold Italic"};
                 this.Style.setListData(styles);
                 this.Parent = parent;
         }

         private void initialiseComponent() {
                 this.Preview = new JLabel();
                 this.FontSize = new JComboBox();
                 this.FontList = new JList();
                 this.jScrollPane1 = new JScrollPane();
                 this.Style = new JList();
                 this.jScrollPane2 = new JScrollPane();
                 this.OK_Button = new JButton();
                 this.Cancel_Button = new JButton();

                 this.Preview.setText("Preview แบบอักษร");

                 this.FontSize.addActionListener(new ActionListener() {
                         public void actionPerformed(ActionEvent e) {
                                 FontChanged();
                         }
                 });
                 this.FontList.addListSelectionListener(new ListSelectionListener() {
                         public void valueChanged(ListSelectionEvent e) {
                                 FontChanged();
                         }
                 });

                 this.jScrollPane1.setViewportView(this.FontList);

                 this.Style.addListSelectionListener(new ListSelectionListener() {
                         public void valueChanged(ListSelectionEvent e) {
                                 FontChanged();
                         }
                 });

                 this.jScrollPane2.setViewportView(this.Style);

                 this.OK_Button.setText("OK");
                 this.OK_Button.addActionListener(new ActionListener() {
                         public void actionPerformed(ActionEvent e) {
                                 OK_Button_actionPerformed(e);
                         }
                 });

                 this.Cancel_Button.setText("Cancel");
                 this.Cancel_Button.addActionListener(new ActionListener() {
                         public void actionPerformed(ActionEvent e) {
                                 Cancel_Button_actionPerformed(e);
                         }
                 });

                 this.setLayout(null);
                 addComponent(this, this.Preview, 18,166,132,27);
                 addComponent(this, this.FontSize, 231,15,100,22);
                 addComponent(this, this.jScrollPane1, 15,14,196,131);
                 addComponent(this, this.jScrollPane2, 231,47,100,100);
                 addComponent(this, this.OK_Button, 158,166,83,28);
                 addComponent(this, this.Cancel_Button, 248,165,83,28);

                 this.setLocation(new Point(100,100));
                 this.setSize(new Dimension(360, 242));
         }

         private void addComponent(Container container,Component c,int x,int y,int width,int height) {
                 c.setBounds(x,y,width,height);
                 container.add(c);
         }

         private void FontChanged() {
                 try {
                        int size = this.FontSize.getSelectedIndex()*2 + 8;
                        String name = this.FontList.getSelectedValue().toString();
                        if (this.Style.getSelectedIndex() == 0) // Regular
                                this.SelectedFont = new Font(name, Font.PLAIN, size);
                        else if (this.Style.getSelectedIndex() == 1) // Italic
                                this.SelectedFont = new Font(name, Font.ITALIC, size);
                        else if (this.Style.getSelectedIndex() == 2) // Bold
                                this.SelectedFont = new Font(name, Font.BOLD, size);
                        else if (this.Style.getSelectedIndex() == 3) // Bold Italic
                                this.SelectedFont = new Font(name, Font.BOLD+Font.ITALIC, size);
                        else // default, none selected
                                this.SelectedFont = new Font(name, Font.PLAIN, size);
                        this.Preview.setFont(this.SelectedFont);
                 } catch (Exception ex) {
                         System.out.println(ex.getMessage());
                 }
         }

         private void OK_Button_actionPerformed(ActionEvent e) {

                 StringBuffer sb = new StringBuffer();
                sb.append("<font><font_name>");
                sb.append(SelectedFont.getName());
                sb.append("</font_name><font_style>");
                sb.append(SelectedFont.getStyle());
                sb.append("</font_style><font_size>");
                sb.append(SelectedFont.getSize());
                sb.append("</font_size></font>");
                IOStream.writeOutput(sb.toString(), DefaultFont.filenamefont);
                SwingUtilities.updateComponentTreeUI(theUS.getJFrame());
                if(theUS.confirmBox(GuiLang.setLanguage("กรุณาเริ่มโปรแกรมใหม่อีกครั้งเพื่อเปลี่ยนรูปแบบการแสดงผล"),UpdateStatus.WARNING)){
                    System.exit(0);
                }
            this.Parent.setVisible(false);
                 // the font has been saved in memory
         }

         private void Cancel_Button_actionPerformed(ActionEvent e) {
                 this.SelectedFont = null;
                 this.Parent.setVisible(false);
         }
}
