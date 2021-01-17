package info.henrysson.advent_bonus.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatProduct {
    private long id;
    private String name;
    private String showPrice;
    private Boolean active;
    private float price;
}
