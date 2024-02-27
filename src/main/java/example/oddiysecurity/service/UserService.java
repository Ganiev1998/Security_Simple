package example.oddiysecurity.service;

import example.oddiysecurity.entity.Users;
import example.oddiysecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder encoder;
    public Users get(Long id){
        return repository.getReferenceById(id);
    }
    public Users create(Users user){
        user.setPassword(encoder.encode(user.getPassword()));
        return repository.save(user);
    }
    public void delete(Long id){
        repository.deleteById(id);
    }
}
