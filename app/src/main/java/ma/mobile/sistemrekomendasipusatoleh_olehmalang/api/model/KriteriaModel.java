package ma.mobile.sistemrekomendasipusatoleh_olehmalang.api.model;
public class KriteriaModel {
    private String id_kriteria;
    private String nama_kriteria;
    private String bobot_kriteria;

    // Constructors
    public KriteriaModel(String id_kriteria, String nama_kriteria, String bobot_kriteria) {
        this.id_kriteria = id_kriteria;
        this.nama_kriteria = nama_kriteria;
        this.bobot_kriteria = bobot_kriteria;
    }

    // Getters
    public String getIdKriteria() {
        return id_kriteria;
    }

    public String getNamaKriteria() {
        return nama_kriteria;
    }

    public String getBobotKriteria() {
        return bobot_kriteria;
    }

    // Setters
    public void setIdKriteria(String id_kriteria) {
        this.id_kriteria = id_kriteria;
    }

    public void setNamaKriteria(String nama_kriteria) {
        this.nama_kriteria = nama_kriteria;
    }

    public void setBobotKriteria(String bobot_kriteria) {
        this.bobot_kriteria = bobot_kriteria;
    }
}