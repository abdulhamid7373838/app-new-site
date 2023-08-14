package uz.cosmos.appnewsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.cosmos.appnewsite.payload.ApiResponse;
import uz.cosmos.appnewsite.payload.RoleDto;
import uz.cosmos.appnewsite.service.RoleService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/role")
public class RoleController {


    @Autowired
    RoleService roleService;

    @PreAuthorize(value = "hasAuthority('ADD_ROLE')")
    @PostMapping
    public HttpEntity<?> addRole(@Valid @RequestBody RoleDto roleDto) {
        ApiResponse apiResponse = roleService.addRole(roleDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('EDIT_ROLE')")
    @PutMapping("/{id}")
    public HttpEntity<?> editRole(@PathVariable Long id,
                                  @Valid @RequestBody RoleDto roleDto) {
        ApiResponse apiResponse = roleService.editRole(id, roleDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
