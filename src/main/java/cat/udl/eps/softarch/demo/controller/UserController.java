package cat.udl.eps.softarch.demo.controller;

import cat.udl.eps.softarch.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/users/{username}/lock")
    public ResponseEntity<Object> lockUser(@PathVariable String username) {
        // If the user is present, lock it and save it. If not, return 404.
        return this.userRepository.findById(username)
                .map(user -> {
                    user.lock();
                    this.userRepository.save(user);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/users/{username}/unlock")
    public ResponseEntity<Object> unlockUser(@PathVariable String username) {
        // If the user is present, unlock it and save it. If not, return 404.
        return this.userRepository.findById(username)
                .map(user -> {
                    user.unlock();
                    this.userRepository.save(user);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
