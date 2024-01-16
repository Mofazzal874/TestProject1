package com.example.testproject1;

public class TeamMember {
    private String name;
    private String userName;
    private String phone;
    private String bloodGroup;
    private String address;
    private String imageUrl;
    private String email;

    // Add constructors, getters, and setters as needed

    public TeamMember() {
        // Default constructor required for Firebase
    }

    public TeamMember(String name, String userName, String phone, String bloodGroup, String address, String imageUrl, String email) {
        this.name = name;
        this.userName = userName;
        this.phone = phone;
        this.bloodGroup = bloodGroup;
        this.address = address;
        this.imageUrl = imageUrl;
        this.email = email;
    }

    // Add getters and setters here

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
}
