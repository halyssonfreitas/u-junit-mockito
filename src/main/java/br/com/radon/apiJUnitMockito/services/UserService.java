package br.com.radon.apiJUnitMockito.services;

import java.util.List;

import br.com.radon.apiJUnitMockito.domain.User;
import br.com.radon.apiJUnitMockito.domain.dto.UserDTO;

public interface UserService {
  User findById(Integer id);
  List<User> findAll();
  User create(UserDTO obj);
}