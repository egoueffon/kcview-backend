package com.kcview.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.net.MediaType;
import com.kcview.dao.ImportSuiviRepository;
import com.kcview.dao.RelanceComptantRepository;
import com.kcview.dao.RelancePeaRepository;
import com.kcview.dao.RelanceRibRepository;
import com.kcview.dao.SuiviComptantRepository;
import com.kcview.dao.SuiviPeaRepository;
import com.kcview.dao.SuiviRibRepository;
import com.kcview.entity.Club;
import com.kcview.entity.ImportSuivi;
import com.kcview.entity.RelanceComptant;
import com.kcview.entity.RelancePEA;
import com.kcview.entity.RelanceRib;
import com.kcview.entity.SuiviComptant;
import com.kcview.entity.SuiviPEA;
import com.kcview.entity.SuiviRib;
import com.kcview.vo.BilanRelanceHebdo;

@Controller
@RequestMapping(path = "/api/v1/", produces = APPLICATION_JSON_VALUE)
public class SuiviRestService {

	@Autowired
	private SuiviComptantRepository suiviComptantRepository;

	@Autowired
	private SuiviPeaRepository suiviPeaRepository;

	@Autowired
	private SuiviRibRepository suiviRibRepository;

	@Autowired
	private ImportSuiviRepository importSuiviRepository;

	@Autowired
	private RelanceComptantRepository relanceComptantRepository;
	
	@Autowired
	private RelancePeaRepository relancePeaRepository;
	
	@Autowired
	private RelanceRibRepository relanceRibRepository;

	// Save the uploaded file to this folder
	private static String UPLOADED_FOLDER = "D://";

	@CrossOrigin(origins = "*")
	@GetMapping("suivisComptant")
	public ResponseEntity<List<SuiviComptant>> getSuivisComptant(Integer clubId) {

		SuiviComptant sc = new SuiviComptant();
		sc.setId_club(clubId);
		Example<SuiviComptant> example = Example.of(sc);

		List<SuiviComptant> list = suiviComptantRepository.findAll(example);
		return new ResponseEntity<List<SuiviComptant>>(list, HttpStatus.OK);
	}

	@CrossOrigin(origins = "*")
	@GetMapping("suivisPEA")
	public ResponseEntity<List<SuiviPEA>> getSuivisPEA(Integer clubId) {

		SuiviPEA sc = new SuiviPEA();
		sc.setId_club(clubId);
		Example<SuiviPEA> example = Example.of(sc);

		List<SuiviPEA> list = suiviPeaRepository.findAll(example);
		// List<SuiviPEA> list = suiviPeaRepository.findAllById(clubId);
		return new ResponseEntity<List<SuiviPEA>>(list, HttpStatus.OK);
	}

	@CrossOrigin(origins = "*")
	@GetMapping("suivisRIB")
	public ResponseEntity<List<SuiviRib>> getSuivisRib(int clubId) {
		SuiviRib sc = new SuiviRib();
		sc.setId_club(clubId);
		Example<SuiviRib> example = Example.of(sc);

		List<SuiviRib> list = suiviRibRepository.findAll(example);
		return new ResponseEntity<List<SuiviRib>>(list, HttpStatus.OK);
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/saveSuiviComptant", method = { GET, POST }, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<SuiviComptant> saveSuiviComptant(@RequestBody SuiviComptant suiviComptant) {

		for (RelanceComptant el : suiviComptant.getRelances()) {
			el.setSuivi(suiviComptant);
		}

		SuiviComptant sc = suiviComptantRepository.save(suiviComptant);

		return new ResponseEntity<SuiviComptant>(sc, HttpStatus.OK);
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/saveSuiviRIB", method = { GET, POST }, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<SuiviRib> saveSuiviRib(@RequestBody SuiviRib suiviRib) {

		for (RelanceRib el : suiviRib.getRelances()) {
			el.setSuivi(suiviRib);
		}

		SuiviRib sc = suiviRibRepository.save(suiviRib);

		return new ResponseEntity<SuiviRib>(sc, HttpStatus.OK);
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/saveSuiviPEA", method = { GET, POST }, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<SuiviPEA> saveSuiviPea(@RequestBody SuiviPEA suiviPea) {

		for (RelancePEA el : suiviPea.getRelances()) {
			el.setSuivi(suiviPea);
		}

		SuiviPEA sc = suiviPeaRepository.save(suiviPea);

		return new ResponseEntity<SuiviPEA>(sc, HttpStatus.OK);
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/validateSuiviComptant", method = { GET, POST }, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<SuiviComptant> validateSuiviComptant(@RequestBody SuiviComptant suiviComptant) {

		suiviComptant.setStatut(1);
		suiviComptant.setDate_changement_statut(Calendar.getInstance().getTime());

		for (RelanceComptant el : suiviComptant.getRelances()) {
			el.setSuivi(suiviComptant);
		}

		SuiviComptant sc = suiviComptantRepository.save(suiviComptant);
		return new ResponseEntity<SuiviComptant>(sc, HttpStatus.OK);
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/cancelSuiviComptant", method = { GET, POST }, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<SuiviComptant> canceledSuiviComptant(@RequestBody SuiviComptant suiviComptant) {

		suiviComptant.setStatut(2);
		suiviComptant.setDate_changement_statut(Calendar.getInstance().getTime());

		for (RelanceComptant el : suiviComptant.getRelances()) {
			el.setSuivi(suiviComptant);
		}

		SuiviComptant sc = suiviComptantRepository.save(suiviComptant);
		return new ResponseEntity<SuiviComptant>(sc, HttpStatus.OK);
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/validateSuiviPEA", method = { GET, POST }, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<SuiviPEA> validateSuiviPea(@RequestBody SuiviPEA suiviPea) {

		suiviPea.setStatut(1);
		suiviPea.setDate_changement_statut(Calendar.getInstance().getTime());

		for (RelancePEA el : suiviPea.getRelances()) {
			el.setSuivi(suiviPea);
		}

		SuiviPEA sc = suiviPeaRepository.save(suiviPea);
		return new ResponseEntity<SuiviPEA>(sc, HttpStatus.OK);
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/validateSuiviRIB", method = { GET, POST }, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<SuiviRib> validateSuiviRib(@RequestBody SuiviRib suiviRib) {

		suiviRib.setStatut(1);
		suiviRib.setDate_changement_statut(Calendar.getInstance().getTime());

		for (RelanceRib el : suiviRib.getRelances()) {
			el.setSuivi(suiviRib);
		}

		SuiviRib sc = suiviRibRepository.save(suiviRib);
		return new ResponseEntity<SuiviRib>(sc, HttpStatus.OK);
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/cancelSuiviPEA", method = { GET, POST }, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<SuiviPEA> canceledSuiviPea(@RequestBody SuiviPEA suiviPea) {

		suiviPea.setStatut(2);
		suiviPea.setDate_changement_statut(Calendar.getInstance().getTime());

		for (RelancePEA el : suiviPea.getRelances()) {
			el.setSuivi(suiviPea);
		}

		SuiviPEA sc = suiviPeaRepository.save(suiviPea);
		return new ResponseEntity<SuiviPEA>(sc, HttpStatus.OK);
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/cancelSuiviRIB", method = { GET, POST }, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<SuiviRib> canceledSuiviPea(@RequestBody SuiviRib suiviRib) {

		suiviRib.setStatut(2);
		suiviRib.setDate_changement_statut(Calendar.getInstance().getTime());

		for (RelanceRib el : suiviRib.getRelances()) {
			el.setSuivi(suiviRib);
		}

		SuiviRib sc = suiviRibRepository.save(suiviRib);
		return new ResponseEntity<SuiviRib>(sc, HttpStatus.OK);
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/lastImportSuiviComptant", method = { GET, POST }, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<ImportSuivi> lastImportSuiviComptant(int clubId) {
		ImportSuivi d = importSuiviRepository.lastImportComptant(clubId, "comptant");
		return new ResponseEntity<ImportSuivi>(d, HttpStatus.OK);
	}

	@CrossOrigin(origins = "*")
	@GetMapping(value = "/countSuiviComptantEncours")
	public ResponseEntity<Integer> countSuiviComptantEncours(Integer clubId) {
		Integer d = suiviComptantRepository.countEncoursByClub(clubId);
		return new ResponseEntity<Integer>(d, HttpStatus.OK);
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/countSuiviPeaEncours", method = { GET, POST }, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> countSuiviPeaEncours(int clubId) {
		Integer d = suiviPeaRepository.countEncoursByClub(clubId);
		return new ResponseEntity<Integer>(d, HttpStatus.OK);
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/countSuiviRibEncours", method = { GET, POST }, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> countSuiviRibEncours(int clubId) {
		Integer d = suiviRibRepository.countEncoursByClub(clubId);
		return new ResponseEntity<Integer>(d, HttpStatus.OK);
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/lastImportSuiviPEA", method = { GET, POST }, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<ImportSuivi> lastImportSuiviPea(int clubId) {
		ImportSuivi d = importSuiviRepository.lastImportComptant(clubId, "pea");
		return new ResponseEntity<ImportSuivi>(d, HttpStatus.OK);
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/lastImportSuiviRIB", method = { GET, POST }, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<ImportSuivi> lastImportSuiviRib(int clubId) {
		ImportSuivi d = importSuiviRepository.lastImportComptant(clubId, "rib");
		return new ResponseEntity<ImportSuivi>(d, HttpStatus.OK);
	}

	@Autowired
	private MailClient mailClient;

	@CrossOrigin(origins = "*")
	@RequestMapping("/uploadSuivisComptant")
	public ResponseEntity<Integer> uploadSuivisComptant(int clubId, @RequestParam("file") MultipartFile file) {

		List<SuiviComptant> suivis = new ArrayList<>();

		if (file.isEmpty()) {

			// return "redirect:uploadStatus";
		}

		ImportSuivi myImport = new ImportSuivi();
		myImport.setType("comptant");
		myImport.setId_club(clubId);

		try {

			Sheet sheet = getSheetFromFile(file);

			for (Row row : sheet) {

				if (row.getRowNum() > 1) {

					SuiviComptant sc = new SuiviComptant(row, clubId);

					if (suiviComptantRepository.findOneEnCours(clubId, sc.getNum_adherent(),
							sc.getDate_expiration()) == 0) {
						suiviComptantRepository.save(sc);
						suivis.add(sc);
					} else {
						suivis.add(sc);
						System.out.println("en cours");
					}
				}
			}

			importSuiviRepository.save(myImport);

		} catch (IOException e) {
			e.printStackTrace();
		}

		String recipient = "eric.goueffon@sofrecom.com";
		String message = "VH";
		// when
		mailClient.prepareAndSend(recipient, message, suivis);

		return new ResponseEntity<Integer>(suivis.size(), HttpStatus.OK);
	}

	private Sheet getSheetFromFile(MultipartFile file) throws IOException {
		// Get the file and save it somewhere
		byte[] bytes = file.getBytes();
		Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
		Files.write(path, bytes);
		FileInputStream in = new FileInputStream(UPLOADED_FOLDER + file.getOriginalFilename());
		Workbook workbook = new XSSFWorkbook(in);
		return workbook.getSheetAt(0);

	}

	@CrossOrigin(origins = "*")
	@RequestMapping("/uploadSuivisPEA")
	public ResponseEntity<Integer> uploadSuivisPEA(int clubId, @RequestParam("file") MultipartFile file) {

		List<SuiviPEA> suivis = new ArrayList<>();

		if (file.isEmpty()) {

			// return "redirect:uploadStatus";
		}

		ImportSuivi myImport = new ImportSuivi();
		myImport.setType("pea");
		myImport.setId_club(clubId);

		try {

			// Get the file and save it somewhere
			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
			Files.write(path, bytes);
			FileInputStream in = new FileInputStream(UPLOADED_FOLDER + file.getOriginalFilename());
			Workbook workbook = new XSSFWorkbook(in);
			Sheet sheet = workbook.getSheetAt(0);

			// to evaluate cell type
			FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();

			for (Row row : sheet) {

				if (row.getRowNum() > 1) {

					double encours = row.getCell(9).getNumericCellValue();

					if (encours < 0) {
						SuiviPEA sc = new SuiviPEA();
						sc.setNum_adherent(Double.parseDouble(row.getCell(0).getStringCellValue()));
						sc.setNom_adherent(row.getCell(1).getStringCellValue());
						sc.setPrenom_adherent(row.getCell(2).getStringCellValue());
						sc.setEncours(new BigDecimal(encours));
						sc.setId_club(clubId);
						sc.setStatut(0);

						suiviPeaRepository.save(sc);
						suivis.add(sc);

					}
				}
			}

			importSuiviRepository.save(myImport);

		} catch (IOException e) {
			e.printStackTrace();
		}

		// given
		String recipient = "eric.goueffon@sofrecom.com";
		String message = "VH";
		// when
		// mailClient.prepareAndSend(recipient, message, suivis);

		return new ResponseEntity<Integer>(suivis.size(), HttpStatus.OK);
	}

	@CrossOrigin(origins = "*")
	@RequestMapping("/uploadSuivisRib")
	public ResponseEntity<Integer> uploadSuivisRib(int clubId, @RequestParam("file") MultipartFile file) {

		List<SuiviRib> suivis = new ArrayList<>();

		if (file.isEmpty()) {

			// return "redirect:uploadStatus";
		}

		ImportSuivi myImport = new ImportSuivi();
		myImport.setType("rib");
		myImport.setId_club(clubId);

		try {

			// Get the file and save it somewhere
			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
			Files.write(path, bytes);
			FileInputStream in = new FileInputStream(UPLOADED_FOLDER + file.getOriginalFilename());
			Workbook workbook = new XSSFWorkbook(in);
			Sheet sheet = workbook.getSheetAt(0);

			for (Row row : sheet) {

				if (row.getRowNum() > 1) {

					SuiviRib sc = new SuiviRib();
					sc.setNum_adherent(row.getCell(0).getNumericCellValue());
					sc.setNom_adherent(row.getCell(1).getStringCellValue());
					sc.setPrenom_adherent(row.getCell(2).getStringCellValue());
					sc.setDate_inscription(row.getCell(3).getDateCellValue());
					System.out.println(clubId);
					sc.setId_club(clubId);
					sc.setStatut(0);

					suiviRibRepository.save(sc);
					suivis.add(sc);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// given
		String recipient = "eric.goueffon@sofrecom.com";
		String message = "VH";
		// when
		// mailClient.prepareAndSend(recipient, message, suivis);

		importSuiviRepository.save(myImport);

		return new ResponseEntity<Integer>(suivis.size(), HttpStatus.OK);
	}

	public List<BilanRelanceHebdo> getListBilanComptantHebdo(List<Club> clubs) {

		List<BilanRelanceHebdo> bilansRelanceComptant = new ArrayList<>();

		for (Club club : clubs) {

			// nb relance faite sur la semaine
			Integer nbRelances = relanceComptantRepository.countInWeekFor(club.getId(),
					Calendar.getInstance().getTime());
			Integer nbSuiviValide = suiviComptantRepository.countOfStatutInWeek(club.getId(),
					Calendar.getInstance().getTime(), 1);
			Integer nbSuiviAbandonne = suiviComptantRepository.countOfStatutInWeek(club.getId(),
					Calendar.getInstance().getTime(), 2);
			Integer nbSuiviEncours = suiviComptantRepository.countEncoursByClub(club.getId());

			BilanRelanceHebdo bilan = new BilanRelanceHebdo();
			bilan.setClub(club);
			bilan.setNbRelance(nbRelances);
			bilan.setNbSuiviAbandonne(nbSuiviAbandonne);
			bilan.setNbSuiviValide(nbSuiviValide);
			bilan.setNbSuiviOuvert(nbSuiviEncours);

			bilansRelanceComptant.add(bilan);

		}
		return bilansRelanceComptant;

	}

	public List<BilanRelanceHebdo> getListBilanPeaHebdo(List<Club> clubs) {

		List<BilanRelanceHebdo> bilansRelancePea = new ArrayList<>();

		for (Club club : clubs) {

			// nb relance faite sur la semaine
			Integer nbRelances = relancePeaRepository.countInWeekFor(club.getId(),
					Calendar.getInstance().getTime());
			Integer nbSuiviValide = suiviPeaRepository.countOfStatutInWeek(club.getId(),
					Calendar.getInstance().getTime(), 1);
			Integer nbSuiviAbandonne = suiviPeaRepository.countOfStatutInWeek(club.getId(),
					Calendar.getInstance().getTime(), 2);
			Integer nbSuiviEncours = suiviPeaRepository.countEncoursByClub(club.getId());

			BilanRelanceHebdo bilan = new BilanRelanceHebdo();
			bilan.setClub(club);
			bilan.setNbRelance(nbRelances);
			bilan.setNbSuiviAbandonne(nbSuiviAbandonne);
			bilan.setNbSuiviValide(nbSuiviValide);
			bilan.setNbSuiviOuvert(nbSuiviEncours);

			bilansRelancePea.add(bilan);

		}
		return bilansRelancePea;

	}

	public List<BilanRelanceHebdo> getListBilanRibHebdo(List<Club> clubs) {

		List<BilanRelanceHebdo> bilansRelanceRib = new ArrayList<>();

		for (Club club : clubs) {

			// nb relance faite sur la semaine
			Integer nbRelances = relanceRibRepository.countInWeekFor(club.getId(),
					Calendar.getInstance().getTime());
			Integer nbSuiviValide = suiviRibRepository.countOfStatutInWeek(club.getId(),
					Calendar.getInstance().getTime(), 1);
			Integer nbSuiviAbandonne = suiviRibRepository.countOfStatutInWeek(club.getId(),
					Calendar.getInstance().getTime(), 2);
			Integer nbSuiviEncours = suiviRibRepository.countEncoursByClub(club.getId());

			BilanRelanceHebdo bilan = new BilanRelanceHebdo();
			bilan.setClub(club);
			bilan.setNbRelance(nbRelances);
			bilan.setNbSuiviAbandonne(nbSuiviAbandonne);
			bilan.setNbSuiviValide(nbSuiviValide);
			bilan.setNbSuiviOuvert(nbSuiviEncours);

			bilansRelanceRib.add(bilan);

		}
		return bilansRelanceRib;

	}
	
	public List<BilanRelanceHebdo> getListBilanComptantMensuel(List<Club> clubs) {

		List<BilanRelanceHebdo> bilansRelanceComptant = new ArrayList<>();

		for (Club club : clubs) {

			// nb relance faite sur la semaine
			Integer nbRelances = relanceComptantRepository.countInMonthFor(club.getId(),
					Calendar.getInstance().getTime());
			Integer nbSuiviValide = suiviComptantRepository.countOfStatutInMonth(club.getId(),
					Calendar.getInstance().getTime(), 1);
			Integer nbSuiviAbandonne = suiviComptantRepository.countOfStatutInMonth(club.getId(),
					Calendar.getInstance().getTime(), 2);
			Integer nbSuiviEncours = suiviComptantRepository.countEncoursByClub(club.getId());

			BilanRelanceHebdo bilan = new BilanRelanceHebdo();
			bilan.setClub(club);
			bilan.setNbRelance(nbRelances);
			bilan.setNbSuiviAbandonne(nbSuiviAbandonne);
			bilan.setNbSuiviValide(nbSuiviValide);
			bilan.setNbSuiviOuvert(nbSuiviEncours);

			bilansRelanceComptant.add(bilan);

		}
		return bilansRelanceComptant;

	}

	public List<BilanRelanceHebdo> getListBilanPeaMensuel(List<Club> clubs) {

		List<BilanRelanceHebdo> bilansRelancePea = new ArrayList<>();

		for (Club club : clubs) {

			// nb relance faite sur la semaine
			Integer nbRelances = relancePeaRepository.countInMonthFor(club.getId(),
					Calendar.getInstance().getTime());
			Integer nbSuiviValide = suiviPeaRepository.countOfStatutInMonth(club.getId(),
					Calendar.getInstance().getTime(), 1);
			Integer nbSuiviAbandonne = suiviPeaRepository.countOfStatutInMonth(club.getId(),
					Calendar.getInstance().getTime(), 2);
			Integer nbSuiviEncours = suiviPeaRepository.countEncoursByClub(club.getId());

			BilanRelanceHebdo bilan = new BilanRelanceHebdo();
			bilan.setClub(club);
			bilan.setNbRelance(nbRelances);
			bilan.setNbSuiviAbandonne(nbSuiviAbandonne);
			bilan.setNbSuiviValide(nbSuiviValide);
			bilan.setNbSuiviOuvert(nbSuiviEncours);

			bilansRelancePea.add(bilan);

		}
		return bilansRelancePea;

	}

	public List<BilanRelanceHebdo> getListBilanRibMensuel(List<Club> clubs) {

		List<BilanRelanceHebdo> bilansRelanceRib = new ArrayList<>();

		for (Club club : clubs) {

			// nb relance faite sur la semaine
			Integer nbRelances = relanceRibRepository.countInMonthFor(club.getId(),
					Calendar.getInstance().getTime());
			Integer nbSuiviValide = suiviRibRepository.countOfStatutInMonth(club.getId(),
					Calendar.getInstance().getTime(), 1);
			Integer nbSuiviAbandonne = suiviRibRepository.countOfStatutInMonth(club.getId(),
					Calendar.getInstance().getTime(), 2);
			Integer nbSuiviEncours = suiviRibRepository.countEncoursByClub(club.getId());

			BilanRelanceHebdo bilan = new BilanRelanceHebdo();
			bilan.setClub(club);
			bilan.setNbRelance(nbRelances);
			bilan.setNbSuiviAbandonne(nbSuiviAbandonne);
			bilan.setNbSuiviValide(nbSuiviValide);
			bilan.setNbSuiviOuvert(nbSuiviEncours);

			bilansRelanceRib.add(bilan);

		}
		return bilansRelanceRib;

	}

}