package br.com.radon.apiJUnitMockito.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
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
  @Mock
  private ModelMapper mapper;

  private User user;
  private UserDTO userDTO;


  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    userResource = new UserResource(service, mapper);
    startUser();
  }

  @Test
  void whenFindByIdThenReturnSuccess() {
    when(service.findById(anyInt())).thenReturn(user);
    when(mapper.map(any(), any())).thenReturn(userDTO);

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
  void findAll() {
  }

  @Test
  void create() {
  }

  @Test
  void update() {
  }

  @Test
  void delete() {
  }

  private void startUser() {
    user = new User(ID, NAME, EMAIL, PASSWORD);
    userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
  }

}
