package info.henrysson.advent_bonus.repository;

import info.henrysson.advent_bonus.model.MatProduct;

import java.io.IOException;
import java.util.List;

public interface MatProductRepo {
    List<MatProduct> getProducts(int categoryId) throws IOException;
}
