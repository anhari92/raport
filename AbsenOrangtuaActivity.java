package com.anhariasrilgmail.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import Response.ResponseProfilSiswa;
import Response.ResponseJumlahAbsen;
import Response.ResponseGetAbsenAlpha;
import Response.ResponseGetAbsenHadir;
import Response.ResponseGetAbsenIzin;
import Network.ApiServer;
import Network.BaseApiList;
import Response.ResponseGetAbsenSakit;
import Response.ResponseGetNama;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AbsenOrangtuaActivity extends AppCompatActivity {
    TextView tvNama;
    TextView tvNis;
    TextView tvKelas;
    TextView tvKehadiran;
    TextView tvTotal;
    TextView tvIzin;
    TextView tvAlpha;
    TextView tvSakit;
    TextView tvOrtu;
    String kodeNIs = null;
    ImageButton btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absen_orangtua);
        tvNama = (TextView) findViewById(R.id.text_nama);
        tvNis = (TextView) findViewById(R.id.text_nis);
        tvKelas = (TextView) findViewById(R.id.text_kelas);
        tvKehadiran = (TextView) findViewById(R.id.text_hadir);
        tvTotal = (TextView) findViewById(R.id.text_totalhadir);
        tvIzin = (TextView) findViewById(R.id.text_izin);
        tvAlpha = (TextView) findViewById(R.id.text_alpha);
        tvSakit = (TextView) findViewById(R.id.text_sakit);
        tvOrtu = (TextView) findViewById(R.id.tv_nama_ortu);
        btnBack = (ImageButton) findViewById(R.id.btn_back_absen_orang_tua);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            kodeNIs = extras.getString("nis");
            Toast.makeText(AbsenOrangtuaActivity.this, "dari Login:"+kodeNIs, Toast.LENGTH_SHORT).show();

            // and get whatever type user account id is
        }
        namaOrtu(kodeNIs);
        absenHadir(kodeNIs,"0");
        namaHadir(kodeNIs);
        namaSakit(kodeNIs,"1");
        absenIzin(kodeNIs,"2");
        absenAlpha(kodeNIs,"3");
        jumlahAbsen(kodeNIs);
    }
    private  void jumlahAbsen(String nis){
        BaseApiList apiservice = ApiServer.getClient().create(BaseApiList.class);
        Call<ResponseJumlahAbsen> call = apiservice.getreadJumlah(nis);
        call.enqueue((new Callback<ResponseJumlahAbsen>() {
            @Override
            public void onResponse(Call<ResponseJumlahAbsen> call, Response<ResponseJumlahAbsen> response) {
                tvTotal.setText(response.body().getHasil().get(0).getJumlah());

            }

            @Override
            public void onFailure(Call<ResponseJumlahAbsen> call, Throwable t) {

            }
        }));
    }
    private  void namaOrtu(String nis){
        BaseApiList apiservice = ApiServer.getClient().create(BaseApiList.class);
        Call<ResponseProfilSiswa> call = apiservice.getprofilSiswa(nis);
        call.enqueue((new Callback<ResponseProfilSiswa>() {
            @Override
            public void onResponse(Call<ResponseProfilSiswa> call, Response<ResponseProfilSiswa> response) {
                tvOrtu.setText(response.body().getHasil().get(0).getNmOrtu());
            }

            @Override
            public void onFailure(Call<ResponseProfilSiswa> call, Throwable t) {

            }
        }));
    }
    private void absenAlpha(String nis, String absen){
        BaseApiList apiservice = ApiServer.getClient().create(BaseApiList.class);
        Call<ResponseGetAbsenAlpha> call = apiservice.getreadAlpha(nis,absen);
        call.enqueue((new Callback<ResponseGetAbsenAlpha>() {
            @Override
            public void onResponse(Call<ResponseGetAbsenAlpha> call, Response<ResponseGetAbsenAlpha> response) {
                tvAlpha.setText(response.body().getHasil().get(0).getAlpha());
            }

            @Override
            public void onFailure(Call<ResponseGetAbsenAlpha> call, Throwable t) {

            }
        }));
    }
    private void absenIzin(String nis, String absen){
        BaseApiList apiservice = ApiServer.getClient().create(BaseApiList.class);
        Call<ResponseGetAbsenIzin> call = apiservice.getreadIzin(nis,absen);
        call.enqueue((new Callback<ResponseGetAbsenIzin>() {
            @Override
            public void onResponse(Call<ResponseGetAbsenIzin> call, Response<ResponseGetAbsenIzin> response) {
                tvIzin.setText(response.body().getHasil().get(0).getIzin());
            }

            @Override
            public void onFailure(Call<ResponseGetAbsenIzin> call, Throwable t) {

            }
        }));
    }


    private void absenHadir(String nis, String absen) {
        BaseApiList apiservice = ApiServer.getClient().create(BaseApiList.class);
        Call<ResponseGetAbsenHadir> call = apiservice.getreadHadir(nis, absen);
        call.enqueue((new Callback<ResponseGetAbsenHadir>() {
            @Override
            public void onResponse(Call<ResponseGetAbsenHadir> call, Response<ResponseGetAbsenHadir> response) {
                tvKehadiran.setText(response.body().getHasil().get(0).getHadir());
                Log.d("TAG", "onResponse: " + response.body().getHasil().size());
            }

            @Override
            public void onFailure(Call<ResponseGetAbsenHadir> call, Throwable t) {

            }
        }));
    }

    private  void  namaSakit(String NIS , String absen){
        BaseApiList apiservice = ApiServer.getClient().create(BaseApiList.class);
        Call<ResponseGetAbsenSakit> call = apiservice.getreadSakit(NIS, absen);
        call.enqueue((new Callback<ResponseGetAbsenSakit>() {
            @Override
            public void onResponse(Call<ResponseGetAbsenSakit> call, Response<ResponseGetAbsenSakit> response) {
             tvSakit.setText(response.body().getHasil().get(0).getSakit());
            }

            @Override
            public void onFailure(Call<ResponseGetAbsenSakit> call, Throwable t) {

            }
        }));
    }

   private void namaHadir(String nis){
        BaseApiList apiservice = ApiServer.getClient().create(BaseApiList.class);
        Call<ResponseGetNama> call = apiservice.getnamaHadir(nis);
        call.enqueue((new Callback<ResponseGetNama>() {
            @Override
            public void onResponse(Call<ResponseGetNama> call, Response<ResponseGetNama> response) {
                Log.d("TAG", "onResponse: "+response.body().getHasil().size());
                if(response.body().getHasil().size() == 0){
                    Toast.makeText(AbsenOrangtuaActivity.this, "Data Siswa Tidak Ada", Toast.LENGTH_SHORT).show();
                }else {
                    tvNama.setText(response.body().getHasil().get(0).getNama());
                    tvNis.setText(response.body().getHasil().get(0).getNIS());
                    tvKelas.setText(response.body().getHasil().get(0).getKdKelas());
                }
            }

            @Override
            public void onFailure(Call<ResponseGetNama> call, Throwable t) {

            }
        }));

    }
}
