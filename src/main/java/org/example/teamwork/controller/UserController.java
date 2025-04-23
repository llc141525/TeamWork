package org.example.teamwork.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.teamwork.dto.request.RegisterLoginRequest;
import org.example.teamwork.dto.response.UserResponse;
import org.example.teamwork.model.User;
import org.example.teamwork.repository.UserRepository;
import org.example.teamwork.security.JwtUtil;
import org.example.teamwork.service.UserService;
import org.example.teamwork.utils.ApiResponse;
import org.example.teamwork.utils.CookieUtil;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @PostMapping("/register")
    public ApiResponse<String> register(@Valid @RequestBody RegisterLoginRequest request) {
        userService.register(request.username(), request.password());
        return ApiResponse.success(null);
    }

    @PostMapping("/login")
    public ApiResponse<UserResponse> login(@Valid @RequestBody RegisterLoginRequest request, HttpServletResponse response) {
        boolean judge = userService.login(request.username(), request.password());
        User user = userRepository.findByUsername(request.username());
        if (judge) {
            String cookie = jwtUtil.generateToken(user.getId());
            CookieUtil.setCookie(response, cookie);
        }
        return ApiResponse.success(UserResponse.builder()
                .username(user.getUsername())
                .avatarUrl(user.getAvatarUrl())
                .build());
    }

    @GetMapping("/logout")
    public ApiResponse<Void> logout(HttpServletResponse response) {
        CookieUtil.deleteCookie(response);
        return ApiResponse.success(null);
    }
}
