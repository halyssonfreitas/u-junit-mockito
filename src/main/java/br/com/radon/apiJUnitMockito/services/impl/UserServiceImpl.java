package br.com.radon.apiJUnitMockito.services.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.radon.apiJUnitMockito.domain.User;
import br.com.radon.apiJUnitMockito.domain.dto.UserDTO;
import br.com.radon.apiJUnitMockito.repositories.UserRepository;
import br.com.radon.apiJUnitMockito.services.UserService;
import br.com.radon.apiJUnitMockito.services.impl.exceptions.DataIntegratyViolationException;
import br.com.radon.apiJUnitMockito.services.impl.exceptions.ObjectNotFoundException;
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
    findByEmail(obj);
    return repository.save(mapper.map(obj, User.class));
  }

  private void findByEmail(UserDTO obj) {
    Optional<User> userOptional = repository.findByEmail(obj.getEmail());
    if (userOptional.isPresent() && !userOptional.get().getId().equals(obj.getId())) throw new DataIntegratyViolationException("E-mail já cadastrado no sistema");
  }

  @Override
  public User update(UserDTO obj) {
    findByEmail(obj);
    return repository.save(mapper.map(obj, User.class));
  }

  @Override
  public void delete(Integer id) {
    findById(id);
    repository.deleteById(id);
  }
  
}