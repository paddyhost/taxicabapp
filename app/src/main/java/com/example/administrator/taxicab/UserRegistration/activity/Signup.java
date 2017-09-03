package com.example.administrator.taxicab.UserRegistration.activity;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.taxicab.App.MyApplication;
import com.example.administrator.taxicab.App.PrefManager;
import com.example.administrator.taxicab.R;

public class Signup extends AppCompatActivity {

    private Button signup;
    private PrefManager pref;
    private EditText edtUserName,edtMobile,edtEmail;
    private TextView loginText;
    private TextInputLayout inputLayout_Name,inputLayout_Mobile,inputLayout_Email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        initializations();

        signUpClickListener();

        loginClickListener();

      }

    private void initializations()
    {
        pref = new PrefManager(this);


        signup = (Button) findViewById(R.id.signup);
        edtUserName=(EditText) findViewById(R.id.name);
        edtMobile=(EditText)findViewById(R.id.mobile);
        edtEmail=(EditText)findViewById(R.id.email);

        loginText=(TextView)findViewById(R.id.loginTxt);
        inputLayout_Name=(TextInputLayout) findViewById(R.id.input_layout_name);
        inputLayout_Mobile=(TextInputLayout)findViewById(R.id.input_layout_mobile);
        inputLayout_Email=(TextInputLayout)findViewById(R.id.input_layout_email);

        edtUserName.addTextChangedListener(new MyTextWatcher(edtUserName));
        edtMobile.addTextChangedListener(new MyTextWatcher(edtMobile));
        edtEmail.addTextChangedListener(new MyTextWatcher(edtEmail));

    }

    /**
     * Validating form
     */
    private void CheckValidation()
    {
        if(validateUserName() && validateMobile() && validateEmail())
        {
            signUpNextProcess();
        }

    }


    private boolean validateUserName()
    {
        if (edtUserName.getText().toString().trim().isEmpty()) {
            inputLayout_Name.setError("You must enter a user name !");
            requestFocus(edtUserName);
            return false;
        } else {
            inputLayout_Name.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateMobile()
    {
        if (isValidPhoneNumber(edtMobile.getText().toString()))
        {
            inputLayout_Mobile.setError("You must enter a valid mobile number !");
            requestFocus(edtMobile);
            return false;
        }
        else
        {
            inputLayout_Mobile.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateEmail()
    {
        String email = edtEmail.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            inputLayout_Email.setError("You must enter a valid email address!");
            requestFocus(edtEmail);
            return false;
        } else {
            inputLayout_Email.setErrorEnabled(false);
            return true;
        }
    }

    private static boolean isValidPhoneNumber(String mobile)
    {
        String regEx = "^[0-9]{10}$";
        return mobile.matches(regEx);
    }

    private static boolean isValidEmail(String email)
    {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view)
    {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private void signUpClickListener()
    {
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(MyApplication.isInternetConnected(Signup.this))
                {
                    if(MyApplication.checkLocationServicePermissions(Signup.this)) {
                      //  CheckValidation();
                        signUpNextProcess();
                    }
                }
            }
        });
    }

    private void signUpNextProcess()
    {
        pref.setMobileNumber(edtMobile.getText().toString());
        Intent intent = new Intent(Signup.this,SetPassword.class);
        intent.putExtra(SetPassword.USER_NAME,edtUserName.getText().toString());
        intent.putExtra(SetPassword.USER_EMAIL,edtEmail.getText().toString());
        intent.putExtra(SetPassword.USER_MOBILE,edtMobile.getText().toString());
        startActivity(intent);
    }

    private void loginClickListener()
    {
        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Signup.this,Login.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private class MyTextWatcher implements TextWatcher
    {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.name:
                    validateUserName();
                    break;
                case R.id.mobile:
                    validateMobile();
                    break;
                case R.id.email:
                    validateEmail();
                    break;

            }
        }
    }
}
