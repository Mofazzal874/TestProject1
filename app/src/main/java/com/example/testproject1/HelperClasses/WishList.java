package com.example.testproject1.HelperClasses;

public class WishList {

    String placeName , placeDescription ,placeLocation , placeImageUrl ,placeAddress , placeEmail , placePhone , placeWebsite;
    public WishList() {
    }
    public WishList(String placeName, String placeDescription, String placeLocation, String placeImageUrl, String placeAddress, String placeEmail, String placePhone, String placeWebsite) {
        this.placeName = placeName;
        this.placeDescription = placeDescription;
        this.placeLocation = placeLocation;
        this.placeImageUrl = placeImageUrl;
        this.placeAddress = placeAddress;
        this.placeEmail = placeEmail;
        this.placePhone = placePhone;
        this.placeWebsite = placeWebsite;
    }


    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPlaceDescription() {
        return placeDescription;
    }

    public void setPlaceDescription(String placeDescription) {
        this.placeDescription = placeDescription;
    }

    public String getPlaceLocation() {
        return placeLocation;
    }

    public void setPlaceLocation(String placeLocation) {
        this.placeLocation = placeLocation;
    }

    public String getPlaceImageUrl() {
        return placeImageUrl;
    }

    public void setPlaceImageUrl(String placeImageUrl) {
        this.placeImageUrl = placeImageUrl;
    }

    public String getPlaceAddress() {
        return placeAddress;
    }

    public void setPlaceAddress(String placeAddress) {
        this.placeAddress = placeAddress;
    }

    public String getPlaceEmail() {
        return placeEmail;
    }

    public void setPlaceEmail(String placeEmail) {
        this.placeEmail = placeEmail;
    }

    public String getPlacePhone() {
        return placePhone;
    }

    public void setPlacePhone(String placePhone) {
        this.placePhone = placePhone;
    }

    public String getPlaceWebsite() {
        return placeWebsite;
    }

    public void setPlaceWebsite(String placeWebsite) {
        this.placeWebsite = placeWebsite;
    }
}
