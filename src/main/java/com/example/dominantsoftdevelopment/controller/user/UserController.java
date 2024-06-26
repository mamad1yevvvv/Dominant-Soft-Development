package com.example.dominantsoftdevelopment.controller.user;

import com.example.dominantsoftdevelopment.dto.*;
import com.example.dominantsoftdevelopment.model.User;
import com.example.dominantsoftdevelopment.service.user.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userservice;

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN') or @userService.isOwner(#id, principal.username)")
    public ResponseEntity<UserDTO> profile(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(userservice.profile(id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping("/list")
    public HttpEntity<ApiResult<Page<UserDTO>>> getUsers(Pageable pageable, @RequestParam(required = false) String predicate) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userservice.getAll(pageable, predicate));
    }

    @PutMapping("/edit/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN') or @userService.isOwner(#id, principal.username)")
    public ResponseEntity<ApiResult<Boolean>>edit(@PathVariable Long id,
                                         @Valid @RequestBody UserUpdateDTO updateDTO){
        return ResponseEntity.status(HttpStatus.OK).body(userservice.edit(id, updateDTO));
    }

    @PutMapping("/change/email")
    public ResponseEntity<ApiResult<Boolean>>edit(@Valid @RequestBody EmailUpdateDTO updateDTO){
        return ResponseEntity.status(HttpStatus.OK).body(userservice.changeEmail(updateDTO));
    }

    @PostMapping("/forget/password")
    public ResponseEntity<ApiResult<Boolean>> resetPassword(@RequestBody @Valid UserResetPasswordDTO resetPasswordDTO){
        return ResponseEntity.ok(userservice.resetPassword(resetPasswordDTO));
    }

    @MessageMapping("/user.addUser")
    @SendTo("/user/public")
    public User addUSer(@Payload  User user){
        userservice.saveUser(user);
        return user;
    }

    @MessageMapping("/user.disconnectUser")
    @SendTo("/user/public")
    public User disconnect(@Payload User user){
        userservice.disconnect(user);
        return user;
    }


    @GetMapping("/onlines")
    public ResponseEntity<List<User>> findConnectUsers(){
        return ResponseEntity.ok(userservice.findConnectUsers());
    }

}

