package com.gymapp.fitnesasy.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.gymapp.fitnesasy.Model.DTO.Business.AccountBus;
import com.gymapp.fitnesasy.Model.DTO.Exception.Feedback;
import com.gymapp.fitnesasy.R;



public class SignInActivity extends AppCompatActivity {

    EditText editTextEmail;
    EditText editTextPassword;
    TextView textViewSignUp;
    Button buttonSignIn;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        initControl();
        initEvent();
    }

    private void initEvent() {
        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInActivity.this, SignUpActivity.class));


            }
        });

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    
                    AccountBus accountBus = new AccountBus(editTextEmail.getText().toString(), editTextPassword.getText().toString());
                    
                    final ProgressDialog progressDialog = new ProgressDialog(SignInActivity.this);
                    progressDialog.setMessage("Vui lòng chờ..");
                    progressDialog.show();
                    
                    accountBus.SignIn(SignInActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(Task<AuthResult> task) {
                            progressDialog.dismiss();
                            if (task.isSuccessful()) {
                                
                                finish();
                                startActivity(new Intent(SignInActivity.this, MainActivity.class));
                            } else {
                                try {
                                    throw task.getException();
                                } catch (FirebaseNetworkException e) {
                                    Toast.makeText(SignInActivity.this, "Không có kết nối mạng!", Toast.LENGTH_SHORT).show();
                                } catch (FirebaseAuthInvalidUserException e) {
                                    Toast.makeText(SignInActivity.this, "Tài khoản chưa được đăng ký!", Toast.LENGTH_SHORT).show();
                                } catch (FirebaseAuthInvalidCredentialsException e) {
                                    Toast.makeText(SignInActivity.this, "Dữ liệu nhập không hợp lệ!", Toast.LENGTH_SHORT).show();
                                } catch (Exception e) {
                                    Toast.makeText(SignInActivity.this, "Đã có lỗi xảy ra! Đã gửi feedback, sẽ khắc phục sớm", Toast.LENGTH_SHORT).show();
                                    
                                    Feedback.getInstance().setFeedBackString(e.getClass().toString() + "_" + e.getMessage());

                                }

                                
                            }
                        }
                    });
                } catch (Exception e) {
                    
                    
                }
            }
        });
    }

    private void initControl() {
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textViewSignUp = (TextView) findViewById(R.id.textViewSignUp);
        buttonSignIn = (Button) findViewById(R.id.buttonSignIn);
    }
}

