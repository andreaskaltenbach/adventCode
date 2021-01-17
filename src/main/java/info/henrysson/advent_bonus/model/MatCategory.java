package info.henrysson.advent_bonus.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatCategory {
    private long id;
    private String name;
    private List<MatCategory> subCategories = new ArrayList<>();
    private List<String> products;
}
