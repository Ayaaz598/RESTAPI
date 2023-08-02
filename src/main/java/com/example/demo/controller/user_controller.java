package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.APIResponse;
import com.example.demo.models.users;
import com.example.demo.payload.user_payload;
import com.example.demo.services.users_services;

import jakarta.validation.Valid;
@RestController
@RequestMapping("api/v1/")
public class user_controller {
	@Autowired
	users_services serve;
	@PostMapping("/insert")
	public ResponseEntity<user_payload>addusers(@Valid @RequestBody user_payload up){
		user_payload user = this.serve.addusers(up);
		return new ResponseEntity<>(user,HttpStatus.CREATED);
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<user_payload>updateusers(@Valid @PathVariable int id, @RequestBody user_payload usrp){
		user_payload usrpl = this.serve.updateusers(usrp, id);
		return ResponseEntity.ok(usrpl);
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<APIResponse> delete(@Valid @PathVariable int id){
		this.serve.deleteusers(id);
		return new ResponseEntity<APIResponse>(new APIResponse("ID Deleted Successfully",true),HttpStatus.OK);
	}
	@GetMapping("/getall")
	public ResponseEntity<List<user_payload>> getall(){
		return ResponseEntity.ok(this.serve.getallusers());
	}
	@GetMapping("/getbyid/{id}")
	public ResponseEntity<user_payload> getbyid(@Valid @PathVariable int id){
		return ResponseEntity.ok(this.serve.getbyid(id));
	}
}
