package info.henrysson.advent_bonus.config;

import info.henrysson.advent_bonus.model.City;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AdventBonusConfig {
    @Value("${filerepo.dir}")
    private String prodPath;

    @Value("${url.gothenburg}")
    private String urlGothenburg;

    @Value("${url.products}")
    private String urlProducts;

    public String getFileRepoDir() {
        return prodPath;
    }

    public String getUrl(City city) {
        switch(city) {
            case GOTHENBURG:
                return urlGothenburg;
            default:
                return null;
        }
    }

    public String getUrlProducts() {
        return urlProducts;
    }
}
