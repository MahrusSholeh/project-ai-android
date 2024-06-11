package ma.mobile.sistemrekomendasipusatoleh_olehmalang.api.model;

public class PenilaianModel {
    private String idMatrik;
    private String idOlehOleh;
    private String idKriteria;
    private double nilai;


    // Getter dan Setter
    public String getIdMatrik() {
        return idMatrik;
    }

    public void setIdMatrik(String idMatrik) {
        this.idMatrik = idMatrik;
    }

    public String getIdOlehOleh() {
        return idOlehOleh;
    }

    public void setIdOlehOleh(String idOlehOleh) {
        this.idOlehOleh = idOlehOleh;
    }

    public String getIdKriteria() {
        return idKriteria;
    }

    public void setIdKriteria(String idKriteria) {
        this.idKriteria = idKriteria;
    }

    public double getNilai() {
        return nilai;
    }

    public void setNilai(double nilai) {
        this.nilai = nilai;
    }
}
