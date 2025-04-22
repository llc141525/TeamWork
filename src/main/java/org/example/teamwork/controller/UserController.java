package org.example.teamwork.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.teamwork.dto.request.RegisterLoginRequest;
import org.example.teamwork.security.JwtUtil;
import org.example.teamwork.service.UserService;
import org.example.teamwork.utils.ApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ApiResponse<String> register(@Valid @RequestBody RegisterLoginRequest request) {
        userService.register(request.username(), request.password());
        return ApiResponse.success(null);
    }

    @PostMapping("/login")
    public ApiResponse<Void> login(@Valid @RequestBody RegisterLoginRequest request) {
        boolean judge = userService.login(request.username(), request.password());
//       if(judge){
//           jwtUtil.generateToken()
//       }
        return ApiResponse.success(null);
    }
}
