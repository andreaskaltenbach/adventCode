package info.henrysson.advent_bonus.service;

import com.fasterxml.jackson.databind.JsonNode;
import info.henrysson.advent_bonus.model.City;
import info.henrysson.advent_bonus.model.MatCategory;
import info.henrysson.advent_bonus.repository.MatCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MatCategoryServiceImpl implements MatCategoryService {
    private MatCategoryRepo matCategoryRepo;

    @Autowired
    public MatCategoryServiceImpl(MatCategoryRepo matCategoryRepo) {
        this.matCategoryRepo = matCategoryRepo;
    }

    public MatCategory getCategoryTree() throws IOException {
        MatCategory mc = matCategoryRepo.getCategories(City.GOTHENBURG);
        return null;
    }
}
