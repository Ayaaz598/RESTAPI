package com.example.demo.services.imply;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.models.users;
import com.example.demo.payload.user_payload;
import com.example.demo.repository.users_repository;
import com.example.demo.services.users_services;
@Service
public class users_services_imply implements users_services {
	@Autowired
	users_repository repo;
	@Autowired
	ModelMapper modelmapper;
	@Override
	public user_payload addusers(user_payload up) {
		users ui = this.dto_users(up); 
		users saveusers = this.repo.save(ui);
		return this.users_dto(saveusers);
	}
	@Override
	public user_payload updateusers(user_payload up, int id) {
		users usr = this.repo.findById(id).orElseThrow(()->new ResourceNotFoundException("users", "id", id));
		usr.setName(up.getName());
		usr.setEmail(up.getEmail());
		usr.setPassword(up.getPassword());
		users usr1 = this.repo.save(usr);
		user_payload up1 = this.users_dto(usr1);
		return up1;	
	}
	@Override
	public void deleteusers(int id) {
		users usr = this.repo.findById(id).orElseThrow(()->new ResourceNotFoundException("users", "id", id));
		this.repo.delete(usr);
	}
	@Override
	public List<user_payload> getallusers() {
		List<users> usr = (List<users>) this.repo.findAll();
		List<user_payload> usrpl = usr.stream().map(user-> this.users_dto(user)).collect(Collectors.toList());
		return usrpl;
	}
	@Override
	public user_payload getbyid(int id) {
		users usr = this.repo.findById(id).orElseThrow(()->new ResourceNotFoundException("users", "id", id));
		return this.users_dto(usr);
	}
	public users dto_users(user_payload up) {
		users u = this.modelmapper.map(up, users.class);
		return u;
	}
	public user_payload users_dto(users u) {
		user_payload udup = this.modelmapper.map(u, user_payload.class);
		return udup;
	}

}
