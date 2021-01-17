package info.henrysson.advent_bonus.controller;

import info.henrysson.advent_bonus.model.MatCategory;
import info.henrysson.advent_bonus.service.MatCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DoStuff {
    @Autowired
    private MatCategoryService service;

    @EventListener(ApplicationReadyEvent.class)
    public void DoTheStuff(ApplicationReadyEvent applicationReadyEvent) throws IOException {
        System.out.println("Doing stuff");
        MatCategory m = service.getCategoryTree();
        System.out.println("Stuff done");
    }
}
