/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hosv3.gui.component;

/*
 * And now let's look at the FontDialog class (this one's shorter)
 */

import com.hosv3.utility.GuiLang;
import com.hosv3.utility.connection.UpdateStatus;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Font;

import javax.swing.*;

public class FontDialog extends JDialog {
	public FontChooser font = null;
	public FontDialog(UpdateStatus us)
	{
            font = new FontChooser(this,us);
            this.setTitle(GuiLang.setLanguage("เลือก Font ของโปรแกรม"));
            this.setLocation(new Point(100,100));
            this.setSize(new Dimension(360, 242));
            this.getContentPane().add(font);
            this.setModal(true);
	}

	public Font showDialog()
	{
		this.setVisible(true);
		return this.font.SelectedFont;
	}
        public static void main(String[] argc){
            // EXAMPLE USAGE:
            FontDialog f = new FontDialog(null);
            Font x = f.showDialog();
            System.out.println(x.getFontName());
        }
}
