package www.smktelkom.example.myapplication.Transaksi;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import kotlin.Unit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import www.smktelkom.example.myapplication.ApiClient;
import www.smktelkom.example.myapplication.IniBaruTransaksi.DetailTransaksiModel;
import www.smktelkom.example.myapplication.IniBaruTransaksi.ListItemTransactionAdapter;
import www.smktelkom.example.myapplication.IniBaruTransaksi.MenuRepository;
import www.smktelkom.example.myapplication.Menu.Menu;
import www.smktelkom.example.myapplication.Menu.MenuListAdapter;
import www.smktelkom.example.myapplication.Menu.MenuValue;
import www.smktelkom.example.myapplication.MenuActivity;
import www.smktelkom.example.myapplication.R;
import www.smktelkom.example.myapplication.databinding.ActivityTransaksiBinding;
import www.smktelkom.example.myapplication.databinding.DialogDetailTransaksiBinding;

public class TransaksiActivity extends AppCompatActivity {
    private RecyclerView recycleTransaksi;
    private TransaksiListAdapter transaksiListAdapter;
    private List<Transaksi> initransaksiList;
    String token;

    ActivityTransaksiBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityTransaksiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent ulala = getIntent();
        token = MenuRepository.token.getValue();//ulala.getStringExtra("lebihGataw");
        Log.d("miaww", "onCreate: "+token);


        recycleTransaksi = findViewById(R.id.recyclerViewTransaksi);
        recycleTransaksi.setHasFixedSize(true);
        recycleTransaksi.setLayoutManager(new LinearLayoutManager(this));
        initransaksiList = new ArrayList<>();

        transaksiListAdapter = new TransaksiListAdapter(TransaksiActivity.this, initransaksiList, idTransaksi -> {
            showDetailTransaksi(idTransaksi);
            return null;
        });
        recycleTransaksi.setAdapter(transaksiListAdapter);

        Log.d("TransaksiActivity", "Setting logging level to verbose");
        Log.d("TransaksiActivity", "Debug logs will now appear in the logcat");
        Log.d("TransaksiActivity", "Attempting to get transaksi list...");

        getTransaksi();
    }
    private void getTransaksi() {
        Call<TransaksiValue> call = ApiClient.getuserService().getTransaksi("Bearer "+token);
        call.enqueue(new Callback<TransaksiValue>() {
            @Override
            public void onResponse(Call<TransaksiValue> call, Response<TransaksiValue> response) {
                if (response.isSuccessful()){
                    TransaksiValue transaksiValue = response.body();
                    Log.d("transaksi value", "onResponse: "+transaksiValue);
                    List<Transaksi> transaction = transaksiValue.getValue();
                    List<Transaksi> transaksiList = new ArrayList<>();

                    for (Transaksi transaksi : transaction){
                        transaksiList.add(transaksi);
                    }
                    initransaksiList.addAll(transaksiList);
                    Collections.reverse(initransaksiList);
                    transaksiListAdapter.setData(initransaksiList);
//                    transaksiListAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<TransaksiValue> call, Throwable t) {
                Log.e(TAG,"Failed to get transaksi list: "+t.getMessage());
                Toast.makeText(TransaksiActivity.this, "Failed to get transaksi list", Toast.LENGTH_SHORT).show();
            }
        });
    }
    int totalHarga = 0;
    private void showDetailTransaksi(int idTransaksi) {
        // Call getDetailTransaksi() and observe the response
        DialogDetailTransaksiBinding dialogBinding =
                DialogDetailTransaksiBinding.inflate(getLayoutInflater(), binding.getRoot(), false);
        AlertDialog.Builder dialog = new AlertDialog.Builder(TransaksiActivity.this);
        MenuRepository.getDetailTransaksi(token, idTransaksi)
                .observe(this, new Observer<DetailTransaksiModel>() {
                    @Override
                    public void onChanged(DetailTransaksiModel detailTransaksi) {
                        // Show the detail transaksi in a dialog
                         totalHarga = 0;
                        //count total harga
                        for (Menu i : detailTransaksi.getBarang()) {
                            totalHarga += Integer.parseInt(i.getHarga());
                        }
                        runOnUiThread(() -> {

                            dialogBinding.tvDialogDetailTransaksiId.setText("ID Meja : " + detailTransaksi.getIdMeja());
                            dialogBinding.tvDialogDetailTransaksiTanggal.setText("Tanggal : ");
                            detailTransaksi.getTglTransaksi().replace("T", " ");
                            dialogBinding.rvListItemTransaksi.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                            dialogBinding.rvListItemTransaksi.setAdapter(new ListItemTransactionAdapter() {{
                                setData(detailTransaksi.getBarang());
                            }});
                            dialogBinding.tvDialogDetailTransaksiStatus.setText("Status : " + detailTransaksi.getStatus());
                            dialogBinding.tvDialogDetailTransaksiTotal.setText("Total Harga : Rp" + totalHarga);
                            dialogBinding.tvDialogDetailTransaksiNamaPelanggan.setText("Atas Nama : " + detailTransaksi.getNamaPelanggan());

                            // if the transaction status is "belum_bayar", show the "selesaikan transaksi" button
                            if (detailTransaksi.getStatus().equals("belum_bayar")) {
                                dialogBinding.btnDialogDetailTransaksiSelesaikan.setVisibility(View.VISIBLE);
                            }

                            dialog.setView(dialogBinding.getRoot())
                                    .create();

                            // Remove the view from its parent before adding it to the dialog
                            ViewGroup parent = (ViewGroup) dialogBinding.getRoot().getParent();
                            if (parent != null) {
                                parent.removeView(dialogBinding.getRoot());
                            }
                                    Toast.makeText(TransaksiActivity.this, "TRANSAKSI IS CLICKED " + detailTransaksi.getNamaPelanggan(), Toast.LENGTH_SHORT).show();

                        dialog.show();
                        });
                    }
                });
    }
    }
