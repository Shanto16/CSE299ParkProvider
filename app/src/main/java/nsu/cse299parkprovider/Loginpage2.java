package nsu.cse299parkprovider;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Loginpage2 extends AppCompatActivity {

    private TextView tv_openAcc;
    private EditText et_username,et_password;
    private Button btn_login;
    FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginpage2);


        tv_openAcc = findViewById(R.id.open_new_acc2);
        et_username = findViewById(R.id.username2);
        et_password = findViewById(R.id.password2);
        btn_login = findViewById(R.id.login_button2);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Loginpage2.this, ProviderActivity.class));
            }
        });


    }
}
