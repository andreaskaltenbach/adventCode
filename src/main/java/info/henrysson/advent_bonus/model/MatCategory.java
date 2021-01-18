package info.henrysson.advent_bonus.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Math.min;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatCategory {

    private long id;
    private String name;
    private List<MatCategory> subCategories = new ArrayList<>();
    private List<MatProduct> products;

    public Map<String, Integer> getNumberOfProducts() {
        Map<String, Integer> prodCount = new HashMap<>();
        prodCount.put(name, products.size());
        for (MatCategory mc : subCategories) {
            prodCount.putAll(mc.getNumberOfProducts());
        }
        return prodCount;
    }

    // Använd en databas nästa gång...
    // Vad är bestselling? Antal, antal * pris, antal * (pris - cost)?
    // Varför blir det samma oavsett inclSubCategories????
    public Map<String, Map<String, Long>> getBestSellingProducts(boolean inclSubCategories) {
        Map<String, Map<String, Long>> topProducts = new HashMap<>();
        for (MatCategory mc : subCategories) {
            topProducts.putAll(mc.getBestSellingProducts(inclSubCategories));
        }
        Map<String, Long> categoryProducts = new HashMap<>();
        for (MatProduct mp : products) {
            categoryProducts.put(mp.getName(), mp.getSoldCount());
        }
        if (inclSubCategories) {
            for (String key : topProducts.keySet()) {
                categoryProducts.putAll(topProducts.get(key));
            }
        }
        List<NameValuePair> viableProducts = new ArrayList<>();
        for (String key : categoryProducts.keySet()) {
            viableProducts.add(new NameValuePair(key, categoryProducts.get(key)));
        }
        List<NameValuePair> top5prods = viableProducts.stream().sorted(new Comparator<NameValuePair>() {
            @Override
            public int compare(NameValuePair o1, NameValuePair o2) {
                return Long.valueOf(o2.getValue()).compareTo(o1.getValue());  // Reverse order wanted
            }
        }).collect(Collectors.toList()).subList(0, min(5, viableProducts.size()));
        Map<String, Long> top5Map = new HashMap<>();
        for (NameValuePair nvp : top5prods) {
            top5Map.put(nvp.getProduct(), nvp.getValue());
        }
        topProducts.put(name, top5Map);
        return topProducts;
    }

    public Map<String, Count> getSwedishProducts(boolean inclSubCategories) {
        long partCount = products.stream().filter(p -> "SE".equals(p.getCountryOfOrigin())).count();
        long totCount = products.size();
        Map<String, Count> seCounts = new HashMap<String, Count>();
        for (MatCategory mc : subCategories) {
            seCounts.putAll(mc.getSwedishProducts(inclSubCategories));
        }
        partCount += inclSubCategories ? seCounts.values().stream().map(Count::getPartCount).reduce(0L, Long::sum) : 0;
        totCount += inclSubCategories ? seCounts.values().stream().map(Count::getTotCount).reduce(0L, Long::sum) : 0;
        seCounts.put(name, new Count(totCount, partCount));
        return seCounts;
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

    public class Count {
        @Getter
        long totCount;
        @Getter
        long partCount;

        public Count(long totCount, long partCount) {
            this.totCount = totCount;
            this.partCount = partCount;
        }
    }
}
