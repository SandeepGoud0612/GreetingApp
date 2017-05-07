/**
 * 
 */
package com.sam.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sam.models.Person;

/**
 * @author JavaTraining
 *
 */
@Repository
public interface PersonDAO extends JpaRepository<Person, Integer>{

}
