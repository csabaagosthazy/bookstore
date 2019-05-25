package fi.haagahelia.bookstore.repositorytest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.haagahelia.bookstore.domain.User;
import fi.haagahelia.bookstore.repository.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

	@Autowired
	private UserRepository uRepo;

	private User testUser;

	
	@Before 
	public void setUp(){
		
		testUser = new User("testuser", "$2y$12$cMTHxLNggOHouS6D8D83JutrbVjCj1I0FEO9Uywztogm./nNvGjq2", "test@test.test", "TEST");
		uRepo.save(testUser);
		
	}
	
	@Test
	public void createTest() {

		assertThat(uRepo.findAll()).hasSize(3);
	}
	
	@Test
	public void searchTest(){
		
		assertThat(uRepo.findByUsername("testuser")).isNotNull();
	}
	
	@Test
	public void deleteTest(){
		
		uRepo.delete(testUser);
		assertThat(uRepo.findByUsername("testuser")).isNull();
		assertThat(uRepo.findAll()).hasSize(2);
	}

}
