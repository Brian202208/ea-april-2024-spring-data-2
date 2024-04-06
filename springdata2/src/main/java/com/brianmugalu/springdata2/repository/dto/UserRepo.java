package com.brianmugalu.springdata2.repository.dto;

import com.brianmugalu.springdata2.repository.model.User;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends ListCrudRepository<User,Long> {
}
