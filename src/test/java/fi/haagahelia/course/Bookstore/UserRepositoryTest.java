package fi.haagahelia.course.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.haagahelia.course.Bookstore.domain.UserRepository;
import fi.haagahelia.course.Bookstore.domain.User;



@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Test
    public void findByUsernameShouldReturnEmail() {
        User user = repository.findByUsername("user");
        
        assertThat(user).isNotNull();
        assertThat(user.getRole()).isEqualTo("USER");
    }
    
    @Test
    public void createNewCategory() {
    	User user = new User("user2", "password", "email", "ADMIN");
    	repository.save(user);
    	assertThat(user.getUsername()).isNotNull();
    }
    


}