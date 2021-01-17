package info.henrysson.advent_bonus.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AdventBonusConfig {
    @Value("${filerepo.dir}")
    private String prodPath;

    public String getFileRepoDir() {
        return prodPath;
    }
}
