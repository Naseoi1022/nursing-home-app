package com.example.nursinghome.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {
    private final MutableLiveData<Boolean> _loading = new MutableLiveData<>(false);
    public LiveData<Boolean> loading = _loading;

    private final MutableLiveData<String> _error = new MutableLiveData<>();
    public LiveData<String> error = _error;

    private final MutableLiveData<Boolean> _loginSuccess = new MutableLiveData<>();
    public LiveData<Boolean> loginSuccess = _loginSuccess;

    /** dummy login: 입력된 email/pw가 비어있지 않으면 성공, 비어 있으면 에러 */
    public void login(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            _error.setValue("이메일과 비밀번호를 모두 입력하세요.");
            return;
        }

        // 로딩 표시
        _loading.setValue(true);

        // 간단 딜레이 후 무조건 성공 처리
        new Thread(() -> {
            try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
            _loading.postValue(false);
            _loginSuccess.postValue(true);
        }).start();
    }
}
