package com.project.service;

import com.project.payload.request.authentication.LoginRequest;
import com.project.payload.response.authentication.AuthResponse;
import com.project.repository.user.UserRepository;
import com.project.security.jwt.JwtUtils;
import com.project.security.service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public ResponseEntity<AuthResponse> authenticateUser(LoginRequest loginRequest) {
        //!!! Gelen requestin icinden kullanici adi ve parola bilgisi aliniyor
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        // !!! authenticationManager uzerinden kullaniciyi valide ediyoruz
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        // !!! valide edilen kullanici Context e atiliyor
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // !!! JWT token olusturuluyor
        String token = "Bearer " + jwtUtils.generateJwtToken(authentication);
        // !!! login islemini gerceklestirilen kullaniciya ulasiliyor
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        // !!!  Response olarak login islemini yapan kullaniciyi donecegiz gerekli fieldlar setleniyor

        // !!! GrantedAuthority turundeki role yapisini String turune ceviriliyor
        Set<String> roles = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());

        //!!! bir kullanicinin birden fazla rolu olmayacagi icin ilk indexli elemani aliyoruz
        Optional<String> role = roles.stream().findFirst();

        AuthResponse.AuthResponseBuilder authResponse = AuthResponse.builder();
        authResponse.username(userDetails.getUsername());
        authResponse.token(token.substring(7));
        authResponse.name(userDetails.getName());
        authResponse.ssn(userDetails.getSsn());
        role.ifPresent(authResponse::role);

        return  ResponseEntity.ok(authResponse.build());
    }
}