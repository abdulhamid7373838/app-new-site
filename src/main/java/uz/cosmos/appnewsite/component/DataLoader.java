package uz.cosmos.appnewsite.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.cosmos.appnewsite.entity.Role;
import uz.cosmos.appnewsite.entity.User;
import uz.cosmos.appnewsite.entity.enums.Permissions;
import uz.cosmos.appnewsite.repository.RoleRepository;
import uz.cosmos.appnewsite.repository.UserRepository;
import uz.cosmos.appnewsite.utils.AppConstants;

import java.util.Arrays;

import static uz.cosmos.appnewsite.entity.enums.Permissions.*;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${spring.datasource.initialization-mode}")
    private String initialMode;

    @Override
    public void run(String... args) throws Exception {
        if (initialMode.equals("always")) {
            Permissions[] permissions = Permissions.values();

            Role admin = roleRepository.save(new Role(
                    AppConstants.ADMIN,
                    Arrays.asList(permissions),
                    "Sistema egasi"
            ));
            Role user = roleRepository.save(new Role(
                    AppConstants.USER,
                    Arrays.asList(ADD_COMMENT, EDIT_COMMENT, DELETE_MY_COMMENT),
                    "Oddiy foydalanuvchisecurity"
            ));

            userRepository.save(new User(

                    "Admin",
                    "admin",
                    passwordEncoder.encode("admin123"),
                    admin,
                    true
            ));
            userRepository.save(new User(
                    "User",
                    "user",
                    passwordEncoder.encode("user123"),
                    user,
                    true
            ));
        }
    }
}
