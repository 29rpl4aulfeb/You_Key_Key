package www.smktelkom.example.myapplication.IniBaruTransaksi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import www.smktelkom.example.myapplication.ApiClient;
import www.smktelkom.example.myapplication.MainActivity;
import www.smktelkom.example.myapplication.Meja.Meja;
import www.smktelkom.example.myapplication.Meja.MejaValue;
import www.smktelkom.example.myapplication.Menu.Menu;
import www.smktelkom.example.myapplication.R;
import www.smktelkom.example.myapplication.databinding.ActivityCheckoutBinding;

public class Checkout extends AppCompatActivity {

    private ActivityCheckoutBinding binding;
    private  ListItemTransactionAdapter rvItemTransactionAdapter;
    private int totalHarga = 0;
    private  int selectedMejaId = -0;
    String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCheckoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent ulanyanya = getIntent();
        token = MenuRepository.token.getValue();
//        token = ulanyanya.getStringExtra("YaGataw");
        Log.d("miaww", "onCreate: "+token);

        rvItemTransactionAdapter = new ListItemTransactionAdapter();

        MenuRepository.keranjang.observe(this, it ->{
            rvItemTransactionAdapter.setData(it);

            totalHarga = 0;
            for (Menu menu: it) {
                totalHarga += Integer.valueOf(menu.getHarga());
            }
            runOnUiThread(() -> {
                binding.tvTotal.setText("RP. " + totalHarga);
            });
        });

        setupRvItem();
        setupSpinnerMeja();
        validateCheckout();
        setupButtonCheckout();
    }

    private void setupRvItem(){
        binding.rvItemKeranjang.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        binding.rvItemKeranjang.setAdapter(rvItemTransactionAdapter);
    }

    public void validateCheckout() {
        //if nama pelanggan kosong disable btn checkout
        //if nama pelanggan tidak kosong enable btn checkout
        binding.edtNamaPelanggan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.btnCheckout.setEnabled(!s.toString().isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private  void setupButtonCheckout(){
        binding.btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTransaksi();
            }
        });
    }


    List<Meja> listMejaAvailable;
    private void setupSpinnerMeja(){
        MenuRepository.listMeja.observe(this, listMeja -> {
            listMejaAvailable = new ArrayList<Meja>(listMeja);
            listMejaAvailable = listMejaAvailable.stream()
                    .filter(meja -> meja != null && meja.getAvailable() == 1)
                    .collect(Collectors.toList());

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(),
                    R.layout.item_spinner_meja,
                    listMejaAvailable.stream()
                            .map(Meja::getNomorMeja)
                            .collect(Collectors.toList())
            );
            binding.edtIndustriUsahaSpinner.setAdapter(adapter);
            binding.edtIndustriUsahaSpinner.setOnItemSelectedListener(
                    new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                            String selection = (String) binding.edtIndustriUsahaSpinner.getSelectedItem();
                            Meja selecedMeja = null;
                            for (Meja meja : listMejaAvailable) {
                                if (meja != null && meja.getNomorMeja().equals(selection)) {
                                    selecedMeja = meja;
                                    break;
                                }
                            }

                            if (selecedMeja != null) {
                                selectedMejaId = selecedMeja.getIdMeja();
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    }
            );
//            adapter.setDropDownViewResource(R.layout.item_spinner_meja);
//            adapter.clear();
//            adapter.addAll(listMejaAvailable.stream().map(Meja::getNomorMeja));
        });
    }
    public void addTransaksi(){
        Call<DetailTransaksiModel> call = ApiClient.getuserService().addTransaksi(
                "Bearer "+token,
                selectedMejaId,
                String.valueOf(binding.edtNamaPelanggan.getText()),
                 "belum_bayar",
                MenuRepository.keranjang.getValue().size() > 0 ? MenuRepository.keranjang.getValue().get(0).getId_menu() : null,
                MenuRepository.keranjang.getValue().size() > 1 ? MenuRepository.keranjang.getValue().get(1).getId_menu() : null,
                MenuRepository.keranjang.getValue().size() > 2 ? MenuRepository.keranjang.getValue().get(2).getId_menu() : null,
                MenuRepository.keranjang.getValue().size() > 3 ? MenuRepository.keranjang.getValue().get(3).getId_menu() : null,
                MenuRepository.keranjang.getValue().size() > 4 ? MenuRepository.keranjang.getValue().get(4).getId_menu() : null,
                MenuRepository.keranjang.getValue().size() > 5 ? MenuRepository.keranjang.getValue().get(5).getId_menu() : null,
                MenuRepository.keranjang.getValue().size() > 6 ? MenuRepository.keranjang.getValue().get(6).getId_menu() : null,
                MenuRepository.keranjang.getValue().size() > 7 ? MenuRepository.keranjang.getValue().get(7).getId_menu() : null,
                MenuRepository.keranjang.getValue().size() > 8 ? MenuRepository.keranjang.getValue().get(8).getId_menu() : null,
                MenuRepository.keranjang.getValue().size() > 9 ? MenuRepository.keranjang.getValue().get(9).getId_menu() : null
                );
        call.enqueue(new Callback<DetailTransaksiModel>() {
            @Override
            public void onResponse(Call<DetailTransaksiModel> call, Response<DetailTransaksiModel> response) {
                if(response.isSuccessful()){
                    Intent intent = new Intent(Checkout.this, MainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<DetailTransaksiModel> call, Throwable t) {

            }
        });
    }


}