package info.henrysson.advent_bonus.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import info.henrysson.advent_bonus.config.AdventBonusConfig;
import info.henrysson.advent_bonus.model.City;
import info.henrysson.advent_bonus.model.MatCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class MatCategoryRepoImpl implements MatCategoryRepo {
    private final AdventBonusConfig config;
    private final ObjectMapper mapper = new ObjectMapper();
    private final String categoriesPath;

    @Autowired
    public MatCategoryRepoImpl(AdventBonusConfig config) throws IOException {
        this.config = config;
        this.categoriesPath = String.format("%s/categories", config.getFileRepoDir());
        Files.createDirectories(Paths.get(this.categoriesPath));
    }

    @Override
    public MatCategory getCategories(City city) throws IOException {
        Path path = Paths.get(String.format("%s/%s.json", categoriesPath, city.toString()));
        if (!path.toFile().exists()) {
            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(city.getUrl(), String.class);
            Files.writeString(path, response);
        }
        return mapper.readValue(path.toFile(), MatCategory.class);
    }
}
