package www.smktelkom.example.myapplication;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import www.smktelkom.example.myapplication.IniBaruTransaksi.Checkout;
import www.smktelkom.example.myapplication.IniBaruTransaksi.DetailTransaksiModel;
import www.smktelkom.example.myapplication.Meja.MejaValue;
import www.smktelkom.example.myapplication.Menu.Menu;
import www.smktelkom.example.myapplication.Menu.MenuValue;
import www.smktelkom.example.myapplication.Transaksi.Transaksi;
import www.smktelkom.example.myapplication.Transaksi.TransaksiValue;

public interface userService {

   @POST("login")
   Call<LoginResponse> userLogin(@Body LoginRequest loginRequest);

   @GET("menu")
   Call<MenuValue> getMenu(@Header("Authorization") String authorization);

   @GET("transaksi")
   Call<TransaksiValue> getTransaksi(@Header("Authorization") String authorization);

   @GET("transaksi/{id_transaksi}")
   Call<DetailTransaksiModel> getTransaksiById(
           @Header("Authorization") String authorization,
           @Path("id_transaksi") int id_transaksi);

   @GET("meja")
   Call<MejaValue> getMeja(@Header("Authorization") String authorization);

   @FormUrlEncoded
   @POST("transaksi")
   Call<DetailTransaksiModel> addTransaksi(
           @Header("Authorization") String bearerToken,
           @Field("id_meja") int id_meja,
           @Field("nama_pelanggan") String nama_pelanggan,
           @Field("status") String status,
           @Field("barang1") int barang1,
           @Field("barang2") Integer barang2,
           @Field("barang3") Integer barang3,
           @Field("barang4") Integer barang4,
           @Field("barang5") Integer barang5,
           @Field("barang6") Integer barang6,
           @Field("barang7") Integer barang7,
           @Field("barang8") Integer barang8,
           @Field("barang9") Integer barang9,
           @Field("barang10") Integer barang10
   );

   /* add transaksi */
   @FormUrlEncoded
   @PUT("transaksi/{id_transaksi}")
   Call<DetailTransaksiModel> updateTransaksi(
           @Header("Authorization") String bearerToken,
           @Path("id_transaksi") int id_transaksi,
           @Field("id_meja") int id_meja,
           @Field("nama_pelanggan") String nama_pelanggan,
           @Field("status") String status,
           @Field("barang1") int barang1,
           @Field("barang2") Integer barang2,
           @Field("barang3") Integer barang3,
           @Field("barang4") Integer barang4,
           @Field("barang5") Integer barang5,
           @Field("barang6") Integer barang6,
           @Field("barang7") Integer barang7,
           @Field("barang8") Integer barang8,
           @Field("barang9") Integer barang9,
           @Field("barang10") Integer barang10
   );

   @FormUrlEncoded
   @POST("menu")
   Call<Menu> addMenu(
           @Header("Authorization") String bearerToken,
           @Field("nama_menu") String nama_menu,
           @Field("harga") String harga,
           @Field("jenis") String kategori,
           @Field("deskripsi") String gambar
   );
}
