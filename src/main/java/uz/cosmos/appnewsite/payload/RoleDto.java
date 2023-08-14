package uz.cosmos.appnewsite.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.cosmos.appnewsite.entity.enums.Permissions;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {

    @NotBlank
    private String name;

    private String description;

    @NotEmpty
    private List<Permissions> permissionsList;
}