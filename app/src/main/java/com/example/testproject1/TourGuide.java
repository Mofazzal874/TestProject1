package com.example.testproject1;

public class TourGuide {
     String name;
   String division;
     String region;
     String phone ;
     String paymentNumber ;
      String email ;
      String NIDno ;
      String age ;
      String bloodGroup ;
      String address ;
      String language ;
      String socialMediaLink ;
      String education ;
      String experience ;

      String tourGuideImageUrl ;



    public TourGuide() {
    }

    public TourGuide(String name, String division, String region, String phone, String paymentNumber, String email, String NIDno, String age, String bloodGroup, String address, String language, String socialMediaLink, String education, String experience, String tourGuideImageUrl) {
        this.name = name;
        this.division = division;
        this.region = region;
        this.phone = phone;
        this.paymentNumber = paymentNumber;
        this.email = email;
        this.NIDno = NIDno;
        this.age = age;
        this.bloodGroup = bloodGroup;
        this.address = address;
        this.language = language;
        this.socialMediaLink = socialMediaLink;
        this.education = education;
        this.experience = experience;
        this.tourGuideImageUrl = tourGuideImageUrl;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSocialMediaLink() {
        return socialMediaLink;
    }

    public void setSocialMediaLink(String socialMediaLink) {
        this.socialMediaLink = socialMediaLink;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getTourGuideImageUrl() {
        return tourGuideImageUrl;
    }

    public void setTourGuideImageUrl(String tourGuideImageUrl) {
        this.tourGuideImageUrl = tourGuideImageUrl;
    }


}
