package org.tikal.fuse.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.tikal.fuse.model.User;
import org.tikal.fuse.repository.UserRepository;
import org.tikal.fuse.service.MailService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by guy on 7/19/16.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    Logger LOGGER = Logger.getLogger(UserController.class);

    private Map<String, String> tagToLinksMap = new HashMap<>();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailService mailService;

    @PostConstruct
    private void populateMap() {
        tagToLinksMap.put("ui-router", "https://www.youtube.com/watch?v=nflpIn5vQuo");
        tagToLinksMap.put("angular", "https://www.youtube.com/watch?v=YJamA3uBs_8");
        tagToLinksMap.put("directives", "https://www.youtube.com/watch?v=Ty8XcASK9js");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User findOne(@PathVariable Long id) {
        return userRepository.findOne(id);
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public List<User> findAll() {

        List<User> users = new ArrayList<>();
        Iterable<User> allUsers = userRepository.findAll();
        allUsers.forEach(item -> users.add(item));

        return users;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Void> save(@RequestBody User user, UriComponentsBuilder uriComponentsBuilder) {

        LOGGER.info("Saving user: " + user.getUserId());
        User id = userRepository.save(user);
        LOGGER.info("Saved user: " + id);

        String body = null;
        for (String tag : user.getTags()) {
            if (tagToLinksMap.keySet().contains(tag)) {
                body = tagToLinksMap.get(tag);
                break;
            }
        }

        //save mail
        mailService.sendMail("zpder123@gmail.com", user.getUserEmail(), "Advanced Coding Guide", (null != body) ? body : "No suggestions");

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponentsBuilder.path("/user/{id}").buildAndExpand(user.getUserId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
}
