/**
 * 
 */
package com.sam.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sam.models.Groups;

/**
 * @author JavaTraining
 *
 */
@Repository
public interface GroupsDAO extends JpaRepository<Groups, Integer>{

}
