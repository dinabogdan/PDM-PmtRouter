package org.pdm.ib.pmt.router.repos;

import org.pdm.ib.pmt.router.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
