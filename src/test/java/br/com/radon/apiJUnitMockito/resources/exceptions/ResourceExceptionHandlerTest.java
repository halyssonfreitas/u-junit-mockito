package br.com.radon.apiJUnitMockito.resources.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import br.com.radon.apiJUnitMockito.services.impl.exceptions.ObjectNotFoundException;

@SpringBootTest
public class ResourceExceptionHandlerTest {

  private static final String OBJECT_NOT_FOUND = "Objeto não encontrado";
  private ResourceExceptionHandler exceptionHandler;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    exceptionHandler = new ResourceExceptionHandler();
  }

  @Test
  void wheObjectNotFoundExceotionThenReturnAResponseEntity() {
    ResponseEntity<StandardError> response = exceptionHandler
        .objectNotFound(
            new ObjectNotFoundException(OBJECT_NOT_FOUND),
            new MockHttpServletRequest());

    StandardError body = response.getBody();

    assertNotNull(response);
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertNotNull(body);
    assertEquals(body.getError(), OBJECT_NOT_FOUND);
    assertEquals(ResponseEntity.class, response.getClass());
    assertEquals(StandardError.class, body.getClass());
  }

}
