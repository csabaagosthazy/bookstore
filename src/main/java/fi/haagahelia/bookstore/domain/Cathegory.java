package fi.haagahelia.bookstore.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Cathegory {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long catId;
	private String name;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cathegory")
	private List<Book> books;
	
	public Cathegory(String name, List<Book> books) {
		super();
		this.name = name;
		this.books = books;
	}

	public Long getCatId() {
		return catId;
	}

	public void setCatId(Long id) {
		this.catId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public Cathegory() {}

}
