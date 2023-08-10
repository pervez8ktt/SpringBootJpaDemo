package com.knitkota.jpademo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knitkota.jpademo.dto.UserDto;
import com.knitkota.jpademo.entity.AddressEntity;
import com.knitkota.jpademo.entity.UserEntity;
import com.knitkota.jpademo.entity.UserRepository;

import jakarta.annotation.PostConstruct;

@Service
public class ServiceClass {

	@Autowired
	private UserRepository userRepository;

	@PostConstruct
	public void init() {

		addUser("User one", 1);

		List<UserEntity> userEntities = userRepository.findAllJoinFetch();

		printUserInfo(userEntities);
		
		List<UserDto> userDtos = userRepository.findAllJoinFetchDto();
		
		printUserInfoDto(userDtos);
	}

	public void printUserInfo(List<UserEntity> userEntities) {

		for (UserEntity userEntity : userEntities) {
			System.out.println("Name: " + userEntity.getName());
			for (AddressEntity addressEntity : userEntity.getAddressEntities()) {
				System.out.println("City: " + addressEntity.getCity());
			}
		}

	}

	public void printUserInfoDto(List<UserDto> userDtos) {

		for (UserDto userDto : userDtos) {
			System.out.println("Name: " + userDto.getName());
			System.out.println("City: " + userDto.getCity());

		}

	}

	public void addUser(String name, int age) {
		UserEntity userEntity = new UserEntity();
		userEntity.setName(name);
		userEntity.setAge(age);
		List<AddressEntity> addressEntities = new ArrayList<AddressEntity>();

		addressEntities.add(addAddress("line", "city", userEntity));

		userEntity.setAddressEntities(addressEntities);

		userRepository.save(userEntity);

	}

	public AddressEntity addAddress(String line, String city, UserEntity userEntity) {
		AddressEntity addressEntity = new AddressEntity();
		addressEntity.setLine1(line);
		addressEntity.setCity(city);
		addressEntity.setUserEntity(userEntity);

		return addressEntity;
	}

}
