package uz.cosmos.appnewsite.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotNull(message = "FullName bo'sh bo'lmasin")
    private String fullName;

    @NotNull(message = "UserName bo'sh bo'lmasin")
    private String username;

    @NotNull(message = "Parol bo'sh bo'lmasin")
    private String password;

    @NotNull(message = "Lavozim bo'sh bo'lmasin")
    private Integer lavozimId;
}
