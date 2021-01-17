package info.henrysson.advent_bonus.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import info.henrysson.advent_bonus.service.MatProductService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatCategory {
    @Autowired
    private static MatProductService matProductService;

    private long id;
    private String name;
    private List<MatCategory> subCategories = new ArrayList<>();
    private List<MatProduct> products;
}
