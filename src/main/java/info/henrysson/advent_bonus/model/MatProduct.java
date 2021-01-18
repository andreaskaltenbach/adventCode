package info.henrysson.advent_bonus.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatProduct {
    private long id;
    private String name;
    private Boolean active;
    private double priceWithoutVat;
    private String countryOfOrigin;
    private long soldCount;
    // What does sameContentProductIds and productVarieties mean? Should they be grouped in some way?
}
