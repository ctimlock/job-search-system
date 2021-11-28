package entities;

public class Location {
    private int id;
    private String country;
    private String state;
    private String city;
    private String postcode;

    public Location() {
        id = -1;
        country = "";
        state = "";
        city = "";
        postcode = "";
    }

    public Location(String country, String state, String city, String postcode) {
        this.id = -1;
        this.country = country;
        this.state = state;
        this.city = city;
        this.postcode = postcode;
    }

    public Location(int id, String country, String state, String city, String postcode) {
        this.id = id;
        this.country = country;
        this.state = state;
        this.city = city;
        this.postcode = postcode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
