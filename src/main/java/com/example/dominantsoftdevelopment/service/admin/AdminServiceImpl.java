package com.example.dominantsoftdevelopment.service.admin;

import com.example.dominantsoftdevelopment.dto.ApiResult;
import com.example.dominantsoftdevelopment.exceptions.RestException;
import com.example.dominantsoftdevelopment.model.Product;
import com.example.dominantsoftdevelopment.model.User;
import com.example.dominantsoftdevelopment.model.enums.Country;
import com.example.dominantsoftdevelopment.repository.ProductRepository;
import com.example.dominantsoftdevelopment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl  implements AdminService{

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    @Override
    public ApiResult<List<Product>> allProducts() {
        List<Product> allProduct = productRepository.findAll();
        ArrayList<Product> products = new ArrayList<>();

        for (Product product : allProduct) {
            if (product.isDeleted()){
                products.add(product);
            }
        }
        return ApiResult.successResponse(allProduct);
    }

    @Override
    public ApiResult<List<User>> allUsers() {
        List<User> userList = userRepository.findAll();
        List<User> users= new ArrayList<>();

        for (User user : userList) {
            if (!user.isDeleted() && user.isAccountNonLocked()){
                users.add(user);
            }
        }

        return ApiResult.successResponse(users);
    }

    @Override
    public ApiResult<List<User>> blockedUsers() {

        List<User> users = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            if (!user.isAccountNonLocked() && !user.isDeleted()){
                users.add(user);
            }
        }

        return ApiResult.successResponse(users);
    }

    @Override
    public ApiResult<Boolean> blockedAccounts(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> RestException.restThrow("User id=" + id + " not found"));

        if (!user.isDeleted()) {
            user.setAccountNonLocked(false);
            userRepository.save(user);
        }else {
            return ApiResult.successResponse(false);
        }

        return ApiResult.successResponse(true);
    }

    @Override
    public ApiResult<Boolean> blockedAccountsDelete(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> RestException.restThrow("User id=" + id + " not found"));

        if (!user.isAccountNonLocked() && !user.isDeleted()){
            user.setDeleted(true);
            userRepository.save(user);
        }else {
            return ApiResult.successResponse(false);
        }

        return ApiResult.successResponse(true);
    }

    @Override
    public ApiResult<Boolean> unblockBlockedUsers(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> RestException.restThrow("User id=" + id + " not found"));

        if (!user.isAccountNonLocked() && !user.isDeleted()){
            user.setAccountNonLocked(true);
            userRepository.save(user);
        }
        return ApiResult.successResponse(true);
    }

    @Override
    public ApiResult<List<User>> usersUzbekistan() {

        List<User> userByAddressCountry = userRepository.findUserByAddress_Country(Country.UZBEKISTAN);

        return ApiResult.successResponse(userByAddressCountry);
    }

    @Override
    public ApiResult<List<User>> usersKazakhstan() {
        List<User> userByAddressCountry = userRepository.findUserByAddress_Country(Country.KAZAKHSTAN);

        return ApiResult.successResponse(userByAddressCountry);
    }

    @Override
    public ApiResult<List<User>> usersKyrgyzstan() {
        List<User> userByAddressCountry = userRepository.findUserByAddress_Country(Country.KYRGYZSTAN);

        return ApiResult.successResponse(userByAddressCountry);
    }

    @Override
    public ApiResult<List<User>> usersTajikistan() {
        List<User> userByAddressCountry = userRepository.findUserByAddress_Country(Country.TAJIKISTAN);

        return ApiResult.successResponse(userByAddressCountry);
    }

    @Override
    public ApiResult<List<User>> usersAzerbaijan() {
        List<User> userByAddressCountry = userRepository.findUserByAddress_Country(Country.Azerbaijan);

        return ApiResult.successResponse(userByAddressCountry);
    }

    @Override
    public ApiResult<List<User>> usersRussia() {
        List<User> userByAddressCountry = userRepository.findUserByAddress_Country(Country.Russia);

        return ApiResult.successResponse(userByAddressCountry);
    }

    @Override
    public ApiResult<List<User>> usersTurkey() {
        List<User> userByAddressCountry = userRepository.findUserByAddress_Country(Country.Turkey);

        return ApiResult.successResponse(userByAddressCountry);
    }
}
