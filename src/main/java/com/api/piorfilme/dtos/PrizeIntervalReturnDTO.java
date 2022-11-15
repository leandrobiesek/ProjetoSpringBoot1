package com.api.piorfilme.dtos;

import java.util.ArrayList;
import java.util.List;

public class PrizeIntervalReturnDTO {

    private List<PrizeIntervalDTO> min;

    private List<PrizeIntervalDTO> max;

    public List<PrizeIntervalDTO> getMin() {
        if (min == null) {
            min = new ArrayList<>();
        }
        return min;
    }

    public void setMin(List<PrizeIntervalDTO> min) {
        this.min = min;
    }

    public List<PrizeIntervalDTO> getMax() {
        if (max == null) {
            max = new ArrayList<>();
        }
        return max;
    }

    public void setMax(List<PrizeIntervalDTO> max) {
        this.max = max;
    }
}
