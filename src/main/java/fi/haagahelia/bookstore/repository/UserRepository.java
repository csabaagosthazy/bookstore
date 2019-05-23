package fi.haagahelia.bookstore.repository;

import org.springframework.data.repository.CrudRepository;

import fi.haagahelia.bookstore.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByUsername(String username);
	User findByEmail(String email);
}
