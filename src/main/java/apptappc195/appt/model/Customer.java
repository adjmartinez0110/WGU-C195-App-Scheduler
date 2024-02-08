package apptappc195.appt.model;

public class Customer {

    private int custId;

    private String custName;

  private String address;

  private String postalCode;

  private String phone;

  private String division;

  private String country;

    /**
     * Constructor
     * @param custId
     * @param custName
     * @param address
     * @param postalCode
     * @param phone
     * @param division
     * @param country
     */
    public Customer(int custId, String custName, String address, String postalCode, String phone, String division, String country) {
        this.custId = custId;
        this.custName = custName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.division = division;
        this.country = country;
    }

    //Getters and Setters
    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
      this.division = division;
   }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return String.valueOf(custId);
    }


}
