/**
 * 
 */
package com.sam.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sam.models.Address;

/**
 * @author JavaTraining
 *
 */
@Repository
public interface AddressDAO extends JpaRepository<Address, Integer>{

}
