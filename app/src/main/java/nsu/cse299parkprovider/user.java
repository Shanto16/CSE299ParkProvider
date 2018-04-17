package nsu.cse299parkprovider;
import android.widget.EditText;


/**
 * Created by jh_anik on 3/17/2018.
 */

public class user {


    String name;
    String email;
    String password;
    String phone;
    String vehicleMake;
    String vehicleModel;
    String licencenum;

    public user(){

    }
    public user(EditText name, EditText email, EditText password, EditText phone, EditText vehicleMake, EditText vehicleModel, EditText licencenum){

    }

    public user(String name, String email, String password, String phone, String vehicleMake, String vehicleModel, String licencenum) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.vehicleMake = vehicleMake;
        this.vehicleModel = vehicleModel;
        this.licencenum = licencenum;
    }

    public String getName() {
        return name;
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
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVehicleMake() {
        return vehicleMake;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public String getLicencenum() {
        return licencenum;
    }






}


