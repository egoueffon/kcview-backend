package com.kcview.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kcview.entity.ImportSuivi;


public interface ImportSuiviRepository extends JpaRepository<ImportSuivi, Integer> {
	@Query("SELECT p FROM ImportSuivi p WHERE id_club = 8 and type = 'comptant' and p.id = (select MAX(c.id) from ImportSuivi c WHERE id_club = 8 and type = 'comptant')")
	 public ImportSuivi lastImportComptant();
}
