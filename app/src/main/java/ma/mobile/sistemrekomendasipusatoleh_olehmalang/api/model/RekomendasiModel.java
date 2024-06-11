package ma.mobile.sistemrekomendasipusatoleh_olehmalang.api.model;

public class RekomendasiModel {
    private String id_oleholeh;
    private String nama_tempat;
    private String lokasi;
    private double preference_score;

    // Getters
    public String getIdOleholeh() {
        return id_oleholeh;
    }

    public String getNamaTempat() {
        return nama_tempat;
    }

    public String getLokasi() {
        return lokasi;
    }

    public double getPreferenceScore() {
        return preference_score;
    }

    // Setters
    public void setIdOleholeh(String id_oleholeh) {
        this.id_oleholeh = id_oleholeh;
    }

    public void setNamaTempat(String nama_tempat) {
        this.nama_tempat = nama_tempat;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public void setPreferenceScore(double preference_score) {
        this.preference_score = preference_score;
    }
}

