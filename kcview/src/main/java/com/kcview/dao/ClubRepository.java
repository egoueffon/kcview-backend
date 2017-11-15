package com.kcview.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kcview.entity.Club;

public interface ClubRepository extends JpaRepository<Club, Integer> {
	
	@Query("SELECT count(o) FROM Operation o, Fiche f where o.fiche.id = f.id and MONTH(f.jour) = :month and YEAR(f.jour) = :year AND club_id = :clubId and o.type = 'abonnement'")
	 public Integer countClubAbosByMonth(@Param("clubId") int clubId, @Param("month") int month, @Param("year") int year);

	@Query("SELECT count(o) FROM Operation o, Fiche f where o.fiche.id = f.id and DAY(f.jour) <= :day and MONTH(f.jour) = :month and YEAR(f.jour) = :year AND club_id = :clubId and o.type = 'abonnement'")
	public Integer countClubAbosByMonthAtDay(@Param("clubId") int clubId, @Param("day") int day, @Param("month") int month, @Param("year") int year);

	@Query("SELECT count(o) FROM Operation o, Fiche f where o.fiche.id = f.id and weekofyear(f.jour) = :week and YEAR(f.jour) = :year AND club_id = :clubId and o.type = 'abonnement'")
	 public Integer countClubAbosByWeek(@Param("clubId") int clubId, @Param("week") int week, @Param("year") int year);

	@Query("SELECT count(o) FROM Operation o, Fiche f where o.fiche.id = f.id and DAY(f.jour) <= :day and weekofyear(f.jour) = :week and YEAR(f.jour) = :year AND club_id = :clubId and o.type = 'abonnement'")
	 public Integer countClubAbosByWeekAtDay(@Param("clubId") int clubId, @Param("day") int day, @Param("week") int week, @Param("year") int year);

	@Query("SELECT count(o) FROM Operation o, Fiche f where o.fiche.id = f.id and DAY(f.jour) = :day and MONTH(f.jour) = :month and YEAR(f.jour) = :year AND club_id = :clubId and o.type = 'abonnement'")
	public Integer countClubAbosByDay(@Param("clubId") int clubId, @Param("day") int day, @Param("month") int month, @Param("year") int year);

	@Query("SELECT day(f.jour) as jour, count(*) as abos, f.vp as vps FROM Operation o, Fiche f where o.fiche.id = f.id and MONTH(f.jour) = :month  and YEAR(f.jour) = :year and club_id = :clubId and o.type = 'abonnement' group by f.jour, vp")
	public List<Object[]> countClubAbosVPByDayForMonth(@Param("clubId") int clubId, @Param("month") int month, @Param("year") int year);

	@Query("SELECT day(f.jour) as jour, count(*) as abos, f.vp as vps FROM Operation o, Fiche f where o.fiche.id = f.id and weekofyear(f.jour) = :week  and YEAR(f.jour) = :year and club_id = :clubId and o.type = 'abonnement' group by f.jour, vp")
	public List<Object[]> countClubAbosVPByDayForWeek(@Param("clubId") int clubId, @Param("week") int week, @Param("year") int year);

	
	@Query("SELECT ROUND(SUM(e.montant_encaisse)) FROM Operation o, Fiche f, Encaissement e where o.fiche.id = f.id and e.operation.id = o.id and MONTH(f.jour) = :month and YEAR(f.jour) = :year AND club_id = :clubId")
	 public BigDecimal getCaByMonth(@Param("clubId") int clubId, @Param("month") int month, @Param("year") int year);

	@Query("SELECT ROUND(SUM(e.montant_encaisse)) FROM Operation o, Fiche f, Encaissement e where o.fiche.id = f.id and e.operation.id = o.id and DAY(f.jour) <= :day and MONTH(f.jour) = :month and YEAR(f.jour) = :year AND club_id = :clubId")
	 public BigDecimal getCaByMonthAtDay(@Param("clubId") int clubId, @Param("day") int day, @Param("month") int month, @Param("year") int year);

	@Query("SELECT ROUND(SUM(e.montant_encaisse)) FROM Operation o, Fiche f, Encaissement e where o.fiche.id = f.id and e.operation.id = o.id and weekofyear(f.jour) = :week and YEAR(f.jour) = :year AND club_id = :clubId")
	 public BigDecimal getCaByWeek(@Param("clubId") int clubId, @Param("week") int week, @Param("year") int year);

	@Query("SELECT ROUND(SUM(e.montant_encaisse)) FROM Operation o, Fiche f, Encaissement e where o.fiche.id = f.id and e.operation.id = o.id and DAY(f.jour) <= :day and weekofyear(f.jour) = :week and YEAR(f.jour) = :year AND club_id = :clubId")
	 public BigDecimal getCaByWeekAtDay(@Param("clubId") int clubId, @Param("day") int day, @Param("week") int week, @Param("year") int year);

	

}
