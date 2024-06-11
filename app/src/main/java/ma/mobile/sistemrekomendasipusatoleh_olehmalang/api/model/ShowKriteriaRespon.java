package ma.mobile.sistemrekomendasipusatoleh_olehmalang.api.model;

import java.util.List;


public class ShowKriteriaRespon {
    private List<KriteriaModel> Data;

    // Getter
    public List<KriteriaModel> getData() {
        return Data;
    }

    // Setter
    public void setData(List<KriteriaModel> Data) {
        this.Data = Data;
    }
}