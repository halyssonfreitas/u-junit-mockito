package br.com.radon.apiJUnitMockito.services.imp;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.radon.apiJUnitMockito.domain.User;
import br.com.radon.apiJUnitMockito.domain.dto.UserDTO;
import br.com.radon.apiJUnitMockito.repositories.UserRepository;
import br.com.radon.apiJUnitMockito.services.UserService;
import br.com.radon.apiJUnitMockito.services.imp.exceptions.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

  private final UserRepository repository;
  private final ModelMapper mapper;

  @Override
  public User findById(Integer id) {
    Optional<User> obj = repository.findById(id);
    return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
  }

  @Override
  public List<User> findAll() {
    return repository.findAll();
  }

  @Override
  public User create(UserDTO obj) {
    return repository.save(mapper.map(obj, User.class));
  }
  
}