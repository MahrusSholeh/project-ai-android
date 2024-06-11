package ma.mobile.sistemrekomendasipusatoleh_olehmalang.api.model;

import java.util.List;

public class ShowTokoRespones {
    private List<RekomendasiModel> tokoList;

    // Getter
    public List<RekomendasiModel> getTokoList() {
        return tokoList;
    }

    // Setter
    public void setTokoList(List<RekomendasiModel> tokoList) {
        this.tokoList = tokoList;
    }
}

