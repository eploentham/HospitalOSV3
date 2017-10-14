/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hosv3.utility;

import com.hospital_os.usecase.connection.ConnectionInf;

/**
 *
 * @author henbe
 */
public class Splash extends Splash1{

    public void setText(ConnectionInf con_inf) {
        this.setText(Splash.getNews(con_inf));
    }


}
