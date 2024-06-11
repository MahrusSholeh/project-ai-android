package ma.mobile.sistemrekomendasipusatoleh_olehmalang.api.model;

import java.util.List;

public class RekomendasiResponse {
    private List<RekomendasiModel> rekomendasiList;

    // Getter
    public List<RekomendasiModel> getRekomendasiList() {

        return rekomendasiList;
    }

    // Setter
    public void setRekomendasiList(List<RekomendasiModel> rekomendasiList) {
        this.rekomendasiList = rekomendasiList;
    }
}

