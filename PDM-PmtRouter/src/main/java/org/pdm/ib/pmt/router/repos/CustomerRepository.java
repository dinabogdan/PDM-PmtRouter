package org.pdm.ib.pmt.router.repos;

import org.pdm.ib.pmt.router.entities.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
