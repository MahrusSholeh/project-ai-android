package ma.mobile.sistemrekomendasipusatoleh_olehmalang.api.model;

import java.util.List;

public class RekomendasiResponse {
    private List<RekomendasiModel> Data;

    // Getter
    public List<RekomendasiModel> getRekomendasiList() {
        return Data;
    }

    // Setter
    public void setData(List<RekomendasiModel> Data) {
        this.Data = Data;
    }
}

