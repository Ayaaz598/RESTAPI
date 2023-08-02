package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.users;

@Repository
public interface users_repository extends  CrudRepository<users, Integer> {
	
}
