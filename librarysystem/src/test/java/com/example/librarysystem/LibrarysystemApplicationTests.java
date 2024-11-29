package com.example.librarysystem;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.librarysystem.Controller.BookController;
import com.example.librarysystem.Controller.PatronController;
import com.example.librarysystem.Entity.Book;
import com.example.librarysystem.Entity.Patron;
import com.example.librarysystem.Exceptions.BorrRecNotFoundExcption;
import com.example.librarysystem.Service.BookService;
import com.example.librarysystem.Service.PatronService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
//class LibrarysystemApplicationTests {
//
//	@Test
//	void contextLoads() {
//	}
//
//}
///////////////////////////////////////////


public class LibrarysystemApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private BookService bookService;

	@Mock
	private PatronService patronService;

	@InjectMocks
	private BookController bookController;

	@InjectMocks
	private PatronController patronController;

	private ObjectMapper objectMapper;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders
				.standaloneSetup(bookController, patronController)
				.build();
		objectMapper = new ObjectMapper();
	}

	// Book Tests

	@Test
	public void testAddBook() throws Exception {
		Book book = new Book();
		book.setTitle("Test Book");
		book.setAuthor("Author");
		book.setPublication_year("2023");
		book.setISBN("1234567890");
		bookService.addNewBook(any(Book.class));

		mockMvc.perform(post("/api/books")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(book)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.title").value("Test Book"))
				.andExpect(jsonPath("$.author").value("Author"));
	}

	@Test
	public void testGetBookById() throws Exception {
		Book book = new Book();
		book.setId(1L);
		book.setTitle("Test Book");
		book.setAuthor("Author");
		book.setPublication_year("2023");
		book.setISBN("1234567890");

		when(bookService.getBookById(1L)).thenReturn(book);

		mockMvc.perform(get("/api/books/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.title").value("Test Book"))
				.andExpect(jsonPath("$.author").value("Author"));
	}

	@Test
	public void testGetBookNotFound() throws Exception {
		when(bookService.getBookById(999L)).thenThrow(new BorrRecNotFoundExcption("Book not found"));

		mockMvc.perform(get("/api/books/999"))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.error").value("Book not found"));
	}

	// Patron Tests

	@Test
	public void testAddPatron() throws Exception {
		Patron patron = new Patron();
		patron.setName("John Doe");
		patron.setContact_info("john.doe@example.com");

		patronService.addNewPatron(any(Patron.class));

		mockMvc.perform(post("/api/patrons")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(patron)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("John Doe"));
	}

	@Test
	public void testGetPatronById() throws Exception {
		Patron patron = new Patron();
		patron.setId(1L);
		patron.setName("John Doe");
		patron.setContact_info("john.doe@example.com");

		when(patronService.gePatronById(1L)).thenReturn(patron);

		mockMvc.perform(get("/api/patrons/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("John Doe"));
	}

	@Test
	public void testGetPatronNotFound() throws Exception {
		when(patronService.gePatronById(999L)).thenThrow(new BorrRecNotFoundExcption("Patron not found"));

		mockMvc.perform(get("/api/patrons/999"))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.error").value("Patron not found"));
	}
}
