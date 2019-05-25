package fi.haagahelia.bookstore.controllertest;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import fi.haagahelia.bookstore.controller.BookController;
import fi.haagahelia.bookstore.repository.BookRepository;
import fi.haagahelia.bookstore.repository.CathegoryRepository;
import fi.haagahelia.bookstore.repository.UserRepository;
import fi.haagahelia.bookstore.service.UserDetailServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest
public class BookControllerTest {

	
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

	@Autowired
	private BookController bookController;
	

    @Test
	public void testApplicationLoad() {

		assertNotNull("BookController is not loaded", bookController);
	}
	
	@Test
	public void loginTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/login")).andExpect(MockMvcResultMatchers.status().is(200));
	}

	
	@Test
	public void unauthorizedRequestTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/booklist")).andExpect(MockMvcResultMatchers.status().is(302));
	}
	
	
	
}