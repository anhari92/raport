package com.anhariasrilgmail.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import Response.ResponseGetLogin;

import Network.ApiServer;
import Network.BaseApiList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    Button btnLogin;
    Button btnCancel;
    EditText edtUser, edtPass;
    TextView tx1;
    ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progress = new ProgressDialog(this);
        btnLogin = (Button) findViewById(R.id.button_login);
        btnCancel = (Button) findViewById(R.id.button_cancel);
        edtUser = (EditText) findViewById(R.id.text_user);
        edtPass = (EditText) findViewById(R.id.text_password);




        btnLogin.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            if (!edtUser.getText().toString().equals("") &&
                                                    !edtPass.getText().toString().equals("")) {
                                               progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                                progress.setIndeterminate(true);
                                                progress.setMessage("Memuat Data");
                                                progress.show();




                                               final Thread t = new Thread(){
                                                    @Override
                                                    public void run(){
                                                        try {
                                                            sleep(1000);
                                                            login(edtUser.getText().toString(),edtPass.getText().toString());
                                                            edtUser.setText("");
                                                            edtPass.setText("");
                                                            finish();
                                                        } catch (InterruptedException e) {
                                                            e.printStackTrace();
                                                            Log.d("TAG", "run: "+e);
                                                        }


                                                    }
                                                };
                                                    t.start();




                                            } else if(edtUser.getText().toString().length()==0){
                                               edtUser.setError("Username kosong");
                                            } else if(edtPass.getText().toString().length()==0){
                                                edtPass.setError("Password Kosong");
                                            }


                                        }
                                    }


        );

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });

    }
    private void login(final String user, String pass){
        BaseApiList apiservice = ApiServer.getClient().create(BaseApiList.class);
        Call<ResponseGetLogin> call = apiservice.postLogin(user,pass);
        Log.d("TAG", "login: " + call.request());
        call.enqueue((new Callback<ResponseGetLogin>() {
            @Override
            public void onResponse(Call<ResponseGetLogin> call, Response<ResponseGetLogin> response) {

                Log.d("TAG", "login: " + response.code());
                if (response.code() == 200) {
                    if (response.body().getHasil().equals("1")) {
                        Toast.makeText(MainActivity.this, "Login Sebagai Guru",
                                Toast.LENGTH_LONG).show();
                        Intent guru = new Intent(MainActivity.this,GuruActivity.class);
                        guru.putExtra("kodeGuru",user);
                        startActivity(guru);
                    } else  if (response.body().getHasil().equals("2")){
                        Toast.makeText(MainActivity.this, "Login Sebagai Orang Tua",
                                Toast.LENGTH_LONG).show();
                        Intent orangtua = new Intent(MainActivity.this,OrangtuaActivity.class);
                        orangtua.putExtra("nis", user);
                        startActivity(orangtua);
                    } else {
                        Toast.makeText(MainActivity.this, "Username atau Password salah",
                                Toast.LENGTH_LONG).show();
                        edtUser.requestFocus();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Ga bisa Nangkep Respon",
                            Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseGetLogin> call, Throwable t) {
                t.printStackTrace();
                Log.d("TAG", "onFailure: "+ call.toString());
            }
        }));
    }


}
