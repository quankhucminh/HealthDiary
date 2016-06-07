package com.quankm.healthdiary.pojo;

/**
 * Created by Infernocorez on 5/30/2016.
 */
public class Prescription {
    private long _id;
    private long UserID;
    private String Name;
    private String NameClean;
    private String ConditionName;
    private String ConditionNameClean;
    private String DoctorName;
    private String Note;
    private long ValidFromDate;
    private long ValidToDate;
    private boolean isActive;
    private boolean isUpdated;
    private long UpdatedTimeStamp;

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public long getUserID() {
        return UserID;
    }

    public void setUserID(long userID) {
        UserID = userID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getConditionName() {
        return ConditionName;
    }

    public void setConditionName(String conditionName) {
        ConditionName = conditionName;
    }

    public String getDoctorName() {
        return DoctorName;
    }

    public void setDoctorName(String doctorName) {
        DoctorName = doctorName;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public long getValidFromDate() {
        return ValidFromDate;
    }

    public void setValidFromDate(long validFromDate) {
        ValidFromDate = validFromDate;
    }

    public long getValidToDate() {
        return ValidToDate;
    }

    public void setValidToDate(long validToDate) {
        ValidToDate = validToDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isUpdated() {
        return isUpdated;
    }

    public void setUpdated(boolean updated) {
        isUpdated = updated;
    }

    public long getUpdatedTimeStamp() {
        return UpdatedTimeStamp;
    }

    public void setUpdatedTimeStamp(long updatedTimeStamp) {
        UpdatedTimeStamp = updatedTimeStamp;
    }

    public String getNameClean() {
        return NameClean;
    }

    public void setNameClean(String nameClean) {
        NameClean = nameClean;
    }

    public String getConditionNameClean() {
        return ConditionNameClean;
    }

    public void setConditionNameClean(String conditionNameClean) {
        ConditionNameClean = conditionNameClean;
    }
}
