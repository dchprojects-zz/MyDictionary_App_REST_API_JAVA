package com.dchprojects.mydictionaryrestapi.repository;

import com.dchprojects.mydictionaryrestapi.domain.entity.role.Role;
import com.dchprojects.mydictionaryrestapi.domain.entity.role.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleName roleName);

}
