package com.kcview.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kcview.entity.Club;
import com.kcview.entity.ObjectifMensuel;

public interface ObjectifMensuelRepository extends JpaRepository<ObjectifMensuel, Integer> {

	@Query("SELECT o FROM ObjectifMensuel o where mois = :month and annee = :year AND id_club = :clubId")
	public ObjectifMensuel findObjectifMensuel(@Param("clubId") int clubId, @Param("month") int month, @Param("year") int year);
	
	
	
}
