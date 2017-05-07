package com.sam.services;

import java.util.List;

import com.sam.models.Groups;

/**
 * @author JavaTraining
 *
 */
public interface GroupService {
	
	Groups getGroupById(Integer id);
	
	List<Groups> getAllGroups();
	
	Groups createGroup(Groups group);
	
	Groups updateGroup(Groups group);
	
	void deleteGroup(Integer id);

}
