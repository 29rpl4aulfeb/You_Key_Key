package www.smktelkom.example.myapplication.IniBaruTransaksi;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import www.smktelkom.example.myapplication.Menu.Menu;
import www.smktelkom.example.myapplication.databinding.ItemCashierTransactionBinding;

public class ListItemTransactionAdapter extends RecyclerView.Adapter<ListItemTransactionAdapter.ListItemTransactionViewHolder> {

    private ArrayList<Menu> transactionItemList = new ArrayList<>();

    public void setData(List<Menu> data) {
        transactionItemList.clear();
        transactionItemList.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public ListItemTransactionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemCashierTransactionBinding binding = ItemCashierTransactionBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ListItemTransactionViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ListItemTransactionViewHolder holder, int position) {
        Menu item = transactionItemList.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return transactionItemList.size();
    }

    public static class ListItemTransactionViewHolder extends RecyclerView.ViewHolder {

        private final ItemCashierTransactionBinding binding;

        public ListItemTransactionViewHolder(ItemCashierTransactionBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Menu itemListBarang) {
            binding.tvTransactionNamaMenu.setText(itemListBarang.getNamaMenu());
            binding.tvTransactionHargaMenu.setText("Rp " + itemListBarang.getHarga());
        }
    }
}
