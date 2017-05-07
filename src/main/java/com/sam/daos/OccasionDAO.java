/**
 * 
 */
package com.sam.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sam.models.Occasion;

/**
 * @author JavaTraining
 *
 */
@Repository
public interface OccasionDAO extends JpaRepository<Occasion, Integer> {

}
