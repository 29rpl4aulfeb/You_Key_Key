package www.smktelkom.example.myapplication.Meja;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import www.smktelkom.example.myapplication.BasicResponse;
import www.smktelkom.example.myapplication.IniBaruTransaksi.MenuRepository;
import www.smktelkom.example.myapplication.Menu.MenuListAdapter;
import www.smktelkom.example.myapplication.Menu.TambahMenu;
import www.smktelkom.example.myapplication.databinding.ActivityTambahMejaBinding;

public class TambahMejaActivity extends AppCompatActivity {

    ActivityTambahMejaBinding binding;
    String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTambahMejaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        token = MenuRepository.token.getValue();
        observeAddMeja();
    }

    private void observeAddMeja(){
        if(binding != null ){
            binding.btnAddMeja.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (binding.editNamaMeja.getText().length() > 0) {
                        MenuRepository.addMeja(
                                token,
                                binding.editNamaMeja.getText().toString()
                        );
                    }
                    MenuRepository.addMejaResponse.observe(TambahMejaActivity.this, (Observer<BasicResponse>) basicResponse -> {

                        if (basicResponse != null) {
                            runOnUiThread(() -> {
                                Toast.makeText(getBaseContext(), "Meja Ditambahkan", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(TambahMejaActivity.this, MenuListAdapter.class);
                                startActivity(intent);
                                finish();

                            });
//                        getViewLifecycleOwner().getLifecycleScope().launch(Dispatchers.Main, () -> {
//                        });
                        }

                    });
                }
            });
        }
    }


}