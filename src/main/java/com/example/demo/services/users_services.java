package com.example.demo.services;

import java.util.List;

import com.example.demo.payload.user_payload;

public interface users_services {
	user_payload addusers(user_payload up);
	user_payload updateusers(user_payload up,int id);
	void deleteusers(int id);
	List<user_payload>getallusers();
	user_payload getbyid(int id);
}
