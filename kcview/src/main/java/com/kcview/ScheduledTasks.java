package com.kcview;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.thymeleaf.standard.expression.Each;

import com.kcview.controller.MailBilanRelanceHebdoClient;
import com.kcview.controller.MailClient;
import com.kcview.controller.SuiviRestService;
import com.kcview.dao.ClubRepository;
import com.kcview.dao.RelanceComptantRepository;
import com.kcview.dao.SuiviComptantRepository;
import com.kcview.dao.SuiviPeaRepository;
import com.kcview.dao.SuiviRibRepository;
import com.kcview.entity.Club;
import com.kcview.vo.BilanRelanceHebdo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class ScheduledTasks {
	private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Autowired
	private MailBilanRelanceHebdoClient mailClient;

	@Autowired
	private SuiviRestService suiviService;

	@Autowired
	private ClubRepository clubRepository;


	public void scheduleTaskWithFixedRate() {
	}

	public void scheduleTaskWithFixedDelay() {
	}

	public void scheduleTaskWithInitialDelay() {
	}

	@Scheduled(cron = "0 0 1 * * ?")
	public void scheduleTaskWithCronExpression() {
		System.out.println("1 er jour du mois");
	}

	@Scheduled(cron = "0 0 15 * * ?")
	public void scheduleTaskWithCronExpression2() {
		System.out.println("15 eme jour du mois");
	}

	// @Scheduled(cron = "0 14 * * * Sun")
	//@Scheduled(cron = "10 * * * * ?")
	public void scheduleBilanRelanceHebdo() {
		List<Club> clubs = clubRepository.findAll();
		List<BilanRelanceHebdo> bilansRelanceComptant = suiviService.getListBilanComptantHebdo(clubs);
		List<BilanRelanceHebdo> bilansRelancePea = suiviService.getListBilanPeaHebdo(clubs);
		List<BilanRelanceHebdo> bilansRelanceRib = suiviService.getListBilanRibHebdo(clubs);

		String recipient = "eric.goueffon@sofrecom.com,samah.goueffon@keepcool.fr";
		// when
		mailClient.prepareAndSend(recipient, "de la semaine", bilansRelanceComptant, bilansRelancePea,
				bilansRelanceRib);

	}

	// @Scheduled(cron = "0 0 20 28-31 * ?")
	//@Scheduled(cron = "10 * * * * ?")
	public void scheduleBilanRelanceMensuelle() {

		final Calendar c = Calendar.getInstance();
		if (c.get(Calendar.DATE) == c.getActualMaximum(Calendar.DATE)) {
			List<Club> clubs = clubRepository.findAll();
			List<BilanRelanceHebdo> bilansRelanceComptant = suiviService.getListBilanComptantMensuel(clubs);
			List<BilanRelanceHebdo> bilansRelancePea = suiviService.getListBilanPeaMensuel(clubs);
			List<BilanRelanceHebdo> bilansRelanceRib = suiviService.getListBilanRibMensuel(clubs);

			String recipient = "eric.goueffon@sofrecom.com,samah.goueffon@keepcool.fr";
			// when
			mailClient.prepareAndSend(recipient, "du mois", bilansRelanceComptant, bilansRelancePea, bilansRelanceRib);
		}

	}
}