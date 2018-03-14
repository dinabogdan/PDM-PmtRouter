package org.pdm.ib.pmt.router.repos;

import org.pdm.ib.pmt.router.entities.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
}
