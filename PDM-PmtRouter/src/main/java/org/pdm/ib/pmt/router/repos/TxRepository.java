package org.pdm.ib.pmt.router.repos;

import org.pdm.ib.pmt.router.entities.Tx;
import org.springframework.data.repository.CrudRepository;

public interface TxRepository extends CrudRepository<Tx, Long> {

    Tx findFirstByOrderByIdDesc();
}
