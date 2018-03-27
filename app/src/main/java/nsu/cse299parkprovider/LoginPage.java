package nsu.cse299parkprovider;
import static java.lang.System.*;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class LoginPage extends AppCompatActivity {

    private TextView tv_openAcc;
    private EditText et_username,et_password;
    private Button btn_login;
    DatabaseReference databaseUser;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        //databaseUser = FirebaseDatabase.getInstance().getReference("user");
        mAuth = FirebaseAuth.getInstance();

        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(LoginPage.this, MainPage.class);
                    startActivity(intent);
                    finish();

                }
            }
        };

        /***
         * Initializing the widgets
         * ***/
        tv_openAcc = findViewById(R.id.open_new_acc);
        et_username = findViewById(R.id.username);
        et_password = findViewById(R.id.password);
        btn_login = findViewById(R.id.login_button);


        /***
         * Opening sign up page if SIGN UP text is clicked
         * ***/
        tv_openAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginPage.this, SignupDriver.class));
            }
        });


        /***
         * Codes for login procedures are here. They will be executed if the login button is pressed
         * ***/


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = et_username.getText().toString();
                final String password = et_password.getText().toString();
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginPage.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(LoginPage.this, "sign in error", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            startActivity(new Intent(LoginPage.this, MainPage.class));
                        }
                    }
                });

            }




        });

    }




}




