package com.project.service.user;

import com.project.entity.concretes.user.UserRole;
import com.project.entity.enums.RoleType;
import com.project.exception.RessourceNotFoundException;
import com.project.payload.messages.ErrorMessages;
import com.project.repository.user.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRoleService {
    private UserRoleRepository userRoleRepository;


    public UserRole getUserRole(RoleType roleType) {
        return userRoleRepository.findByEnumRoleEquals(roleType).orElseThrow(()->
                new RessourceNotFoundException(ErrorMessages.ROLE_NOT_FOUND));
    }
}
