package com.example.sels1.obligatoriskopgave;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    public static final String PREF_FILE_NAME = "loginPref";
    public static final String USERNAME = "USERNAME";
    public static final String PASSWORD = "PASSWORD";
    private SharedPreferences preferences;
    private EditText usernameField;
    private EditText passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        preferences = getSharedPreferences(PREF_FILE_NAME, MODE_PRIVATE);
        passwordField = findViewById(R.id.LoginEditTextPassword);
        usernameField = findViewById(R.id.LoginEditTextUsername);
        String username = preferences.getString(USERNAME, null);
        String password = preferences.getString(PASSWORD, null);
        if (username != null && password != null) {
            usernameField.setText(username);
            passwordField.setText(password);
        }

    }
    public void loginButtonClicked(View view) {
        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();
        boolean ok = PasswordChecker.Check(username, password);
        if (ok) {
            CheckBox checkBox = findViewById(R.id.login_remember_checkbox);
            SharedPreferences.Editor editor = preferences.edit();
            if (checkBox.isChecked()) {
                editor.putString(USERNAME, username);
                editor.putString(PASSWORD, password);
            } else {
                editor.remove(USERNAME);
                editor.remove(PASSWORD);
            }
            editor.apply();
            Intent intent = new Intent(this, UserLogInActivity.class);
            intent.putExtra(USERNAME, username);
            startActivity(intent);
        } else {
            usernameField.setError("Wrong username or password");
            TextView messageView = findViewById(R.id.login_message_textview);
            messageView.setText("Username + password not valid");
        }
    }
   /* private void StartLogin()
    {
        String passwordString = password.getText().toString();
        String emailString = email.getText().toString();


        mAuth.signInWithEmailAndPassword(emailString, passwordString)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(getBaseContext(), UserLogInActivity.class);
                            intent.putExtra("USER",user);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("###error###", "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }



                    }
                });

    }*/

    public void Login(View view) {
        //StartLogin();
    }
}


