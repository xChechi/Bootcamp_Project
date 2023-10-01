package chechi.nino.bootcamp.controller.api;

import chechi.nino.bootcamp.dto.security.AuthResponse;
import chechi.nino.bootcamp.dto.security.LoginRequest;
import chechi.nino.bootcamp.dto.security.RegisterRequest;
import chechi.nino.bootcamp.dto.user.UserRequest;
import chechi.nino.bootcamp.security.AuthenticationService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerUser (@Valid @RequestBody UserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUser (@Valid @RequestBody LoginRequest request) {

        return ResponseEntity.status(HttpStatus.OK).body(authenticationService.login(request));
    }

}
