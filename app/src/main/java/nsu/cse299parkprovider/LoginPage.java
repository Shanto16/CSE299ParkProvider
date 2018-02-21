package nsu.cse299parkprovider;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import nsu.cse299parkprovider.R;



public class LoginPage extends AppCompatActivity {

    private TextView tv_openAcc;
    private EditText et_username,et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        tv_openAcc = findViewById(R.id.open_new_acc);
        et_username = findViewById(R.id.username);
        et_password = findViewById(R.id.password);

        tv_openAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginPage.this,SignUp_Driver.class));
            }
        });



    }
}
