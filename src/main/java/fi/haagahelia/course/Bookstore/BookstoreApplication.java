package fi.haagahelia.course.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.course.Bookstore.domain.Book;
import fi.haagahelia.course.Bookstore.domain.BookRepository;
import fi.haagahelia.course.Bookstore.domain.Category;
import fi.haagahelia.course.Bookstore.domain.CategoryRepository;
import fi.haagahelia.course.Bookstore.domain.User;
import fi.haagahelia.course.Bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(BookRepository repository, CategoryRepository crepository, UserRepository urepository) {
		return (args) -> {
			log.info("save a couple of books");
			crepository.save(new Category("drama"));
			crepository.save(new Category("history"));
			crepository.save(new Category("sci-fi"));

			repository.save(new Book("Hamlet", "Shakespeare", 1603, "3432", 10, crepository.findByName("drama").get(0)));
			repository.save(new Book("The Picture of Dorian Gray", "Oscar Wilde", 1890, "6657", 7, crepository.findByName("drama").get(0)));

			// Create users: admin/admin user/world
			User user1 = new User("user", "$2a$06$ROH.6r8cM1IfWS6wa1rhQOWfndYCwFnFGNyKdUrjFXfpPWnnnFfsm", "user@mail.com", "USER");
			User user2 = new User("admin", "$2a$06$fClI3fmnqZPuWcB7Ac40bOwku33v124kdtl2wSwcUiguX.STIYpbG", "admin@mail.com", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);

			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}

		};
	}
}
