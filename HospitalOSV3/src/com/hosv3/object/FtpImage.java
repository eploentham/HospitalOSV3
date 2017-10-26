/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hosv3.object;

import com.hosv3.utility.connection.UpdateStatus;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPReply;

/**
 *
 * @author ekapop
 */
public class FtpImage {
    FTPClient ftpc;
    public GraphicsConfiguration gc=null;
    GraphicsEnvironment ge = null;
    public GraphicsDevice gd = null;
    
    UpdateStatus theUS;
    public FtpImage(UpdateStatus us){
        theUS = us;
        
        ftpc = new FTPClient();
        gc = getDefaultConfiguration();
    }
    public GraphicsConfiguration getDefaultConfiguration() {
        ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        gd = ge.getDefaultScreenDevice();
        return gd.getDefaultConfiguration();
    }
    public boolean appendFileToServer(String address, String user, String password, String pathFile, String filename, InputStream fis){
        boolean chk = false;
        FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
        try {
            ftpc.configure(conf);
            ftpc.connect(address);
            int reply = ftpc.getReplyCode();
            if(!FTPReply.isPositiveCompletion(reply)){//check ???????????????????????
                ftpc.disconnect();
                return false;
            }
            boolean login = ftpc.login(user, password);
            if(login){
                String[] path = pathFile.split("\\//");// change path folder ???????????? year month day
                for(int i=0;i<=path.length-1;i++){
                    if(path[i].equals("")){
                        continue;
                    }
                    boolean chkpath= ftpc.changeWorkingDirectory(ftpc.printWorkingDirectory()+"//"+path[i]);
                    if(!chkpath){
                        boolean chkmkpath = ftpc.makeDirectory(ftpc.printWorkingDirectory()+"//"+path[i]);
                        if(!chkmkpath){
                            return false;
                        }else{
                            ftpc.changeWorkingDirectory(ftpc.printWorkingDirectory()+"//"+path[i]);
                        }
                    }
                }
                ftpc.setFileType(FTP.BINARY_FILE_TYPE);
                ftpc.deleteFile(filename);
                ftpc.remoteStore(filename);
                chk = ftpc.storeFile(filename, fis);
                if(chk){
//                    JOptionPane.showMessageDialog(null, "save success");
                }else{
                    
                }
            }
            ftpc.logout();
            ftpc.disconnect();
        } catch (IOException ex) {
            Logger.getLogger(FtpImage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return chk;
    }
    public BufferedImage retriveFileFromServer(String address, String user, String password, String pathFile, String filename){
        InputStream fis = null;
        BufferedImage image = null;
        try {
            ftpc.connect(address);
            int reply = ftpc.getReplyCode();
            if(!FTPReply.isPositiveCompletion(reply)){//check ???????????????????????
                ftpc.disconnect();
                return null;
            }
            boolean login = ftpc.login(user, password);
            if(login){
                String[] path = pathFile.split("\\//");
                for(int i=0;i<=path.length-1;i++){
                    if(path[i].equals("")){
                        continue;
                    }
                    boolean chkpath= ftpc.changeWorkingDirectory(ftpc.printWorkingDirectory()+"//"+path[i]);
                    if(!chkpath){
                        return null;
                    }
                }
                ftpc.setFileType(FTP.BINARY_FILE_TYPE);
                fis = ftpc.retrieveFileStream(filename+".JPG");
                int reply1 = ftpc.getReplyCode();
                if(fis!=null){
                    image = ImageIO.read(fis);// ??????? ????? close fis ??? ???????????????? image ?????????? error ????????? ???????? fis ?????? image ?? error
                    fis.close();
                }
                
            }
            ftpc.logout();
            ftpc.disconnect();
        } catch (IOException ex) {
            Logger.getLogger(FtpImage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return image;
    }
    public BufferedImage retriveFileFromServerDx(String pathFile, String filename){
        String server = "172.25.1.5";
//        if(isUseReserveFtp())
//            server = config1.branch.getServerImageReserveName();
        return retriveFileFromServer(server, "opd","opd",pathFile, "dx"+filename);
    }
}
