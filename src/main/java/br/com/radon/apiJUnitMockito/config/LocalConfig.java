package br.com.radon.apiJUnitMockito.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.radon.apiJUnitMockito.domain.User;
import br.com.radon.apiJUnitMockito.repositories.UserRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Configuration
@Profile("local")
public class LocalConfig {

  private final UserRepository repository;
  
  @Bean
  public void startDB() {
    User u1 = new User(null, "Halysson", "halysson@radon.com.br", "123");
    User u2 = new User(null, "Freitas", "freitas@radon.com.br", "123");

    repository.saveAll(List.of(u1, u2));
  }
}
