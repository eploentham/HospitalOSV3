/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hosv3;
 
import com.hosv3.utility.Constant;
import com.hosv3.utility.GuiLang;
import com.hosv3.utility.Splash; 
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import javax.swing.JOptionPane;
import sun.misc.BASE64Encoder;
/**
 *
 * @author henbe
 */
public class HosUpdate {

    static final String filename = "hosv3.zip";
    static final String db_fn = ".hospital_os.cfg";
    static final String version = "version.txt";
    static final String link_fn = "config/update_server.txt";
    private static String proxy;
    private static String port;
    private static String user;
    private static String password;

    public static void main(String[] argc){
            checkUpdate(new Splash());
    }
    public static boolean deleteFileDir(String pathi){
        File path = new File(pathi);
        if (path.exists()) {
            if(path.isDirectory()){
                File[] files = path.listFiles();
                for (int i = 0; i < files.length; i++) {
                    if (files[i].isDirectory()) {
                        deleteFileDir(files[i].getAbsolutePath());
                    } else {
                        files[i].delete();
                    }
                }
            }
            else
                path.delete();
        }
        return (path.delete());

    }
    public static String checkVersionServer(String version) throws MalformedURLException, IOException{

        FileReader filev = new FileReader(version);
        BufferedReader br = new BufferedReader(filev);
        String server_version = br.readLine();
        filev.close();
        deleteFileDir(version);
        return server_version;
    }
    public static String checkVersionClientOld() throws IOException{
        int BUFFER = 2048;
        JarFile jar = new JarFile("lib/HospitalOSV3.jar");
        ZipEntry entry = jar.getEntry("com/hosv3/property/Config.properties");
        File dir = new File("./");
        File destFile = new File(dir, entry.getName());
        File destinationParent = destFile.getParentFile();
        destinationParent.mkdirs();
        if (!entry.isDirectory()) {
            BufferedInputStream is = new BufferedInputStream(jar.getInputStream(entry));
            int currentByte;
            byte[] data = new byte[BUFFER];
            FileOutputStream fos = new FileOutputStream(destFile);
            BufferedOutputStream dest = new BufferedOutputStream(fos, BUFFER);
            while ((currentByte = is.read(data, 0, BUFFER)) != -1) {
                dest.write(data, 0, currentByte);
            }
            dest.flush();
            dest.close();
            is.close();
        }
        jar.close();
        destFile.deleteOnExit();
        ////////////////////////////////////////////////////////////
        FileReader filev = new FileReader("com/hosv3/property/Config.properties");
        BufferedReader br = new BufferedReader(filev);
        String s,sret=null;
        while((s = br.readLine()) != null){
            if(s.startsWith("APP_CODE=")){
                sret = s.substring(s.indexOf("=")+1);
                //Constant.println(s);
                break;
            }
        }
        filev.close();
        deleteFileDir("com");
        return sret;
    }
    public static String checkVersionClient() throws IOException{
         return ResourceBundle.getBundle("com/hosv3/property/Config").getString("APP_CODE");
    }
    public static void loadFile(String surl,String file) throws MalformedURLException, IOException{

        Constant.print("LoadFile:..."+surl);
        URL url = new URL(surl);
        URLConnection urlConnection = url.openConnection();
        if(proxy!=null){
            System.setProperty("http.proxyHost", proxy);
            System.setProperty("http.proxyPort", port);
            BASE64Encoder encoder = new BASE64Encoder();
            String encoded = encoder.encode((user+":"+password).getBytes());
            urlConnection = url.openConnection();
            urlConnection.setRequestProperty("Proxy-Authorization", "Basic " + encoded);
        }
        urlConnection.setConnectTimeout(3500);
        urlConnection.connect();
        InputStream input = url.openStream();
        File dist = new File(file);
        OutputStream out = new FileOutputStream(dist);
        byte[] buf = new byte[1024];
        int len;
        while ((len = input.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        input.close();
        out.close();
        Constant.println("complete");
    }

    public static void unzipFile(String zip, String des)  throws IOException, ZipException {
        Constant.print("UnzipFile:...");
        int BUFFER = 2048;
        File sourceZipFile = new File(zip);
        File unzipDestinationDirectory = new File(des);
        if (!unzipDestinationDirectory.exists()) {
            unzipDestinationDirectory.mkdir();
        }
        if (!unzipDestinationDirectory.isDirectory()) {
            throw new ZipException("[ERROR] " + unzipDestinationDirectory.getPath() + " does not a directory");
        }
        ZipFile zipFile = new ZipFile(sourceZipFile, ZipFile.OPEN_READ);
        Enumeration zipFileEntries = zipFile.entries();
        while (zipFileEntries.hasMoreElements()) {
            ZipEntry entry = (ZipEntry) zipFileEntries.nextElement();
            String currentEntry = entry.getName();
            File destFile = new File(unzipDestinationDirectory, currentEntry);
            File destinationParent = destFile.getParentFile();
            destinationParent.mkdirs();
            if (!entry.isDirectory()) {
                BufferedInputStream is = new BufferedInputStream(zipFile.getInputStream(entry));
                int currentByte;
                byte[] data = new byte[BUFFER];
                FileOutputStream fos = new FileOutputStream(destFile);
                BufferedOutputStream dest = new BufferedOutputStream(fos, BUFFER);
                while ((currentByte = is.read(data, 0, BUFFER)) != -1) {
                    dest.write(data, 0, currentByte);
                }
                dest.flush();
                dest.close();
                is.close();
            }
        }
        zipFile.close();
        deleteFileDir(sourceZipFile.getAbsolutePath());
        Constant.println("complete");
    }

    public static void checkUpdate(Splash theSplash){
        Constant.println("update version start...");
        String path = "./";
//        if(pathU!=null)
//            path = pathU;
        try {
            ////////////////////////////////////////////////
            File serverfn = new File(link_fn);
            if(!serverfn.exists())
                return;
            FileReader filev = new FileReader(link_fn);
            BufferedReader br = new BufferedReader(filev);
            String url = br.readLine();
            String ss;
            while((ss = br.readLine()) != null){
                String[] s = ss.split("=");
                if(s[0].equals("ip")) proxy = s[1];
                else if(s[0].equals("port")) port = s[1];
                else if(s[0].equals("user")) user = s[1];
                else if(s[0].equals("password")) password = s[1];
            }
            System.out.println("proxy:"+proxy);
            System.out.println("port:"+port);
            System.out.println("user:"+user);
            System.out.println("password:"+password);
            filev.close();
            try{
                loadFile(url+db_fn,path+db_fn);
                Constant.println("load database file");
            }catch(Exception e){
                Constant.println("load database file:Fail");
            }
            String clientV = checkVersionClient();
            loadFile(url+version,version);
            String serverV = checkVersionServer(version);
            Constant.println("client version is "+clientV);
            Constant.println("server version is "+serverV);
            String server_arr[] = serverV.split("\\.");
            String server_arr2[] = server_arr[2].split("build");
            if(server_arr2[0].length()>1)
            {
                String client_arr[] = clientV.split("\\.");
                String client_arr2[] = client_arr[2].split("build");
                if(client_arr2[0].length()==1)
                {
                    clientV = client_arr[0] + "." + client_arr[1] + ".0" + client_arr2[0] + "build" + client_arr2[1];
                }
            }
            if((clientV.compareTo(serverV)>=0)){
               Constant.println("version is ok not load new app");
               return;
            }
//            System.in.read();
            ////////////////////////////////////////////////
            loadFile(url+filename,path+filename);
            unzipFile(path+filename,path);
            Constant.println("update version is complete");
            theSplash.setVisible(false);
            JOptionPane.showMessageDialog(null,"การปรับปรุงโปรแกรมเวอร์ชันล่าสุดเรียบร้อย กรุณาเปิดโปรแกรมอีกครั้งหนึ่ง");
            System.exit(0);
            ////////////////////////////////////////////////
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            theSplash.setVisible(false);
            JOptionPane.showMessageDialog(null,GuiLang.setLanguage("เกิดความผิดพลาดในการปรับปรุงโปรแกรมเวอร์ชันล่าสุด"));
            theSplash.setVisible(true);
        }
    }
}
