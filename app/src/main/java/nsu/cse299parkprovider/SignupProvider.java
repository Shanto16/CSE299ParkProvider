package nsu.cse299parkprovider;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;


public class SignupProvider extends Fragment {


    EditText email;
    EditText password;
    EditText phoneno;
    Spinner area;
    EditText latitude;
    EditText longitude;
    CheckBox high;
    CheckBox medium;

    CheckBox low;
    TextView text;
    Button provider;
    DatabaseReference databaseUser;
    DatabaseReference newuser;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_provider_signup, container, false);



        databaseUser = FirebaseDatabase.getInstance().getReference("provider");
        newuser = FirebaseDatabase.getInstance().getReference("info");
        text = (TextView) rootView.findViewById(R.id.link);
        text.setMovementMethod(LinkMovementMethod.getInstance());


                email = (EditText) rootView.findViewById(R.id.mEmail);
        password = (EditText) rootView.findViewById(R.id.mPassword);
        phoneno = (EditText) rootView.findViewById(R.id.mPhone);
        latitude = (EditText) rootView.findViewById(R.id.lat);
        longitude = (EditText) rootView.findViewById(R.id.Long);
        area = (Spinner) rootView.findViewById(R.id.mArea);
        high = (CheckBox) rootView.findViewById(R.id.high);
        medium = (CheckBox) rootView.findViewById(R.id.medium);
        low = (CheckBox)  rootView.findViewById(R.id.low);
        provider = (Button) rootView.findViewById(R.id.mbutton);

        provider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addprovider();
                addinfo();

            }
        });




        return rootView;
    }


    private  void addinfo(){

        String lat = latitude.getText().toString().trim();
        String Long = longitude.getText().toString().trim();

        if(!TextUtils.isEmpty(lat) && !TextUtils.isEmpty(Long) ){
            String id = newuser.push().getKey();
            info info1 = new info(lat,Long);
            newuser.child(id).setValue(info1);

        }
        else makeText(getActivity(), "Please fill up all the fields properly", LENGTH_SHORT).show();


    }

    private  void addprovider(){


        String Email = email.getText().toString().trim();
        String Password = password.getText().toString().trim();
        String Phone = phoneno.getText().toString().trim();
        String Area = area.getSelectedItem().toString().trim();
        String lat = latitude.getText().toString().trim();
        String Long = longitude.getText().toString().trim();


        String High = high.getText().toString().trim();
        String Medium = medium.getText().toString().trim();
        String Low = low.getText().toString().trim();

        if(!TextUtils.isEmpty(Email) && !TextUtils.isEmpty(Password) && !TextUtils.isEmpty(Phone) && !TextUtils.isEmpty(Area)
                && !TextUtils.isEmpty(lat) && !TextUtils.isEmpty(Long) ){
            String id = databaseUser.push().getKey();
            provider provider1 = new provider(Email, Password, Phone,Area,lat,Long,High,Medium,Low);
            databaseUser.child(id).setValue(provider1);
            makeText(getActivity(), " Provider Added Successfully", LENGTH_SHORT).show();
        }
        else makeText(getActivity(), "Please fill up all the fields properly", LENGTH_SHORT).show();





    }





}