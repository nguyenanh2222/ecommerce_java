package com.example.restservice.auth.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.restservice.user.dto.UserDto;
import com.example.restservice.user.entity.RoleEntity;
import com.example.restservice.user.entity.UserEntity;
import com.example.restservice.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserResource {
    @Autowired
    private UserService userService;
    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }
    @PostMapping("/user/save")
    public ResponseEntity<UserEntity> saveUser(@RequestBody UserEntity user){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/role/save").toUriString());
        return ResponseEntity.ok().body(userService.saveUser(user));}
    @PostMapping("/role/save")
    public ResponseEntity<RoleEntity> saveUser(@RequestBody RoleEntity role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/role/save").toUriString());
        return ResponseEntity.ok().body(userService.saveRole(role));}
    @PostMapping("/role/addtouser")
    public ResponseEntity addRoleToUser(@RequestBody RoleToUserForm form){
        userService.addRoleToUser(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().build();}

    @PostMapping("/token/refresh")
    public void refreshToken(
            HttpServletRequest request, HttpServletResponse response,
            @RequestBody String refresh_token
    ) throws IOException {
        if (refresh_token != null) {
            try {
                Base64.Decoder decoder = Base64.getUrlDecoder();
                String[] parts = refresh_token.split("\\."); // split out the "parts" (header, payload and signature)
                String payloadJson = new String(decoder.decode(parts[1]));
                String[] items = payloadJson.split("[{},:]");
                for (int i = 0; i < items.length; i++) {
                    if (items[i].contains("sub")) {
                        UserEntity user = userService.getUser(items[i + 1].substring(1, items[i + 1].length() - 1));
                        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                        stream(user.getRoles().toArray(new RoleEntity[0])).forEach(role -> {
                            authorities.add(new SimpleGrantedAuthority(role.toString()));
                        });
                        Algorithm algorithm = Algorithm.HMAC256("Secret".getBytes());
                        String access_token = JWT.create().withSubject(user.getUsername())
                                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                                .withIssuer(request.getRequestURL().toString())
                                .withClaim("roles", authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                                .sign(algorithm);
                        Map<String, String> tokens = new HashMap<>();
                        tokens.put("access_token", access_token);
                        tokens.put("refresh_token", refresh_token);
                        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
                    }
                }
            } catch (Exception e) {
                response.setHeader("error", e.getMessage());
                response.setStatus(FORBIDDEN.value());
//                    response.sendError(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_messenger", e.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("refresh token missing");
        }
    }
}
    @Data
    class RoleToUserForm{
        private String username;
        private String roleName;
    }

