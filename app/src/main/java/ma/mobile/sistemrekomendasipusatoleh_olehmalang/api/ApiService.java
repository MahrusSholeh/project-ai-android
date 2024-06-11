package ma.mobile.sistemrekomendasipusatoleh_olehmalang.api;



import ma.mobile.sistemrekomendasipusatoleh_olehmalang.api.model.PenilaianModel;
import ma.mobile.sistemrekomendasipusatoleh_olehmalang.api.model.RekomendasiResponse;
import ma.mobile.sistemrekomendasipusatoleh_olehmalang.api.model.ShowKriteriaRespon;
import ma.mobile.sistemrekomendasipusatoleh_olehmalang.api.model.ShowTokoRespones;
import ma.mobile.sistemrekomendasipusatoleh_olehmalang.api.model.TokoModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {
    @POST("input_oleh.php")
    Call<TokoModel> addToko(@Body TokoModel tokoModel);

    @GET("show_all_oleh.php")
    Call<ShowTokoRespones> getToko();

    @GET("show_all_kriteria.php")
    Call<ShowKriteriaRespon> getKriteria();

    @GET("topsis.php")
    Call<RekomendasiResponse> getData();

    @POST("input_penilaian.php")
    Call<PenilaianModel> addRating( @Body PenilaianModel ratingModel);
}
