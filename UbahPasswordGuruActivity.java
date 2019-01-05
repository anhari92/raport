package com.anhariasrilgmail.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import Network.ApiServer;
import Network.BaseApiList;
import Response.ResponseUbahPass;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UbahPasswordGuruActivity extends AppCompatActivity {
    EditText passBaru;
    EditText passLama;
    Button btnUbah;
    String kodeGuru;
    ImageButton btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_password_guru);
        passBaru = (EditText) findViewById(R.id.edit_pass_baru);
        passLama = (EditText) findViewById(R.id.edit_password);
        btnUbah  = (Button) findViewById(R.id.btn_ubah_pass);
        btnBack = (ImageButton) findViewById(R.id.btn_back_ubah_password_guru);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            kodeGuru = extras.getString("kodeGuru");
            Toast.makeText(UbahPasswordGuruActivity.this, "dari Login:"+kodeGuru, Toast.LENGTH_SHORT).show();

            // and get whatever type user account id is
        }
        btnUbah.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ubahPass(kodeGuru,passBaru.getText().toString());
                passBaru.setText("");
                passLama.setText("");
            }
        });
    }
    private void ubahPass(String user,String pass){
        BaseApiList apiservice = ApiServer.getClient().create(BaseApiList.class);
        Call<ResponseUbahPass> call = apiservice.getubahPass(user,pass);
        call.enqueue((new Callback<ResponseUbahPass>() {
            @Override
            public void onResponse(Call<ResponseUbahPass> call, Response<ResponseUbahPass> response) {
                if(response.code() == 200){
                    Toast.makeText(UbahPasswordGuruActivity.this, "Berhasil Dirubah", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ResponseUbahPass> call, Throwable t) {

            }
        }));
    }
}
