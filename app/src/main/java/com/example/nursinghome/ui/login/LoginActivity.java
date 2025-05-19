package com.example.nursinghome.ui.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.nursinghome.R;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {
    private LoginViewModel vm;
    private TextInputEditText etEmail, etPassword;
    private Button btnLogin;
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail    = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin   = findViewById(R.id.btnLogin);
        progress   = findViewById(R.id.progressLoading);

        vm = new ViewModelProvider(this).get(LoginViewModel.class);

        // 로딩 표시 관찰
        vm.loading.observe(this, isLoading -> {
            progress.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            btnLogin.setEnabled(!isLoading);
        });

        // 에러 메시지 관찰
        vm.error.observe(this, msg -> {
            if (msg != null && !msg.isEmpty()) {
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            }
        });

        // 로그인 성공 관찰
        vm.loginSuccess.observe(this, success -> {
            if (Boolean.TRUE.equals(success)) {
                Toast.makeText(this, "로그인 성공! (쉘 테스트용)", Toast.LENGTH_SHORT).show();
                // TODO: 실제 메인 화면으로 이동
            }
        });

        btnLogin.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String pw    = etPassword.getText().toString();
            vm.login(email, pw);
        });
    }
}
