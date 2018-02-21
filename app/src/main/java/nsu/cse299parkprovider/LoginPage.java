package nsu.cse299parkprovider;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import nsu.cse299parkprovider.R;



public class LoginPage extends AppCompatActivity {

    private TextView tv_openAcc;
    private EditText et_username,et_password;
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

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
                startActivity(new Intent(LoginPage.this,SignUp_Driver.class));
            }
        });

        /***
         * Codes for login procedures are here. They will be executed if the login button is pressed
         * ***/
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginPage.this, "App will check for authentication and log in", Toast.LENGTH_LONG).show();
            }
        });


    }
}
