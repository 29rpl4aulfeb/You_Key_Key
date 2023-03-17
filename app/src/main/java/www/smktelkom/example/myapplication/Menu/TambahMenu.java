package www.smktelkom.example.myapplication.Menu;

import static www.smktelkom.example.myapplication.IniBaruTransaksi.MenuRepository.token;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import www.smktelkom.example.myapplication.IniBaruTransaksi.MenuRepository;
import www.smktelkom.example.myapplication.Meja.MejaListAdapter;
import www.smktelkom.example.myapplication.R;
import www.smktelkom.example.myapplication.databinding.ActivityTambahMenuBinding;

public class TambahMenu extends AppCompatActivity {

    private ActivityTambahMenuBinding binding;
//    private final Observer<Menu> selectedMenuObserver = new Observer<Menu>() {
//        @Override
//        public void onChanged(Menu menu) {
//
//        }
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTambahMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupButtonSave();
    }
    private void setupButtonSave() {
        binding.btnSave.setOnClickListener(v -> {
            Menu menuModel = new Menu(
                    binding.edtAddNamaMenu.getText().toString(),
                    binding.edtAddMenuType.getText().toString(),
                    binding.edtAddDescription.getText().toString(),
                    "",
                    binding.edtAddPrice.getText().toString(),
                    0
            );


                MenuRepository.addMenu(token.getValue().toString(), menuModel).observe(this, it -> {
                    if (it != null) {
                        runOnUiThread(() -> {
                            Toast.makeText(getBaseContext(), "Menu Ditambahkan " + it.getNamaMenu(), Toast.LENGTH_SHORT).show();
                         Intent intent = new Intent(TambahMenu.this, MenuListAdapter.class);
                         startActivity(intent);
                         finish();

                        });
//                        getViewLifecycleOwner().getLifecycleScope().launch(Dispatchers.Main, () -> {
//                        });
                    }
                });

        });
    }
}