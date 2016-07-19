package org.tikal.fuse.repository;

import org.tikal.fuse.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by guy on 7/19/16.
 */
public interface UserRepository extends CrudRepository<User,Long> {

    List<User> findByUserName(String userName);

}
