package com.kcview.dao;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kcview.entity.Club;
import com.kcview.entity.Fiche;
import com.kcview.entity.ObjectifMensuel;

public interface FicheRepository extends JpaRepository<Fiche, Integer> {

	@Query("Select f FROM Fiche f WHERE f.club_id = :clubId  AND JOUR = :date")
	public Fiche findFiche(@Param("clubId") int clubId, @Param("date") Date date);
	
	@Query("SELECT ROUND(SUM(e.montant_encaisse),2) FROM Operation o, Fiche f, Encaissement e where o.fiche.id = f.id and e.operation.id = o.id and f.id = :ficheId")
	public BigDecimal getEncaissement(@Param("ficheId") int ficheId);
	
	@Query("SELECT ROUND(SUM(e.montant_encaisse),2) FROM Operation o, Fiche f, Encaissement e where o.fiche.id = f.id and e.operation.id = o.id and f.id = :ficheId and e.mode_versement = 'CB'")
	public BigDecimal getEncaissementCB(@Param("ficheId") int ficheId);
	
	@Query("SELECT ROUND(SUM(e.montant_encaisse),2) FROM Operation o, Fiche f, Encaissement e where o.fiche.id = f.id and e.operation.id = o.id and f.id = :ficheId and e.mode_versement = 'CHQ'")
	public BigDecimal getEncaissementCheque(@Param("ficheId") int ficheId);
	
	@Query("SELECT ROUND(SUM(e.montant_encaisse),2) FROM Operation o, Fiche f, Encaissement e where o.fiche.id = f.id and e.operation.id = o.id and f.id = :ficheId and e.mode_versement = 'ESP'")
	public BigDecimal getEncaissementEspece(@Param("ficheId") int ficheId);
	
	@Query("SELECT ROUND(SUM(e.montant_encaisse),2) FROM Operation o, Fiche f, Encaissement e where o.fiche.id = f.id and e.operation.id = o.id and f.id = :ficheId and e.mode_versement not in('ESP', 'CHQ', 'CB')")
	public BigDecimal getEncaissementAutre(@Param("ficheId") int ficheId);

	
}
