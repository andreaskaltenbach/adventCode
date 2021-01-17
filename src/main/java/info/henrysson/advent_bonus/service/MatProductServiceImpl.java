package info.henrysson.advent_bonus.service;

import info.henrysson.advent_bonus.model.MatProduct;
import info.henrysson.advent_bonus.repository.MatProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class MatProductServiceImpl implements MatProductService {
    private MatProductRepo matProductRepo;

    @Autowired
    public MatProductServiceImpl(MatProductRepo matProductRepo) {
        this.matProductRepo = matProductRepo;
    }

    @Override
    public List<MatProduct> getCategoryProducts(int categoryId) throws IOException {
        return matProductRepo.getProducts(categoryId);
    }
}
