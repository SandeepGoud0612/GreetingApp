/**
 * 
 */
package com.sam;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.CollectionUtils;

import com.sam.models.Address;
import com.sam.models.Occasion;
import com.sam.models.Person;

/**
 * @author JavaTraining
 *
 */
public class CloneUtil {

	public static void copyPerson(Person uiPerson, Person dbPerson) {
		dbPerson.setFirstName(uiPerson.getFirstName());
		dbPerson.setLastName(uiPerson.getLastName());
		dbPerson.setDob(uiPerson.getDob());
		dbPerson.setGender(uiPerson.getGender());
		dbPerson.setMaritalStatus(uiPerson.getMaritalStatus());
		dbPerson.setPrimaryEmail(uiPerson.getPrimaryEmail());
		dbPerson.setSecondaryEmail(uiPerson.getSecondaryEmail());
		dbPerson.setPrimaryContact(uiPerson.getPrimaryContact());
		dbPerson.setSecondaryContact(uiPerson.getSecondaryContact());

		if (!CollectionUtils.isEmpty(uiPerson.getOccasions())) {
			if (uiPerson.getOccasions().size() == dbPerson.getOccasions().size()) {
				updateOccationList(uiPerson.getOccasions(), dbPerson.getOccasions());
			} else if (uiPerson.getOccasions().size() > dbPerson.getOccasions().size()) {
				addOccasionToList(uiPerson.getOccasions(), dbPerson.getOccasions(), dbPerson);
			} else {
				deleteOccasionFromOccasionList(uiPerson.getOccasions(), dbPerson.getOccasions());
			}
		}
		if (!CollectionUtils.isEmpty(uiPerson.getAddresses())) {
			if (uiPerson.getAddresses().size() == dbPerson.getAddresses().size()) {
				updateAddressList(uiPerson.getAddresses(), dbPerson.getAddresses());
			} else if (uiPerson.getAddresses().size() > dbPerson.getAddresses().size()) {
				addAddressInAddressList(uiPerson.getAddresses(), dbPerson.getAddresses(), dbPerson);
			} else {
				deleteAddressFromAddressList(uiPerson.getAddresses(), dbPerson.getAddresses());
			}
		}
	}
	
	private static void updateOccationList(List<Occasion> uiOccasionList, List<Occasion> dbOccasionList) {
		uiOccasionList.stream().forEach(uiOccasion -> {
			Occasion dbOccasion = dbOccasionList.stream()
					.filter(occasion -> occasion.getId().equals(uiOccasion.getId())).collect(Collectors.toList())
					.get(0);
			copyOccasion(uiOccasion, dbOccasion);
		});
	}

	private static void addOccasionToList(List<Occasion> uiOccasionList, List<Occasion> dbOccasionList, Person dbPerson) {
		uiOccasionList.stream().forEach(occasionUi -> {
			List<Occasion> dbOccasions = dbOccasionList.stream()
					.filter(occasion -> occasion.getId().equals(occasionUi.getId())).collect(Collectors.toList());
			if (CollectionUtils.isEmpty(dbOccasions)) {
				occasionUi.setPerson(dbPerson);
				dbOccasionList.add(occasionUi);
				return;
			}
		});
	}
	
	private static void deleteOccasionFromOccasionList(List<Occasion> uiOccasionList, List<Occasion> dbOccasionList) {
		for (int i = 0; i < dbOccasionList.size(); i++) {
			Occasion dbOccasion = dbOccasionList.get(i);
			List<Occasion> uiOccasions = uiOccasionList.stream()
					.filter(occasion -> occasion.getId().equals(dbOccasion.getId())).collect(Collectors.toList());
			if (CollectionUtils.isEmpty(uiOccasions)) {
				dbOccasionList.set(i, null);
				break;
			}
		}
	}
	
	private static void updateAddressList(List<Address> uiAddressList, List<Address> dbAddressList) {
		for (int i = 0; i < uiAddressList.size(); i++) {
			Address uiAddress = uiAddressList.get(i);
			Address dbAddress = dbAddressList.stream().filter(address -> address.getId().equals(uiAddress.getId()))
					.collect(Collectors.toList()).get(0);
			copyAddress(uiAddress, dbAddress);
		}
	}

	private static void addAddressInAddressList(List<Address> uiAddressList, List<Address> dbAddressList,
			Person dbPerson) {
		for (int i = 0; i < uiAddressList.size(); i++) {
			Address uiAddress = uiAddressList.get(i);
			List<Address> dbAddress = dbAddressList.stream()
					.filter(address -> address.getId().equals(uiAddress.getId())).collect(Collectors.toList());
			if (CollectionUtils.isEmpty(dbAddress)) {
				uiAddress.setPerson(dbPerson);
				dbAddressList.add(uiAddress);
				break;
			}
		}
	}

	private static void deleteAddressFromAddressList(List<Address> uiAddressList, List<Address> dbAddressList) {
		for (int i = 0; i < dbAddressList.size(); i++) {
			Address dbAddress = dbAddressList.get(i);
			List<Address> uiAddress = uiAddressList.stream()
					.filter(address -> address.getId().equals(dbAddress.getId())).collect(Collectors.toList());
			if (CollectionUtils.isEmpty(uiAddress)) {
				dbAddressList.set(i, null);
				break;
			}
		}
	}
	
	public static Address copyAddress(final Address sourceAddress, final Address destinationAddress) {
		destinationAddress.setAddressLine1(sourceAddress.getAddressLine1());
		destinationAddress.setAddressLine2(sourceAddress.getAddressLine2());
		destinationAddress.setCity(sourceAddress.getCity());
		destinationAddress.setState(sourceAddress.getState());
		destinationAddress.setCountry(sourceAddress.getCountry());
		destinationAddress.setZipCode(sourceAddress.getZipCode());
		return destinationAddress;
	}

	public static Occasion copyOccasion(final Occasion sourceOccasion, final Occasion destinationOccasion) {
		destinationOccasion.setName(sourceOccasion.getName());
		destinationOccasion.setDate(sourceOccasion.getDate());
		destinationOccasion.setActive(sourceOccasion.getActive());
		return destinationOccasion;
	}

}
