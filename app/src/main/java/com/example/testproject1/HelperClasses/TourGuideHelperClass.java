package com.example.testproject1.HelperClasses;

public class TourGuideHelperClass {

    String name, image, description, division, region, phone, paymentNumber, email, NIDno, age,address, languageSkill, socialMediaLink, education , bloodGroup ;


    public TourGuideHelperClass() {
    }

    public TourGuideHelperClass(String name, String image, String description, String division, String region, String phone, String paymentNumber, String email, String NIDno, String age, String bloodGroup, String address, String languageSkill, String socialMediaLink, String education) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.division = division;
        this.region = region;
        this.phone = phone;
        this.paymentNumber = paymentNumber;
        this.email = email;
        this.NIDno = NIDno;
        this.age = age;
        this.bloodGroup = bloodGroup;
        this.address = address;
        this.languageSkill = languageSkill;
        this.socialMediaLink = socialMediaLink;
        this.education = education;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPaymentNumber() {
        return paymentNumber;
    }

    public void setPaymentNumber(String paymentNumber) {
        this.paymentNumber = paymentNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLanguageSkill() {
        return languageSkill;
    }

    public void setLanguageSkill(String languageSkill) {
        this.languageSkill = languageSkill;
    }

    public String getSocialMediaLink() {
        return socialMediaLink;
    }

    public void setSocialMediaLink(String socialMediaLink) {
        this.socialMediaLink = socialMediaLink;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }
    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getNIDno() {
        return NIDno;
    }

    public void setNIDno(String NIDno) {
        this.NIDno = NIDno;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}

