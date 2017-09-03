package com.example.administrator.taxicab.UserRegistration.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.taxicab.Activity.HomeScreen;
import com.example.administrator.taxicab.App.MyApplication;
import com.example.administrator.taxicab.App.PrefManager;
import com.example.administrator.taxicab.UserRegistration.database.UserRegistration_Helper;
import com.example.administrator.taxicab.UserRegistration.database.UserTable;
import com.example.administrator.taxicab.UserRegistration.apihelper.WebUserRegistrationHelper;
import com.example.administrator.taxicab.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Login extends AppCompatActivity
{
    private Button login;
    TextView txtsignup;
    private PrefManager pref;
    private TextInputLayout inputLayout_mobile,inputLayout_passwd;
    private EditText edtMobile,edtPassword;
    private SweetAlertDialog registerDialog;

    @Override
    public void onStart() {
        EventBus.getDefault().register(this);
        super.onStart();// ATTENTION: This was auto-generated to implement the App Indexing API.
// See https://g.co/AppIndexing

    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();// ATTENTION: This was auto-generated to implement the App Indexing API.

    }



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        intialization();

        checkUserLoginStatus();

        loginClickListener();

        signUpclickListener();
      }

    private void intialization()
    {
        pref = new PrefManager(this);

        edtMobile=(EditText)findViewById(R.id.mobileLogin);
        edtPassword=(EditText)findViewById(R.id.passwordLogin);
        login = (Button) findViewById(R.id.loginbtn);
        txtsignup = (TextView) findViewById(R.id.txtsignup);
        inputLayout_mobile=(TextInputLayout)findViewById(R.id.input_layout_mobileLogin);
        inputLayout_passwd=(TextInputLayout)findViewById(R.id.input_layout_passwdLogin);

      //  edtMobile.addTextChangedListener(new MyTextWatcher(edtMobile));
      //  edtPassword.addTextChangedListener(new MyTextWatcher(edtPassword));
    }

    /**
     * Validating form
     */
    private void CheckValidation()
    {
        if(validateMobile() && validatePassword())
        {
            Intent intent = new Intent(Login.this,HomeScreen.class);
            startActivity(intent);
            finish();
        }

    }

    private boolean validatePassword()
    {
        if (edtPassword.getText().toString().trim().isEmpty() && edtPassword.getText().toString().length()<8) {
            inputLayout_passwd.setError("Password length must be at least 8 !");
            requestFocus(edtPassword);
            return false;
        } else {
            inputLayout_passwd.setErrorEnabled(false);
            return true;
        }


    }

    private boolean validateMobile()
    {
        String mobile=edtMobile.getText().toString();
        if (isValidPhoneNumber(mobile))
        {
            inputLayout_mobile.setError("You must enter a valid mobile number !");
            requestFocus(edtMobile);
            return false;
        }
        else
        {
            inputLayout_mobile.setErrorEnabled(false);
            return true;
        }
    }

    private static boolean isValidPhoneNumber(String mobile)
    {
        String regEx = "^[0-9]{10}$";
        return mobile.matches(regEx);
    }

    private void requestFocus(View view)
    {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private void checkUserLoginStatus()
    {
        // Checking for user session
        // if user is already logged in, take him to main activity
        if (pref.isLoggedIn())
        {
            Intent myintent = new Intent(Login.this, HomeScreen.class);
            myintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(myintent);
            finish();

        }
    }

    private void loginClickListener()
    {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(MyApplication.isInternetConnected(Login.this))
                {
                    if(MyApplication.checkLocationServicePermissions(Login.this)) {
                      //  CheckValidation();
                        UserTable userTable= UserRegistration_Helper.getUserInfo(Login.this);
                        if(edtMobile.getText().toString().equalsIgnoreCase(userTable.getMOBILE_NO_VALUE()) &&
                                edtPassword.getText().toString().equalsIgnoreCase(userTable.getPASSWORD_VALUE()))
                        {
                            registerDialog = new SweetAlertDialog(Login.this, SweetAlertDialog.PROGRESS_TYPE)
                                    .setTitleText("Loading").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sDialog) {
                                            sDialog.dismissWithAnimation();
                                        }
                                    });

                            registerDialog.show();
                            registerDialog.setCancelable(false);

                            WebUserRegistrationHelper.userLogin(Login.this,userTable);
                        }
                        else
                        {
                            Toast.makeText(Login.this,"Login credentials does not match",Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });
    }

    private void signUpclickListener()
    {
        txtsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Signup.class);
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
                case R.id.mobileLogin:
                    validateMobile();
                    break;
                case R.id.passwordLogin:
                    validatePassword();
                    break;
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(UserTable userTable)
    {
        switch (userTable.getUSER_LOGIN_STATUS()) {
            case UserTable.USER_LOGIN_FAILED:
                registerDialog.setTitleText("Failed!")
                        .setConfirmText("OK")
                        .changeAlertType(SweetAlertDialog.ERROR_TYPE);


                break;
            case UserTable.USER_LOGIN_SUCCESS:
                registerDialog.setTitleText("Success!")
                        .setConfirmText("OK")
                        .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);

                Intent intent=new Intent(Login.this,HomeScreen.class);
                startActivity(intent);
                this.finish();

                break;

        }
    }
}
