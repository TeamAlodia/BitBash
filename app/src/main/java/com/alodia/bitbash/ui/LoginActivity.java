package com.alodia.bitbash.ui;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alodia.bitbash.AuthListenerActivity;
import com.alodia.bitbash.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AuthListenerActivity implements View.OnClickListener {
    @BindView(R.id.button_SignIn) Button mButton_SignIn;
    @BindView(R.id.editText_Email) EditText mEditText_Email;
    @BindView(R.id.editText_Password) EditText mEditText_Password;
    @BindView(R.id.textView_CreateAccount) TextView mTextView_CreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mButton_SignIn.setOnClickListener(this);
        mTextView_CreateAccount.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String email = mEditText_Email.getText().toString();
        String password = mEditText_Password.getText().toString();

        if(view == mButton_SignIn){
            signIn(email, password);
        }else if(view == mTextView_CreateAccount){
            createAccount(email, password);
        }
    }

    public void createAccount(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(mContext, "Welcome to BitBash!", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    public void signIn(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }
}
