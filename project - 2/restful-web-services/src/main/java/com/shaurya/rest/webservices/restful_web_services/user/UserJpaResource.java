package com.shaurya.rest.webservices.restful_web_services.user;

import com.shaurya.rest.webservices.restful_web_services.Post;
import com.shaurya.rest.webservices.restful_web_services.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJpaResource {

    private UserRepository repository;

    public UserJpaResource(UserRepository repository) {

        this.repository = repository;
    }

    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers() {

        return repository.findAll();
    }

    //http://localhost:8080/users

    //EntityModel
    //WebMvcLinkBuilder
    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> retrieveUserById(@PathVariable int id) {
        Optional<User> user = repository.findById(id);

        if(user.isEmpty())
            throw new UserNotFoundException("id: "+ id);

        EntityModel<User> entityModel = EntityModel.of(user.get());

        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));

        return entityModel;
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUserById(@PathVariable int id) {

        repository.deleteById(id);
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrievePostsForUser(@PathVariable int id) {

        Optional<User> user = repository.findById(id);

        if(user.isEmpty())
            throw new UserNotFoundException("id: "+ id);

        return user.get().getPosts();
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser = repository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }


}
