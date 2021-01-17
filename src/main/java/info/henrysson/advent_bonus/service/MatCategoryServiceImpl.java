package info.henrysson.advent_bonus.service;

import info.henrysson.advent_bonus.model.City;
import info.henrysson.advent_bonus.model.MatCategory;
import info.henrysson.advent_bonus.repository.MatCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MatCategoryServiceImpl implements MatCategoryService {
    private final MatCategoryRepo matCategoryRepo;
    private final MatProductService matProductService;

    @Autowired
    public MatCategoryServiceImpl(MatCategoryRepo matCategoryRepo, MatProductService matProductService) {
        this.matCategoryRepo = matCategoryRepo;
        this.matProductService = matProductService;
    }

    public MatCategory getCategoryTree(City city) throws IOException {
        MatCategory mcTree = matCategoryRepo.getCategories(city);
        addProducts(mcTree);
        return mcTree;
    }

    private void addProducts(MatCategory matCategory) throws IOException {
        matCategory.setProducts(matProductService.getCategoryProducts(matCategory.getId()));
        for (MatCategory mc : matCategory.getSubCategories()) {
            addProducts(mc);
        }
    }
}
