package br.com.radon.apiJUnitMockito.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.radon.apiJUnitMockito.domain.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {
  
}
