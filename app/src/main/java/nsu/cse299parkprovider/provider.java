package nsu.cse299parkprovider;

/**
 * Created by jh_anik on 3/19/2018.
 */

public class provider {

    String email;
    String password;
    String phone;
    String area;
    String latno;
    String longno;
    String high;
    String medium;
    String low;

    public provider(String email, String password, String phone, String area, String latno, String longno, String high, String medium, String low) {
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.area = area;
        this.latno = latno;
        this.longno = longno;
        this.high = high;
        this.medium = medium;
        this.low = low;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getLatno() {
        return latno;
    }

    public void setLatno(String latno) {
        this.latno = latno;
    }

    public String getLongno() {
        return longno;
    }

    public void setLongno(String longno) {
        this.longno = longno;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }
}
