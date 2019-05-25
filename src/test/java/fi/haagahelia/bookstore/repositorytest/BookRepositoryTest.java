package fi.haagahelia.bookstore.repositorytest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.haagahelia.bookstore.domain.Book;
import fi.haagahelia.bookstore.repository.BookRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {

	@Autowired
	private BookRepository bRepo;

	private Book testBook;

	
	@Before 
	public void setUp(){
		
		testBook = new Book("testbook", "test author", "test isbn", "test year", "test price", null);
		bRepo.save(testBook);
		
	}
	
	@Test
	public void createTest() {

		assertThat(bRepo.findAll()).hasSize(3);
	}
	
	@Test
	public void searchTest(){
		
		assertThat(bRepo.findByTitle("testbook")).isNotNull();
	}
	
	@Test
	public void deleteTest(){
		
		bRepo.delete(testBook);
		assertThat(bRepo.findByTitle("testbook")).isNull();
		assertThat(bRepo.findAll()).hasSize(2);
	}

}
