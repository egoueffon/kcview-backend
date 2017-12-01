package com.kcview.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kcview.entity.SuiviComptant;
import com.kcview.vo.BilanRelanceHebdo;

public interface SuiviComptantRepository extends JpaRepository<SuiviComptant, Integer> {
	 @Query("SELECT count(*) FROM SuiviComptant p WHERE num_adherent = :numAdherent AND id_club = :clubId AND date_expiration = :dateExpiration")
	 public Integer findOneEnCours(@Param("clubId") int clubId, @Param("numAdherent") double numAdherent, @Param("dateExpiration") Date date_expiration);
	 
	 @Query("SELECT count(*) FROM SuiviComptant p WHERE id_club = :clubId and statut = 0")
	 public Integer countEncoursByClub(@Param("clubId") int clubId);
	 
	 @Query("SELECT count(*) FROM SuiviComptant where id_club = :clubId and statut=:statut and FUNCTION('week', date_changement_statut) = FUNCTION('week', :dateDeb)")
	 public Integer countOfStatutInWeek(@Param("clubId") int clubId, @Param("dateDeb") Date dateDeb, @Param("statut") int statut);

	 @Query("SELECT count(*) FROM SuiviComptant where id_club = :clubId and statut=:statut and FUNCTION('month', date_changement_statut) = FUNCTION('month', :dateDeb)")
	 public Integer countOfStatutInMonth(@Param("clubId") int clubId, @Param("dateDeb") Date dateDeb, @Param("statut") int statut);

}
