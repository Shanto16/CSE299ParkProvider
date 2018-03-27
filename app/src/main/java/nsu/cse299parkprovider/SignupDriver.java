package nsu.cse299parkprovider;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import nsu.cse299parkprovider.R;

import static android.widget.Toast.*;

public class SignupDriver extends AppCompatActivity {

    EditText name;
    EditText email;
    EditText password;
    EditText phone;
    EditText licencenum;
    EditText vehicleMake;
    EditText vehicleModel;
    Button buttonAdd;
    DatabaseReference databaseUser;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up__driver);
        mAuth = FirebaseAuth.getInstance();
        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user!=null){
                    Intent intent = new Intent(SignupDriver.this, MainPage.class);
                    startActivity(intent);
                    finish();

                }
            }
        };

        databaseUser = FirebaseDatabase.getInstance().getReference("user");

        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        phone = (EditText) findViewById(R.id.phone);
        licencenum = (EditText) findViewById(R.id.licenceNum);

        vehicleMake = (EditText) findViewById(R.id.vehicleMake);
        vehicleModel = (EditText) findViewById(R.id.vehicleModel);
        buttonAdd = (Button) findViewById(R.id.signup);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUser();
            }
        });

    }
    private void addUser(){

        String Name = name.getText().toString().trim();
        String Email = email.getText().toString().trim();
        String Password = password.getText().toString().trim();
        String Phone = phone.getText().toString().trim();
        String VehicleMake = vehicleMake.getText().toString().trim();
        String VehicleModel = vehicleModel.getText().toString().trim();
        String Licencenum = licencenum.getText().toString().trim();

        if(!TextUtils.isEmpty(Name) && !TextUtils.isEmpty(Email) && !TextUtils.isEmpty(Password) && !TextUtils.isEmpty(Phone) && !TextUtils.isEmpty(VehicleMake)
                && !TextUtils.isEmpty(VehicleModel) && !TextUtils.isEmpty(Licencenum)){

            user user1 = new user(Name,Email, Password, Phone,VehicleMake,VehicleModel,Licencenum);
            databaseUser.child(Password).setValue(user1);
            makeText(this, "USer Added Successfully", LENGTH_SHORT).show();
        }
        else makeText(this, "Please fill up all the fields properly", LENGTH_SHORT).show();

        mAuth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(SignupDriver.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()){
                    Toast.makeText(SignupDriver.this, "sign up error", Toast.LENGTH_SHORT).show();
                }else{
                    String user_id = mAuth.getCurrentUser().getUid();
                    DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("user").child("user_id");
                    current_user_db.setValue(true);
                }
            }
        });






    }




}
