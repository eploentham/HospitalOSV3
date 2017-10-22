/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hosv3.control;

import com.hosv3.utility.Constant;
import com.hosv3.utility.connection.UpdateStatus;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;        //+1
import th.go.nhso.smartcard.appletmodel.MoiApplet1Info;
import th.go.nhso.smartcard.appletmodel.NhsoAppletInfo;
import th.go.nhso.smartcard.appletservice.SmartCardServices;
import th.go.nhso.smartcard.appletservice.SmartcardServiceFactory;
import th.go.nhso.smartcard.reader.ConnectionSession;
//import com.sun.image.codec.jpeg.JPEGCodec;        //-1
//import com.sun.image.codec.jpeg.JPEGEncodeParam;        //-1
//import com.sun.image.codec.jpeg.JPEGImageEncoder;        //-1
//import com.sun.image.codec.jpeg.JPEGEncodeParam;        //-1
//import com.sun.image.codec.jpeg.JPEGImageEncoder;        //-1
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.smartcardio.Card;
import javax.smartcardio.CardChannel;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.CommandAPDU;
import javax.smartcardio.ResponseAPDU;
import javax.smartcardio.TerminalFactory;
import javax.swing.JOptionPane;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Somprasong
 */
/**
 * ekapop   60-10-14
 * 1.   modify ไม่สามารถ build ได้เพราะติด com.sun.image.codec.jpeg.JPEGCodec;
 *      ต้องเปลี่ยนไปใช้ แบบอื่นๆ เพราะ java 1.7 ให้ยกเลิกการใช้งาน
 *      ยังไม่รู้ว่า ใช้งานได้เปลี่ยน modify เพื่อ ให้สามารถ build ได้
 *      ใช้งานไม่ได้ ค่อยแก้กันอีก รอบ
 * เลขที่เอกสาร 0.
*/
public class SmartCardControl {

    private final static String[] ADDR = new String[]{"หมู่ที่", "ซอย", "ถนน", "ตำบล", "อำเภอ", "จังหวัด"};
    private final static String DEFUALT_PATTERN = "yyyyMMdd";
    private final static String FULLTHAIDATE_PATTERN = "d MMMM yyyy";
    private final static String DATETIMESTAMP_PATTERN = "yyyy-mm-dd hh:mm:ss";
    public static int WIDTH = 160;
    public static int HEIGTH = 192;
    public final static Locale LOCALE_TH = new Locale("TH", "th");
    public final static Locale LOCALE_EN = new Locale("EN", "en");
    private UpdateStatus theUS;
    private MoiApplet1Info theMOI;
    private NhsoAppletInfo theNHSO;
    private ConnectionSession session;
    private SmartCardServices service;
    private HashMap addressHashMap = null;

    public  SmartCardControl(UpdateStatus theUS) {
        this.theUS = theUS;
        this.initControl(null);
    }
    

    public void initControl(Object oobject) {
        session = ConnectionSession.instance();
        service = SmartcardServiceFactory.createSmartCardServices();
        addressHashMap = new HashMap();
    }

    public void setupControl(Object oobject) {
    }

    public boolean startReader(boolean isGetPic, boolean isGetNHSO) {
        theUS.setStatus(Constant.getTextBundle("กำลังอ่านข้อมูลจากบัตร SmartCard") + " " +
                Constant.getTextBundle("กรุณารอสักครู่") +
                Constant.getTextBundle("..."), UpdateStatus.NORMAL);
        addressHashMap.clear();
        try {
            
            TerminalFactory tf = TerminalFactory.getDefault();
            List< CardTerminal> terminals = tf.terminals().list();
            System.out.println("Available Readers:");
            System.out.println(terminals + "\n");
            Scanner scanner = new Scanner(System.in);
            System.out.print("Which reader do you want to send your commands to? (0 or 1 or ...): ");
//            String input = scanner.nextLine();
//            int readerNum = Integer.parseInt(input);
            CardTerminal cardTerminal = (CardTerminal) terminals.get(0);
            Card connection = cardTerminal.connect("*");
            CardChannel cardChannel = connection.getBasicChannel();
            

            
            
            
            connection.disconnect(true);
        
            session.open("");

            theMOI = service.getMoiApplet1Info(isGetPic);

            if (isGetNHSO) {
                theNHSO = service.getNhsoAppletInfo();
            }
            session.close();
            theUS.setStatus(("อ่านข้อมูลจากบัตร SmartCard เรียบร้อย"), UpdateStatus.COMPLETE);
            return true;
        } catch (Throwable ex) {
            ex.printStackTrace(Constant.getPrintStream());
            JOptionPane.showMessageDialog(theUS.getJFrame(),
                    Constant.getTextBundle(ex.getMessage()));
            theUS.setStatus(Constant.getTextBundle(ex.getMessage()), UpdateStatus.WARNING);
            return false;
        }
    }
    

    // Thai name
    public String getFullThaiName() {
        return theMOI.getThaiName() == null ? "" : theMOI.getThaiName().toString().trim();
    }

    public String getThaiTitleName() {
        return theMOI.getThaiName() == null ? "" : (theMOI.getThaiName().getTitle() == null ? "" : theMOI.getThaiName().getTitle().trim());
    }

    public String getThaiFirstName() {
        return theMOI.getThaiName() == null ? "" : (theMOI.getThaiName().getFirstName() == null ? "" : theMOI.getThaiName().getFirstName().trim());
    }

    public String getThaiMidName() {
        return theMOI.getThaiName() == null ? "" : (theMOI.getThaiName().getMidName() == null ? "" : theMOI.getThaiName().getMidName().trim());
    }

    public String getThaiLastName() {
        return theMOI.getThaiName() == null ? "" : (theMOI.getThaiName().getLastName() == null ? "" : theMOI.getThaiName().getLastName().trim());
    }

    // English name
    public String getFullEngName() {
        return theMOI.getEngName() == null ? "" : theMOI.getEngName().toString().trim();
    }

    public String getEngTitleName() {
        return theMOI.getEngName() == null ? "" : (theMOI.getEngName().getTitle() == null ? "" : theMOI.getEngName().getTitle().trim());
    }

    public String getEngFirstName() {
        return theMOI.getEngName() == null ? "" : (theMOI.getEngName().getFirstName() == null ? "" : theMOI.getEngName().getFirstName().trim());
    }

    public String getEngMidName() {
        return theMOI.getEngName() == null ? "" : (theMOI.getEngName().getMidName() == null ? "" : theMOI.getEngName().getMidName().trim());
    }

    public String getEngLastName() {
        return theMOI.getEngName() == null ? "" : (theMOI.getEngName().getLastName() == null ? "" : theMOI.getEngName().getLastName().trim());
    }

    // get p_id
    public String getPID() {
        return theMOI.getPid() == null ? "" : theMOI.getPid().trim();
    }
    // get address

    public String getAddress() {
        return theMOI.getAddress() == null ? "" : theMOI.getAddress().trim();
    }

    public String getAddressNo() {
        HashMap hashMap = convertAddress();
        String string = "";
        if (hashMap.containsKey("บ้านเลขที่")) {
            string = (String) hashMap.get("บ้านเลขที่");
        }
        return string;
    }

    public String getAddressMoo() {
        HashMap hashMap = convertAddress();
        String string = "";
        if (hashMap.containsKey("หมู่ที่")) {
            string = (String) hashMap.get("หมู่ที่");
        }
        return string;
    }

    public String getAddressSoi() {
        HashMap hashMap = convertAddress();
        String string = "";
        if (hashMap.containsKey("ซอย")) {
            string = (String) hashMap.get("ซอย");
        }
        return string;
    }

    public String getAddressRoad() {
        HashMap hashMap = convertAddress();
        String string = "";
        if (hashMap.containsKey("ถนน")) {
            string = (String) hashMap.get("ถนน");
        }
        return string;
    }

    public String getAddressTumbon() {
        HashMap hashMap = convertAddress();
        String string = "";
        if (hashMap.containsKey("ตำบล")) {
            string = (String) hashMap.get("ตำบล");
        }
        return string;
    }

    public String getAddressAmphur() {
        HashMap hashMap = convertAddress();
        String string = "";
        if (hashMap.containsKey("อำเภอ")) {
            string = (String) hashMap.get("อำเภอ");
        }
        return string;
    }

    public String getAddressChangwat() {
        HashMap hashMap = convertAddress();
        String string = "";
        if (hashMap.containsKey("จังหวัด")) {
            string = (String) hashMap.get("จังหวัด");
        }
        return string;
    }

    private HashMap convertAddress() {
        if (addressHashMap == null || addressHashMap.isEmpty()) {
            String address = getAddress();
            address = address.trim().replaceAll(" ", "#");
            address = address.replaceAll("หมู่ที่#", "หมู่ที่");
            address = address.replaceAll("###", "#");
            String strs[] = address.split("#");
            Pattern p = Pattern.compile("\\d*/*\\d*");
            for (int i = 0; i < strs.length; i++) {
                for (int j = 0; j < ADDR.length; j++) {
                    Matcher m = p.matcher(strs[i]);
                    if (m.matches()) {
                        if (!addressHashMap.containsKey("บ้านเลขที่")) {
                            addressHashMap.put("บ้านเลขที่", strs[i]);
                        }
                    }
                    if (strs[i].startsWith(ADDR[j])) {
                        addressHashMap.put(ADDR[j], strs[i].substring(ADDR[j].length()));
                    }
                }
            }
        }
        return addressHashMap;
    }
    // get date of brith

    public String getDOB() {
        return theMOI.getBod() == null ? "" : theMOI.getBod().trim();
    }
    // get date of brith in new pattern

    public String getDOB(String pattern, Locale locale) {
        String dob = this.getDOB();
        if (dob == null || dob.isEmpty()) {
            return "";
        }
        return this.convertDatePattern(dob, pattern, locale);
    }

    // get age
    public int getAgeYear() {
        int age = 0;
        try {
            String str_dob = getDOB();
            if (str_dob == null || str_dob.isEmpty()) {
                return 0;
            }
            Date birthDate = new SimpleDateFormat(DEFUALT_PATTERN, LOCALE_TH).parse(str_dob);
            Calendar now = Calendar.getInstance();
            Calendar dob = Calendar.getInstance();
            dob.setTime(birthDate);
            if (dob.after(now)) {
                throw new IllegalArgumentException("Can't be born in the future");
            }
            int year1 = now.get(Calendar.YEAR);
            int year2 = dob.get(Calendar.YEAR);
            age = year1 - year2;
            int month1 = now.get(Calendar.MONTH) + 1;
            int month2 = dob.get(Calendar.MONTH) + 1;
            if (month2 > month1) {
                age--;
            } else if (month1 == month2) {
                int day1 = now.get(Calendar.DAY_OF_MONTH);
                int day2 = dob.get(Calendar.DAY_OF_MONTH);
                if (day2 > day1) {
                    age--;
                }
            }
        } catch (ParseException ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        return age;

    }

    public int getAgeMonth() {
        int month = 0;
        int day = 0;
        try {
            String str_dob = getDOB();
            if (str_dob == null || str_dob.isEmpty()) {
                return 0;
            }
            Date birthDate = new SimpleDateFormat(DEFUALT_PATTERN, LOCALE_TH).parse(str_dob);
            Calendar now = Calendar.getInstance();
            Calendar dob = Calendar.getInstance();
            dob.setTime(birthDate);
            if (dob.after(now)) {
                throw new IllegalArgumentException("Can't be born in the future");
            }
            int year1 = now.get(Calendar.YEAR);
            int year2 = dob.get(Calendar.YEAR);
            int year = year1 - year2;
            int month1 = now.get(Calendar.MONTH) + 1;
            int month2 = dob.get(Calendar.MONTH) + 1;
            int day1 = now.get(Calendar.DAY_OF_MONTH);
            int day2 = dob.get(Calendar.DAY_OF_MONTH);
            day = day1 - day2;
            month = month1 - month2;
            if (month < 0) {
                month = month1 - month2 + 12;
                year = year - 1;
            }
            if (month == 0) {
                if (day < 0) {
                    month = month1 - 1;
                }
            }
//            if (year > 0) {
//                month += year * 12;
//            }
        } catch (ParseException ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        return month;
    }
    // get sex

    public String getSexID() {
        return theMOI.getSex() == null ? "" : theMOI.getSex().trim();
    }

    public String getSexDesc() {
        return theMOI.getSexDesc() == null ? "" : theMOI.getSexDesc().trim();
    }

    // วัน เดือน ปีที่ออกบัตร
    public String getFormattedDateOfIssue() {
        return theMOI.getFormattedDateOfIssue() == null ? "" : theMOI.getFormattedDateOfIssue().trim();
    }

    // วัน เดือน ปีที่บัตรหมดอายุ
    public String getFormattedDateOfExpiry() {
        return theMOI.getFormattedDateOfExpiry() == null ? "" : theMOI.getFormattedDateOfExpiry().trim();
    }

    // get version
    public String getVersion() {
        return theMOI.getVersion() == null ? "" : theMOI.getVersion().trim();
    }

    // get CardNumberOrRequest
    public String getCardNumberOrRequest() {
        return theMOI.getCardNumberOrRequest() == null ? "" : theMOI.getCardNumberOrRequest().trim();
    }

    // get CardOwner
    public String getCardOwner() {
        return theMOI.getCardOwner() == null ? "" : theMOI.getCardOwner().trim();
    }

    // get CardTtype
    public String getCardTtype() {
        return theMOI.getCardTtype() == null ? "" : theMOI.getCardTtype().trim();
    }

    // get DateOfExpiry
    public String getDateOfExpiry() {
        return theMOI.getDateOfExpiry() == null ? "" : theMOI.getDateOfExpiry().trim();
    }

    // get DateOfIssue
    public String getDateOfIssue() {
        return theMOI.getDateOfIssue() == null ? "" : theMOI.getDateOfIssue().trim();
    }

    // get FacesImageId
    public String getFacesImageId() {
        return theMOI.getFacesImageId() == null ? "" : theMOI.getFacesImageId().trim();
    }

    // get FormattedBod
    public String getFormattedBod() {
        return theMOI.getFormattedBod() == null ? "" : theMOI.getFormattedBod().trim();
    }

    // get IssuerCode
    public String getIssuerCode() {
        return theMOI.getIssuerCode() == null ? "" : theMOI.getIssuerCode().trim();
    }

    // NHSO
    public String getNHSOMainRights() {
        if (theNHSO == null) {
            return "";
        }
        return theNHSO.getMainRights() == null ? "" : theNHSO.getMainRights().trim();
    }

    public String getNHSOSubRights() {
        if (theNHSO == null) {
            return "";
        }
        return theNHSO.getSubRights() == null ? "" : theNHSO.getSubRights().trim();
    }

    public String getNHSOMainHospitalsName() {
        if (theNHSO == null) {
            return "";
        }
        return theNHSO.getMainHospitalsName() == null ? "" : theNHSO.getMainHospitalsName().trim();
    }

    public String getNHSOSubHospitalsName() {
        if (theNHSO == null) {
            return "";
        }
        return theNHSO.getSubHospitalsName() == null ? "" : theNHSO.getSubHospitalsName().trim();
    }

    public String getNHSOFormattedDateOfIssue() {
        if (theNHSO == null) {
            return "";
        }
        return theNHSO.getFormattedDateOfIssue() == null ? "" : theNHSO.getFormattedDateOfIssue().trim();
    }

    public String getNHSOFormattedDateOfExpirey() {
        if (theNHSO == null) {
            return "";
        }
        return theNHSO.getFormattedDateOfExpirey() == null ? "" : theNHSO.getFormattedDateOfExpirey().trim();
    }

    public String getNHSOFormattedUpdateDate() {
        if (theNHSO == null) {
            return "";
        }
        return theNHSO.getFormattedUpdateDate() == null ? "" : theNHSO.getFormattedUpdateDate().trim();
    }

    public String getNHSOChangeHospitalAmount() {
        if (theNHSO == null) {
            return "";
        }
        return theNHSO.getChangeHospitalAmount() == null ? "" : theNHSO.getChangeHospitalAmount().trim();
    }

    public byte[] getByteFacesImage() {
        return theMOI.getFacesImage();
    }

    public Image getFacesImage(int width, int heigth) throws IOException, Exception {
        BufferedInputStream is = new BufferedInputStream(new ByteArrayInputStream(theMOI.getFacesImage()));
        Image image = ImageIO.read(changeImageScale(is, width, heigth));
        return image;
    }

    public byte[] getByteOfficerSignatureImage() {
        return theMOI.getOfficerSignatureImage();
    }

    public Image getOfficerSignatureImage(int width, int heigth) throws IOException, Exception {
        BufferedInputStream is = new BufferedInputStream(new ByteArrayInputStream(theMOI.getOfficerSignatureImage()));
        Image image = ImageIO.read(changeImageScale(is, width, heigth));
        return image;
    }

    public Date getDateFromFullThaiDate(String issuedate) {
        return convertFullThaiDate(issuedate);
    }

    public Date getCurrentTimemilisec(long date) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(date);
        return c.getTime();
    }

    public static InputStream changeImageScale(InputStream p_image, int p_width, int p_height) throws Exception {

        InputStream imageStream = new BufferedInputStream(p_image);
        Image image = (Image) ImageIO.read(imageStream);

        int thumbWidth = p_width;
        int thumbHeight = p_height;

        // Make sure the aspect ratio is maintained, so the image is not skewed
        double thumbRatio = (double) thumbWidth / (double) thumbHeight;
        int imageWidth = image.getWidth(null);
        int imageHeight = image.getHeight(null);
        double imageRatio = (double) imageWidth / (double) imageHeight;
        if (thumbRatio < imageRatio) {
            thumbHeight = (int) (thumbWidth / imageRatio);
        } else {
            thumbWidth = (int) (thumbHeight * imageRatio);
        }

        // Draw the scaled image
        BufferedImage thumbImage = new BufferedImage(thumbWidth,
                thumbHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = thumbImage.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.drawImage(image, 0, 0, thumbWidth, thumbHeight, null);

        // Write the scaled image to the outputstream
        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);        //-1
//        JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(thumbImage);        //-1
        Iterator readers = ImageIO.getImageReadersByFormatName("jpg");        //+1
        ImageReader reader = (ImageReader)readers.next();        //+1
        ImageReadParam param = reader.getDefaultReadParam();        //+1
        int quality = 100; // Use between 1 and 100, with 100 being highest quality
        quality = Math.max(0, Math.min(quality, 100));
//        param.setQuality((float) quality / 100.0f, false);
//        encoder.setJPEGEncodeParam(param);
//        encoder.encode(thumbImage);
        ImageIO.write(thumbImage, "jpg", out);

        // Read the outputstream into the inputstream for the return value
        ByteArrayInputStream bis = new ByteArrayInputStream(out.toByteArray());

        return bis;
    }

    /**
     * @author Somprasong
     * @param str_date
     * @param new_pattern ex yyyy-MM-dd
     * @return
     */
    private String convertDatePattern(String str_date, String new_pattern, Locale locale) {
        Date date = new Date();
        if (str_date == null) {
            return str_date;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(DEFUALT_PATTERN, LOCALE_TH);
        try {
            date = sdf.parse(str_date);
        } catch (ParseException ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        sdf = new SimpleDateFormat(new_pattern, locale);
        return sdf.format(date);
    }

    private Date convertFullThaiDate(String str_date) {
        Date date = new Date();
        if (str_date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(FULLTHAIDATE_PATTERN, LOCALE_TH);
        try {
            date = sdf.parse(str_date);
        } catch (ParseException ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
//        sdf = new SimpleDateFormat(DATETIMESTAMP_PATTERN, locale);
        return date;
    }
}
