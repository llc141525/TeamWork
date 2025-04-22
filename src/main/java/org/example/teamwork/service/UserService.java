package org.example.teamwork.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.teamwork.exception.UserException;
import org.example.teamwork.exception.errors.UserError;
import org.example.teamwork.model.User;
import org.example.teamwork.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    private final UserRepository userRepository;

    public void register(String username, String password) {
        // 用户名必须以小写字符开头. 长度大于4, 小于12. 除了下划线, 大小写字母, 数字, 不可以用其它字符.
        if (!username.matches("[a-z]+[a-zA-Z_]{4,11}")) {
            log.warn("非法用户名{}", username);
            throw new UserException(
                    UserError.INVALID_USER.getCode(),
                    UserError.INVALID_USER.getMessage());
        }

        // 如果已经存在相同的用户名了
        if (userRepository.findByUsername(username) != null)
            throw new UserException(
                    UserError.DUPLICATE_USER.getCode(),
                    UserError.DUPLICATE_USER.getMessage());


        // 密码必须大于8位
        if (password.length() < 8) {
            log.warn("非法密码是: {}", password);
            throw new UserException(
                    UserError.INVALID_PASSWD_LENGTH.getCode(),
                    UserError.INVALID_PASSWD_LENGTH.getMessage());
        }

        String encodePasswd = bCryptPasswordEncoder.encode(password);
        User user = User.builder()
                .username(username)
                .passwd(encodePasswd)
                .build();

        userRepository.save(user);
    }


    public boolean login(String username, String password) {
        // 如果不存在对应的用户名
        if (!userRepository.existsByUsername(username))
            throw new UserException(
                    UserError.INVALID_USER_PASSWORD.getCode(),
                    UserError.INVALID_USER_PASSWORD.getMessage());

        User user = userRepository.findByUsername(username);
        // 如果密码错误
        if (!bCryptPasswordEncoder.matches(password, user.getPasswd())) {
            throw new UserException(
                    UserError.INVALID_USER_PASSWORD.getCode(),
                    UserError.INVALID_USER_PASSWORD.getMessage()
            );
        }

        return true;
    }
}
