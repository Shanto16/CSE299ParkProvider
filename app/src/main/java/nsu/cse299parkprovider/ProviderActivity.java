package nsu.cse299parkprovider;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.gson.Gson;



public class ProviderActivity extends AppCompatActivity {

    TextView text;
    TextView text2;
    Gson gson = new Gson();
    //DatabaseReference databaseprovider2;
  //  private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.provider);


        user ob = new user();
             // ob= gson.fromJson(getIntent().getStringExtra("myjson"), user.class);
        //mAuth = FirebaseAuth.getInstance();

        ob.setEmail("rahbar916@gmail.com");

        text=findViewById(R.id.area1);
        text2 = findViewById(R.id.phone1);
       // Toast.makeText(getApplicationContext(),"Booked "+  mAuth.getCurrentUser().getEmail(),Toast.LENGTH_SHORT).show();


        text.setText(ob.getEmail());
        //text2.setText(ob.getPhone());

    }


}
