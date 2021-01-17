package info.henrysson.advent_bonus.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import info.henrysson.advent_bonus.config.AdventBonusConfig;
import info.henrysson.advent_bonus.model.MatCategory;
import info.henrysson.advent_bonus.model.MatProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class MatProductRepoImpl implements MatProductRepo {
    static final String PRODUCTS_URL = "https://mat.se/api/product/listByCategory?categoryId=%s";

    private final AdventBonusConfig config;
    private final ObjectMapper mapper = new ObjectMapper();
    private final String productsPath;

    @Autowired
    public MatProductRepoImpl(AdventBonusConfig config) throws IOException {
        this.config = config;
        this.productsPath = String.format("%s/products", config.getFileRepoDir());
        Files.createDirectories(Paths.get(this.productsPath));
    }

    @Override
    public List<MatProduct> getProducts(int categoryId) throws IOException {
        Path path = Paths.get(String.format("%s/%s.json", productsPath, categoryId));
        if (!path.toFile().exists()) {
            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(String.format(PRODUCTS_URL, categoryId), String.class);
            Files.writeString(path, response);
        }
        return mapper.readValue(path.toFile(), new TypeReference<List<MatProduct>>(){});
    }
}
