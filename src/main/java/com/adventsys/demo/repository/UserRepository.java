package com.adventsys.demo.repository;

import com.adventsys.demo.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {
}
