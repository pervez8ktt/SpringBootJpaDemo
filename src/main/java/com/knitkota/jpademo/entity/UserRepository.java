package com.knitkota.jpademo.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.knitkota.jpademo.dto.UserDto;

public interface UserRepository extends JpaRepository<UserEntity, Long>{

	
	@Query("from UserEntity u join fetch u.addressEntities a")
	List<UserEntity> findAllJoinFetch(); 

	
	@Query("select new com.knitkota.jpademo.dto.UserDto(u.name, a.city) from UserEntity u join fetch u.addressEntities a")
	List<UserDto> findAllJoinFetchDto();
}
