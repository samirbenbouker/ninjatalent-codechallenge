package com.samirbenbouker.ninjatalent.repository;

import com.samirbenbouker.ninjatalent.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
}
