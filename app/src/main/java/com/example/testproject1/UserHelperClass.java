package com.example.testproject1;

public class UserHelperClass {

    String regName, regEmail, regPassword, regUsername, regPhone, regDateOfBirth, regBloodGroup, regProfileImageUrl, regAddress;

    public UserHelperClass() {
    }

    public UserHelperClass(String regName, String regEmail, String regPassword, String regUsername,
                           String regPhone, String regDateOfBirth, String regBloodGroup,
                           String regProfileImageUrl, String regAddress) {
        this.regName = regName;
        this.regEmail = regEmail;
        this.regPassword = regPassword;
        this.regUsername = regUsername;
        this.regPhone = regPhone;
        this.regDateOfBirth = regDateOfBirth;
        this.regBloodGroup = regBloodGroup;
        this.regProfileImageUrl = regProfileImageUrl;
        this.regAddress = regAddress;
    }

    public String getRegName() {
        return regName;
    }

    public void setRegName(String regName) {
        this.regName = regName;
    }

    public String getRegEmail() {
        return regEmail;
    }

    public void setRegEmail(String regEmail) {
        this.regEmail = regEmail;
    }

    public String getRegPassword() {
        return regPassword;
    }

    public void setRegPassword(String regPassword) {
        this.regPassword = regPassword;
    }

    public String getRegUsername() {
        return regUsername;
    }

    public void setRegUsername(String regUsername) {
        this.regUsername = regUsername;
    }

    public String getRegPhone() {
        return regPhone;
    }

    public void setRegPhone(String regPhone) {
        this.regPhone = regPhone;
    }

    public String getRegDateOfBirth() {
        return regDateOfBirth;
    }

    public void setRegDateOfBirth(String regDateOfBirth) {
        this.regDateOfBirth = regDateOfBirth;
    }

    public String getRegBloodGroup() {
        return regBloodGroup;
    }

    public void setRegBloodGroup(String regBloodGroup) {
        this.regBloodGroup = regBloodGroup;
    }

    public String getRegProfileImageUrl() {
        return regProfileImageUrl;
    }

    public void setRegProfileImageUrl(String regProfileImageUrl) {
        this.regProfileImageUrl = regProfileImageUrl;
    }

    public String getRegAddress() {
        return regAddress;
    }

    public void setRegAddress(String regAddress) {
        this.regAddress = regAddress;
    }
}
