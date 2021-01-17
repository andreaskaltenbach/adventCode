package info.henrysson.advent_bonus.service;

import info.henrysson.advent_bonus.model.City;
import info.henrysson.advent_bonus.model.MatCategory;

import java.io.IOException;

public interface MatCategoryService {
    MatCategory getCategoryTree(City city) throws IOException;
}
