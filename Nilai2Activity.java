package com.anhariasrilgmail.login;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import Response.ResponseGetNilai2;
import HasilResponse.HasilMatpel;
import Response.ResponseGetKelas;
import java.util.ArrayList;
import java.util.List;
import HasilResponse.HasilNilaiSiswa2;
import HasilResponse.HasilItem;
import Network.ApiServer;
import Network.BaseApiList;
import Response.ResponseGetMatpel;
import View.RecyclerViewNilai2;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Nilai2Activity extends AppCompatActivity {
    RecyclerView listNilai;
    Spinner spinMatpel;
    Spinner spnKelas;
    Context mContext;
    String selectedKelas;
    String selecedMatpel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nilai2);
        mContext = this;
        listNilai = (RecyclerView) findViewById(R.id.list_nilai_siswa);
        spinMatpel = (Spinner) findViewById(R.id.spinner_mat_pel);
        spnKelas = (Spinner) findViewById(R.id.spinner_kelas);
        spinnerMatpel();
        initSpinerKelas();
        spnKelas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               selectedKelas = adapterView.getItemAtPosition(i).toString();
                loadNilai(selectedKelas);
                Toast.makeText(mContext, "Kelas "+selectedKelas, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinMatpel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selecedMatpel = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(mContext, "Mata Pelajaran " + selecedMatpel , Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    public void loadNilai(String kdKelas ){
        BaseApiList apiService = ApiServer.getClient().create(BaseApiList.class);
        Call<ResponseGetNilai2> call = apiService.getnilaisiswa2(kdKelas);
        call.enqueue(new Callback<ResponseGetNilai2>() {
            @Override
            public void onResponse(Call<ResponseGetNilai2> call, Response<ResponseGetNilai2> response) {
                Log.d("TAG", "onResponse: "+response.code());
                if(response.code() == 200){
                    List<HasilNilaiSiswa2> hasilNilaiSiswa2 = response.body().getHasil();
                    Log.d("TAG", "onResponse: "+hasilNilaiSiswa2.size());
                    RecyclerViewNilai2 recyclerViewNilai = new RecyclerViewNilai2(hasilNilaiSiswa2,spnKelas.getSelectedItem().toString(), Nilai2Activity.this);
                    listNilai.setLayoutManager(new LinearLayoutManager(Nilai2Activity.this));
                    listNilai.setItemAnimator(new DefaultItemAnimator());
                    listNilai.setAdapter(recyclerViewNilai);
                }else{
                    Toast.makeText(mContext,"Gagal",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseGetNilai2> call, Throwable t) {
                Toast.makeText(Nilai2Activity.this,"Error bro "+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void initSpinerKelas(){
        BaseApiList apiService = ApiServer.getClient().create(BaseApiList.class);
        Call<ResponseGetKelas> call = apiService.getreadkelas();
        call.enqueue((new Callback<ResponseGetKelas>() {
            @Override
            public void onResponse(Call<ResponseGetKelas> call, retrofit2.Response<ResponseGetKelas> response) {
                if(response.code() == 200){
                    List<HasilItem> hasilItems = response.body().getHasil();
                    List<String> listspinner = new ArrayList<String>();
                    for (int i = 0; i < response.body().getHasil().size(); i++) {
                        listspinner.add(hasilItems.get(i).getKdKelas());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,listspinner);
                    spnKelas.setAdapter(adapter);
                }else{
                    Toast.makeText(mContext,"Gagal",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseGetKelas> call, Throwable throwable) {

            }
        }));
    }
    private void spinnerMatpel(){
        BaseApiList apiservice = ApiServer.getClient().create(BaseApiList.class);
        Call<ResponseGetMatpel> call = apiservice.getreadMatpel();
        call.enqueue((new Callback<ResponseGetMatpel>() {
            @Override
            public void onResponse(Call<ResponseGetMatpel> call, Response<ResponseGetMatpel> response) {
                if(response.code() == 200){
                    List<HasilMatpel> hasilMatpels = response.body().getHasil();
                    List<String> listSpinner = new ArrayList<String>();
                    for(int i = 0; i < response.body().getHasil().size(); i++){
                        listSpinner.add(hasilMatpels.get(i).getKdMatpel());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,listSpinner);
                    spinMatpel.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<ResponseGetMatpel> call, Throwable t) {

            }
        }));
    }
}
