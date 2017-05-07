/**
 * 
 */
package com.sam.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sam.daos.GroupsDAO;
import com.sam.models.Groups;

/**
 * @author JavaTraining
 *
 */
@Service
@Transactional
public class GroupServiceImpl implements GroupService {

	@Autowired
	private GroupsDAO groupsDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sam.services.GroupService#getGroupById(java.lang.Integer)
	 */
	@Override
	public Groups getGroupById(Integer id) {
		return groupsDAO.findOne(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sam.services.GroupService#getAllGroups()
	 */
	@Override
	public List<Groups> getAllGroups() {
		return groupsDAO.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sam.services.GroupService#createGroup(com.sam.models.Groups)
	 */
	@Override
	public Groups createGroup(Groups group) {
		return groupsDAO.save(group);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sam.services.GroupService#updateGroup(com.sam.models.Groups)
	 */
	@Override
	public Groups updateGroup(Groups group) {
		return groupsDAO.save(group);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sam.services.GroupService#deleteGroup(java.lang.Integer)
	 */
	@Override
	public void deleteGroup(Integer id) {
		groupsDAO.delete(id);
	}

}
