package com.hospital_os.object;
import com.hospital_os.usecase.connection.Persistent;
/* Modify Sumo 08/08/2549*/
public class Visit extends Persistent 
{
        /**�Ţ hn*/
        public String hn;
        /**�Ţ vn/an*/
        public String vn;
        /**�����ż�����*/
        public String patient_id;
        /**���ʪ�Դ�ͧ�����·������Ѻ��ԡ��*/
        public String visit_type;
        /**�������������Ѻ��ԡ��*/
        public String begin_visit_time;
        /**�����˵آͧ��� Visit*/
        public String visit_note; 
        /**����ʶҹ��Һ�� 5 ��ѡ �ͧ��� refer in*/
        public String refer_in;
        /**����ʶҹ��Һ�� 5 ��ѡ �ͧ��� refer in*/
        public String refer_out;
        /**�����˵ء���ԹԨ����ä*/
        public String diagnosis_note;
        /**��Դ�ͧ��è�˹��·ҧ OPD*/
        public String discharge_opd_status;
        /**��Դ�ͧ��è�˹��·ҧ IPD*/
        public String discharge_ipd_type;
        /**ʶҹТͧ��è�˹��·ҧ IPD*/
        public String discharge_ipd_status;
        /**¡��ԡ*/
        public String admisstion;
        /**ʶҹТͧ�����¶١ lock �������*/
        public String locking;
        /**���ʢͧ��� lock*/
        public String lock_user;
        /**���ҷ��١ lock*/
        public String lock_time;
        /**ʶҹТͧ�������Ѻ��ԡ��*/
        public String visit_status;
        /**�ա�õ�駤�����������*/
        public String pregnant;
        /**��Թԡ*/
        public String admit_clinic;
        /**��ª���Ἱ��������*/
        public String ward;
        /**��§*/
        public String bed;
        /**�͹���ҡ���������*/
        public String observe;
        /**¡��ԡ*/
        public String visit_clinic = "";
        /**�ӴѺ��Ǣͧ������*/
        public String queue; /*????????????*/
        /**������ª��ͨش��ԡ�÷�������¡��*/
        public String service;/*????????????*/
        /**���ʢͧ�����觹͹���ҡ��*/
        public String observe_user;
        /**����ԹԨ���*/
        public String doctor_dx;
        /**
         * @deprecated �Ѻ����� diagnosis_note
         * �����˵آͧᾷ��*/
        public String doctor_note;
        /**ʶҹТͧ��è�˹��·ҧ ipd*/
        public String is_discharge_ipd;
        /**ʶҹТͧ��è�˹��·ҧ����Թ*/
        public String is_discharge_money;
        /**ʶҹТͧ��è�˹��·ҧ���ᾷ��*/
        public String is_discharge_doctor;
        /**���ҷ���˹��·ҧ����Թ*/
        public String financial_discharge_time; 
        /**���ʢͧ����˹��·ҧ����Թ*/
        public String financial_discharge_user;        
        /**���ʢͧ����˹��·ҧ���ᾷ��*/
        public String doctor_discharge_user;
        /**���ҷ���˹��·ҧ���ᾷ��*/
        public String doctor_discharge_time;
        /**����ԹԨ��� �ͧ�ǪʶԵ�*/
        public String stat_dx;
        /**�Ţ vn ��ѧ�ҡ Admit*/
        public String an;
        /**���ҷ��Admit*/
        public String begin_admit_time;
        /**¡��ԡ*/
        public String patient_type;        
        /** VN �������ش*/
        public String max_vn;
        /**���ʢͧᾷ����ӡ�� Admin*/
        public String visit_patient_self_doctor = "";
        /**����ʸ�������*/
        public String deny_allergy;/*??????????????*/
        /**�Ѻ��ԡ��� PCU*/
        public String is_pcu_service;
         /**�Ѻ��ԡ����ç��Һ��*/
        public String is_hospital_service;
        /**ʶҹ�����繡������Ѻ��ԡ�ä����á*/
        public String is_first;
        /**���ؼ�����*/
        public String patient_age;   
        /**ʶҹ�㹤���Ż*/
        public String queue_lab_status;
        /**�������˵ء�� refer*/
        public String refer_cause;
        /**���ʢͧᾷ����ӡ�� Admin  EmergencyStatus.xxx */
        public String emergency;
        /**ʶҹм����©ء�Թ*/
        public String emergency_staff;
        /**���������ѡ�Ҵ����ä NCD ��������(0=���,1=�ѡ��)*/
        public String ncd;
        /**������ä NCD ������ѡ��*/
        public String ncd_group;
        /**�����µ�ͧ�ӹѴ ��������(0=����ͧ,1=��ͧ�ӹѴ)*/
        public String have_appointment;
        /**�����µ�ͧ Admit ��������(0=����ͧ,1=��ͧ Admit)*/
        public String have_admit;
        /**�����µ�ͧ Refer ��������(0=����ͧ,1=��ͧ Refer)*/
        public String have_refer;
        /**�����š�ùѴ*/
        public String appointment_id;
        /**��Ǫ��¤ӹǳ�ѹ���Ѵ*/
        public String cal_date_appointment;
        /**���˵ء�ùѴ*/
        public String cause_appointment;

        /**
         * konshow
         * ���ѹ��衴���� visit*/
        public String visit_record_date_time;
        /**
         * konshow
         * �����ʢͧ�����ҹ��衴 visit*/
        public String visit_record_staff;
        /**
         * konshow
         * ���ѹ��衴���� visit*/
        public String visit_financial_record_date_time;
        /**
         * konshow
         * �����ʢͧ�����ҹ��衴 visit*/
        public String visit_financial_record_staff;
        /**
         * LionHeart
         * �������ͧ Visit
         * 1= ����ԡ���˹���
           2= ����ԡ�ù͡˹���*/
        public String service_location;
   /**
    * @roseuid 3F658BBB036E
    */
   public Visit() 
   {
        hn=new String();
        vn=new String();
        patient_id=new String();
        visit_type=new String();
        begin_visit_time=new String();
        visit_note=new String();
        refer_in=new String();
        refer_out=new String();
        diagnosis_note=new String();
        discharge_opd_status=new String();
        discharge_ipd_type=new String();
        discharge_ipd_status=new String();
        /*admisstion=new String();  //?????????????
*/
        locking=new String();
        lock_user=new String();
        lock_time=new String();
        visit_status=VisitStatus.isInProcess();
        pregnant=new String();
        admit_clinic=new String();
        ward=new String();
        bed=new String();
        observe=new String();
        /*¡��ԡ visit_clinic=new String();*/
        queue=new String(); 
        service=new String();
        observe_user=new String();
        doctor_dx=new String();
        doctor_note=new String();
        is_discharge_ipd=new String();
        is_discharge_money=new String();
        is_discharge_doctor=new String();
        financial_discharge_time=new String();   
        financial_discharge_user=new String();
        doctor_discharge_user=new String();
        doctor_discharge_time=new String();   
        stat_dx= new String();
        an = new String();
        begin_admit_time = new String();
        max_vn = new String();
        /*¡��ԡ patient_type = new String(); */
        deny_allergy = "1";/*?????????????????*/
        is_pcu_service = "0";
        is_hospital_service = "0";
        is_first = "0";
        patient_age = "";   
        refer_cause = new String();
        emergency = new String();
        emergency_staff = new String();
        ncd = "0";
        ncd_group = "";
        have_appointment = "0";
        have_admit = "0";
        have_refer = "0";
        appointment_id = "";
        cal_date_appointment = "";
        cause_appointment = "";

        visit_record_date_time = "";
        visit_record_staff = "";
        visit_financial_record_date_time = "";
        visit_financial_record_staff = "";
   }
   public boolean isOutProcess() {
        return visit_status.equals(VisitStatus.isOutProcess());
    }
    public boolean isLockingByOther(String user_id){
        boolean is_lockby_other = locking.equals("1")
            && !lock_user.equals(user_id);
        return is_lockby_other;
    }

    public boolean isDropVisit() {
        return visit_status.equals(VisitStatus.isDropVisit());
    }

    public boolean isDischargeMoney() {
        return is_discharge_money.equals("1");
    }

    public boolean isInStat() {
        return visit_status.equals(VisitStatus.isInStat());
    }

    public boolean isDischargeDoctor() {
        return is_discharge_doctor.equals("1");
    }

}
