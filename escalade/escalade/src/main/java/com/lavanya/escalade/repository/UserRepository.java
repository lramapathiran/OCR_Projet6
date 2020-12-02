package com.lavanya.escalade.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.ui.context.Theme;

import com.lavanya.escalade.model.User;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

/**
 * Repository extending JPA repository for persistence of User object.
 * @author lavanya
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	/**
	 * Query to retrieve a specific user using his email address.
	 * @param email used to identify the user searched in database.
	 * @return the User of interest.
	 */
	Optional<User> findByEmail(String email);
	
	/**
	 * Query to count the amount of user created in database.
	 * @return the total amount of User registered in database.
	 */
	@Query("select count(id) from User")
	Integer countUsersRegistered();

}

