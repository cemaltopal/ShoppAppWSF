package com.project.controller.user;

import com.project.payload.request.user.CustomerRequest;
import com.project.payload.response.business.ResponseMessage;
import com.project.payload.response.user.CustomerResponse;
import com.project.service.user.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/save")// http://localhost:8080/customer/save + POST + JSON
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<ResponseMessage<CustomerResponse>> saveCustomer(@RequestBody @Valid CustomerRequest customerRequest){
        return ResponseEntity.ok(customerService.saveCustomer(customerRequest));
    }

    @PutMapping("/update/{userId") // http://localhost:8080/customer/update/1  + PUT  + JSON
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ResponseMessage<CustomerResponse> updateCustomerForAdmins(@RequestBody @Valid CustomerRequest customerRequest,
                                                                     @PathVariable Long userId){
        return customerService.updateCustomerForAdmins(customerRequest, userId);
    }
}
