package com.kcview.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kcview.entity.Employe;
import com.kcview.entity.SuiviComptant;

public interface SuiviComptantRepository extends JpaRepository<SuiviComptant, Integer> {
	 @Query("SELECT count(*) FROM SuiviComptant p WHERE num_adherent = :numAdherent AND date_expiration = :dateExpiration")
	 public Integer findOneEnCours(@Param("numAdherent") double numAdherent, @Param("dateExpiration") Date date_expiration);
}
