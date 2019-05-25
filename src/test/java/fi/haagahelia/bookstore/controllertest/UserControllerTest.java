package fi.haagahelia.bookstore.controllertest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import fi.haagahelia.bookstore.repository.BookRepository;
import fi.haagahelia.bookstore.repository.CathegoryRepository;
import fi.haagahelia.bookstore.repository.UserRepository;
import fi.haagahelia.bookstore.service.UserDetailServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest
public class UserControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserDetailServiceImpl service;
	
	@MockBean
	private BookRepository bRepo;
	@MockBean
	private CathegoryRepository cRepo;
	@MockBean
	private UserRepository uRepo;

	
	@Test
	public void signupTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/signup")).andExpect(MockMvcResultMatchers.status().is(200));
	}

}
