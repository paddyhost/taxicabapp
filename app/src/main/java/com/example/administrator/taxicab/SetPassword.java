package com.example.administrator.taxicab;

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

import com.example.administrator.taxicab.App.MyApplication;
import com.example.administrator.taxicab.Database.UserDatabase.UserTable;
import com.example.administrator.taxicab.Database.UserDatabase.WebUserRegistrationAPI.WebUserRegistrationHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class SetPassword extends AppCompatActivity
{

    private Button confirm_signup;
    public static final String USER_NAME = "username", USER_MOBILE = "mobile", USER_EMAIL = "email";
    private String userName, userEmail, userMobile;
    private EditText edtPasswrd, edtConfrmPasswrd;
    private SweetAlertDialog registerDialog;
    private TextInputLayout inputLayoutPasswd,inputLayoutConfrmPasswd;

    @Override
    public void onStart() {
        EventBus.getDefault().register(this);
        super.onStart();// ATTENTION: This was auto-generated to implement the App Indexing API.
// See https://g.co/AppIndexing/


    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();// ATTENTION: This was auto-generated to implement the App Indexing API.

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_password);

        intializations();

        getIntentData();

        confirmSignUpClickListner();

    }

    private void getIntentData()
    {
        userName = getIntent().getStringExtra(USER_NAME);
        userEmail = getIntent().getStringExtra(USER_EMAIL);
        userMobile = getIntent().getStringExtra(USER_MOBILE);
    }

    private void intializations()
    {
        confirm_signup = (Button) findViewById(R.id.confirm_signup);
        edtPasswrd = (EditText) findViewById(R.id.password);
        edtConfrmPasswrd = (EditText) findViewById(R.id.confirm_password);
        inputLayoutPasswd = (TextInputLayout) findViewById(R.id.input_layout_passwd);
        inputLayoutConfrmPasswd = (TextInputLayout) findViewById(R.id.input_layout_confirm_passwd);

        edtPasswrd.addTextChangedListener(new MyTextWatcher(edtPasswrd));
        edtConfrmPasswrd.addTextChangedListener(new MyTextWatcher(edtConfrmPasswrd));

    }

    private void confirmSignUpClickListner()
    {
        confirm_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(MyApplication.isInternetConnected(SetPassword.this)) {
                    if(MyApplication.checkLocationServicePermissions(SetPassword.this)) {
                        //CheckValidation();
                        saveDataToLoacalNServer();
                    }
                }

            }
        });

    }

    /**
     * Validating form
     */
    private void CheckValidation()
    {
        if (!validatePassword()) {
            return;
        }
        if (!validateConfirmPasswrd()) {
            return;
        }
        if(validatePassword() && validateConfirmPasswrd())
        {
           // if(checkPasswordMatch()) {
                saveDataToLoacalNServer();
           // }
        }

    }

    private void saveDataToLoacalNServer()
    {
            registerDialog = new SweetAlertDialog(SetPassword.this, SweetAlertDialog.PROGRESS_TYPE)
                    .setTitleText("Loading").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismissWithAnimation();
                        }
                    });

            registerDialog.show();
            registerDialog.setCancelable(false);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String currentDateandTime = sdf.format(new Date());


            UserTable userDetails = UserTable.getUsertable(userName,userMobile, edtPasswrd.getText().toString(), null, userEmail, currentDateandTime, "", "1111", "2222");
            WebUserRegistrationHelper.newUserRegistration(SetPassword.this, userDetails);

    }

    private boolean validatePassword()
    {
        if (edtPasswrd.getText().toString().trim().isEmpty() && edtConfrmPasswrd.getText().toString().length()<8) {
            inputLayoutPasswd.setError("Password length must be at least 8 !");
            requestFocus(edtPasswrd);
            return false;
        } else {
            inputLayoutPasswd.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateConfirmPasswrd()
    {
        if (edtConfrmPasswrd.getText().toString().trim().isEmpty() && edtConfrmPasswrd.getText().toString().length()<8) {
            inputLayoutConfrmPasswd.setError("Password length must be at least 8 !");
            requestFocus(edtConfrmPasswrd);
            return false;
        } else {
            inputLayoutConfrmPasswd.setErrorEnabled(false);
        }

        return true;
    }

    private boolean checkPasswordMatch()
    {
        if (edtPasswrd.getText().toString().equals(edtConfrmPasswrd.getText().toString()))
        {
            inputLayoutConfrmPasswd.setError("Password doesn't match !");
            requestFocus(edtConfrmPasswrd);
            return false;
        }
        else
        {
            inputLayoutConfrmPasswd.setErrorEnabled(false);
        }

            return true;
    }


    private void requestFocus(View view)
    {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
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
                case R.id.password:
                    validatePassword();
                    break;
                case R.id.confirm_password:
                    validateConfirmPasswrd();
                    break;
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(UserTable userTable)
    {
        switch (userTable.getUSER_REGISTER_STATUS()) {
            case UserTable.USER_REGISTER_FAILED:
                registerDialog.setTitleText("Failed!")
                        .setConfirmText("OK")
                        .changeAlertType(SweetAlertDialog.ERROR_TYPE);


                break;
            case UserTable.USER_REGISTER_SUCCESS:
                registerDialog.setTitleText("Success!")
                        .setConfirmText("OK")
                        .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);

                Intent intent=new Intent(SetPassword.this,HomeScreen.class);
                startActivity(intent);
                this.finish();

                break;

        }
    }
}
