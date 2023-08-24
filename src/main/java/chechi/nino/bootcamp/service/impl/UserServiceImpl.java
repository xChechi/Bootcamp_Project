package chechi.nino.bootcamp.service.impl;

import chechi.nino.bootcamp.converter.UserConverter;
import chechi.nino.bootcamp.dto.user.UserRequest;
import chechi.nino.bootcamp.dto.user.UserResponse;
import chechi.nino.bootcamp.dto.user.UserUpdatePasswordRequest;
import chechi.nino.bootcamp.dto.user.UserUpdateRequest;
import chechi.nino.bootcamp.entity.user.Role;
import chechi.nino.bootcamp.entity.user.User;
import chechi.nino.bootcamp.exception.DuplicateEmailException;
import chechi.nino.bootcamp.exception.UserNotFoundException;
import chechi.nino.bootcamp.repository.UserRepository;
import chechi.nino.bootcamp.service.UserService;
import chechi.nino.bootcamp.util.EmailUtils;
import chechi.nino.bootcamp.util.PasswordUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final EmailUtils emailUtils;

    @Override
    public List<UserResponse> findAllUsers() {

        List<User> users = userRepository.findAll();
        List<UserResponse> responses = new ArrayList<>();

        for (User u : users) {
            UserResponse response = userConverter.toUserResponse(u);
            responses.add(response);
        }
        return responses;
    }

    @Override
    public UserResponse findUserById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        return userConverter.toUserResponse(user);
    }

    @Override
    public boolean existByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserResponse createUser(UserRequest request) {

        if (existByEmail(request.getEmail())) {
            throw new DuplicateEmailException(request.getEmail(), "Email already exist");
        }

        User user = userConverter.createUser(request);
        user.setRole(Role.USER);

        User savedUser = userRepository.save(user);
        return userConverter.toUserResponse(savedUser);
    }

    @Override
    public UserResponse updateUser(Integer id, UserUpdateRequest request) {

        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());

        User savedUser = userRepository.save(user);
        return userConverter.toUserResponse(savedUser);
    }

    @Override
    public UserResponse updateUserPassword(Integer id, UserUpdatePasswordRequest request) {

        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));

        user.setPassword(request.getPassword());

        User savedUser = userRepository.save(user);
        return userConverter.toUserResponse(savedUser);
    }

    @Override
    public void resetPasswordByEmail(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        String newPassword = PasswordUtils.generateRandomPassword();
        user.setPassword(newPassword);
        userRepository.save(user);

        //EmailUtils emailUtils = new EmailUtils();
        emailUtils.sendPasswordEmail(user.getEmail(), newPassword);
    }

    /*
    @Override
    public UserResponse findByEmail (String email) {

        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found"));
        return userConverter.toUserResponse(user);
    }

     */

}
