package com.example.dominantsoftdevelopment.controller.admin;

import com.example.dominantsoftdevelopment.dto.ApiResult;
import com.example.dominantsoftdevelopment.model.Product;
import com.example.dominantsoftdevelopment.model.User;
import com.example.dominantsoftdevelopment.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/all-products")
    public HttpEntity<ApiResult<List<Product>>> allProducts(){
        return ResponseEntity.ok(adminService.allProducts());
    }

    @GetMapping("/all-users")
    public HttpEntity<ApiResult<List<User>>> allUsers(){
        return ResponseEntity.ok(adminService.allUsers());
    }

    @GetMapping("/blocked-users")
    public HttpEntity<ApiResult<List<User>>> blockedUsers(){
        return ResponseEntity.ok(adminService.blockedUsers());
    }

    @GetMapping("/{id}/blocked-accounts")
    public HttpEntity<ApiResult<Boolean>> blockedAccounts(@PathVariable Long id){
        return ResponseEntity.ok(adminService.blockedAccounts(id));
    }

    @DeleteMapping("/{id}/blocked-accounts-delete")
    public HttpEntity<ApiResult<Boolean>> blockedAccountsDelete(@PathVariable Long id){
        return ResponseEntity.ok(adminService.blockedAccountsDelete(id));
    }

    @PostMapping("/{id}/unblock-blocked-users")
    public HttpEntity<ApiResult<Boolean>> unblockBlockedUsers(@PathVariable Long id){
        return ResponseEntity.ok(adminService.unblockBlockedUsers(id));
    }

    @GetMapping("/users-uzbekistan")
    public HttpEntity<ApiResult<List<User>>> usersUzbekistan(){
        return ResponseEntity.ok(adminService.usersUzbekistan());
    }

    @GetMapping("/users-kazakhstan")
    public HttpEntity<ApiResult<List<User>>> usersKazakhstan(){
        return ResponseEntity.ok(adminService.usersKazakhstan());
    }
    @GetMapping("/users-kyrgyzstan")
    public HttpEntity<ApiResult<List<User>>> usersKyrgyzstan(){
        return ResponseEntity.ok(adminService.usersKyrgyzstan());
    }

    @GetMapping("/users-tajikistan")
    public HttpEntity<ApiResult<List<User>>> usersTajikistan(){
        return ResponseEntity.ok(adminService.usersTajikistan());
    }
    @GetMapping("/users-azerbaijan")
    public HttpEntity<ApiResult<List<User>>> usersAzerbaijan(){
        return ResponseEntity.ok(adminService.usersAzerbaijan());
    }

    @GetMapping("/users-russia")
    public HttpEntity<ApiResult<List<User>>> usersRussia(){
        return ResponseEntity.ok(adminService.usersRussia());
    }

    @GetMapping("/users-turkey")
    public HttpEntity<ApiResult<List<User>>> usersTurkey(){
        return ResponseEntity.ok(adminService.usersTurkey());
    }


}
