import java.util.Date;

public class DisplayGuests {
    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private String zip;
    private String country;
    private String email;
    private Date birth;
    private String phoneNumber;
    private String document;
    private Date arrival;
    private Date depature;

    public DisplayGuests(int id, String firstName, String lastName, String address, String zip, String country, String email, Date birth, String phoneNumber, String document, Date arrival, Date depature) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.zip = zip;
        this.country = country;
        this.email = email;
        this.birth = birth;
        this.phoneNumber = phoneNumber;
        this.document = document;
        this.arrival = arrival;
        this.depature = depature;
    }

    public void createGuest(String firstName) {
        this.firstName = firstName;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getZip() {
        return zip;
    }
    public void setZip(String zip) {
        this.zip = zip;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Date getBirth() {
        return birth;
    }
    public void setBirth(Date birth) {
        this.birth = birth;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getDocument() {
        return document;
    }
    public void setDocument(String document) {
        this.document = document;
    }

    public Date getArrival() {
        return arrival;
    }

    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }

    public Date getDepature() {
        return depature;
    }

    public void setDepature(Date depature) {
        this.depature = depature;
    }

    @Override
    public String toString() {
        return "DisplayGuests{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", zip='" + zip + '\'' +
                ", country='" + country + '\'' +
                ", email='" + email + '\'' +
                ", birth=" + birth +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", document='" + document + '\'' +
                ", arrival=" + arrival +
                ", depature=" + depature +
                '}';
    }
}
