package com.example.shima;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnLogin;
    EditText edtUsername, edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.login);
        edtUsername = findViewById(R.id.username);
        edtPassword = findViewById(R.id.password);
        btnLogin.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setTitle(getString(R.string.screen_name_login));
    }

    private void checkLogin(String userName, String password) {
        //đoạn này trong thực tế thì sẽ call và check thông tin ở database
        if (userName.equals("meo123") && password.equals("meo123")) {
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        } else {
            Toast.makeText(this, getString(R.string.msg_login_error), Toast.LENGTH_LONG).show();
        }
    }

    private boolean validForm(String username, String password) {
        boolean result = true;
        if (username.length() == 0) {
            edtUsername.setError(getString(R.string.msg_valid_notempty));
            result = false;
        }
        if (password.length() == 0) {
            edtPassword.setError(getString(R.string.msg_valid_notempty));
            result = false;
        }
        return result;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                String userName = edtUsername.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                if (validForm(userName, password)) {
                    checkLogin(userName, password);
                }
                break;
        }
    }
}
