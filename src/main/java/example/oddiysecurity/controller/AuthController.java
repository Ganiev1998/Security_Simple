package example.oddiysecurity.controller;

import example.oddiysecurity.entity.Users;
import example.oddiysecurity.service.SecurityUserService;
import example.oddiysecurity.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final UserService service;
    @PostMapping()
    public Users create(@RequestBody Users users){
        return service.create(users);
    }
    @PostMapping("/sign")
    public String signIn(@RequestBody Users users){
        service.signIn(users);
        return "success";
    }
}

