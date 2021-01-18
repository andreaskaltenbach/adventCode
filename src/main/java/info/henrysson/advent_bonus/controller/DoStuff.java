package info.henrysson.advent_bonus.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import info.henrysson.advent_bonus.model.City;
import info.henrysson.advent_bonus.model.MatCategory;
import info.henrysson.advent_bonus.service.MatCategoryService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@Profile("!test")
public class DoStuff {
    private final MatCategoryService matCategoryService;

    @Autowired
    public DoStuff(MatCategoryService matCategoryService) {
        this.matCategoryService = matCategoryService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void DoTheStuff(ApplicationReadyEvent applicationReadyEvent) throws IOException {
        System.out.println("Doing stuff");
        ObjectMapper mapper = new ObjectMapper();
        MatCategory m = matCategoryService.getCategoryTree(City.GOTHENBURG);
        Files.writeString(Paths.get("target/productCount.json"), mapper.writeValueAsString(m.getNumberOfProducts()));
        Files.writeString(Paths.get("target/bestSellingProductsExclSubcats.json"), mapper.writeValueAsString(m.getBestSellingProducts(false)));
        Files.writeString(Paths.get("target/bestSellingProductsInclSubcats.json"), mapper.writeValueAsString(m.getBestSellingProducts(true)));
        Files.writeString(Paths.get("target/swedishProductsExclSubcats.json"), mapper.writeValueAsString(convertSePercent(m, false)));
        Files.writeString(Paths.get("target/swedishProductsInclSubcats.json"), mapper.writeValueAsString(convertSePercent(m, true)));
        System.out.println("Stuff done");
    }

    private List<NameValuePair> convertSePercent(MatCategory m, boolean inclSubCategories) {
        List<NameValuePair> nvp = new ArrayList<>();
        Map<String, MatCategory.Count> seCounts = m.getSwedishProducts(inclSubCategories);
        for (String key : seCounts.keySet()) {
            MatCategory.Count count = seCounts.get(key);
            nvp.add(new NameValuePair(key, (count.getPartCount() * 100) / count.getTotCount()));
        }
        return nvp;
    }

    @Data
    public class NameValuePair {
        private String product;
        private long value;

        public NameValuePair(String product, long value) {
            this.product = product;
            this.value = value;
        }
    }
}
