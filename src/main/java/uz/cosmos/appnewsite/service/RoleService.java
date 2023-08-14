package uz.cosmos.appnewsite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.cosmos.appnewsite.entity.Role;
import uz.cosmos.appnewsite.payload.ApiResponse;
import uz.cosmos.appnewsite.payload.RoleDto;
import uz.cosmos.appnewsite.repository.RoleRepository;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public ApiResponse addRole(RoleDto roleDto) {
        if (roleRepository.existsByName(roleDto.getName()))
            return new ApiResponse("Bunday role bor", false);
        Role role = new Role(
                roleDto.getName(),
                roleDto.getPermissionsList(),
                roleDto.getDescription());
        roleRepository.save(role);
        return new ApiResponse("Saqlandi", true);
    }

    public ApiResponse editRole(Long id, RoleDto roleDto) {
        return new ApiResponse("", true);
    }
}
