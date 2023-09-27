package br.com.radon.apiJUnitMockito.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.radon.apiJUnitMockito.domain.User;
import br.com.radon.apiJUnitMockito.domain.dto.UserDTO;
import br.com.radon.apiJUnitMockito.services.UserService;

@SpringBootTest
public class UserResourceTest {

  private static final Integer ID = 100;
  private static final String NAME = "Halysson";
  private static final String EMAIL = "halyssonfreitas@gmail.com";
  private static final String PASSWORD = "123";

  private UserResource userResource;
  @Mock
  private UserService service;

  private User user;
  private UserDTO userDTO;


  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    userResource = new UserResource(service, new ModelMapper());
    startUser();
  }

  @Test
  void whenFindByIdThenReturnSuccess() {
    when(service.findById(anyInt())).thenReturn(user);

    ResponseEntity<UserDTO> response = userResource.findById(ID);

    assertNotNull(response);
    assertNotNull(response.getBody());
    assertEquals(ResponseEntity.class, response.getClass());
    assertEquals(UserDTO.class, response.getBody().getClass());

    assertEquals(ID, response.getBody().getId());
    assertEquals(NAME, response.getBody().getName());
    assertEquals(EMAIL, response.getBody().getEmail());
    assertEquals(PASSWORD, response.getBody().getPassword());
  }

  @Test
  void whenFindFindAllThenReturnListOfUserDTO() {
    when(service.findAll()).thenReturn(List.of(user, user, user));

    ResponseEntity<List<UserDTO>> response = userResource.findAll();

    assertNotNull(response);
    assertNotNull(response.getBody());
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(ResponseEntity.class, response.getClass());
    assertEquals(ArrayList.class, response.getBody().getClass());
    assertEquals(UserDTO.class, response.getBody().get(0).getClass());

    assertEquals(response.getBody().size(), 3);

    assertEquals(ID, response.getBody().get(0).getId());
    assertEquals(NAME, response.getBody().get(0).getName());
    assertEquals(EMAIL, response.getBody().get(0).getEmail());
    assertEquals(PASSWORD, response.getBody().get(0).getPassword());
  }

  @Test
  void whenCreateThenReturnCreated() {
    when(service.create(any())).thenReturn(user);

    ResponseEntity<UserDTO> response = userResource.create(userDTO);

    assertNotNull(response);
    assertEquals(ResponseEntity.class, response.getClass());
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertNotNull(response.getHeaders().get("Location"));
  }

  @Test
  void whenUpdateThenReturnSuccess() {
    when(service.update(any())).thenReturn(user);

    ResponseEntity<UserDTO> response = userResource.update(ID, userDTO);

    assertNotNull(response);
    assertNotNull(response.getBody());
    assertEquals(ResponseEntity.class, response.getClass());
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(UserDTO.class, response.getBody().getClass());

    assertEquals(ID, response.getBody().getId());
    assertEquals(NAME, response.getBody().getName());
    assertEquals(EMAIL, response.getBody().getEmail());
  }

  @Test
  void whenDeleteThenReturnSuccess() {
    doNothing().when(service).delete(anyInt());

    ResponseEntity<UserDTO> response = userResource.delete(ID);

    assertNotNull(response);
    assertEquals(ResponseEntity.class, response.getClass());
    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    verify(service, times(1)).delete(anyInt());
  }

  private void startUser() {
    user = new User(ID, NAME, EMAIL, PASSWORD);
    userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
  }

}
