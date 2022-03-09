package hellojpa.jpashop.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {

    @Column(length = 10)
    private String city;

    @Column(length = 20)
    private String street;

    @Column(length = 5)
    private String zipcode;

    public String fullAddress() {
        return getCity() + " " + getStreet() + " " + getZipcode();
    }

    public String getCity() {
        return city;
    }
    private void setCity(String city) {
        this.city = city;
    }
    public String getStreet() {
        return street;
    }
    private void setStreet(String street) {
        this.street = street;
    }
    public String getZipcode() {
        return zipcode;
    }
    private void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCity() == null) ? 0 : getCity().hashCode());
        result = prime * result + ((getStreet() == null) ? 0 : getStreet().hashCode());
        result = prime * result + ((getZipcode() == null) ? 0 : getZipcode().hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Address other = (Address) obj;
        if (getCity() == null) {
            if (other.getCity() != null)
                return false;
        } else if (!getCity().equals(other.getCity()))
            return false;
        if (getStreet() == null) {
            if (other.getStreet() != null)
                return false;
        } else if (!getStreet().equals(other.getStreet()))
            return false;
        if (getZipcode() == null) {
            if (other.getZipcode() != null)
                return false;
        } else if (!getZipcode().equals(other.getZipcode()))
            return false;
        return true;
    }

    
    
}
