package fi.haagahelia.bookstore.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fi.haagahelia.bookstore.domain.Cathegory;

public interface CathegoryRepository extends CrudRepository<Cathegory, Long> {

	List<Cathegory> findByName(String name);
}
