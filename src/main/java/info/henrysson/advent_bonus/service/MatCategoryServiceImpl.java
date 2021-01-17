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

    @Autowired
    public MatCategoryServiceImpl(MatCategoryRepo matCategoryRepo) {
        this.matCategoryRepo = matCategoryRepo;
    }

    public MatCategory getCategoryTree(City city) throws IOException {
        return matCategoryRepo.getCategories(city);
    }
}
