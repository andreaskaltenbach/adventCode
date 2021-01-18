package info.henrysson.advent_bonus.repository;

import info.henrysson.advent_bonus.config.AdventBonusConfig;
import info.henrysson.advent_bonus.model.MatProduct;
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
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class MatProductRepoTest {
    private static final String productJson = "[{\"id\":91024,\"name\":\"Gummihandske Large\",\"countryOfOrigin\":\"MY\",\"active\":true,\"priceWithoutVat\":13.5600006103515600131004248396493494510650634765625,\"soldCount\":1828}," +
            "{\"id\":5,\"name\":\"Rubiks Kub\",\"countryOfOrigin\":\"SE\",\"active\":true,\"priceWithoutVat\":27.9,\"soldCount\":1700}]";

    @TempDir
    public static File temporaryFolder;

    @Mock
    private AdventBonusConfig config;

    @Test
    public void fetchAndPersistTest() throws IOException {
        when(config.getFileRepoDir()).thenReturn(temporaryFolder.getAbsolutePath());
        Path path = Paths.get(String.format("%s/products/1.json", config.getFileRepoDir()));
        path.getParent().toFile().mkdirs();
        Files.writeString(path, productJson);

        MatProductRepo productRepository = new MatProductRepoImpl(config);
        List<MatProduct> products = productRepository.getProducts(1);
        assert path.toFile().exists();
        assert products.size() == 2;
        assert products.stream().map(MatProduct::getId).reduce(0L, Long::sum) == 91024 + 5;
    }
}