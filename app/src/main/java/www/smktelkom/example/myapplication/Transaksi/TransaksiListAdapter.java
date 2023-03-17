package www.smktelkom.example.myapplication.Transaksi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.function.Function;

import kotlin.Unit;
import www.smktelkom.example.myapplication.R;

public class TransaksiListAdapter extends RecyclerView.Adapter<TransaksiListAdapter.TransaksiListViewHolder> {
    private List<Transaksi> initransaksiList;
    private Context iniContext;
    private final Function<Integer, Unit> observeSelectedTransaksi;

    public TransaksiListAdapter(Context context, List<Transaksi> transaksiList,  Function<Integer, Unit> observeSelectedTransaksi){
        iniContext = context;
        initransaksiList = transaksiList;
        this.observeSelectedTransaksi = observeSelectedTransaksi;
    }

    @NonNull
    @Override
    public TransaksiListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(iniContext).inflate(R.layout.activity_transaksi_list_adapter, parent, false);
        return new TransaksiListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransaksiListViewHolder holder, int position) {
        Transaksi transaksi = initransaksiList.get(position);
        holder.bind(transaksi);
    }

    @Override
    public int getItemCount() {
        return initransaksiList.size();
    }

    public void setData(List<Transaksi> initransaksiList) {
        this.initransaksiList = initransaksiList;
        notifyDataSetChanged();
    }

    class TransaksiListViewHolder extends RecyclerView.ViewHolder{
        public TextView idTransaksi;
        public TextView idMeja;
        public TextView namaPelangan;
        public TextView status;
        public TextView tgl_transaksi;
        public TransaksiListViewHolder(@NonNull View itemView) {
            super(itemView);

            this.idTransaksi = itemView.findViewById(R.id.tvidTransaksi);
            idMeja = itemView.findViewById(R.id.idMeja);
            namaPelangan =  itemView.findViewById(R.id.namaPelanggan);
            status = itemView.findViewById(R.id.status);
            tgl_transaksi = itemView.findViewById(R.id.tgl_transaksi);
        }

        public void bind(Transaksi transaksiData){
//            if(idTransaksi != null ){
//                Toast.makeText(iniContext, "View is init" + transaksiData.getId_transaksi(), Toast.LENGTH_SHORT).show();
//            }
            idTransaksi.setText("" +transaksiData.getId_transaksi());

            itemView.getRootView().setOnClickListener(view -> observeSelectedTransaksi.apply(transaksiData.getId_transaksi()));
            idMeja.setText("" +transaksiData.getId_meja());
            namaPelangan.setText(transaksiData.getNama_pelanggan());
            status.setText("" + transaksiData.getStatus());
            tgl_transaksi.setText(transaksiData.getTgl_transaksi());


        }
    }

}