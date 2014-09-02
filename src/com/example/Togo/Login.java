package com.example.Togo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.view.View;
import android.widget.*;
import com.example.Togo.library.DatabaseHandler;
import com.example.Togo.library.UserFunctions;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by lilian on 8/19/14.
 */
public class Login extends Activity {
    Button btnLogin;
    Button btnLinkToRegister;
    EditText inputEmail;
    EditText inputPassword;
    TextView loginErrorMsg;
    CheckBox checkbox;

    // JSON Response node names
    private static String KEY_SUCCESS = "success";
    private static String KEY_ERROR = "error";
    private static String KEY_ERROR_MSG = "error_msg";
    private static String KEY_UID = "uid";
    private static String KEY_NAME = "name";
    private static String KEY_EMAIL = "email";
    private static String KEY_CREATED_AT = "created_at";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // Importing all assets like buttons, text fields
        inputEmail = (EditText) findViewById(R.id.loginEmail);
        inputPassword = (EditText) findViewById(R.id.loginPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLinkToRegister = (Button) findViewById(R.id.btnLinkToRegisterScreen);
        loginErrorMsg = (TextView) findViewById(R.id.login_error);
        checkbox=(CheckBox)findViewById(R.id.checkBox);
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(!isChecked){
                    inputPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }else {
                    inputPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }


            }
        });

        // Login button Click Event
        btnLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                String email = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();
                UserFunctions userFunction = new UserFunctions();
                JSONObject json = userFunction.loginUser(email, password);

                // check for login response
                try {
                    if (json.getString(KEY_SUCCESS) != null) {
                        loginErrorMsg.setText("login unsuccessful");
                        String res = json.getString(KEY_SUCCESS);
                        if(Integer.parseInt(res) == 1){
                            // user successfully logged in
                            // Store user details in SQLite Database
                            DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                            JSONObject json_user = json.getJSONObject("user");

                            // Clear all previous data in database
                            userFunction.logoutUser(getApplicationContext());
                            db.addUser(json_user.getString(KEY_NAME), json_user.getString(KEY_EMAIL), json.getString(KEY_UID), json_user.getString(KEY_CREATED_AT));

                            // Launch Dashboard Screen
                            Intent dashboard = new Intent(getApplicationContext(), Chat.class);

                            // Close all views before launching Dashboard
                            //dashboard.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            //startActivity(dashboard);

                            // Close Login Screen
                            finish();
                        }else{
                            // Error in login
                            loginErrorMsg.setText("Incorrect username/password");
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        // Link to Register Screen
        btnLinkToRegister.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Register.class);
                startActivity(i);
                finish();
            }
        });
    }
}