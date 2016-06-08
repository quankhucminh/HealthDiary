package com.quankm.healthdiary.pojo;

/**
 * Created by Infernocorez on 5/30/2016.
 */
public class User {
    private long _id;
    private String ReferenceCode;
    private String Email;
    private String Password;
    private String FirstName;
    private String LastName;
    private byte Sex;
    private long DateOfBirth;
    private int Height;
    private float Weight;
    private long DateJoined;
    private String CloudID;

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getReferenceCode() {
        return ReferenceCode;
    }

    public void setReferenceCode(String referenceCode) {
        ReferenceCode = referenceCode;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public byte getSex() {
        return Sex;
    }

    public void setSex(byte sex) {
        Sex = sex;
    }

    public long getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(long dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public int getHeight() {
        return Height;
    }

    public void setHeight(int height) {
        Height = height;
    }

    public float getWeight() {
        return Weight;
    }

    public void setWeight(float weight) {
        Weight = weight;
    }

    public long getDateJoined() {
        return DateJoined;
    }

    public void setDateJoined(long dateJoined) {
        DateJoined = dateJoined;
    }

    public String getCloudID() {
        return CloudID;
    }

    public void setCloudID(String cloudID) {
        CloudID = cloudID;
    }
}
