package com.pojo;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 * This component and its source code representation are copyright protected and
 * proprietary to Trivera Technologies, Inc., Worldwide
 *
 * This component and source code may be used for instructional and evaluation
 * purposes only. No part of this component or its source code may be sold,
 * transferred, or publicly posted, nor may it be used in a commercial or
 * production environment, without the express written consent of the Trivera
 * Technologies, Inc.
 *
 * Copyright (c) 2017 Trivera Technologies, Inc. http://www.triveratech.com
 * </p>
 * 
 * @author The Trivera Tech Team.
 */
public class UserDatastore {

	private Map<Integer, User> users = new ConcurrentHashMap<Integer, User>();
	private AtomicInteger idGenerator = new AtomicInteger();
	private static UserDatastore store;

	public static UserDatastore getuserDatastore() {
		if (store == null)
			store = new UserDatastore();
		return store;
	}

	private UserDatastore() {
		User thisUser = new User();
		thisUser.setId(idGenerator.incrementAndGet());
		thisUser.setCity("Rigby");
		thisUser.setFullName("Philo T Farnsworth");
		thisUser.setStreet("Spud Alley");
		thisUser.setUserName("Philo");
		thisUser.setZipcode("83400");
		users.put(thisUser.getId(), thisUser);
		thisUser = new User();
		thisUser.setId(idGenerator.incrementAndGet());
		thisUser.setCity("Ozone");
		thisUser.setFullName("Horatio Hornblower");
		thisUser.setStreet("Mills View");
		thisUser.setUserName("Horatio");
		thisUser.setZipcode("83600");
		users.put(thisUser.getId(), thisUser);
		thisUser = new User();
		thisUser.setId(idGenerator.incrementAndGet());
		thisUser.setCity("Ozone");
		thisUser.setFullName("Henrietta Hornblower");
		thisUser.setStreet("Mills View");
		thisUser.setUserName("Henrietta");
		thisUser.setZipcode("83600");
		users.put(thisUser.getId(), thisUser);
		thisUser = new User();
		thisUser.setId(idGenerator.incrementAndGet());
		thisUser.setCity("Chill");
		thisUser.setFullName("Frank N Stein");
		thisUser.setStreet("Main");
		thisUser.setUserName("Frank");
		thisUser.setZipcode("83400");
		users.put(thisUser.getId(), thisUser);
		thisUser = new User();
		thisUser.setId(idGenerator.incrementAndGet());
		thisUser.setCity("Mud Lake");
		thisUser.setFullName("Foggy McGroggy");
		thisUser.setStreet("Malfunction Junction");
		thisUser.setUserName("Foggy");
		thisUser.setZipcode("83400");
		users.put(thisUser.getId(), thisUser);
		thisUser = new User();
		thisUser.setId(idGenerator.incrementAndGet());
		thisUser.setCity("Paradise");
		thisUser.setFullName("Joe Grunt");
		thisUser.setStreet("Stowa Way");
		thisUser.setUserName("Joe");
		thisUser.setZipcode("83400");
		users.put(thisUser.getId(), thisUser);
	}

	public List<User> getUsers() {
		List<User> userList = new ArrayList<User>(users.values());
		return userList;
	}

	public List<User> getUsers(Integer start, Integer size) {
		List<User> userList = new ArrayList<User>(size);
		if (start == null)
			return userList;
		Set<Integer> keys = users.keySet();
		if (start < 1 || start > keys.size())
			return userList;
		userList.add(users.get(start));
		int end;
		if (size == 0)
			end = keys.size();
		else {
			end = start + size - 1;
			if (end > keys.size())
				end = keys.size();
		}
		;
		while (start < end) {
			start++;
			userList.add(users.get(start));
		}
		;
		return userList;
	}

	public User getUser(Integer userToFind) {
		User candidate = null;
		candidate = users.get(userToFind);
		return candidate;
	}

	public List<User> getUsersByZipcode(String zipcode) {
		List<User> userList = new ArrayList<User>();
		for (Iterator<Integer> it = users.keySet().iterator(); it.hasNext();) {
			User temp = users.get(it.next());
			if (temp.getZipcode().equals(zipcode)) {
				userList.add(temp);
			}
			;
		}
		return userList;
	}

	public Integer createUser(User newUser) {
		newUser.setId(idGenerator.incrementAndGet());
		users.put(newUser.getId(), newUser);
		return newUser.getId();
	}

	public Integer updateUser(User updatedUser) {
		Integer id = updatedUser.getId();
		users.put(id, updatedUser);
		return id;
	}

	public void deleteUser(Integer userToDelete) {
		users.remove(userToDelete);
	}

}
