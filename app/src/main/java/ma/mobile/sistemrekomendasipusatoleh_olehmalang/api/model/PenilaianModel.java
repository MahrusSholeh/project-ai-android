package ma.mobile.sistemrekomendasipusatoleh_olehmalang.api.model;

public class PenilaianModel {
    private String idTempat;

    private String idKriteria;

    private float nilai;

    public String getIdTempat() {
        return idTempat;
    }

    public void setIdTempat(String idTempat) {
        this.idTempat = idTempat;
    }

    public String getIdKriteria(String selectedKriteriaId) {
        return idKriteria;
    }

    public void setIdKriteria(String idKriteria) {
        this.idKriteria = idKriteria;
    }

    public float getNilai() {
        return nilai;
    }

    public void setNilai(float nilai) {
        this.nilai = nilai;
    }
}
