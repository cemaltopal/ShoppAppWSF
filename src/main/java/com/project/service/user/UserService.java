package com.project.service.user;

import com.project.entity.concretes.user.User;
import com.project.entity.enums.RoleType;
import com.project.exception.RessourceNotFoundException;
import com.project.payload.mappers.UserMapper;
import com.project.payload.messages.ErrorMessages;
import com.project.payload.messages.SuccessMessages;
import com.project.payload.request.user.UserRequest;
import com.project.payload.response.abstracts.BaseUserResponse;
import com.project.payload.response.business.ResponseMessage;
import com.project.payload.response.user.UserResponse;
import com.project.repository.user.UserRepository;
import com.project.service.helper.PageableHelper;
import com.project.service.validator.UniquePropertyValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;
    private final UniquePropertyValidator uniquePropertyValidator;
    private final UserMapper userMapper;
    private final UserRoleService userRoleService;
    private final PasswordEncoder passwordEncoder;
    private final PageableHelper pageableHelper;


    public ResponseMessage<UserResponse> saveUser(UserRequest userRequest, String userRole) {
        
        uniquePropertyValidator.checkDuplicate(userRequest.getUsername(), userRequest.getSsn(), 
                userRequest.getPhoneNumber(), userRequest.getEmail());
        User user = userMapper.mapUserRequestToUser(userRequest);
        
        if (userRole.equalsIgnoreCase(RoleType.ADMIN.name())){
            if (Objects.equals(userRequest.getUsername(), "Admin")){
                user.setBuilt_in(true);
            }
            user.setUserRole(userRoleService.getUserRole(RoleType.ADMIN));
            
        } else if (userRole.equalsIgnoreCase("Customer")) {
            user.setUserRole(userRoleService.getUserRole(RoleType.CUSTOMER));
        } else if (userRole.equalsIgnoreCase("GuestCustomer")) {
            user.setUserRole(userRoleService.getUserRole(RoleType.GUEST_CUSTOMER));
        }else {
            throw new RessourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_USER_USERROLE_MESSAGE, userRole));
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userRepository.save(user);

        return ResponseMessage.<UserResponse>builder()
                .message(SuccessMessages.USER_CREATED)
                .object(userMapper.mapUserToUserResponse(savedUser))
                .build();
    }

    public Page<UserResponse> getUsersByPage(int page, int size, String sort, String type, String userRole) {
        Pageable pageable = pageableHelper.getPageableWithProperties(page, size, sort, type);

        return userRepository.findByUserByRole(userRole, pageable)
                .map(userMapper::mapUserToUserResponse);
    }


    public ResponseMessage<BaseUserResponse> getUserById(Long userId) {
        BaseUserResponse baseUserResponse = null;
        User user = userRepository.findById(userId).orElseThrow(()->
                new RessourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_USER_MESSAGE, userId)));
        if (user.getUserRole().getRoleType()== RoleType.CUSTOMER){
            baseUserResponse = userMapper.mapUserToCustomerResponse(user);
        } else if (user.getUserRole().getRoleType() == RoleType.GUEST_CUSTOMER) {
            baseUserResponse = userMapper.mapUserToGuestCustomerResponse(user);
        }else {
            baseUserResponse = userMapper.mapUserToUserResponse(user);
        }
        return ResponseMessage.<BaseUserResponse>builder()
                .message(SuccessMessages.USER_FOUND)
                .httpStatus(HttpStatus.OK)
                .object(baseUserResponse)
                .build();
    }
}
