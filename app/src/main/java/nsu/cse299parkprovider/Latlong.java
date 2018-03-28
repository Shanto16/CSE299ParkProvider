package nsu.cse299parkprovider;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by jh_anik on 3/28/2018.
 */

public class Latlong {
    public double lat[];
    public double lon[];
    public int count;
    DatabaseReference databaseprovider;


    public Latlong getLatLong(){

         final Latlong l1 = new Latlong();

    databaseprovider = FirebaseDatabase.getInstance().getReference("provider");
         databaseprovider.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i=0;
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting artist
                    provider newprovider = postSnapshot.getValue(provider.class);
                    double d = Double.parseDouble(newprovider.getLatno());
                    double d1 = Double.parseDouble(newprovider.getLongno());
                    l1.lat[i]=d;
                    l1.lon[i]=d1;
                    i++;
                }
                l1.count = i-1;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return l1;
    }
    }

