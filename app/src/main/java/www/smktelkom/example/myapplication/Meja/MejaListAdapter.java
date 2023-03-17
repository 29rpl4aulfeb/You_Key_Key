package www.smktelkom.example.myapplication.Meja;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import www.smktelkom.example.myapplication.R;

public class MejaListAdapter extends RecyclerView.Adapter<MejaListAdapter.MejaViewHolder> {
    private List<Meja> inimejaList;
    private Context iniContext;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_meja_list_adapter);
//    }

    public MejaListAdapter(Context context, List<Meja> mejaList){
        iniContext = context;
        inimejaList = mejaList;
    }
    @NonNull
    @Override
    public MejaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(iniContext).inflate(R.layout.activity_meja_list_adapter, parent, false);
        return new MejaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MejaViewHolder holder, int position) {
        Meja meja = inimejaList.get(position);
        holder.bind(meja);
    }

    @Override
    public int getItemCount() {
        return inimejaList.size();
    }

    public void setData(List<Meja> inimejaList){
        this.inimejaList = inimejaList;
        notifyDataSetChanged();
    }
    public class MejaViewHolder extends RecyclerView.ViewHolder {
        public TextView tvIdMeja;
        public TextView tvNomorMeja;
        public TextView tvAvailable;
        public MejaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIdMeja = itemView.findViewById(R.id.tvIdMeja);
            tvNomorMeja = itemView.findViewById(R.id.tvNomorMeja);
            tvAvailable = itemView.findViewById(R.id.tvAvailable);
        }

        public void bind(Meja mejaData){
            tvIdMeja.setText(""+mejaData.getIdMeja());
            tvNomorMeja.setText(mejaData.getNomorMeja());
            tvAvailable.setText(""+mejaData.getAvailable());
        }
    }
}