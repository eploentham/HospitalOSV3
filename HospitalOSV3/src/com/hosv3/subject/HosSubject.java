/*
 * HosSubject.java
 *
 * Created on 11 æƒ…¿“§¡ 2548, 10:45 π.
 */
package com.hosv3.subject;

import com.hosv3.object.UseCase;
import com.hosv3.utility.Constant;
import com.hosv3.utility.connection.UpdateStatus;

/**
 *
 * @author  administrator
 */
public class HosSubject {

    public DiagnosisSubject theDiagnosisSubject;
    public SystemSubject theSystemSubject;
    public BillingSubject theBillingSubject;
    public HealthSubject theHealthSubject;
    public ResultSubject theResultSubject;
    public OrderSubject theOrderSubject;
    public PatientSubject thePatientSubject;
    public PrintSubject thePrintSubject;
    public ReportSubject theReportSubject;
    public SetupSubject theSetupSubject;
    public VisitSubject theVisitSubject;
    public VitalSubject theVitalSubject;
    public VPaymentSubject theVPaymentSubject;
    public BalloonSubject theBalloonSubject;
//    public PersonSubject thePersonSubject;
    public ItemDxSubject theItemDxSubject;
    public ICD10GroupChronicSubject theICD10GroupChronicSubject;
    public ICD10GroupSurveilSubject theICD10GroupSurveilSubject;

    /** Creates a new instance of HosSubject */
    public HosSubject() {
        theBillingSubject = new BillingSubject();
        theSystemSubject = new SystemSubject();
        theDiagnosisSubject = new DiagnosisSubject();
        theHealthSubject = new HealthSubject();
        theResultSubject = new ResultSubject();
        theOrderSubject = new OrderSubject();
        thePatientSubject = new PatientSubject();
        thePrintSubject = new PrintSubject();
        theReportSubject = new ReportSubject();
        theSetupSubject = new SetupSubject();
        theVisitSubject = new VisitSubject();
        theVitalSubject = new VitalSubject();
        theVPaymentSubject = new VPaymentSubject();
        theBalloonSubject = new BalloonSubject();
//        thePersonSubject = new PersonSubject();
        theItemDxSubject = new ItemDxSubject();
        theICD10GroupChronicSubject = new ICD10GroupChronicSubject();
        theICD10GroupSurveilSubject = new ICD10GroupSurveilSubject();
    }

    public void initSubject() {
        theBillingSubject.removeAttach();
        //theSystemSubject.removeAttach();
        theDiagnosisSubject.removeAttach();
        theHealthSubject.removeAttach();
        theResultSubject.removeAttach();
        thePatientSubject.removeAttach();
        thePrintSubject.removeAttach();
        theReportSubject.removeAttach();
        theVitalSubject.removeAttach();
        theVPaymentSubject.removeAttach();
//        thePersonSubject.removeAttach();
        theVisitSubject.removeAttach();
        theOrderSubject.removeAttach();
        theResultSubject.removeAttach();
        theItemDxSubject.removeAttach();
        theICD10GroupChronicSubject.removeAttach();
        theICD10GroupSurveilSubject.removeAttach();

    }

    public void notifyUC(String ucname) {
        if (ucname.equals(UseCase.TH_dischargeFinancial)) {
            theVisitSubject.notifyDischargeFinancial(
                    Constant.getTextBundle(ucname) + Constant.STR_COMPLETE, UpdateStatus.COMPLETE);
        } else if (ucname.equals(UseCase.TH_visitPatient)) {
            theVisitSubject.notifyVisitPatient(
                    Constant.getTextBundle(ucname) + Constant.STR_COMPLETE, UpdateStatus.COMPLETE);
        } else if (ucname.equals(UseCase.TH_dropVisitSurvey)) {
            theVisitSubject.notifyDropVisit(
                    Constant.getTextBundle(ucname) + Constant.STR_COMPLETE, UpdateStatus.COMPLETE);
        } else if (ucname.equals(UseCase.TH_commitVisitSurvey)) {
            theVisitSubject.notifyDischargeDoctor(
                    Constant.getTextBundle(ucname) + Constant.STR_COMPLETE, UpdateStatus.COMPLETE);
        } else if (ucname.equals(UseCase.TH_visitSurvey)) {
            theVisitSubject.notifyVisitPatient(
                    Constant.getTextBundle(ucname) + Constant.STR_COMPLETE, UpdateStatus.COMPLETE);
        }
    }
}
