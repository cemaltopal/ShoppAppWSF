package com.project.service.helper;

import com.project.entity.concretes.user.User;
import com.project.entity.enums.RoleType;
import com.project.exception.BadRequestException;
import com.project.exception.RessourceNotFoundException;
import com.project.payload.messages.ErrorMessages;
import com.project.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MethodHelper {
    private final UserRepository userRepository;


    public User isUserExist(Long userId) {
        return userRepository.findById(userId).orElseThrow(()->
                new RessourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_USER_MESSAGE, userId)));
    }

    public void checkBuiltIn(User user) {
        if (Boolean.TRUE.equals(user.getBuilt_in())){
            throw new BadRequestException(ErrorMessages.NOT_PERMITTED_METHOD_MESSAGE);
        }
    }

    public void checkRole(User user, RoleType roleType) {
        if (!user.getUserRole().getRoleType().equals(roleType)){
            throw new RessourceNotFoundException(
                    String.format(ErrorMessages.NOT_FOUND_USER_WITH_ROLE_MESSAGE, user.getId(), roleType));
        }
    }
}
