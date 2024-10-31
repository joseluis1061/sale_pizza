package com.jlz.sale_pizza.persistence.repository;

import com.jlz.sale_pizza.persistence.entity.UserEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface UserRepository extends ListCrudRepository<UserEntity, String> {
}
