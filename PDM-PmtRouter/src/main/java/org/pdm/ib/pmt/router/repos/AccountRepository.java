package org.pdm.ib.pmt.router.repos;

import org.pdm.ib.pmt.router.entities.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, Long> {

    Optional<Account> findByAccountNumber(Integer accountNumber);

}
