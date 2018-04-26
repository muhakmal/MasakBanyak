package com.baskom.masakbanyak;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bartoszlipinski.constraint.StaggeredAnimationGroup;

public class PreLoginRegisterActivity extends AppCompatActivity {
    private StaggeredAnimationGroup gLoginForm;
    private StaggeredAnimationGroup gLoginDaftarBtn;
    private Button mBtnPreLogin;
    private Button mBtnPreDaftar;
    private Button mBtnLogin;
    private int hideFlag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_login_register);
        gLoginForm = findViewById(R.id.groupLoginForm);
        gLoginDaftarBtn = findViewById(R.id.groupLoginRegisterBtn);
        mBtnPreLogin = findViewById(R.id.btn_pre_login);
        mBtnPreDaftar = findViewById(R.id.btn_pre_daftar);
        mBtnLogin = findViewById(R.id.button_login);
        gLoginForm.hide();

        mBtnPreLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gLoginForm.show();
                gLoginDaftarBtn.hide();
                hideFlag = 1;
            }
        });

        mBtnPreDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PreLoginRegisterActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PreLoginRegisterActivity.this, MainActivity.class);
                startActivity(intent);
                //finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (hideFlag == 1){
            gLoginForm.hide();
            gLoginDaftarBtn.show(true);
            hideFlag = 0;
        }else{
            super.onBackPressed();
        }
    }
}
