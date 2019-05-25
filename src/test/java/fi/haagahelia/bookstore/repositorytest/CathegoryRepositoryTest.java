package fi.haagahelia.bookstore.repositorytest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.haagahelia.bookstore.domain.Cathegory;
import fi.haagahelia.bookstore.repository.CathegoryRepository;


@RunWith(SpringRunner.class)
@DataJpaTest
public class CathegoryRepositoryTest {

	@Autowired
	private CathegoryRepository cRepo;

	private Cathegory testCat;

	
	@Before 
	public void setUp(){
		
		testCat = new Cathegory("testcat",null);
		cRepo.save(testCat);
		
	}
	
	@Test
	public void createTest() {

		assertThat(cRepo.findAll()).hasSize(4);
	}
	
	@Test
	public void searchTest(){
		
		assertThat(cRepo.findByName("testcat")).isNotNull();
	}
	
	@Test
	public void deleteTest(){
		
		cRepo.delete(testCat);
		assertThat(cRepo.findAll()).hasSize(3);
	}

	
	

}
