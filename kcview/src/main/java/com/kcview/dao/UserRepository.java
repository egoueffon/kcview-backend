package com.kcview.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kcview.entity.Employe;

public interface UserRepository extends JpaRepository<Employe, Integer> {
	
	 @Query("SELECT p.id FROM Employe p WHERE login = :lastName AND password = :password")
	 public Integer findByLoginPassword(@Param("lastName") String lastName, @Param("password") String password);


}
