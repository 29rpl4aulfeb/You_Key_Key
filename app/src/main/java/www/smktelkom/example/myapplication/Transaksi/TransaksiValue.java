package www.smktelkom.example.myapplication.Transaksi;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TransaksiValue {
        @SerializedName("values")
        private List<Transaksi> value;

        public List<Transaksi> getValue() {
            return value;
        }
}
