package entities;

public class Location {
    private String country;
    private String state;
    private String suburb;
    private String postcode;

    public Location() {
        country = "";
        state = "";
        suburb = "";
        postcode = "";
    }

    public Location(String country, String state, String suburb, String postcode) {
        this.country = country;
        this.state = state;
        this.suburb = suburb;
        this.postcode = postcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
}
