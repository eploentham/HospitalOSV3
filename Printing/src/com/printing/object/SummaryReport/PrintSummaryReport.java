/*
 * PrintSummaryReport.java
 *
 * Created on 30 กรกฎาคม 2547, 14:33 น.
 */

package com.printing.object.SummaryReport;
import java.util.*;
import com.printing.usecase.*;

/**
 *
 * @author  ojika
 */
public class PrintSummaryReport implements ObjectInf
{
    
    /** Creates a new instance of PrintSummaryReport */
    public String phospital = "hospital";
    public String pdate = "date";
    public String phn = "hn";
    public String pan = "an";
    public String ppatient_name = "patient_name";
    public String pmarritual = "marritual";
    public String preadmit = "readmit";
    public String paddress = "address";
    public String psex = "sex";
    public String pnation = "nation";
    public String prace = "race";
    public String preligions = "religions";
    public String poccupation = "occupation";
    public String pbirthdate = "birthdate";
    public String page = "age";
    public String pnotified_name = "notified_name";
    public String pnotified_address = "notified_address";
    public String paddmission_date = "admission_date";
    public String pdischarge_date = "discharge_date";
    public String pdepartment = "department";
    public String punit = "unit";
    public String pward = "ward";
    public String pday_stay = "day_stay";
    public String pICD10 = "ICD10";
    public String pICD9 = "ICD9";
    public String pdisc_status = "disc_status";
    public String pdisc_type = "disc_type";
    public String pdx = "dx";
    public String pplan = "plan";
    public String pplan_id = "plan_id";
    public String pplan_exp = "plan_exp";
    public String ppid = "pid";
    public String pdoctor = "doctor";
    public String ptime_admit = "time_admit";
    public String ptime_disc = "time_disc";
    
    // 24/6/2548
    public String picd10_primary = "icd10_primary";
    public String picd10_comorbidity = "icd10_comorbidity";
    public String picd10_complication = "icd10_complication";
    public String picd10_external = "icd10_external";
    public String picd10_other = "icd10_other";
    public String picd9_principal = "icd9_principal";
    public String picd9_secondary = "icd9_secondary";
    public String picd9_other = "icd9_other";
    public String pplan_mainhos = "plan_mainhos";
    public String pplan_subhos = "plan_subhos";
    public String prefer_hospital = "refer_hospital";
    public String pday_stay_24h = "day_stay_24h";
    public String ppatient_self_doctor = "patient_self_doctor";
    
    public Map printSummaryReport;
    
    public PrintSummaryReport()
    {
        printSummaryReport = new HashMap();
    }
    
    public void setPatientSelfDoctor(String name)
    {
        setMap(ppatient_self_doctor,name);
    }
    
    public void setDayStay24h(String name)
    {
        setMap(pday_stay_24h,name);
    }
    
    public void setReferHospital(String name)
    {
        setMap(prefer_hospital,name);
    }
    
    public void setPlanSubHos(String name)
    {
        setMap(pplan_subhos,name);
    }
    
    public void setPlanMainHos(String name)
    {
        setMap(pplan_mainhos,name);
    }
    
    public void setICD9Other(String name)
    {
        setMap(picd9_other,name);
    }
    
    public void setICD9Secondary(String name)
    {
        setMap(picd9_secondary,name);
    }
    
    public void setICD9Principal(String name)
    {
        setMap(picd9_principal,name);
    }
    
    public void setICD10Other(String name)
    {
        setMap(picd10_other,name);
    }
    
    public void setICD10External(String name)
    {
        setMap(picd10_external,name);
    }
    
    public void setICD10Complication(String name)
    {
        setMap(picd10_complication,name);
    }
    
    public void setICD10Comorbidity(String name)
    {
        setMap(picd10_comorbidity,name);
    }
    
    public void setICD10Primary(String name)
    {
        setMap(picd10_primary,name);
    }
    
    public void setTimeAdmit(String name)
    {
        setMap(ptime_admit,name);
    }
    
    public void setTimeDisc(String name)
    {
        setMap(ptime_disc,name);
    }
    
    public void setDoctor(String name)
    {
        setMap(pdoctor,name);
    }
    
    public void setHospital(String name)
    {
        setMap(phospital,name);
    }
    
    public void setDate(String name)
    {
        setMap(pdate,name);
    }
    
    public void setHn(String name)
    {
        setMap(phn,name);
    }
    
    public void setAn(String name)
    {
        setMap(pan,name);
    }
    
    public void setPatientName(String name)
    {
        setMap(ppatient_name,name);
    }
    
    public void setMarry(String name)
    {
        setMap(pmarritual,name);
    }
    
    public void setReAdmit(String name)
    {
        setMap(preadmit,name);
    }
    
    public void setAddress(String name)
    {
        setMap(paddress,name);
    }
    
    public void setSex(String name)
    {
        setMap(psex,name);
    }
    
    public void setNation(String name)
    {
        setMap(pnation,name);
    }
    
    public void setRace(String name)
    {
        setMap(prace,name);
    }
    
    public void setReligion(String name)
    {
        setMap(preligions,name);
    }
    
    public void setOccupation(String name)
    {
        setMap(poccupation,name);
    }
    
    public void setBirthdate(String name)
    {
        setMap(pbirthdate,name);
    }
    public void setAge(String name)
    {
        setMap(page,name);
    }
    public void setNotifiedName(String name)
    {
        setMap(pnotified_name,name);
    }
    public void setNotifiedAddress(String name)
    {
        setMap(pnotified_address,name);
    }
    public void setAdmissionDate(String name)
    {
        setMap(paddmission_date,name);
    }
    public void setDischargeDate(String name)
    {
        setMap(pdischarge_date,name);
    }
    public void setDepartment(String name)
    {
        setMap(pdepartment,name);
    }
    public void setUnit(String name)
    {
        setMap(punit,name);
    }
    public void setWard(String name)
    {
        setMap(pward,name);
    }
    public void setDayStay(String name)
    {
        setMap(pday_stay,name);
    }
    public void setICD10(String name)
    {
        setMap(pICD10,name);
    }
    public void setICD9(String name)
    {
        setMap(pICD9,name);
    }
    public void setDiscStatus(String name)
    {
        setMap(pdisc_status,name);
    }
    public void setDiscType(String name)
    {
        setMap(pdisc_type,name);
    }
    public void setDx(String name)
    {
        setMap(pdx,name);
    }
    public void setPlan(String name)
    {
        setMap(pplan,name);
    }
    public void setPlanId(String name)
    {
        setMap(pplan_id,name);
    }
    public void setPlanExp(String name)
    {
        setMap(pplan_exp,name);
    }
    public void setPid(String name)
    {
        setMap(ppid,name);
    }
    
    public void setMap(String Param, String Data)
    {
        printSummaryReport.put(Param,Data);
    }
    
    public Map getData()
    {
        return printSummaryReport;
    }
    
}
