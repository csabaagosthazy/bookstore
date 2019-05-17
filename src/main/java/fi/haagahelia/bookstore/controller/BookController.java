package fi.haagahelia.bookstore.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.haagahelia.bookstore.domain.Book;
import fi.haagahelia.bookstore.repository.BookRepository;
import fi.haagahelia.bookstore.repository.CathegoryRepository;

@Controller
public class BookController {
	
	@Autowired
	private BookRepository BookRepository;
	
	@Autowired
	private CathegoryRepository CathegoryRepository;
	
	@RequestMapping("/index")
	public String Book(Model model) {
		model.addAttribute("books", BookRepository.findAll());
		
		return "booklist";
	}
	
	@RequestMapping(value= "/delete/{id}", method=RequestMethod.GET)
	public String deleteStudent(@PathVariable("id") Long bookId, Model model) {
		BookRepository.deleteById(bookId);
		return "redirect:../index";
	}
	
	@RequestMapping("/add")
	public String addBook(Model model) {
		model.addAttribute("cathegories", CathegoryRepository.findAll());
		model.addAttribute("book", new Book());
		
		return "addbook";
	}
	
	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
	    Book book = BookRepository.findById(id)
	      .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	    model.addAttribute("cathegories", CathegoryRepository.findAll());
	     
	    model.addAttribute("book", book);
	    return "editbook";
	}
	
	@GetMapping("/update/{id}")
	public String updateBook(@PathVariable("id") long id, @Valid Book book, 
	  BindingResult result, Model model) {
	    if (result.hasErrors()) {
	        book.setId(id);
	        return "editbook";
	    }
	         
	    BookRepository.save(book);
	    //model.addAttribute("users", BookRepository.findAll());
	    return "redirect:/index";
	}
	
	@RequestMapping(value= "/save", method=RequestMethod.POST)
	public String saveBook(Book book) {
		BookRepository.save(book);
		return "redirect:index";
	}

}
