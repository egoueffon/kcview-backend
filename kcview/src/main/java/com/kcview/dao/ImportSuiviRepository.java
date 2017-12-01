package com.kcview.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kcview.entity.ImportSuivi;


public interface ImportSuiviRepository extends JpaRepository<ImportSuivi, Integer> {
	@Query("SELECT p FROM ImportSuivi p WHERE id_club = :clubId and type = :typeSuivi and p.id = (select MAX(c.id) from ImportSuivi c WHERE id_club = :clubId and type = :typeSuivi)")
	 public ImportSuivi lastImportComptant(@Param("clubId") int clubId, @Param("typeSuivi") String typeSuivi);
}
