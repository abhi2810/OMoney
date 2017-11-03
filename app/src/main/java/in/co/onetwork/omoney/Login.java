package in.co.onetwork.omoney;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText ed1,ed2;
    String email,password;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth=FirebaseAuth.getInstance();
        getSupportActionBar().setTitle("Login");
        if(mAuth.getCurrentUser()!=null&& !mAuth.getCurrentUser().getEmail().equals("admin@admin.com")){
            Toast.makeText(this, "Already Signed in.", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,Homescreen.class));
            finish();
        }
        setContentView(R.layout.activity_login);
        ed1=(EditText)findViewById(R.id.email);
        ed2=(EditText)findViewById(R.id.passw);
    }
    public void login(View v){
        email=ed1.getText().toString().trim();
        password=ed2.getText().toString();
        if(email.equals("")||password.equals("")){
            Toast.makeText(this, "Fields are empty", Toast.LENGTH_SHORT).show();
        }else{
            if(email.equals("admin")&&password.equals("12345")){
                Toast.makeText(this, "Logged in as Admin", Toast.LENGTH_SHORT).show();
                mAuth.signInWithEmailAndPassword("admin@admin.com","12345678");
                startActivity(new Intent(this,Admin.class));
            }else {
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Login.this, "Signed in Successfully", Toast.LENGTH_SHORT).show();
                            ed1.setText("");
                            ed2.setText("");
                            startActivity(new Intent(Login.this, Homescreen.class));
                        } else {
                            Toast.makeText(Login.this, "Signin error!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
    }
    public void signup(View v){
        startActivity(new Intent(Login.this,Signup.class));
    }
}
