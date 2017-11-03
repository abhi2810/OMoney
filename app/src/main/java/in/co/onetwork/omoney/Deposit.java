package in.co.onetwork.omoney;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Deposit extends AppCompatActivity {
    DatabaseReference root= FirebaseDatabase.getInstance().getReference();
    EditText ed1,ed2;
    String acc,amt,uid;
    long money;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);
        getSupportActionBar().setTitle("Deposit Money");
        ed1=(EditText)findViewById(R.id.acc);
        ed2=(EditText)findViewById(R.id.amt);
    }
    public void deposit(View v){
        acc=ed1.getText().toString();
        amt=ed2.getText().toString();
        if (acc.equals("")||amt.equals("")){
            Toast.makeText(this, "Fields are empty!", Toast.LENGTH_SHORT).show();
        }else {
            root.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                        if (dataSnapshot1.child("acc").getValue(String.class).equals(acc)){
                            uid=dataSnapshot1.child("uid").getValue(String.class);
                            money=dataSnapshot1.child("money").getValue(Long.class);
                        }
                    }
                    if(uid==null){
                        Toast.makeText(Deposit.this, "Account doesn't exist!", Toast.LENGTH_SHORT).show();
                    }else{
                        root.child("Users").child(uid).child("money").setValue((money+Integer.parseInt(amt)));
                        Toast.makeText(Deposit.this, "Money Deposited", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }
}
