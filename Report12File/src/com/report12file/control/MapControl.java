/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.report12file.control;

import com.hospital_os.usecase.connection.ConnectionInf;
import com.hosv3.control.MapCon;
import com.hosv3.utility.connection.UpdateStatus;
import com.report12file.utility.Report12FileData;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author henbe
 */
public class MapControl extends MapCon {
    public MapControl(String[] str,UpdateStatus us,ConnectionInf con){
        super(str,us,con) ;
    }
    protected int intMapData(String[] map, String[] lookup) throws SQLException {

        int ret=0;
            PreparedStatement ps = theCon.getConnection().prepareStatement(sqlupdate);
            if(sqlupdate.equals(Report12FileData.MAP_CLINIC_12FILE[2])){
                PreparedStatement ps1 = theCon.getConnection().prepareStatement(sqldelete);
                ps1.setString(1, map[0]);
                ps1.executeUpdate();
                ps.setString(1, map[0]+lookup[0]);
                ps.setString(2, map[0]);
                ps.setString(3, "");
                ps.setString(4, map[1]);
                ps.setString(5, lookup[0]);
                ret = ps.executeUpdate();
            }
            else{
                ps.setString(1, lookup[0]);
                ps.setString(2, map[0]);
                ret = ps.executeUpdate();
            }
            return ret;
    }

}
