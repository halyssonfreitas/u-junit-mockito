package br.com.radon.apiJUnitMockito.services;

import java.util.List;

import br.com.radon.apiJUnitMockito.domain.User;

public interface UserService {
  User findById(Integer id);
  List<User> findAll();
}