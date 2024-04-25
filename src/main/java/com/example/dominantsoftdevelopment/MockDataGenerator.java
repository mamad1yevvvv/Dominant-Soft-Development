package com.example.dominantsoftdevelopment;

import com.example.dominantsoftdevelopment.model.Address;
import com.example.dominantsoftdevelopment.model.Category;
import com.example.dominantsoftdevelopment.model.User;
import com.example.dominantsoftdevelopment.model.enums.Country;
import com.example.dominantsoftdevelopment.model.enums.Roles;
import com.example.dominantsoftdevelopment.repository.CategoryRepository;
import com.example.dominantsoftdevelopment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class MockDataGenerator {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public void generate(){
        String[] parentCategories = new String[]{"Electronics", "Apparel", "Home & Garden", "Beauty & Personal Care",
                "Health & Wellness", "Books & Media", "Sports & Outdoors", "Toys & Games", "Automotive", "Food & Beverages"};
        Faker faker = new Faker();


        for(int i = 0; i < 30; i++){
            User user = new User();
            Address address = new Address();
            address.setRegion(faker.country().capital());
            address.setCountry(Country.UZBEKISTAN);
            user.setFirstName(faker.name().firstName());
            user.setLastName(faker.name().lastName());
            user.setEmail(faker.internet().emailAddress());
            user.setPassword(passwordEncoder.encode(faker.internet().password()));
            user.setRoles(Roles.USER);
            user.setPhoneNumber(faker.phoneNumber().phoneNumber());
            user.setAddress(address);

            userRepository.save(user);
        }

        categoryRepository.deleteAll();

        for (int i = 0; i < parentCategories.length; i++) {
            Category category = new Category();
            category.setName(parentCategories[i]);
            category.setParentCategory(null);
            category.setAttachment(null);
            categoryRepository.save(category);
        }

        for (int i = 0; i < 20; i++) {
            Category category = new Category();
            category.setName(faker.commerce().productName());
            category.setParentCategory(categoryRepository
                    .findById(new Random().nextLong(1, parentCategories.length-1))
                    .orElse(category));
            category.setAttachment(null);
            categoryRepository.save(category);
        }
    }
}
