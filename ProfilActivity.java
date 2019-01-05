package com.anhariasrilgmail.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import Network.ApiServer;
import Network.BaseApiList;
import Response.ResponseProfilSiswa;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilActivity extends AppCompatActivity {
    TextView tvNis;
    TextView tvNama;
    TextView tvTempat;
    TextView tvTanggal;
    TextView tvJenkel;
    TextView tvAlamat;
    TextView tvAgama;
    TextView tvOrtu;
    TextView tvPekerjaan;
    TextView tvMasuk;
    TextView tvKelas;
    String kodeNIs = null;
    ImageButton btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil2);
        tvNis = (TextView) findViewById(R.id.tv_nis);
        tvNama = (TextView) findViewById(R.id.tv_nama);
        tvTempat = (TextView) findViewById(R.id.tv_tempat);
        tvTanggal = (TextView) findViewById(R.id.tv_tangal);
        tvJenkel = (TextView) findViewById(R.id.tv_jenkel);
        tvAlamat = (TextView) findViewById(R.id.tv_alamat);
        tvAgama = (TextView) findViewById(R.id.tv_agama);
        tvOrtu = (TextView) findViewById(R.id.tv_ortu);
        tvPekerjaan = (TextView) findViewById(R.id.tv_pekerjaan);
        tvMasuk = (TextView) findViewById(R.id.tv_masuk);
        tvKelas = (TextView) findViewById(R.id.tv_kelas);
        btnBack = (ImageButton) findViewById(R.id.btn_back_profil_orang_tua);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            kodeNIs = extras.getString("nis");
            Toast.makeText(ProfilActivity.this, "dari Login:"+kodeNIs, Toast.LENGTH_SHORT).show();

            // and get whatever type user account id is
        }
        profilSiswa(kodeNIs);
    }
    private void profilSiswa(String nis){
        BaseApiList apiservice = ApiServer.getClient().create(BaseApiList.class);
        Call<ResponseProfilSiswa> call = apiservice.getprofilSiswa(nis);
        call.enqueue((new Callback<ResponseProfilSiswa>() {
            @Override
            public void onResponse(Call<ResponseProfilSiswa> call, Response<ResponseProfilSiswa> response) {
                if(response.code() == 200){
                    for (int i = 0; i < response.body().getHasil().size() ; i++) {
                        tvNis.setText(response.body().getHasil().get(i).getNIS());
                        tvNama.setText(response.body().getHasil().get(i).getNama());
                        tvTempat.setText(response.body().getHasil().get(i).getTempLahir());
                        tvTanggal.setText(response.body().getHasil().get(i).getTglLahir());
                        tvJenkel.setText(response.body().getHasil().get(i).getJenkel());
                        tvAlamat.setText(response.body().getHasil().get(i).getAlamat());
                        tvAgama.setText(response.body().getHasil().get(i).getAgama());
                        tvOrtu.setText(response.body().getHasil().get(i).getNmOrtu());
                        tvPekerjaan.setText(response.body().getHasil().get(i).getPekerjaan());
                        tvMasuk.setText(response.body().getHasil().get(i).getTglMasuk());
                        tvKelas.setText(response.body().getHasil().get(i).getKdKelas());
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseProfilSiswa> call, Throwable t) {

            }
        }));
    }
}


