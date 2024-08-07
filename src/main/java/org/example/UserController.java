package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (userService.findUserByUsername(user.getUsername()) != null) {
            return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userService.registerUser(user.getUsername(), user.getPassword()), HttpStatus.CREATED);
    }

    @GetMapping("/me")
    public ResponseEntity<?> getUserDetails(@AuthenticationPrincipal UserDetails userDetails) {
        return new ResponseEntity<>(userDetails, HttpStatus.OK);
    }
}
