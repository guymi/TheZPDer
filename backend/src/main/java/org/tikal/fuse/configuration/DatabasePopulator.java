package org.tikal.fuse.configuration;

import org.tikal.fuse.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.tikal.fuse.repository.UserRepository;

/**
 * Created by guy on 7/19/16.
 */
@Configuration
public class DatabasePopulator implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    public void run(String... arg0) throws Exception {

        User user1 = new User();
        user1.setUserId(1L);
        user1.setUserName("Guy");
        user1.setUserEmail("Guy@someMail.com");
        user1.setTags(new String[] {"tag1", "tag2", "tag3"});

        userRepository.save(user1);

        System.out.println(userRepository.findByUserName("Guy"));
    }

}
