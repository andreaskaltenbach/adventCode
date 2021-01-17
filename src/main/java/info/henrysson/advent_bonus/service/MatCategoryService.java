package info.henrysson.advent_bonus.service;

import info.henrysson.advent_bonus.model.MatCategory;

import java.io.IOException;

public interface MatCategoryService {
    MatCategory getCategoryTree() throws IOException;
}
