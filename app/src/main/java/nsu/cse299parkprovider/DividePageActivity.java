package nsu.cse299parkprovider;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DividePageActivity extends AppCompatActivity {


    private Button driver;
    private  Button provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.divide_page);



        driver = findViewById(R.id.btn_driver);
        provider= findViewById(R.id.btn_provider);


        driver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DividePageActivity.this,LoginPage.class));
            }
        });


        provider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DividePageActivity.this,Loginpage2.class));
            }
        });

    }
}
