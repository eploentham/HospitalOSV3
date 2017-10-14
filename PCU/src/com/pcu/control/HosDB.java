/*
 * HosDB.java
 *
 * Created on 2 ÁÔ¶Ø¹ÒÂ¹ 2548, 14:02 ¹.
 */

package com.pcu.control;   
import com.pcu.objdb.objdbclass.*;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.objdb.specialQuery.SpecialPatientDB;
import com.hospital_os.objdb.*;

/**
 *
 * @author Administrator
 */
public class HosDB {
    
    /** Creates a new instance of HosDB */
    public HosDB() {
    }
    public AddressDB theAddressDB;
    public DrugInDB theDrugInDB;
    public VillageLocationDB theVillageLocationDB;
    public VillageDB theVillageDB;
    public HomeDB theHomeDB;
    public SubHomeDB theSubHomeDB;
    public WaterUseDB theWaterUseDB;
    public GarbageMethodDB theGarbageMethodDB;
    public HomeCharacterDB theHomeCharacterDB;
    public CommunityCharacterDB theCommunityCharacterDB;
    public DungMethodDB theDungMethodDB;
    public HomeZoneDB theHomeZoneDB;
    public PetTypeDB thePetTypeDB;
    public PetSexDB thePetSexDB;
    public WaterEradicateDB theWaterEradicateDB;
    public SchoolMaxClassDB theSchoolMaxClassDB;
 
    public CompanyTypeDB theCompanyTypeDB;
    public WaterTypeDB theWaterTypeDB;
    public com.pcu.objdb.objdbclass.ServiceTypeDB theServiceTypeDB;

    public HouseStandardDB theHouseStandardDB;
    public FoodStandardDB theFoodStandardDB;
    public BugCarrierDB theBugCarrierDB;
    public HerbDB theHerbDB;
    public PetDB thePetDB;

    public SchoolDB theSchoolDB;
    public TempleDB theTempleDB;
    public CompanyDB theCompanyDB;
    public CompanyHistoryDB theCompanyHistoryDB;
    public WaterDB theWaterDB;
    public WaterHistoryDB theWaterHistoryDB; 
    
    public VisitHomeDB theVisitHomeDB;
    public CounselDB theCounselDB;
    public EfficiencyDB theEfficiencyDB;
    public NutritionTypeDB theNutritionTypeDB;
    public CheckNutritionDB theCheckNutritionDB;
    public CheckStudentHealthDB  theCheckStudentHealthDB;  
    public CheckGoiterDB theCheckGoiterDB;
    public CheckFeBloodDB theCheckFeBloodDB;
    public CheckStudentDentalDB theCheckStudentDentalDB;
    public CheckWormDB theCheckWormDB;
    public CheckThalassemiaDB theCheckThalassemiaDB;
    public CheckLiceDB theCheckLiceDB;
    public CheckBodyDB theCheckBodyDB;
    public CheckOtherDB theCheckOtherDB;    
    
    public GumLevelDB  theGumLevelDB;    

    public PostpartumBirthPlaceDB thePostpartumBirthPlaceDB;
    public PregnancyPregnantLevelDB thePregnancyPregnantLevelDB;
    public PostpartumStatusResultDB thePostpartumStatusResultDB;
    public PostpartumGivebirthResultDB thePostpartumGivebirthResultDB;
    public PregnancyBirthDoctorTypeDB thePregnancyBirthDoctorTypeDB;
    public PregnancyBirthHighRiskDB thePregnancyBirthHighRiskDB;
    public AncSectionDB theAncSectionDB;
    public NutritionDB  theNutritionDB;
    public CheckHealthYearDB theCheckHealthYearDB;
    public CheckHealthYearActivityDB theCheckHealthYearActivityDB;
    public DentalDB theDentalDB;
    public EpiSetDB theEpiSetDB;
    public EpiSetGroupDB theEpiSetGroupDB;
    public EpiDB theEpiDB;
    public EpiDetailDB theEpiDetailDB;
    public CategoryGroupItemDB theCategoryGroupItemDB;    
    public SchoolClassDB theSchoolClassDB;
    public DoseEpiSetDB theDoseEpiSetDB;
    public VisitSchoolDB theVisitSchoolDB;
    public CommunityResorceOwnerDB theCommunityResorceOwnerDB;
    public SchoolServiceTypeDB theSchoolServiceTypeDB;
    public CommunityStandardTypeDB theCommunityStandardTypeDB;
    public CommunityStandardDB theCommunityStandardDB;
 
    /**tong*/
    public FamilyPlaningMethodDB theFamilyPlaningMethodDB;
    public FamilyPlaningDB theFamilyPlaningDB;
    public FamilyPlaningSupplyDrugDoseDB theFamilyPlaningSupplyDrugDoseDB;
    public FamilyPlaningSupplyGroupDB theFamilyPlaningSupplyGroupDB;
    public FamilyPlaningSupplyItemDB theFamilyPlaningSupplyItemDB;
    public FamilyPlaningCauseDB theFamilyPlaningCauseDB;
    public DrugDB theDrugDB;
    public PostpartumEpisiotomyTypeDB thePostpartumEpisiotomyTypeDB;
    public AfterMchMotherDB theAfterMchMotherDB;
    public PostpartumDetailDB thePostpartumDetailDB;
    public ItemPriceDB theItemPriceDB;
    public OrderItemDB theOrderItemDB;
    public OrderItemDrugDB theOrderItemDrugDB;
    public VisitPCUDB theVisitPCUDB;
    public ResultGiveBirthDB theResultGiveBirthDB;
    public BDoctorDB theBDoctorDB;
    public PPResultDetailDB thePPResultDetailDB;
    public ResultStatusDB theResultStatusDB;
    public ICD10DB theICD10DB;
    public BornMchDB theBornMchDB;
    
    public UomDB theUomDB;
    public DrugInstructionDB theDrugInstructionDB;
    public DrugFrequencyDB theDrugFrequencyDB;
    public GrowDB theGrowDB;
    public SchoolHistoryDB theSchoolHistoryDB;
    public GrowPcuDB theGrowPcuDB;
    public GrowHistoryDB theGrowHistoryDB;
    public PrefixDB thePrefixDB;
    public StudentDB theStudentDB;
    public CheckResultDB theCheckResultDB;
    public CheckEyesDB theCheckEyesDB;
    public CheckEarsDB theCheckEarsDB;
    public SexDB theSexDB;
    public TempleTypeDB theTempleTypeDB;
    public TemplePersonelDB theTemplePersonelDB;

    public UterusLevelDB theUterusLevelDB;
    public BabyStatusDB theBabyStatusDB;
    public ConductDB theConductDB;
    public AbNomalDB theAbNomalDB;
    public PregnancyDB thePregnancyDB;
    public AncPcuDB theAncPcuDB;
    public AncDetailPcuDB theAncDetailPcuDB;

    public ReligionDB theReligionDB;
    public OfficeDB theOfficeDB;    
    public MarketTypeDB theMarketTypeDB;
    public MarketTypeLockDB theMarketTypeLockDB;
    public MarketDB theMarketDB;
    public MarketHistoryDB theMarketHistoryDB;
    public DrugHistoryDB theDrugHistoryDB;
    public DrugStockDB theDrugStockDB;
    public TempleHistoryDetailDB theTempleHistoryDetailDB;
    public TempleHistoryDB theTempleHistoryDB;
    public CommunityResourceDB theCommunityResourceDB;
    public ResourceDB theResourceDB;
    public ResourceHistoryDB theResourceHistoryDB; 
    public AGRCodeDB theAGRCodeDB;
    public AGRTypeDB theAGRTypeDB;
    public AGRDB theAGRDB;
    public AGRHistoryDB theAGRHistoryDB;
    public PPConditionDB thePPConditionDB;
    public PPColorDB thePPColorDB;
    public PPActivityDB thePPActivityDB;
    public PPBreathingTimeDB thePPBreathingTimeDB;
    public PPAspirationDB thePPAspirationDB;
    public PPEyeDrugDB thePPEyeDrugDB;
    public BirthMethodDB theBirthMethodDB;
    public PPAsphyxiaDB thePPAsphyxiaDB;
    public PPVitKDB thePPVitKDB;
    public PPStateDB thePPStateDB;
    public PPDeadTypeDB thePPDeadTypeDB;
    public PPDeadCauseDB thePPDeadCauseDB;
    public SiteDB theSiteDB;
    public PPDB thePPDB;
    public VisitDB theVisitDB;
    public PatientDB thePatientDB;
    public PPCareDB thePPCareDB;
    public PPCareChildDB thePPCareChildDB;
    public FamilyDB theFamilyDB;
    public VitalSignDB theVitalSignDB;
    public EpiOutSiteDB theEpiOutSiteDB;
    public BloodGroupDB theBloodGroupDB;
    public MarryStatusDB theMarryStatusDB;
    public NationDB theNationDB;
    public EducateDB theEducateDB;
    public TypeAreaDB theTypeAreaDB;
    public PrefixPcuDB thePrefixPcuDB; 
    public OccupationPcuDB theOccupationPcuDB;    
    public SpecialPatientDB theSpecialPatientDB;
    public LaborDB theLaborDB;
    public RelationDB theRelationDB;
    public ItemPCUDB theItemPCUDB;
    public ItemDB theItemDB;
    public PatientOldHnDB thePatientOldHnDB;
    public MaimTypeDB theMaimTypeDB;
    public MaimDB theMaimDB;
    
    //neung
    public DiseaseDB theDiseaseDB;
    public UncontagiousDB theUncontagiousDB;
    public DischargeDB theDischargeDB;
    public ChronicDB theChronicDB;
    public AgeSurveyDB theAgeSurveyDB;
    public SurveyDB theSurveyDB;
    public CheckHealthDB theCheckHealthDB;
    public VitalsignHomeVisitDB theVitalsignHomeVisitDB;
    //jao
    public SequenceDataDB theSequenceDataDB;
    //aut
    public DiagIcd10DB theDiagIcd10DB;
    
    public HosDB(ConnectionInf c) 
    {
        theAddressDB = new AddressDB(c);
        theDrugInDB = new DrugInDB(c);
        theVillageLocationDB = new VillageLocationDB(c);
        theVillageDB = new VillageDB(c);
        theHomeDB = new HomeDB(c);
        theSubHomeDB = new SubHomeDB(c);
        theWaterUseDB = new WaterUseDB(c);
        theGarbageMethodDB = new GarbageMethodDB(c);
        theHomeCharacterDB = new HomeCharacterDB(c);
        theCommunityCharacterDB = new CommunityCharacterDB(c);
        theDungMethodDB = new DungMethodDB(c);
        theHomeZoneDB = new HomeZoneDB(c);
        thePetTypeDB = new PetTypeDB(c);
        thePetSexDB = new PetSexDB(c);
        theWaterEradicateDB = new WaterEradicateDB(c);
        theSchoolMaxClassDB = new SchoolMaxClassDB(c);
      
        theCompanyTypeDB = new CompanyTypeDB(c);
        theWaterTypeDB = new WaterTypeDB(c);
        theServiceTypeDB = new com.pcu.objdb.objdbclass.ServiceTypeDB(c);

        theHouseStandardDB = new HouseStandardDB(c);
        theFoodStandardDB = new FoodStandardDB(c);
        theBugCarrierDB = new BugCarrierDB(c); 
        theHerbDB = new HerbDB(c);
        thePetDB = new PetDB(c);

        theSchoolDB = new SchoolDB(c);
        theTempleDB = new TempleDB(c);
        theCompanyDB = new CompanyDB(c);
        theCompanyHistoryDB = new CompanyHistoryDB(c);
        theWaterDB = new WaterDB(c);
        theWaterHistoryDB = new WaterHistoryDB(c);

        theVisitHomeDB = new VisitHomeDB(c);
        theCounselDB = new CounselDB(c);
        theEfficiencyDB = new EfficiencyDB(c);
        theNutritionTypeDB = new NutritionTypeDB(c);
        theCheckNutritionDB = new CheckNutritionDB(c);
        theCheckStudentHealthDB = new CheckStudentHealthDB(c);
        theCheckGoiterDB = new CheckGoiterDB(c);
        theCheckFeBloodDB = new CheckFeBloodDB(c);       
        theCheckStudentDentalDB = new CheckStudentDentalDB(c);
        theCheckWormDB = new CheckWormDB(c);
        theCheckThalassemiaDB = new CheckThalassemiaDB(c);
        theCheckLiceDB = new CheckLiceDB(c);
        theCheckBodyDB = new CheckBodyDB(c);
        theCheckOtherDB = new CheckOtherDB(c);
        theGumLevelDB = new GumLevelDB(c);        
        
        thePostpartumBirthPlaceDB = new PostpartumBirthPlaceDB(c); 
        thePregnancyPregnantLevelDB = new PregnancyPregnantLevelDB(c);
        thePostpartumStatusResultDB = new PostpartumStatusResultDB(c);
        thePostpartumGivebirthResultDB = new PostpartumGivebirthResultDB(c);
        thePregnancyBirthDoctorTypeDB = new PregnancyBirthDoctorTypeDB(c);
        thePregnancyBirthHighRiskDB = new PregnancyBirthHighRiskDB(c);
        theAncSectionDB = new AncSectionDB(c);
        theNutritionDB = new NutritionDB(c);
        theCheckHealthYearDB = new CheckHealthYearDB(c);
        theCheckHealthYearActivityDB = new CheckHealthYearActivityDB(c);
        theDentalDB = new DentalDB(c);
        theEpiSetDB = new EpiSetDB(c);
        theEpiSetGroupDB = new EpiSetGroupDB(c);
        theEpiDB = new EpiDB(c);
        theEpiDetailDB = new EpiDetailDB(c);
        theCategoryGroupItemDB = new CategoryGroupItemDB(c);        
        theSchoolClassDB = new SchoolClassDB(c);
        theDoseEpiSetDB = new DoseEpiSetDB(c);
        theVisitSchoolDB = new VisitSchoolDB(c); 
        theCommunityResorceOwnerDB = new CommunityResorceOwnerDB(c);
        theSchoolServiceTypeDB = new SchoolServiceTypeDB(c);
        theCommunityStandardTypeDB = new CommunityStandardTypeDB(c);
        theCommunityStandardDB = new CommunityStandardDB(c);
        
        /**tong*/
        theFamilyPlaningMethodDB = new FamilyPlaningMethodDB(c);
        theFamilyPlaningDB = new FamilyPlaningDB(c);
        theFamilyPlaningSupplyDrugDoseDB = new FamilyPlaningSupplyDrugDoseDB(c);
        theFamilyPlaningSupplyGroupDB = new FamilyPlaningSupplyGroupDB(c);
        theFamilyPlaningSupplyItemDB = new FamilyPlaningSupplyItemDB(c);
        theFamilyPlaningCauseDB = new FamilyPlaningCauseDB(c);
        theDrugDB = new DrugDB(c);
        theVisitPCUDB = new VisitPCUDB(c);
        theResultGiveBirthDB = new ResultGiveBirthDB(c);
        theBDoctorDB = new BDoctorDB(c);
        thePPResultDetailDB = new PPResultDetailDB(c);
        theResultStatusDB = new ResultStatusDB(c);
        theICD10DB = new ICD10DB(c);
        theBornMchDB = new BornMchDB(c);
        
        thePostpartumEpisiotomyTypeDB = new PostpartumEpisiotomyTypeDB(c);
        theItemPriceDB = new ItemPriceDB(c);
        theOrderItemDB = new OrderItemDB(c);
        theOrderItemDrugDB = new OrderItemDrugDB(c);
        theAfterMchMotherDB = new AfterMchMotherDB(c);
        thePostpartumDetailDB = new PostpartumDetailDB(c);
        theUomDB = new UomDB(c);
        theDrugInstructionDB = new DrugInstructionDB(c);
        theDrugFrequencyDB = new DrugFrequencyDB(c);
        theGrowDB = new GrowDB(c);
        theSchoolHistoryDB = new SchoolHistoryDB(c);
        theGrowPcuDB = new GrowPcuDB(c);
        theGrowHistoryDB = new GrowHistoryDB(c);
        thePrefixDB = new PrefixDB(c);
        theStudentDB = new StudentDB(c);
        theCheckResultDB = new CheckResultDB(c);
        theCheckEyesDB = new CheckEyesDB(c);
        theCheckEarsDB = new CheckEarsDB(c);
        theSexDB = new SexDB(c);
        theTempleTypeDB = new TempleTypeDB(c);
        theTemplePersonelDB = new TemplePersonelDB(c);

        theUterusLevelDB = new UterusLevelDB(c);
        theBabyStatusDB = new BabyStatusDB(c);
        theConductDB = new ConductDB(c);
        theAbNomalDB = new AbNomalDB(c);
        thePregnancyDB = new PregnancyDB(c); 
        theAncPcuDB = new AncPcuDB(c);
        theAncDetailPcuDB = new AncDetailPcuDB(c);

        theReligionDB = new ReligionDB(c);
        theOfficeDB = new OfficeDB(c);
        theMarketTypeDB = new MarketTypeDB(c);
        theMarketTypeLockDB = new MarketTypeLockDB(c);
        theMarketDB = new MarketDB(c);
        theMarketHistoryDB = new MarketHistoryDB(c);
        theDrugStockDB = new DrugStockDB(c);
        theDrugHistoryDB = new DrugHistoryDB(c);
        theTempleHistoryDetailDB = new TempleHistoryDetailDB(c);
        theTempleHistoryDB = new TempleHistoryDB(c);
        theCommunityResourceDB = new CommunityResourceDB(c);
        theResourceHistoryDB = new ResourceHistoryDB(c);
        theResourceDB = new ResourceDB(c);    
        theAGRCodeDB = new AGRCodeDB(c);
        theAGRTypeDB = new AGRTypeDB(c);
        theAGRDB = new AGRDB(c);
        theAGRHistoryDB = new AGRHistoryDB(c);
        thePPConditionDB = new PPConditionDB(c);
        thePPColorDB = new PPColorDB(c);
        thePPActivityDB = new PPActivityDB(c);
        thePPBreathingTimeDB = new PPBreathingTimeDB(c);
        thePPAspirationDB = new PPAspirationDB(c);
        thePPEyeDrugDB = new PPEyeDrugDB(c);
        theBirthMethodDB = new BirthMethodDB(c);
        thePPAsphyxiaDB = new PPAsphyxiaDB(c);
        thePPVitKDB = new PPVitKDB(c);
        thePPStateDB = new PPStateDB(c);
        thePPDeadTypeDB = new PPDeadTypeDB(c);
        thePPDeadCauseDB = new PPDeadCauseDB(c);
        theSiteDB = new SiteDB(c);
        thePPDB = new PPDB(c);
        theVisitDB = new VisitDB(c);
        thePPCareDB = new PPCareDB(c);
        thePPCareChildDB = new PPCareChildDB(c);
        theFamilyDB = new FamilyDB(c);
        thePatientDB = new PatientDB(c,theFamilyDB);
        theVitalSignDB = new VitalSignDB(c);
        theEpiOutSiteDB = new EpiOutSiteDB(c);
        theBloodGroupDB = new BloodGroupDB(c);
        theMarryStatusDB = new MarryStatusDB(c);
        theNationDB = new NationDB(c);
        theEducateDB = new EducateDB(c);
        theTypeAreaDB = new TypeAreaDB(c);
        thePrefixPcuDB = new PrefixPcuDB(c);
        theOccupationPcuDB = new OccupationPcuDB(c);        
        theSpecialPatientDB = new SpecialPatientDB(c);
        theLaborDB = new LaborDB(c);
        theRelationDB = new RelationDB(c);
        theItemPCUDB = new ItemPCUDB(c);
        theItemDB = new ItemDB(c);
        thePatientOldHnDB = new PatientOldHnDB(c);
        //neung
        theDiseaseDB = new DiseaseDB(c);
        theUncontagiousDB = new UncontagiousDB(c);
        theChronicDB = new ChronicDB(c);
        theMaimTypeDB = new MaimTypeDB(c);
        theDischargeDB = new DischargeDB(c);
        theAgeSurveyDB = new AgeSurveyDB(c);
        theSurveyDB = new SurveyDB(c);
        theCheckHealthDB = new CheckHealthDB(c);
        theMaimDB = new MaimDB(c);
        theSequenceDataDB = new SequenceDataDB(c);
        theVitalsignHomeVisitDB = new VitalsignHomeVisitDB(c);
        
        theDiagIcd10DB = new DiagIcd10DB(c);
    } 

}
