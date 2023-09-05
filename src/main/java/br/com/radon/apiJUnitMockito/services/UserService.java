package br.com.radon.apiJUnitMockito.services;

import br.com.radon.apiJUnitMockito.domain.User;

public interface UserService {
  User findById(Integer id);
}