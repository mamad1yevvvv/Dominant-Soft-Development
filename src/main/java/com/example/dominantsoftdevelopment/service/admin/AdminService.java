package com.example.dominantsoftdevelopment.service.admin;

import com.example.dominantsoftdevelopment.dto.ApiResult;
import com.example.dominantsoftdevelopment.model.Product;
import com.example.dominantsoftdevelopment.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {
    ApiResult<List<Product>> allProducts();

    ApiResult<List<User>> allUsers();

    ApiResult<List<User>> blockedUsers();

    ApiResult<Boolean> blockedAccounts(Long id);

    ApiResult<Boolean> blockedAccountsDelete(Long id);

    ApiResult<Boolean> unblockBlockedUsers(Long id);

    ApiResult<List<User>> usersUzbekistan();

    ApiResult<List<User>> usersKazakhstan();

    ApiResult<List<User>> usersKyrgyzstan();

    ApiResult<List<User>> usersTajikistan();

    ApiResult<List<User>> usersAzerbaijan();

    ApiResult<List<User>> usersRussia();

    ApiResult<List<User>> usersTurkey();
}
