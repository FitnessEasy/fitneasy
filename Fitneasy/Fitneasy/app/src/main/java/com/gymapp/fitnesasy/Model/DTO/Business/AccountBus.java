package com.gymapp.fitnesasy.Model.DTO.Business;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.gymapp.fitnesasy.Model.DTO.Exception.EmptyStringException;
import com.gymapp.fitnesasy.Model.DTO.Exception.InvalidException;


public final class AccountBus {
    String email,password;

    public AccountBus() throws EmptyStringException, InvalidException {
        String email = "", password="";
        setEmail(email);
        setPassword(password);
    }


    
    static FirebaseUser getCurrentFirebaseUser(){
        return FirebaseAuth.getInstance().getCurrentUser();
    }

    
    public AccountBus(String email, String password) throws EmptyStringException, InvalidException {
        setEmail(email);
        setPassword(password);
    }


    
    public void setEmail(String email) throws EmptyStringException, InvalidException {
        
        if(email.isEmpty()){
            throw new EmptyStringException("Email không được để trống");
        }
        
        if(email.indexOf("@")==-1){
            throw new InvalidException("Email không hợp lệ");
        }
        this.email = email;
    }

    
    public void setPassword(String password) throws EmptyStringException, InvalidException{
        
        if(password.isEmpty()){
            throw new EmptyStringException("Password không được để trống");
        }
        

        this.password=password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }



    public void SignIn(Activity activity,@NonNull OnCompleteListener<AuthResult> var1){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(getEmail(),getPassword()).addOnCompleteListener(activity,var1);
    }


    public void SignUp(Activity activity,@NonNull OnCompleteListener<AuthResult> var1){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(getEmail(), getPassword()).addOnCompleteListener(activity,var1);
    }

}
