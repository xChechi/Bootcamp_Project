package chechi.nino.bootcamp.controller.api;

import chechi.nino.bootcamp.dto.reservation_room.RoomReservationResponse;
import chechi.nino.bootcamp.dto.user.UserRequest;
import chechi.nino.bootcamp.dto.user.UserResponse;
import chechi.nino.bootcamp.dto.user.UserUpdatePasswordRequest;
import chechi.nino.bootcamp.dto.user.UserUpdateRequest;
import chechi.nino.bootcamp.entity.user.User;
import chechi.nino.bootcamp.service.RoomReservationService;
import chechi.nino.bootcamp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final RoomReservationService roomReservationService;

    @GetMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponse>> getAllUsers () {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAllUsers());
    }

    @GetMapping("/{id}")
    //@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<UserResponse> getUserById (@PathVariable Integer id) {
        UserResponse userResponse = userService.findUserById(id);
        if (userResponse != null) {
            return ResponseEntity.status(HttpStatus.OK).body(userResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser (@Valid @RequestBody UserRequest request) {
        UserResponse createdUser = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);

    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Integer id, @RequestBody UserUpdateRequest request) {
        UserResponse updatedUser = userService.updateUser(id, request);
        if (updatedUser != null) {
            return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<UserResponse> updateUserPassword(@PathVariable Integer id, @Valid @RequestBody UserUpdatePasswordRequest request) {
        UserResponse updatedUser = userService.updateUserPassword(id, request);
        if (updatedUser != null) {
            return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Void> resetPasswordByEmail(@Valid @RequestBody String email) {
        userService.resetPasswordByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{userId}/reservations")
    public ResponseEntity<List<RoomReservationResponse>> getUserReservations(@PathVariable Integer userId) {
        List<RoomReservationResponse> reservations = roomReservationService.searchByUser(userId);
        if (reservations.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(reservations);
        }
    }


}
