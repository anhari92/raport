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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;
import View.RecyclerViewNilai;
import java.util.ArrayList;
import java.util.List;
import Response.ResponseGetNilai;
import HasilResponse.HasilNilaiSiswa;
import HasilResponse.HasilMatpel;
import Network.ApiServer;
import Network.BaseApiList;
import HasilResponse.HasilItem;
import Response.ResponseNamaSiswa;
import View.HasilSiswa;
import Response.ResponseInputNilai;
import Response.ResponseGetKelas;
import Response.ResponseGetMatpel;
import View.ResponseGetNama;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import Response.ResponseGetKkm;

public class NilaiActivity extends AppCompatActivity {
    String[] items = {"ulangan1","ulangan2","ulangan3","ulangan4","uts","uas"};
    RecyclerView listNilai;
Spinner spinMatpel;
    String selectedNis;
    String selecedMatpel;
    EditText edtnmSiswa;
    EditText edtKkm;
    Spinner spnNis;
    Spinner spnUjian;
    Spinner spnKelas;
    EditText edtNilai;
    Context mContext;
    Button btnSimpan;
    ImageButton btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nilai);
        mContext = this;
        edtnmSiswa = (EditText) findViewById(R.id.edt_nama);
        spinMatpel = (Spinner) findViewById(R.id.spn_matpel);
        spnNis    = (Spinner) findViewById(R.id.spn_nis);
        spnUjian  = (Spinner) findViewById(R.id.spn_ujian);
        spnKelas  = (Spinner) findViewById(R.id.spn_kelas);
        edtKkm    = (EditText) findViewById(R.id.edt_kkm);
        edtKkm.setEnabled(false);
        edtNilai  = (EditText) findViewById(R.id.edt_nilai);
        btnSimpan = (Button) findViewById(R.id.btn_simpan);
        listNilai = (RecyclerView) findViewById(R.id.list_nilai);
        spinnerMatpel();
        initSpinerKelas();
        btnBack = (ImageButton) findViewById(R.id.btn_back_input_nilai);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        final ArrayAdapter<String> aa = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,items);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnUjian.setAdapter(aa);
        spnUjian.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(mContext, "Tipe Ujian "+aa.getItem(i), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(spnUjian.getSelectedItemPosition() == 0){
                    Toast.makeText(NilaiActivity.this, "Ulangan 1 Tersimpan", Toast.LENGTH_SHORT).show();
                    simpanNilai("1");
                    edtNilai.setText("");
                }else if(spnUjian.getSelectedItemPosition() == 1){
                    Toast.makeText(NilaiActivity.this, "Ulangan 2 Tersimpan", Toast.LENGTH_SHORT).show();
                    simpanNilai("2");
                    edtNilai.setText("");
                }else if(spnUjian.getSelectedItemPosition() == 2){
                    Toast.makeText(NilaiActivity.this, "Ulangan 3 Tersimpan", Toast.LENGTH_SHORT).show();
                    simpanNilai("3");
                    edtNilai.setText("");
                }else if(spnUjian.getSelectedItemPosition() == 3){
                    Toast.makeText(NilaiActivity.this, "Ulangan 4 Tersimpan", Toast.LENGTH_SHORT).show();
                    simpanNilai("4");
                    edtNilai.setText("");
                }else if(spnUjian.getSelectedItemPosition() == 4){
                    Toast.makeText(NilaiActivity.this, "UTS Tersimpan", Toast.LENGTH_SHORT).show();
                    simpanNilai("5");
                    edtNilai.setText("");
                }else if(spnUjian.getSelectedItemPosition() == 5){
                    Toast.makeText(NilaiActivity.this, "UAS Tersimpan", Toast.LENGTH_SHORT).show();
                    simpanNilai("6");
                    edtNilai.setText("");
                }

            }
        });
        spnKelas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedKelas = adapterView.getItemAtPosition(i).toString();
                spinnerNis(selectedKelas);
                Toast.makeText(mContext, "Kelas "+selectedKelas, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spnNis.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedNis = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(mContext, "NIS "+selectedNis, Toast.LENGTH_SHORT).show();
                namaSiswa(selectedNis);
                loadNilai(selectedNis,selecedMatpel);

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
                loadNilai(selectedNis,selecedMatpel);
                nilaiKkm(selecedMatpel);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
    public void loadNilai(String nis ,String kdMatpel){
        BaseApiList apiService = ApiServer.getClient().create(BaseApiList.class);
        Call<ResponseGetNilai> call = apiService.getnilaisiswa(nis,kdMatpel);
        call.enqueue(new Callback<ResponseGetNilai>() {
            @Override
            public void onResponse(Call<ResponseGetNilai> call, Response<ResponseGetNilai> response) {
                Log.d("TAG", "onResponse: "+response.code());
                if(response.code() == 200){
                   List<HasilNilaiSiswa> hasilNilaiSiswa = response.body().getHasil();
                    Log.d("TAG", "onResponse: "+hasilNilaiSiswa.size());
                    RecyclerViewNilai recyclerViewNilai = new RecyclerViewNilai(hasilNilaiSiswa,spnNis.getSelectedItem().toString(),spinMatpel.getSelectedItem().toString(), NilaiActivity.this);
                    listNilai.setLayoutManager(new LinearLayoutManager(NilaiActivity.this));
                    listNilai.setItemAnimator(new DefaultItemAnimator());
                    listNilai.setAdapter(recyclerViewNilai);
                }else{
                    Toast.makeText(mContext,"Gagal",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseGetNilai> call, Throwable t) {
                Toast.makeText(NilaiActivity.this,"Error bro "+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void namaSiswa(String nis){
        BaseApiList apiservice = ApiServer.getClient().create(BaseApiList.class);
        Call<ResponseNamaSiswa> call = apiservice.postNamasiswa(nis);
        call.enqueue((new Callback<ResponseNamaSiswa>() {
            @Override
            public void onResponse(Call<ResponseNamaSiswa> call, Response<ResponseNamaSiswa> response) {
                edtnmSiswa.setText(response.body().getHasil().get(0).getNama());
            }

            @Override
            public void onFailure(Call<ResponseNamaSiswa> call, Throwable t) {

            }
        }));
    }
    private void nilaiKkm(String kodeMatpel){
        BaseApiList apiservice = ApiServer.getClient().create(BaseApiList.class);
        Call<ResponseGetKkm> call = apiservice.getKkm(kodeMatpel);
        call.enqueue((new Callback<ResponseGetKkm>() {
            @Override
            public void onResponse(Call<ResponseGetKkm> call, Response<ResponseGetKkm> response) {
                edtKkm.setText(response.body().getHasil().get(0).getKkm());

            }

            @Override
            public void onFailure(Call<ResponseGetKkm> call, Throwable t) {

            }
        }));
    }
    private  void simpanNilai(String ujian){
        int kkm = Integer.parseInt(String.valueOf(edtKkm.getText()));
        int nilai = Integer.parseInt(String.valueOf(edtNilai.getText()));
        String spinerNis = (String) spnNis.getSelectedItem();
        String spinnerMatpel = (String) spinMatpel.getSelectedItem();
        String namaSiswa = edtnmSiswa.getText().toString();
        String spinnerUjian = (String)spnUjian.getSelectedItem();
        String spinnerKelas = (String)spnKelas.getSelectedItem();
        BaseApiList apiservice = ApiServer.getClient().create(BaseApiList.class);
        Call<ResponseInputNilai> call = apiservice.postnilaiSiswa(spinnerMatpel,kkm,spinerNis,spinnerKelas,namaSiswa,ujian,nilai);
        call.enqueue((new Callback<ResponseInputNilai>() {
            @Override
            public void onResponse(Call<ResponseInputNilai> call, Response<ResponseInputNilai> response) {
                if(response.code() == 200){
                    Log.d("TAG", "onResponse: " +response.body().getSuccess());
                    Log.d("TAG", "onResponse: " +response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseInputNilai> call, Throwable t) {

            }
        }));
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
    private void spinnerNis(String kodeKelas){
        BaseApiList apiservice = ApiServer.getClient().create(BaseApiList.class);
        Call<ResponseGetNama> call = apiservice.getreadsiswa(kodeKelas);
        call.enqueue((new Callback<ResponseGetNama>() {
            @Override
            public void onResponse(Call<ResponseGetNama> call, Response<ResponseGetNama> response) {
                if(response.code() == 200){
                    List<HasilSiswa> hasilSiswa = response.body().getHasil();
                    List<String> listSpinner = new ArrayList<String>();
                    for(int i = 0; i < response.body().getHasil().size(); i++){
                        listSpinner.add(hasilSiswa.get(i).getNIS());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,listSpinner);
                    spnNis.setAdapter(adapter);
                }
            }
            @Override
            public void onFailure(Call<ResponseGetNama> call, Throwable t) {

            }


        }));
    }
}
