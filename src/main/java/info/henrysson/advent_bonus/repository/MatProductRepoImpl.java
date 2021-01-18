package info.henrysson.advent_bonus.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import info.henrysson.advent_bonus.config.AdventBonusConfig;
import info.henrysson.advent_bonus.model.MatCategory;
import info.henrysson.advent_bonus.model.MatProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class MatProductRepoImpl implements MatProductRepo {
    private final AdventBonusConfig config;
    private final ObjectMapper mapper = new ObjectMapper();
    private final String productsPath;
    private final String productsUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    public MatProductRepoImpl(AdventBonusConfig config) throws IOException {
        this.config = config;
        this.productsPath = String.format("%s/products", config.getFileRepoDir());
        this.productsUrl = config.getUrlProducts() + "?categoryId=%s";
        Files.createDirectories(Paths.get(this.productsPath));
    }

    @Override
    // TODO: Fetches at most 1000 entries? How to get the rest?
    public List<MatProduct> getProducts(long categoryId) throws IOException {
        Path path = Paths.get(String.format("%s/%s.json", productsPath, categoryId));
        if (!path.toFile().exists()) {
            String response = restTemplate.getForObject(String.format(productsUrl, categoryId), String.class);
            Files.writeString(path, response);
        }
        return mapper.readValue(path.toFile(), new TypeReference<List<MatProduct>>(){});
    }
}
