package com.shaurya.rest.webservices.restful_web_services.repository;

import com.shaurya.rest.webservices.restful_web_services.Post;
//import com.shaurya.rest.webservices.restful_web_services.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
