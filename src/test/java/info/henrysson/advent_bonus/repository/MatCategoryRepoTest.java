package info.henrysson.advent_bonus.repository;

import info.henrysson.advent_bonus.config.AdventBonusConfig;
import info.henrysson.advent_bonus.model.City;
import info.henrysson.advent_bonus.model.MatCategory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class MatCategoryRepoTest {
    private final String categoryJson = "{\"id\":1,\"name\":\"Mat.se Göteborg\",\"subCategories\":[{\"id\":2,\"name\":\"Bageri\", \"subCategories\":[]}, {\"id\":3,\"name\":\"Sport\", \"subCategories\":[]}]}";

    @TempDir
    public static File temporaryFolder;

    @Mock
    private AdventBonusConfig config;

    @Test
    public void fetchAndPersistTest() throws IOException {
        when(config.getFileRepoDir()).thenReturn(temporaryFolder.getAbsolutePath());
        Path path = Paths.get(String.format("%s/categories/" + City.GOTHENBURG + ".json", config.getFileRepoDir()));
        path.getParent().toFile().mkdirs();
        Files.writeString(path, categoryJson);

        MatCategoryRepo categoryRepo = new MatCategoryRepoImpl(config);
        MatCategory categories = categoryRepo.getCategories(City.GOTHENBURG);
        assert path.toFile().exists();
        assert categories.getId() == 1;
        assert categories.getSubCategories().size() == 2;
    }
}
