/**
 * 
 */
package com.sam.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sam.models.Groups;
import com.sam.services.GroupService;

/**
 * @author JavaTraining
 *
 */
@RestController
@RequestMapping(value = "/group")
public class GroupController {

	@Autowired
	private GroupService groupService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Groups> getGroupById(@PathVariable Integer id) {
		Groups groupsPersistent = groupService.getGroupById(id);
		return new ResponseEntity<Groups>(groupsPersistent, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Groups>> getAllGroups() {
		List<Groups> groupsPersistent = groupService.getAllGroups();
		return new ResponseEntity<List<Groups>>(groupsPersistent, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Groups> createGroup(@RequestBody Groups group) {
		Groups groupsPersistent = groupService.createGroup(group);
		return new ResponseEntity<Groups>(groupsPersistent, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Groups> updateGroup(@PathVariable Integer id, @RequestBody Groups groupUi) {
		Groups groupsPersistent = groupService.getGroupById(id);
		groupsPersistent.setName(groupUi.getName());
		Groups newGroup = groupService.updateGroup(groupsPersistent);
		return new ResponseEntity<Groups>(newGroup, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteGroupById(@PathVariable Integer id) {
		groupService.deleteGroup(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
