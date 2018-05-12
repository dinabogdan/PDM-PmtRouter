package org.pdm.ib.pmt.router.repos;

import org.pdm.ib.pmt.router.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUsernameAndPassword(String username, String password);
}
