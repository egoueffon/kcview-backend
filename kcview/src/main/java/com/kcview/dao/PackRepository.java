package com.kcview.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kcview.entity.Pack;

public interface PackRepository extends JpaRepository<Pack, Integer> {
	
	
}
