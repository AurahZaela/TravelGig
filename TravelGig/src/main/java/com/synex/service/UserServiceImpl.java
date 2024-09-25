
package com.synex.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.synex.domain.User;
import com.synex.repository.UserRepository;



@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired 
	PasswordEncoder bCrypt;
	
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User save(User u) {
		
		String encryptedPassword = bCrypt.encode(u.getUserPassword());
        u.setUserPassword(encryptedPassword);
		User user = userRepository.save(u);
		return user;
	}

	@Override
	public User findByUserId(long uId) {
		Optional<User> u = userRepository.findById(uId);
		if(u.isPresent()) {
			return u.get();
		}
		else {
			return null;
		}
	}

	@Override
	public void deleteUserById(long uId) {
		userRepository.deleteById(uId);
		
	}

	@Override
	public User findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

	@Override
	public Long getNextUserId() {
		return userRepository.getNextSeriesId();
	}
}
