package info.henrysson.advent_bonus.controller;

import info.henrysson.advent_bonus.model.City;
import info.henrysson.advent_bonus.model.MatCategory;
import info.henrysson.advent_bonus.model.MatProduct;
import info.henrysson.advent_bonus.service.MatCategoryService;
import info.henrysson.advent_bonus.service.MatProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@Profile("!test")
public class DoStuff {
    private final MatCategoryService matCategoryService;
    private final MatProductService matProductService;

    @Autowired
    public DoStuff(MatCategoryService matCategoryService, MatProductService matProductService) {
        this.matCategoryService = matCategoryService;
        this.matProductService = matProductService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void DoTheStuff(ApplicationReadyEvent applicationReadyEvent) throws IOException {
        System.out.println("Doing stuff");
        MatCategory m = matCategoryService.getCategoryTree(City.GOTHENBURG);
        List<MatProduct> p = matProductService.getCategoryProducts(6830);
        List<MatProduct> mp = m.getProducts();
        System.out.println(p.size());
        System.out.println(mp.size());
        for (MatCategory mc : m.getSubCategories()) {
            System.out.println(mc.getProducts().size());
        }
        System.out.println("Stuff done");
    }
}
