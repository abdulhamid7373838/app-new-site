package uz.cosmos.appnewsite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.cosmos.appnewsite.entity.User;
import uz.cosmos.appnewsite.exceptions.ResourceNotFoundException;
import uz.cosmos.appnewsite.payload.ApiResponse;
import uz.cosmos.appnewsite.payload.RegisterDto;
import uz.cosmos.appnewsite.repository.RoleRepository;
import uz.cosmos.appnewsite.repository.UserRepository;
import uz.cosmos.appnewsite.utils.AppConstants;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public ApiResponse registerUser(RegisterDto registerDto) {
        if (!registerDto.getPassword().equals(registerDto.getPrePassword()))
            return new ApiResponse("Parollar mos emas", false);

        if (userRepository.existsByUsername(registerDto.getUsername()))
            return new ApiResponse("Bunday username avval ro'yxatdan o'tgan", false);

        User user = new User(
                registerDto.getFullName(),
                registerDto.getUsername(),
                passwordEncoder.encode(registerDto.getPassword()),
                roleRepository.findByName(AppConstants.USER).orElseThrow(() -> new ResourceNotFoundException("role", "name", AppConstants.USER)),
                true
        );
        userRepository.save(user);
        return new ApiResponse("Muvaffaqiyatli ro'yxatdan o'tdingiz", true);
    }

    public UserDetails loadUserByUsername(String username) {
       return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
