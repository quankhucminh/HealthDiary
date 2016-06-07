package com.quankm.healthdiary.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Infernocorez on 5/30/2016.
 */
public class DBHelper extends SQLiteOpenHelper {


    private static final String DB_NAME = "HealthDiaryDB";
    private static final int DB_VERSION = 1;

    //Table User
    public static final String TBL_USER = "User";
    public static final String USER_COL_ID = "_id";
    public static final String USER_COL_REFERENCECODE = "ReferenceCode";
    public static final String USER_COL_EMAIL = "Email";
    public static final String USER_COL_PASSWORD = "Password";
    public static final String USER_COL_FIRSTNAME = "FirstName";
    public static final String USER_COL_LASTNAME = "LastName";
    public static final String USER_COL_SEX = "Sex";
    public static final String USER_COL_DOB = "DateOfBirth";
    public static final String USER_COL_HEIGHT = "Height";
    public static final String USER_COL_WEIGHT = "Weight";
    public static final String USER_COL_DATEJOINED = "DateJoined";
    public static final String USER_COL_CLOUDID = "CloudID";

    //Table ChronicDisease
    public static final String TBL_CHRONICDISEASE = "ChronicDisease";
    public static final String DISEASE_COL_ID = "_id";
    public static final String DISEASE_COL_USERID = "UserID";
    public static final String DISEASE_COL_CONDITIONNAME = "ConditionName";
    public static final String DISEASE_COL_NOTE = "Note";
    public static final String DISEASE_COL_ISACTIVE = "IsActive";
    public static final String DISEASE_COL_ISUPDATED = "IsUpdated";
    public static final String DISEASE_COL_TIMESTAMP = "UpdatedTimeStamp";

    //Table Permission
    public static final String TBL_PERMISSION = "Permission";
    public static final String PERMISSION_COL_ID = "_id";
    public static final String PERMISSION_COL_USERID = "UserID";
    public static final String PERMISSION_COL_OBSERVERID = "ObserverID";
    public static final String PERMISSION_COL_DATEADDED = "DateAdded";

    //Table BloodPressure
    public static final String TBL_BLOODPRESSURE = "BloodPressure";
    public static final String BLOODPRESSURE_COL_ID = "_id";
    public static final String BLOODPRESSURE_COL_USERID = "UserID";
    public static final String BLOODPRESSURE_COL_SYSTOLIC = "Systolic";
    public static final String BLOODPRESSURE_COL_DIASTOLIC = "Diastolic";
    public static final String BLOODPRESSURE_COL_HEARTRATE = "HeartRate";
    public static final String BLOODPRESSURE_COL_STATE = "State";
    public static final String BLOODPRESSURE_COL_DATETAKEN = "DateTaken";
    public static final String BLOODPRESSURE_COL_ISACTIVE = "IsActive";
    public static final String BLOODPRESSURE_COL_ISUPDATED = "IsUpdated";
    public static final String BLOODPRESSURE_COL_TIMESTAMP = "UpdatedTimeStamp";

    //Table Weight
    public static final String TBL_WEIGHT = "Weight";
    public static final String WEIGHT_COL_ID = "_id";
    public static final String WEIGHT_COL_USERID = "UserID";
    public static final String WEIGHT_COL_BODYWEIGHT = "BodyWeight";
    public static final String WEIGHT_COL_BMI = "BMI";
    public static final String WEIGHT_COL_DATETAKEN = "DateTaken";
    public static final String WEIGHT_COL_ISUPDATED = "IsUpdated";
    public static final String WEIGHT_COL_ISACTIVE = "IsActive";
    public static final String WEIGHT_COL_TIMESTAMP = "UpdatedTimeStamp";

    //Table BloodSugar
    public static final String TBL_BLOODSUGAR = "BloodSugar";
    public static final String BLOODSUGAR_COL_ID = "_id";
    public static final String BLOODSUGAR_COL_USERID = "UserID";
    public static final String BLOODSUGAR_COL_RECORDTYPE = "RecordType";
    public static final String BLOODSUGAR_COL_SUGARLEVEL = "SugarLevel";
    public static final String BLOODSUGAR_COL_DATETAKEN = "DateTaken";
    public static final String BLOODSUGAR_COL_ISUPDATED = "IsUpdated";
    public static final String BLOODSUGAR_COL_ISACTIVE = "IsActive";
    public static final String BLOODSUGAR_COL_TIMESTAMP = "UpdatedTimeStamp";

    //Table Prescription
    public static final String TBL_PRESCRIPTION = "Prescription";
    public static final String PRESCRIPTION_COL_ID = "_id";
    public static final String PRESCRIPTION_COL_USERID = "UserID";
    public static final String PRESCRIPTION_COL_NAME = "Name";
    public static final String PRESCRIPTION_COL_NAMECLEAN = "NameClean";
    public static final String PRESCRIPTION_COL_CONDITIONNAME = "ConditionName";
    public static final String PRESCRIPTION_COL_CONDITIONNAMECLEAN = "ConditionNameClean";
    public static final String PRESCRIPTION_COL_DOCTORNAME = "DoctorName";
    public static final String PRESCRIPTION_COL_NOTE = "Note";
    public static final String PRESCRIPTION_COL_VALIDFROMDATE = "ValidFromDate";
    public static final String PRESCRIPTION_COL_VALIDTODATE = "ValidToDate";
    public static final String PRESCRIPTION_COL_ISUPDATED = "IsUpdated";
    public static final String PRESCRIPTION_COL_ISACTIVE = "IsActive";
    public static final String PRESCRIPTION_COL_TIMESTAMP = "UpdatedTimeStamp";

    //Table Medicine
    public static final String TBL_MEDICINE = "Medicine";
    public static final String MEDICINE_COL_ID = "_id";
    public static final String MEDICINE_COL_USERID = "UserID";
    public static final String MEDICINE_COL_MEDICINENAME = "MedicineName";
    public static final String MEDICINE_COL_MEDICINENAMECLEAN = "MedicineNameClean";
    public static final String MEDICINE_COL_ACTIVEINGREDIENT = "ActiveIngredient";
    public static final String MEDICINE_COL_ACTIVEINGREDIENTCLEAN = "ActiveIngredientClean";
    public static final String MEDICINE_COL_DOSAGE = "Dosage";
    public static final String MEDICINE_COL_ISALLERGIC = "IsAllergic";
    public static final String MEDICINE_COL_ISUPDATED = "IsUpdated";
    public static final String MEDICINE_COL_ISACTIVE = "IsActive";
    public static final String MEDICINE_COL_TIMESTAMP = "UpdatedTimeStamp";

    //Table PresMed
    public static final String TBL_PRESMED = "PresMed";
    public static final String PRESMED_COL_ID = "_id";
    public static final String PRESMED_COL_PRESID = "PresID";
    public static final String PRESMED_COL_MEDID = "MedID";
    public static final String PRESMED_COL_ISUPDATED = "IsUpdated";
    public static final String PRESMED_COL_ISACTIVE = "IsActive";
    public static final String PRESMED_COL_TIMESTAMP = "UpdatedTimeStamp";

    //Table Remind
    public static final String TBL_REMIND = "Remind";
    public static final String REMIND_COL_ID = "_id";
    public static final String REMIND_COL_PRESMEDID = "PresMedID";
    public static final String REMIND_COL_TIMEREMIND = "TimeRemind";

    /*Create Table Queries*/

/*    //Table User
    private String tblUserCreateQuery = "CREATE VIRTUAL TABLE "+TBL_USER+" USING fts4( " +
            USER_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            USER_COL_EMAIL + " TEXT NOT NULL UNIQUE, " +
            USER_COL_PASSWORD + " TEXT NOT NULL, " +
            USER_COL_FIRSTNAME + " TEXT NOT NULL, " +
            USER_COL_LASTNAME + " TEXT NOT NULL, " +
            USER_COL_DOB + " INTEGER NOT NULL, " +
            USER_COL_SEX + " INTEGER NOT NULL, " +
            USER_COL_REFERENCECODE + " TEXT, " +
            USER_COL_HEIGHT + " INTEGER, " +
            USER_COL_WEIGHT + " REAL, " +
            USER_COL_CLOUDID + " TEXT, " +
            USER_COL_DATEJOINED + " INTEGER " +
            ")";*/

    //Table ChronicDisease
    private String tblChronicDiseaseCreateQuery = "CREATE TABLE "+TBL_CHRONICDISEASE+"( " +
            DISEASE_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            DISEASE_COL_USERID + " INTEGER NOT NULL, " +
            DISEASE_COL_CONDITIONNAME + " TEXT NOT NULL, " +
            DISEASE_COL_NOTE + " TEXT, " +
            DISEASE_COL_ISACTIVE + " INTEGER, " +
            DISEASE_COL_ISUPDATED + " INTEGER, " +
            DISEASE_COL_TIMESTAMP + " INTEGER " +
            ")";

/*    //Table Permission
    private String tblPermissionCreateQuery = "CREATE VIRTUAL TABLE "+TBL_PERMISSION+" USING fts4( " +
            PERMISSION_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            PERMISSION_COL_USERID + " INTEGER NOT NULL, " +
            PERMISSION_COL_OBSERVERID + " INTEGER NOT NULL, " +
            PERMISSION_COL_DATEADDED + " INTEGER " +
            ")";*/

    //Table BloodPressure
    private String tblBloodPressureCreateQuery = "CREATE TABLE "+TBL_BLOODPRESSURE+"( " +
            BLOODPRESSURE_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            BLOODPRESSURE_COL_USERID + " INTEGER NOT NULL, " +
            BLOODPRESSURE_COL_SYSTOLIC + " INTEGER NOT NULL, " +
            BLOODPRESSURE_COL_DIASTOLIC + " INTEGER NOT NULL, " +
            BLOODPRESSURE_COL_HEARTRATE + " INTEGER NOT NULL, " +
            BLOODPRESSURE_COL_STATE + " INTEGER NOT NULL, " +
            BLOODPRESSURE_COL_DATETAKEN + " INTEGER, " +
            BLOODPRESSURE_COL_ISACTIVE + " INTEGER, " +
            BLOODPRESSURE_COL_ISUPDATED + " INTEGER, " +
            BLOODPRESSURE_COL_TIMESTAMP + " INTEGER " +
            ")";

    //Table Weight
    private String tblWeightCreateQuery = "CREATE TABLE "+TBL_WEIGHT+"( " +
            WEIGHT_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            WEIGHT_COL_USERID + " INTEGER NOT NULL, " +
            WEIGHT_COL_BODYWEIGHT + " REAL NOT NULL, " +
            WEIGHT_COL_BMI + " REAL NOT NULL, " +
            WEIGHT_COL_DATETAKEN + " INTEGER, " +
            WEIGHT_COL_ISACTIVE + " INTEGER, " +
            WEIGHT_COL_ISUPDATED + " INTEGER, " +
            WEIGHT_COL_TIMESTAMP + " INTEGER " +
            ")";

    //Table BloodSugar
    private String tblBloodSugarCreateQuery = "CREATE TABLE "+TBL_BLOODSUGAR+"( " +
            BLOODSUGAR_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            BLOODSUGAR_COL_USERID + " INTEGER NOT NULL, " +
            BLOODSUGAR_COL_RECORDTYPE + " INTEGER NOT NULL, " +
            BLOODSUGAR_COL_SUGARLEVEL + " REAL NOT NULL, " +
            BLOODSUGAR_COL_DATETAKEN + " INTEGER, " +
            BLOODSUGAR_COL_ISACTIVE + " INTEGER, " +
            BLOODSUGAR_COL_ISUPDATED + " INTEGER, " +
            BLOODSUGAR_COL_TIMESTAMP + " INTEGER " +
            ")";

    //Table Prescription
    private String tblPrescriptionCreateQuery = "CREATE VIRTUAL TABLE "+TBL_PRESCRIPTION+" USING fts4( " +
            PRESCRIPTION_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            PRESCRIPTION_COL_USERID + " INTEGER NOT NULL, " +
            PRESCRIPTION_COL_NAME + " TEXT NOT NULL, " +
            PRESCRIPTION_COL_NAMECLEAN + " TEXT, " +
            PRESCRIPTION_COL_CONDITIONNAME + " TEXT NOT NULL, " +
            PRESCRIPTION_COL_CONDITIONNAMECLEAN + " TEXT, " +
            PRESCRIPTION_COL_DOCTORNAME + " TEXT," +
            PRESCRIPTION_COL_NOTE + " TEXT," +
            PRESCRIPTION_COL_VALIDFROMDATE + " INTEGER, " +
            PRESCRIPTION_COL_VALIDTODATE + " INTEGER, " +
            PRESCRIPTION_COL_ISACTIVE + " INTEGER, " +
            PRESCRIPTION_COL_ISUPDATED + " INTEGER, " +
            PRESCRIPTION_COL_TIMESTAMP + " INTEGER " +
            ")";

    //Table Medicine
    private String tblMedicineCreateQuery = "CREATE VIRTUAL TABLE "+TBL_MEDICINE+" USING fts4( " +
            MEDICINE_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MEDICINE_COL_USERID + " INTEGER NOT NULL, " +
            MEDICINE_COL_MEDICINENAME + " TEXT NOT NULL, " +
            MEDICINE_COL_MEDICINENAMECLEAN + " TEXT, " +
            MEDICINE_COL_ACTIVEINGREDIENT + " TEXT, " +
            MEDICINE_COL_ACTIVEINGREDIENTCLEAN + " TEXT, " +
            MEDICINE_COL_DOSAGE + " TEXT, " +
            MEDICINE_COL_ISALLERGIC + " INTEGER, " +
            MEDICINE_COL_ISACTIVE + " INTEGER, " +
            MEDICINE_COL_ISUPDATED + " INTEGER, " +
            MEDICINE_COL_TIMESTAMP + " INTEGER " +
            ")";

    //Table PresMed
    private String tblPresMedCreateQuery = "CREATE TABLE "+TBL_PRESMED+"( " +
            PRESMED_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            PRESMED_COL_MEDID + " INTEGER NOT NULL, " +
            PRESMED_COL_PRESID + " INTEGER NOT NULL, " +
            PRESMED_COL_ISACTIVE + " INTEGER, " +
            PRESMED_COL_ISUPDATED + " INTEGER, " +
            PRESMED_COL_TIMESTAMP + " INTEGER " +
            ")";

    //Table Remind
    private String tblRemindCreateQuery = "CREATE TABLE "+TBL_REMIND+"( " +
            REMIND_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            REMIND_COL_PRESMEDID + " INTEGER NOT NULL, " +
            REMIND_COL_TIMEREMIND + " TEXT NOT NULL " +
            ")";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tblChronicDiseaseCreateQuery);
        db.execSQL(tblBloodPressureCreateQuery);
        db.execSQL(tblWeightCreateQuery);
        db.execSQL(tblBloodSugarCreateQuery);
        db.execSQL(tblPrescriptionCreateQuery);
        db.execSQL(tblMedicineCreateQuery);
        db.execSQL(tblPresMedCreateQuery);
        db.execSQL(tblRemindCreateQuery);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
