package com.codingdojo.loginandregister.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.codingdojo.loginandregister.models.User;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	Optional<User> findByEmail(String email);
    List<User> findAll();
    void deleteById(Long id);
    Optional<User> findById(Long id);
    @SuppressWarnings("unchecked")
	User save(User id);    
}