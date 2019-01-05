package com.anhariasrilgmail.login;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.List;
import  View.RecyclerViewNilaiorangtua;
import HasilResponse.HasilNilaiOrangtua;
import Response.ResponseNilaiOrangtua;
import Network.ApiServer;
import Network.BaseApiList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NilaiOrangtuaActivity extends AppCompatActivity {
    //Spinner spinMatpel;
    Context mContext;
    String kodeNIs = null;
    RecyclerView recyclerViewOrtu;
    ImageButton btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nilai_orangtua);
        mContext = this;
        recyclerViewOrtu = (RecyclerView) findViewById(R.id.recyclerview_nilai);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            kodeNIs = extras.getString("nis");
            Toast.makeText(NilaiOrangtuaActivity.this, "dari Login:"+kodeNIs, Toast.LENGTH_SHORT).show();


        }
        loadNilai(kodeNIs);


        btnBack = (ImageButton) findViewById(R.id.btn_back_nilai_orang_tua);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
    public void onBackPressed(){
        super.onBackPressed();
    }
    public void loadNilai(String nis){
        BaseApiList apiService = ApiServer.getClient().create(BaseApiList.class);
        Call<ResponseNilaiOrangtua> call = apiService.getreadNilai(nis);
        call.enqueue((new Callback<ResponseNilaiOrangtua>() {
            @Override
            public void onResponse(Call<ResponseNilaiOrangtua> call, Response<ResponseNilaiOrangtua> response) {
                Log.d("TAG", "onResponse: "+response.code());
                if(response.code() == 200){
                    List<HasilNilaiOrangtua> hasilNilaiOrangtua = response.body().getHasil();
                    Log.d("TAG", "onResponse: "+hasilNilaiOrangtua.size());
                    RecyclerViewNilaiorangtua recyclerVieworangtua = new RecyclerViewNilaiorangtua(hasilNilaiOrangtua,kodeNIs, NilaiOrangtuaActivity.this);
                    recyclerViewOrtu.setLayoutManager(new LinearLayoutManager(NilaiOrangtuaActivity.this));
                    recyclerViewOrtu.setItemAnimator(new DefaultItemAnimator());
                    recyclerViewOrtu.setAdapter(recyclerVieworangtua);
                }else{
                    Toast.makeText(mContext,"Gagal",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseNilaiOrangtua> call, Throwable t) {

            }
        }));
    }

}
