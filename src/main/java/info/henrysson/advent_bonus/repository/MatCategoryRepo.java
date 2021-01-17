package info.henrysson.advent_bonus.repository;

import info.henrysson.advent_bonus.model.City;
import info.henrysson.advent_bonus.model.MatCategory;

import java.io.IOException;

public interface MatCategoryRepo {
    MatCategory getCategories(City city) throws IOException;
}
