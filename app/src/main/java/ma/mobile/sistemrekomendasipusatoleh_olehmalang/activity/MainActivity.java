package ma.mobile.sistemrekomendasipusatoleh_olehmalang.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import ma.mobile.sistemrekomendasipusatoleh_olehmalang.R;
import ma.mobile.sistemrekomendasipusatoleh_olehmalang.api.ApiClient;
import ma.mobile.sistemrekomendasipusatoleh_olehmalang.api.ApiService;
import ma.mobile.sistemrekomendasipusatoleh_olehmalang.api.model.TokoModel;
import retrofit2.Call;
import retrofit2.Callback;

import java.util.ArrayList;
import java.util.List;

import ma.mobile.sistemrekomendasipusatoleh_olehmalang.adapter.RecomendationAdapter;
import ma.mobile.sistemrekomendasipusatoleh_olehmalang.adapter.ViewPagerAdapter;
import ma.mobile.sistemrekomendasipusatoleh_olehmalang.api.model.KriteriaModel;
import ma.mobile.sistemrekomendasipusatoleh_olehmalang.api.model.PenilaianModel;
import ma.mobile.sistemrekomendasipusatoleh_olehmalang.api.model.RekomendasiModel;
import ma.mobile.sistemrekomendasipusatoleh_olehmalang.api.model.RekomendasiResponse;
import ma.mobile.sistemrekomendasipusatoleh_olehmalang.api.model.ShowKriteriaRespon;
import ma.mobile.sistemrekomendasipusatoleh_olehmalang.api.model.ShowTokoRespones;
import retrofit2.Response;

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
        private EditText namaTempatInput, lokasiInput;
        private Button submitButton;
        private ApiService apiService;

        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_tempat, container, false);
            namaTempatInput = view.findViewById(R.id.etNamaTempat);
            lokasiInput = view.findViewById(R.id.etLokasi);
            submitButton = view.findViewById(R.id.btSimpan);

            apiService = ApiClient.getClient().create(ApiService.class);

            submitButton.setOnClickListener(v -> {
                String namaTempat = namaTempatInput.getText().toString();
                String lokasi = lokasiInput.getText().toString();

                if (namaTempat.isEmpty() || lokasi.isEmpty()) {
                    Toast.makeText(getContext(), "Please fill in both fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                TokoModel tokoModel = new TokoModel();
                tokoModel.setNama_tempat(namaTempat);
                tokoModel.setLokasi(lokasi);

                apiService.addToko(tokoModel).enqueue(new Callback<TokoModel>() {
                    @Override
                    public void onResponse(Call<TokoModel> call, retrofit2.Response<TokoModel> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(getContext(), "Store Added Successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Failed to Add Store", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<TokoModel> call, @NonNull Throwable t) {
                        Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            });

            return view;
        }
    }

    public static class addValue extends Fragment {
        private Spinner Idoleholeh, IdKriteria;
        private RatingBar nilai;
        private Button submitButton;
        private ApiService apiService;
        private List<TokoModel> tokoList = new ArrayList<>();
        private List<KriteriaModel> kriteriaList = new ArrayList<>();

        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_penilaian, container, false);
            Idoleholeh = view.findViewById(R.id.id_toko);
            IdKriteria = view.findViewById(R.id.id_kriteria);
            nilai = view.findViewById(R.id.nilai);
            submitButton = view.findViewById(R.id.submit_button);

            apiService = ApiClient.getClient().create(ApiService.class);

            fetchToko();
            fetchKriteria();

            submitButton.setOnClickListener(v -> {
                int selectedTokoPosition = Idoleholeh.getSelectedItemPosition();
                int selectedKriteriaPosition = IdKriteria.getSelectedItemPosition();

                if (selectedTokoPosition != -1 && !tokoList.isEmpty() && selectedKriteriaPosition != -1 && !kriteriaList.isEmpty()) {
                    String selectedTokoId = String.valueOf(tokoList.get(selectedTokoPosition).getId_oleholeh());
                    String selectedKriteriaId = kriteriaList.get(selectedKriteriaPosition).getIdKriteria();

                    PenilaianModel penilaianModel = new PenilaianModel();
                    penilaianModel.setIdTempat(selectedTokoId);
                    penilaianModel.setIdKriteria(selectedKriteriaId);
                    penilaianModel.setNilai(nilai.getRating());

                    apiService.addRating(penilaianModel).enqueue(new Callback<PenilaianModel>() {
                        @Override
                        public void onResponse(Call<PenilaianModel> call, retrofit2.Response<PenilaianModel> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(getContext(), "Rating Submitted Successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getContext(), "Failed to Submit Rating: " + response.message(), Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onFailure(Call<PenilaianModel> call, Throwable t) {
                            Toast.makeText(getContext(), "Error Submitting Rating: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });

            return view;
        }

        private void fetchToko() {
            apiService.getToko().enqueue(new Callback<ShowTokoRespones>() {
                @Override
                public void onResponse(Call<ShowTokoRespones> call, Response<ShowTokoRespones> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        ShowTokoRespones tokoResponse = response.body();
                        List<TokoModel> tokoList = tokoResponse.getData(); // Assuming there's a method to get the list of TokoModel from the ShowTokoResponse
                        List<String> nameToko = new ArrayList<>();
                        for (TokoModel toko : tokoList) {
                            nameToko.add(toko.getNama_tempat());
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, nameToko);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        Idoleholeh.setAdapter(adapter);
                        Log.d("fetchToko", "Toko fetched successfully.");
                    } else {
                        String errorMessage = "Failed to fetch toko: " + response.message();
                        Log.e("fetchToko", errorMessage);
                        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ShowTokoRespones> call, Throwable t) {
                    String errorMessage = "Error fetching toko: " + t.getMessage();
                    Log.e("fetchToko", errorMessage);
                    Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
                }
            });
        }

        private void fetchKriteria() {
            apiService.getKriteria().enqueue(new Callback<ShowKriteriaRespon>() {
                @Override
                public void onResponse(Call<ShowKriteriaRespon> call, Response<ShowKriteriaRespon> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        ShowKriteriaRespon kriteriaResponse = response.body();
                        List<KriteriaModel> kriteriaList = kriteriaResponse.getData();
                        List<String> nameKriteria = new ArrayList<>();
                        for (KriteriaModel kriteria : kriteriaList) {
                            nameKriteria.add(kriteria.getNamaKriteria());
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, nameKriteria);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        IdKriteria.setAdapter(adapter);
                        Log.d("fetchKriteria", "Kriteria fetched successfully.");
                    } else {
                        String errorMessage = "Failed to fetch kriteria: " + response.message();
                        Log.e("fetchKriteria", errorMessage);
                        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ShowKriteriaRespon> call, Throwable t) {
                    String errorMessage = "Error fetching kriteria: " + t.getMessage();
                    Log.e("fetchKriteria", errorMessage);
                    Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
                }
            });
        }


    }

    public static class RecommendationsFragmentOleh extends Fragment {
                private RecyclerView recyclerView;
                private ApiService apiService;

                @Override
                public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
                    View view = inflater.inflate(R.layout.fragment_data, container, false);
                    recyclerView = view.findViewById(R.id.recyclerView); // Initialize RecyclerView
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext())); // Set layout manager
                    apiService = ApiClient.getClient().create(ApiService.class);
                    fetchData(); // Fetch data from API
                    return view;
                }

                private void fetchData() {
                    apiService.getData().enqueue(new Callback<RekomendasiResponse>() {
                        @Override
                        public void onResponse(Call<RekomendasiResponse> call, retrofit2.Response<RekomendasiResponse> response) {
                            if (response.isSuccessful() && response.body() != null) {
                                List<RekomendasiModel> recommendations = response.body().getRekomendasiList();
                                RecomendationAdapter adapter = new RecomendationAdapter(recommendations);
                                recyclerView.setAdapter(adapter); // Set adapter to RecyclerView
                            } else {
                                Toast.makeText(getContext(), "Failed to retrieve data: " + response.message(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<RekomendasiResponse> call, Throwable t) {
                            Toast.makeText(getContext(), "Error retrieving data: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }

    }}



