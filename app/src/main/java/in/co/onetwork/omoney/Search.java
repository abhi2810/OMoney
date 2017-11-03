package in.co.onetwork.omoney;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Search extends AppCompatActivity {
    EditText ed1;
    TextView acc,name,add,email,age,amt;
    DatabaseReference root= FirebaseDatabase.getInstance().getReference();
    String ac,uid;
    User u;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getSupportActionBar().setTitle("Search Accounts");
        ed1=(EditText)findViewById(R.id.a);
        acc=(TextView)findViewById(R.id.accn);
        name=(TextView)findViewById(R.id.name);
        add=(TextView)findViewById(R.id.add);
        email=(TextView)findViewById(R.id.email);
        age=(TextView)findViewById(R.id.age);
        amt=(TextView)findViewById(R.id.amt);
    }
    public void search(View v){
        ac=ed1.getText().toString();
        if(ac.equals("")){
            Toast.makeText(this, "Field is empty.", Toast.LENGTH_SHORT).show();
        }else{
            root.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                        if (dataSnapshot1.child("acc").getValue(String.class).equals(ac)){
                            uid=dataSnapshot1.child("uid").getValue(String.class);
                            u=dataSnapshot1.getValue(User.class);
                        }
                    }
                    if(uid==null){
                        Toast.makeText(Search.this, "Account doesn't exist!", Toast.LENGTH_SHORT).show();
                    }else{
                        acc.setText("Account number:\t"+u.getAcc());
                        name.setText("Name:\t"+u.getName());
                        add.setText("Address:\t"+u.getAddress());
                        email.setText("Email:\t"+u.getEmail());
                        age.setText("Age:\t"+u.getAge());
                        amt.setText("Amount:\t"+u.getMoney());
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }
}
