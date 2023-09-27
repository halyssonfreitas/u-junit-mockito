package br.com.radon.apiJUnitMockito.resources;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.radon.apiJUnitMockito.domain.User;
import br.com.radon.apiJUnitMockito.domain.dto.UserDTO;
import br.com.radon.apiJUnitMockito.services.UserService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@SpringBootTest
public class UserResourceTest {

  private static final Integer ID = 100;
  private static final String NAME = "Halysson";
  private static final String EMAIL = "halyssonfreitas@gmail.com";
  private static final String PASSWORD = "123";

  @InjectMocks
  private UserResource userResource;
  @Mock
  private final UserService userService;
  @Mock
  private final ModelMapper mapper;

  private User user;
  private UserDTO userDTO;


  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    startUser();
  }

  @Test
  void findById() {
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
