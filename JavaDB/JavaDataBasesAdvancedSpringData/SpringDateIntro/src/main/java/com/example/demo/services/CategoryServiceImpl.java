package com.example.demo.services;

import com.example.demo.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Set<Category> getRandomCategories() {
        long countId = this.categoryRepository.count();
        Random random = new Random();

        int randomId = random.nextInt((int) countId) + 1;

        Set<Integer> categoriesId = new LinkedHashSet<>();

        for (int i = 0; i < randomId; i++) {
            int id = random.nextInt((int) countId) + 1;

            categoriesId.add(id);
        }

        List<Category> allById = this.categoryRepository.findAllById(categoriesId);

        return new LinkedHashSet<>(allById);
    }
}
