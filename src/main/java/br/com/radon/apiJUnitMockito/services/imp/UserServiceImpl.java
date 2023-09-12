package br.com.radon.apiJUnitMockito.services.imp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.radon.apiJUnitMockito.domain.User;
import br.com.radon.apiJUnitMockito.repositories.UserRepository;
import br.com.radon.apiJUnitMockito.services.UserService;
import br.com.radon.apiJUnitMockito.services.imp.exceptions.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository repository;

  @Override
  public User findById(Integer id) {
    Optional<User> obj = repository.findById(id);
    return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
  }
  
}