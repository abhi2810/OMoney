package in.co.onetwork.omoney;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewAccount extends AppCompatActivity {
    FirebaseAuth auth=FirebaseAuth.getInstance();
    DatabaseReference user= FirebaseDatabase.getInstance().getReference("Users");
    TextView acc,name,add,email,age,amt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_account);
        getSupportActionBar().setTitle("View Account");
        acc=(TextView)findViewById(R.id.accn);
        name=(TextView)findViewById(R.id.name);
        add=(TextView)findViewById(R.id.add);
        email=(TextView)findViewById(R.id.email);
        age=(TextView)findViewById(R.id.age);
        amt=(TextView)findViewById(R.id.amt);
        user.child(auth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User u=dataSnapshot.getValue(User.class);
                acc.setText("Account number:\t"+u.getAcc());
                name.setText("Name:\t"+u.getName());
                add.setText("Address:\t"+u.getAddress());
                email.setText("Email:\t"+u.getEmail());
                age.setText("Age:\t"+u.getAge());
                amt.setText("Amount:\t"+u.getMoney());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
