package www.smktelkom.example.myapplication.Menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import www.smktelkom.example.myapplication.IniBaruTransaksi.MenuRepository;
import www.smktelkom.example.myapplication.R;

public class MenuListAdapter extends RecyclerView.Adapter<MenuListAdapter.ViewHolder> {
    private List<Menu> inimenuList;
    private Context iniContext;
    private final Runnable observeSelectedMenu;

    public MenuListAdapter(Context context, List<Menu> menuList,Runnable observeSelectedMenu){
        iniContext = context;
        inimenuList = menuList;
        this.observeSelectedMenu = observeSelectedMenu;
        }
        
        @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(iniContext).inflate(R.layout.activity_menu_list_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,int position){
        Menu menu = inimenuList.get(position);
        holder.titleTextView.setText(menu.getNamaMenu());
        holder.typeTextView.setText(menu.getJenis());
        holder.descriptionTextView.setText(menu.getDeskripsi());
        holder.priceTextView.setText(menu.getHarga());

        Glide.with(iniContext)
                .load(menu.getGambar())
                .into(holder.iconImage);

        Integer size = MenuRepository.keranjang.getValue() != null ? MenuRepository.keranjang.getValue().size() : null;
        if (size != null) {
            if (size < 10) {
                holder.iconTambah.setOnClickListener(view -> {
                    MenuRepository.addToKeranjang(menu);
                    view.setVisibility(View.GONE);
                    holder.iconKurang.setVisibility(View.VISIBLE);
                    observeSelectedMenu.run();
                });
                holder.iconKurang.setOnClickListener(view -> {
                    MenuRepository.removeFromKeranjang(menu);
                    view.setVisibility(View.GONE);
                    holder.iconTambah.setVisibility(View.VISIBLE);
                    observeSelectedMenu.run();
                });
            } else {
                holder.iconTambah.setVisibility(View.GONE);
                holder.iconKurang.setVisibility(View.GONE);
            }
        }
    }
    @Override
    public int  getItemCount(){
        return inimenuList.size();
    }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public TextView titleTextView;
            public TextView typeTextView;
            public TextView descriptionTextView;
            public TextView priceTextView;
            public ImageView iconImage;
            public ImageButton iconTambah;
            public ImageButton iconKurang;

            public ViewHolder(View itemView) {
                super(itemView);
                iconImage = itemView.findViewById(R.id.iconImage);
                titleTextView = itemView.findViewById(R.id.titleTextView);
                typeTextView = itemView.findViewById(R.id.typeTextView);
                descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
                priceTextView = itemView.findViewById(R.id.priceTextView);
                iconTambah = itemView.findViewById(R.id.iconTambah);
                iconKurang = itemView.findViewById(R.id.iconKurang);
            }
        }
    }


