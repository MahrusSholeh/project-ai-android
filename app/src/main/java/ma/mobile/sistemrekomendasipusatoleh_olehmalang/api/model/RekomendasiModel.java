package ma.mobile.sistemrekomendasipusatoleh_olehmalang.api.model;

public class RekomendasiModel {
    private String idOleholeh;
    private String namaTempat;
    private String lokasi;
    private double preferenceScore;

    // Getters
    public String getIdOleholeh() {
        return idOleholeh;
    }

    public String getNamaTempat() {
        return namaTempat;
    }

    public String getLokasi() {
        return lokasi;
    }

    public double getPreferenceScore() {
        return preferenceScore;
    }

    // Setters
    public void setIdOleholeh(String idOleholeh) {
        this.idOleholeh = idOleholeh;
    }

    public void setNamaTempat(String namaTempat) {
        this.namaTempat = namaTempat;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public void setPreferenceScore(double preferenceScore) {
        this.preferenceScore = preferenceScore;
    }
}

