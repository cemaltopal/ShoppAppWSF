package com.project.service.user;

import com.project.entity.concretes.user.User;
import com.project.entity.enums.RoleType;
import com.project.payload.mappers.UserMapper;
import com.project.payload.messages.SuccessMessages;
import com.project.payload.request.user.CustomerRequest;
import com.project.payload.response.business.ResponseMessage;
import com.project.payload.response.user.CustomerResponse;
import com.project.repository.user.UserRepository;
import com.project.repository.user.UserRoleRepository;
import com.project.service.helper.MethodHelper;
import com.project.service.validator.UniquePropertyValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final UserRoleRepository userRoleRepository;
    private final UniquePropertyValidator uniquePropertyValidator;
    private final UserMapper userMapper;
    private final UserRoleService userRoleService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MethodHelper methodHelper;

    public ResponseMessage<CustomerResponse> saveCustomer(CustomerRequest customerRequest) {

        uniquePropertyValidator.checkDuplicate(customerRequest.getUsername(), customerRequest.getSsn(),
                customerRequest.getPhoneNumber(),customerRequest.getEmail());

        User customer = userMapper.mapCustomerRequestToUser(customerRequest);
        customer.setUserRole(userRoleService.getUserRole(RoleType.CUSTOMER));
        customer.setPassword(passwordEncoder.encode(customerRequest.getPassword()));

        User savedCustomer = userRepository.save(customer);
        return ResponseMessage.<CustomerResponse>builder()
                .message(SuccessMessages.CUSTOMER_SAVED)
                .httpStatus(HttpStatus.CREATED)
                .object(userMapper.mapUserToCustomerResponse(savedCustomer))
                .build();
    }

    public ResponseMessage<CustomerResponse> updateCustomerForAdmins(CustomerRequest customerRequest, Long userId) {
        User user = methodHelper.isUserExist(userId);
        methodHelper.checkRole(user, RoleType.CUSTOMER);

        uniquePropertyValidator.checkUniqueProperties(user,customerRequest);

        User updatedCustomer = userMapper.mapCustomerRequestToUpdatedUser(customerRequest, userId);
        updatedCustomer.setPassword(passwordEncoder.encode(customerRequest.getPassword()));
        updatedCustomer.setUserRole(userRoleService.getUserRole(RoleType.CUSTOMER));

        User savedCustomer = userRepository.save(updatedCustomer);

        return ResponseMessage.<CustomerResponse>builder()
                .object(userMapper.mapUserToCustomerResponse(savedCustomer))
                .message(SuccessMessages.CUSTOMER_UPDATE)
                .httpStatus(HttpStatus.OK)
                .build();

    }
}
