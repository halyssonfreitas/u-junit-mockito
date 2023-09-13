package br.com.radon.apiJUnitMockito.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.radon.apiJUnitMockito.domain.User;
import br.com.radon.apiJUnitMockito.domain.dto.UserDTO;
import br.com.radon.apiJUnitMockito.services.UserService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/user")
public class UserResource {

    private static final String ID = "/{id}";
    private final UserService service;
    private final ModelMapper mapper;

    @GetMapping(value = ID)
    public ResponseEntity<UserDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(mapper.map(service.findById(id), UserDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        return ResponseEntity.ok()
                .body(service
                        .findAll()
                        .stream()
                        .map(x -> mapper.map(x, UserDTO.class))
                        .collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO obj) {
        User newObj = service.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

}
