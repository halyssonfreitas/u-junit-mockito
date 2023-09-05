package br.com.radon.apiJUnitMockito.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.radon.apiJUnitMockito.domain.User;
import br.com.radon.apiJUnitMockito.services.UserService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/user")
public class UserResource {

    private static final String ID = "/{id}";

    private final UserService service;

    @GetMapping(value = ID)
    public ResponseEntity<User> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

}
