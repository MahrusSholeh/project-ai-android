package ma.mobile.sistemrekomendasipusatoleh_olehmalang.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import ma.mobile.sistemrekomendasipusatoleh_olehmalang.R;
import ma.mobile.sistemrekomendasipusatoleh_olehmalang.adapter.RecomendationAdapter;
import ma.mobile.sistemrekomendasipusatoleh_olehmalang.adapter.ViewPagerAdapter;
import ma.mobile.sistemrekomendasipusatoleh_olehmalang.api.ApiClient;
import ma.mobile.sistemrekomendasipusatoleh_olehmalang.api.ApiService;
import ma.mobile.sistemrekomendasipusatoleh_olehmalang.api.model.RekomendasiModel;
import ma.mobile.sistemrekomendasipusatoleh_olehmalang.api.model.RekomendasiResponse;
import ma.mobile.sistemrekomendasipusatoleh_olehmalang.api.model.ShowTokoModel;
import ma.mobile.sistemrekomendasipusatoleh_olehmalang.api.model.ShowTokoRespones;
import okhttp3.Response;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tab);
        viewPager = findViewById(R.id.viewPager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Tambah Toko");
                    break;
                case 1:
                    tab.setText("Tambah Penilaian");
                    break;
                case 2:
                    tab.setText("Rekomendasi");
                    break;
            }
        }).attach();


    }

    public static class addStore extends Fragment {



    }

    public static class addValue extends Fragment {

    }

    public static class RecommendationsFragmentOleh extends Fragment {
            private RecyclerView recyclerView;
            private ApiService apiService;

            @Override
            public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
                View view = inflater.inflate(R.layout.fragment_tempat, container, false);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                apiService = ApiClient.getClient().create(ApiService.class);
                apiService.getData().enqueue(new Callback<RekomendasiResponse>() {
                    @Override
                    public void onResponse(retrofit2.Call<RekomendasiResponse> call, retrofit2.Response<RekomendasiResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            List<RekomendasiModel> recommendations = response.body().getRekomendasiList();
                            RecomendationAdapter adapter = new RecomendationAdapter(recommendations);
                            recyclerView.setAdapter(adapter);
                        } else {
                            Toast.makeText(getContext(), "Failed to retrieve data", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(retrofit2.Call<RekomendasiResponse> call, Throwable t) {
                        Toast.makeText(getContext(), "Error retrieving data", Toast.LENGTH_SHORT).show();
                    }

                });

                return view;
            }

    }
}


