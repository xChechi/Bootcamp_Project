package chechi.nino.bootcamp.initiliazer;

import chechi.nino.bootcamp.entity.user.Role;
import chechi.nino.bootcamp.entity.user.User;
import chechi.nino.bootcamp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserAdminInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        User adminUser = userRepository.findByRole(Role.ADMIN);

        if (adminUser == null) {
            initializeUser();
        }
    }

    private void initializeUser() {

        User user = User.builder()
                .firstName("admin")
                .lastName("admin")
                .email("admin@abv.bg")
                .phoneNumber("+35988723598711")
                .role(Role.ADMIN)
                .password(passwordEncoder.encode("Zzazza11!"))
                .build();

        userRepository.save(user);
    }
}
