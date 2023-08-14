package uz.cosmos.appnewsite.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.cosmos.appnewsite.entity.enums.Permissions;
import uz.cosmos.appnewsite.entity.template.AbstractEntity;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Role extends AbstractEntity {

    @Column(unique = true, nullable = false)
    private String name; // ADMIN, USER, BOSHQALAR

    @Enumerated(value = EnumType.STRING)
    @ElementCollection()
    private List<Permissions> permissionsList;

    @Column(length = 600)
    private String description;
}
