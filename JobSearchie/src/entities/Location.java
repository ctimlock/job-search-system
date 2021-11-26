package entities;

public class Location {
    private String country;
    private String state;
    private String city;
    private String postcode;

    public Location() {
        country = "";
        state = "";
        city = "";
        postcode = "";
    }

    public Location(String country, String state, String city, String postcode) {
        this.country = country;
        this.state = state;
        this.city = city;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
}
