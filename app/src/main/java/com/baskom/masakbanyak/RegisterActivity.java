package com.baskom.masakbanyak;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AutoCompleteTextView;

import com.bartoszlipinski.constraint.StaggeredAnimationGroup;

public class RegisterActivity extends AppCompatActivity {

    private AutoCompleteTextView mNama;
    private AutoCompleteTextView mEmail;
    private AutoCompleteTextView mNoTelp;
    private AutoCompleteTextView mAlamat;
    private AutoCompleteTextView mKataSandi;
    private AutoCompleteTextView mKonfirmasiSandi;
    private StaggeredAnimationGroup gRegisterForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mNama = findViewById(R.id.et_nama_reg);
        mEmail = findViewById(R.id.et_email_reg);
        mNoTelp = findViewById(R.id.et_phone_reg);
        mAlamat = findViewById(R.id.et_address_reg);
        mKataSandi = findViewById(R.id.et_password_reg);
        mKonfirmasiSandi = findViewById(R.id.et_konfirmasiPassword_reg);
        gRegisterForm = findViewById(R.id.groupDaftarForm);
        gRegisterForm.hide();
        gRegisterForm.postDelayed(new Runnable() {
            @Override
            public void run() {
                gRegisterForm.show();
            }
        },500);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
