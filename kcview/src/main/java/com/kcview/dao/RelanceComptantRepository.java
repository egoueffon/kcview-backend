package com.kcview.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kcview.entity.RelanceComptant;

public interface RelanceComptantRepository extends JpaRepository<RelanceComptant, Integer> {
	
	 @Query("SELECT count(*) FROM RelanceComptant where suivi.id_club = :clubId and FUNCTION('week', date_relance) = FUNCTION('week', :dateDeb)")
	 public Integer countInWeekFor(@Param("clubId") int clubId, @Param("dateDeb") Date dateDeb);
	 
	 @Query("SELECT count(*) FROM RelanceComptant where suivi.id_club = :clubId and FUNCTION('month', date_relance) = FUNCTION('month', :dateDeb)")
	 public Integer countInMonthFor(@Param("clubId") int clubId, @Param("dateDeb") Date dateDeb);
	 
}
