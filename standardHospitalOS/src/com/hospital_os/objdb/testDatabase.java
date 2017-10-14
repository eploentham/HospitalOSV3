/*
 * testDatabase.java
 *
 * Created on 30 สิงหาคม 2547, 19:22 น.
 */
package com.hospital_os.objdb;
import com.hospital_os.utility.*;
import com.hospital_os.usecase.connection.*;
import java.util.*;
/**
 *
 * @author  tong
 */
public class testDatabase
{
    
    /** Creates a new instance of testDatabase */
    String url = "jdbc:postgresql://192.168.1.6:5432/develop_support";
    String user = "postgres";
    String pass = "grespost";
    String driver = "org.postgresql.Driver";
    public ConnectionInf theConnectionInf;
    public testDatabase()
    {
        
        theConnectionInf = new ConnectionDBMgr(driver,url, user,pass,"0");
        /*AccidentDB accdb = new AccidentDB(theConnectionInf);
*/
        /* AccproTypeDB accdb = new AccproTypeDB(theConnectionInf);
*/
        /*AccrdTypeDB accdb = new AccrdTypeDB(theConnectionInf);
*/
        /*AccuseTypeDB accdb = new AccuseTypeDB(theConnectionInf);
*/
        /*AddressDB accdb = new AddressDB(theConnectionInf);
*/
        /*AfterMchDB accdb = new AfterMchDB(theConnectionInf);
*/
        /*AncDB accdb = new AncDB(theConnectionInf);
*/
        /*AncSectionDB accdb = new AncSectionDB(theConnectionInf);
*/
        /*AnswerDB  accdb = new AnswerDB(theConnectionInf);
*/
        /*AppointmentDB   accdb = new AppointmentDB(theConnectionInf);
*/
        /*AuthenticationDB   accdb = new AuthenticationDB(theConnectionInf);
*/
        /*AutoOrderItemDB   accdb = new AutoOrderItemDB(theConnectionInf);
*/
        /*AutoPaymentDB  accdb = new AutoPaymentDB(theConnectionInf);
*/
        /*BTypeDB   accdb = new BTypeDB(theConnectionInf);
*/
        /*BeforeMchDB accdb = new BeforeMchDB(theConnectionInf);
*/
        /* BeforeMchDetailDB accdb = new BeforeMchDetailDB(theConnectionInf);
*/
        /*BillingDB accdb = new BillingDB(theConnectionInf);
*/
        /*BillingGroupDB accdb = new BillingGroupDB(theConnectionInf);
*/
        /*BillingGroupItemDB accdb = new BillingGroupItemDB(theConnectionInf);
*/
        /*BillingInvoiceDB accdb = new BillingInvoiceDB(theConnectionInf);
*/
        
        /*BillingInvoiceItemDB accdb = new BillingInvoiceItemDB(theConnectionInf);
*/
        /*BillingInvoiceSubgroupDB accdb = new BillingInvoiceSubgroupDB(theConnectionInf);
*/
        /*BirthPlaceDB accdb = new BirthPlaceDB(theConnectionInf);
*/
        /*BloodGroupDB accdb = new BloodGroupDB(theConnectionInf);
*/
        /*CategoryGroupDB accdb = new CategoryGroupDB(theConnectionInf);
*/
        /*CategoryGroupItemDB accdb = new CategoryGroupItemDB(theConnectionInf);
*/
        /*ClinicDB accdb = new ClinicDB(theConnectionInf);
*/
        /*ComCharacDB accdb = new ComCharacDB(theConnectionInf);
*/
        /*ConductDB accdb = new ConductDB(theConnectionInf);
*/
        /*ContractAdjustDB accdb = new ContractAdjustDB(theConnectionInf);
*/
        /*ContractDB accdb = new ContractDB(theConnectionInf);
*/
        /*CronicDB accdb = new CronicDB(theConnectionInf);
*/
        /* DayTimeDB accdb = new DayTimeDB(theConnectionInf);
*/
        /*DeathDB accdb = new DeathDB(theConnectionInf);
*/
        /*DiagIcd10DB accdb = new DiagIcd10DB(theConnectionInf);
*/
        /*DiagIcd9DB accdb = new DiagIcd9DB(theConnectionInf);
*/
        /*DiagnosisStatusDB accdb = new DiagnosisStatusDB(theConnectionInf);
*/
        /*DischargOpdDB accdb = new DischargOpdDB(theConnectionInf);
*/
        /*DischargeDB accdb = new DischargeDB(theConnectionInf);
*/
        /*DischargeStatusDB accdb = new DischargeStatusDB(theConnectionInf);
*/
        /*DischargeTypeDB accdb = new DischargeTypeDB(theConnectionInf);
*/
        /*DoseDrugSetDB accdb = new DoseDrugSetDB(theConnectionInf);
*/
        /*DoseEpiSetDB accdb = new DoseEpiSetDB(theConnectionInf);
*/
        
        /*DrugAllergyDB accdb = new DrugAllergyDB(theConnectionInf);
*/
        /*DrugDB accdb = new DrugDB(theConnectionInf);
*/
        /*DrugFrequencyDB accdb = new DrugFrequencyDB(theConnectionInf);
*/
        /*DrugInstructionDB accdb = new DrugInstructionDB(theConnectionInf);
*/
        /*DrugSetDB accdb = new DrugSetDB(theConnectionInf);
*/
        /*DrugSetGroupDB accdb = new DrugSetGroupDB(theConnectionInf);
*/
        /*DxTemplateDB accdb = new DxTemplateDB(theConnectionInf);
*/
        /*DxtypeDB accdb = new DxtypeDB(theConnectionInf);
*/
        /*EducateDB accdb = new EducateDB(theConnectionInf);
*/
        /*EmployeeDB accdb = new EmployeeDB(theConnectionInf);
*/
        /*EpiDB accdb = new EpiDB(theConnectionInf);
*/
        /*EpiDetailDB accdb = new EpiDetailDB(theConnectionInf);
*/
        /*EpiSetDB accdb = new EpiSetDB(theConnectionInf);
*/
        /*EpiSetGroupDB accdb = new EpiSetGroupDB(theConnectionInf);
*/
        /*FStatusDB accdb = new FStatusDB(theConnectionInf);
*/
        /*FilmSizeDB accdb = new FilmSizeDB(theConnectionInf);
*/
        /*FormLabreferoutDB accdb = new FormLabreferoutDB(theConnectionInf);
*/
        /*FpTypeDB accdb = new FpTypeDB(theConnectionInf);
*/
        /*FpWomanDB accdb = new FpWomanDB(theConnectionInf);
*/
        /*GarbageDB accdb = new GarbageDB(theConnectionInf);
*/
        /*Group504DB accdb = new Group504DB(theConnectionInf);
*/
        /*Group505DB accdb = new Group505DB(theConnectionInf);
*/
        /*Group506DB accdb = new Group506DB(theConnectionInf);
*/
        /*GroupCronicDB accdb = new GroupCronicDB(theConnectionInf);
*/
        /*GroupIcd10DB accdb = new GroupIcd10DB(theConnectionInf);
*/
        /*GuideAfterDxDB accdb = new GuideAfterDxDB(theConnectionInf);
*/
        /*GuideAfterDxTransactionDB accdb = new GuideAfterDxTransactionDB(theConnectionInf);
*/
        /*HCharacDB accdb = new HCharacDB(theConnectionInf);
*/
        /*HighRiskDB accdb = new HighRiskDB(theConnectionInf);
*/
        /*HomeDB accdb = new HomeDB(theConnectionInf);
*/
        /*ICD10DB accdb = new ICD10DB(theConnectionInf);
*/
        /*ICD9DB accdb = new ICD9DB(theConnectionInf);
*/
        /*Icd10GroupTypeDB accdb = new Icd10GroupTypeDB(theConnectionInf);
*/
        /*Idx10V3DB accdb = new Idx10V3DB(theConnectionInf);
*/
        /*InOutrdTypeDB accdb = new InOutrdTypeDB(theConnectionInf);
*/
        /*ItemDB accdb = new ItemDB(theConnectionInf);
*/
        /*ItemPriceDB accdb = new ItemPriceDB(theConnectionInf);
*/
        /*LabGroupDB accdb = new LabGroupDB(theConnectionInf);
*/
        /*LabResultItemDB accdb = new LabResultItemDB(theConnectionInf);
*/
        /*LabSetDB accdb = new LabSetDB(theConnectionInf);
*/
        /*LaborDB accdb = new LaborDB(theConnectionInf);
*/
        /*LevelDB accdb = new LevelDB(theConnectionInf);
*/
        /*LocalTypeDB accdb = new LocalTypeDB(theConnectionInf);
*/
        /*MapQueueVisitDB accdb = new MapQueueVisitDB(theConnectionInf);
*/
        /*MapVisitDxDB accdb = new MapVisitDxDB(theConnectionInf);
*/
        /*MarryStatusDB accdb = new MarryStatusDB(theConnectionInf);
*/
        /*MchDB accdb = new MchDB(theConnectionInf);
*/
        /*NationDB accdb = new NationDB(theConnectionInf);
*/
        /*NofpDB accdb = new NofpDB(theConnectionInf);
*/
        /*NutritionTypeDB accdb = new NutritionTypeDB(theConnectionInf);
*/
        /*OccupatDB accdb = new OccupatDB(theConnectionInf);
*/
        /*OfficeDB accdb = new OfficeDB(theConnectionInf);
*/
        /*OpdTypeDB accdb = new OpdTypeDB(theConnectionInf);
*/
        /*OptionDB accdb = new OptionDB(theConnectionInf);
*/
        /*OptypeDB accdb = new OptypeDB(theConnectionInf);
*/
        /*OrderContinueDB accdb = new OrderContinueDB(theConnectionInf);
*/
        /*OrderItemDB accdb = new OrderItemDB(theConnectionInf);
*/
        /*OrderItemDrugDB accdb = new OrderItemDrugDB(theConnectionInf);
*/
        /*OrderItemLabreferinDB accdb = new OrderItemLabreferinDB(theConnectionInf);
*/
        /*OrderItemReceiveDrugDB accdb = new OrderItemReceiveDrugDB(theConnectionInf);
*/
        /*OrderItemStatusDB accdb = new OrderItemStatusDB(theConnectionInf);
*/
        /*OrderItemStatusLabreferinDB accdb = new OrderItemStatusLabreferinDB(theConnectionInf);
*/
        /*OrderResultLabreferinDB accdb = new OrderResultLabreferinDB(theConnectionInf);
*/
        /*ParticipateOrDB accdb = new ParticipateOrDB(theConnectionInf);
*/
        /*PatientDB accdb = new PatientDB(theConnectionInf);
*/
        /*PatientLabreferinDB accdb = new PatientLabreferinDB(theConnectionInf);
*/
        /*PatientPaymentDB accdb = new PatientPaymentDB(theConnectionInf);
*/
        /*PayerDB accdb = new PayerDB(theConnectionInf);
*/
        /*PaymentDB accdb = new PaymentDB(theConnectionInf);
*/
        /*PhysicalExamDB accdb = new PhysicalExamDB(theConnectionInf);
*/
        /*PlanDB accdb = new PlanDB(theConnectionInf);
*/
        /*PostureBabyDB accdb = new PostureBabyDB(theConnectionInf);
*/
        /*PrefixDB accdb = new PrefixDB(theConnectionInf);
*/
        /*PregnantLevelDB accdb = new PregnantLevelDB(theConnectionInf);
*/
        /*PtStatusTypeDB accdb = new PtStatusTypeDB(theConnectionInf);
*/
        /*PtmobieTypeDB accdb = new PtmobieTypeDB(theConnectionInf);
*/
        /*QueueDespenseDB accdb = new QueueDespenseDB(theConnectionInf);
*/
        /*QueueICDDB accdb = new QueueICDDB(theConnectionInf);
*/
        /*QueueLabDB accdb = new QueueLabDB(theConnectionInf);
*/
        /*QueueVisitDB accdb = new QueueVisitDB(theConnectionInf);
*/
        /*QueueXrayDB accdb = new QueueXrayDB(theConnectionInf);
*/
        /*ReceiptDB accdb = new ReceiptDB(theConnectionInf);
*/
        /*ReceiptItemDB accdb = new ReceiptItemDB(theConnectionInf);
*/
        /*ReceiptSubgroupDB accdb = new ReceiptSubgroupDB(theConnectionInf);
*/
        /*ReferDB accdb = new ReferDB(theConnectionInf);
*/
        /*RelationDB accdb = new RelationDB(theConnectionInf);
*/
        /*ReligionDB accdb = new ReligionDB(theConnectionInf);
*/
        /*ResultGiveBirthDB accdb = new ResultGiveBirthDB(theConnectionInf);
*/
        /*ResultLabDB accdb = new ResultLabDB(theConnectionInf);
*/
        /*ResultStatusDB accdb = new ResultStatusDB(theConnectionInf);
*/
        /*ResultXRayDB accdb = new ResultXRayDB(theConnectionInf);
*/
        /*ResultXrayPositionDB accdb = new ResultXrayPositionDB(theConnectionInf);
*/
        /*ResultXraySizeDB accdb = new ResultXraySizeDB(theConnectionInf);
*/
        /*ReverseAdmitDB accdb = new ReverseAdmitDB(theConnectionInf);
*/
        /*RuleDB accdb = new RuleDB(theConnectionInf);
*/
        /*SQLTemplateParamDB accdb = new SQLTemplateParamDB(theConnectionInf);
*/
        /*SequenceDataDB accdb = new SequenceDataDB(theConnectionInf);
*/
        /*ServicePointDB accdb = new ServicePointDB(theConnectionInf);
*/
        /*ServicePointDoctorDB accdb = new ServicePointDoctorDB(theConnectionInf);
*/
        /*ServiceSubTypeDB accdb = new ServiceSubTypeDB(theConnectionInf);
*/
        /*ServiceTypeDB accdb = new ServiceTypeDB(theConnectionInf);
*/
        /*SewDB accdb = new SewDB(theConnectionInf);
*/
        /*SexDB accdb = new SexDB(theConnectionInf);
*/
        /*SiteDB accdb = new SiteDB(theConnectionInf);
*/
        /*SurveilDB accdb = new SurveilDB(theConnectionInf);
*/
        /*TlockDB accdb = new TlockDB(theConnectionInf);
*/
        /*TransferDB accdb = new TransferDB(theConnectionInf);
*/
        /*TypeAreaDB accdb = new TypeAreaDB(theConnectionInf);
*/
        /*TypeDishDB accdb = new TypeDishDB(theConnectionInf);
*/
        /*UomDB accdb = new UomDB(theConnectionInf);
*/
        /*UterusLevelDB accdb = new UterusLevelDB(theConnectionInf);
*/
        /*VersionDB accdb = new VersionDB(theConnectionInf);
*/
        /*VisitDB accdb = new VisitDB(theConnectionInf);
*/
        /*VisitLabreferinDB accdb = new VisitLabreferinDB(theConnectionInf);
*/
        /*VisitStatusDB accdb = new VisitStatusDB(theConnectionInf);
*/
        /*VisitStatusLabreferinDB accdb = new VisitStatusLabreferinDB(theConnectionInf);
*/
        /*VisitTypeDB accdb = new VisitTypeDB(theConnectionInf);
*/
        /*VitalSignDB accdb = new VitalSignDB(theConnectionInf);
*/
        /*VitalTemplateDB accdb = new VitalTemplateDB(theConnectionInf);
*/
        /*WardDB accdb = new WardDB(theConnectionInf);
*/
        /*WaterTypeDB accdb = new WaterTypeDB(theConnectionInf);
*/
        /*XRayLeteralDB accdb = new XRayLeteralDB(theConnectionInf);
*/
        XRayPositionDB accdb = new XRayPositionDB(theConnectionInf);
        
        
        try
        {
            Vector vc = accdb.selectAll();
            vc = null;
        }
        catch(Exception ex)
        {    ex.printStackTrace(Constant.getPrintStream());
        }
        
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        testDatabase testdb = new testDatabase();
        /* TODO code application logic here
*/
    }
    
}
