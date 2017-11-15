package com.kcview.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kcview.entity.SuiviComptant;

public interface RelanceComptantRepository extends JpaRepository<SuiviComptant, Integer> {
	 
}
