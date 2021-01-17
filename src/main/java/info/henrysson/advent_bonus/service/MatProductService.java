package info.henrysson.advent_bonus.service;

import info.henrysson.advent_bonus.model.MatProduct;

import java.io.IOException;
import java.util.List;

public interface MatProductService {
    List<MatProduct> getCategoryProducts(long categoryId) throws IOException;
}
