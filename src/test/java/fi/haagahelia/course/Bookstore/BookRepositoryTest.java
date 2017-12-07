package fi.haagahelia.course.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.haagahelia.course.Bookstore.domain.Book;
import fi.haagahelia.course.Bookstore.domain.BookRepository;
import fi.haagahelia.course.Bookstore.domain.Category;
import fi.haagahelia.course.Bookstore.domain.CategoryRepository;



@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository repository;
    
    @Autowired
    private CategoryRepository crepository;


    @Test
    public void findByTitleShouldReturnBook() {
        List<Book> books = repository.findByTitle("Hamlet");
        
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getYear()).isEqualTo(1603);
    }
    
    @Test
    public void createNewBook() {
    	Book book = new Book("War and Peace", "L. Tolstoy", 1867, "2122", 8.0, crepository.findByName("history").get(0));
    	repository.save(book);
    	assertThat(book.getId()).isNotNull();
    } 
    
    @Test
    public void deleteBook() {
    	List<Book> books = repository.findByTitle("Hamlet");
    	Long id = books.get(0).getId();
    	
    	repository.delete(id);
    	assertThat(repository.findByTitle("Hamlet")).hasSize(0);
    	
    }

}