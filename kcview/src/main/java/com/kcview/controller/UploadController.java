package com.kcview.controller;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kcview.dao.FicheRepository;
import com.kcview.dao.ImportSuiviRepository;
import com.kcview.dao.SuiviComptantRepository;
import com.kcview.entity.Club;
import com.kcview.entity.ImportSuivi;
import com.kcview.entity.Operation;
import com.kcview.entity.RelanceComptant;
import com.kcview.entity.SuiviComptant;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class UploadController {
	
	@Autowired
	private SuiviComptantRepository suiviComptantRepository;
	
	@Autowired
	private ImportSuiviRepository importSuiviRepository;

	// Save the uploaded file to this folder
	private static String UPLOADED_FOLDER = "D://";

	@GetMapping("/")
	@CrossOrigin(origins = "http://localhost:4200")
	public String index() {
		return "upload";
	}
	
	
	@CrossOrigin(origins = "*")
	@GetMapping("suivisComptant")
	public ResponseEntity<List<SuiviComptant>> getSuivisComptant() {
		List<SuiviComptant> list = suiviComptantRepository.findAll();
		return new ResponseEntity<List<SuiviComptant>>(list, HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/saveSuiviComptant", method = {GET, POST}, produces = APPLICATION_JSON_VALUE )
	public ResponseEntity<SuiviComptant> saveSuiviComptant(@RequestBody SuiviComptant suiviComptant) {
		
		for (RelanceComptant el : suiviComptant.getRelances()) {
			el.setSuivi(suiviComptant);
		}
		
		SuiviComptant sc = suiviComptantRepository.save(suiviComptant);
		
		return new ResponseEntity<SuiviComptant>(sc, HttpStatus.OK);
	}
	

	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/validateSuiviComptant", method = {GET, POST}, produces = APPLICATION_JSON_VALUE )
	public ResponseEntity<SuiviComptant> validateSuiviComptant(@RequestBody SuiviComptant suiviComptant) {
		suiviComptant.setStatut(1);
		
		for (RelanceComptant el : suiviComptant.getRelances()) {
			el.setSuivi(suiviComptant);
		}
		
		SuiviComptant sc = suiviComptantRepository.save(suiviComptant);
		return new ResponseEntity<SuiviComptant>(sc, HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/cancelSuiviComptant", method = {GET, POST}, produces = APPLICATION_JSON_VALUE )
	public ResponseEntity<SuiviComptant> canceledSuiviComptant(@RequestBody SuiviComptant suiviComptant) {
		suiviComptant.setStatut(2);
		
		for (RelanceComptant el : suiviComptant.getRelances()) {
			el.setSuivi(suiviComptant);
		}
		
		SuiviComptant sc = suiviComptantRepository.save(suiviComptant);
		return new ResponseEntity<SuiviComptant>(sc, HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/lastImportSuiviComptant", method = {GET, POST}, produces = APPLICATION_JSON_VALUE )
	public ResponseEntity<ImportSuivi> lastImportSuiviComptant() {
		ImportSuivi d = importSuiviRepository.lastImportComptant();
		return new ResponseEntity<ImportSuivi>(d, HttpStatus.OK);
	}
	
	@Autowired
	private MailClient mailClient;

	@CrossOrigin(origins = "*")
	@RequestMapping("/up")
	public ResponseEntity<String> Fiche(@RequestParam("file") MultipartFile file) {
		

	    List<SuiviComptant> suivis = new ArrayList<>();
	    
		if (file.isEmpty()) {
            
            //return "redirect:uploadStatus";
        }
		
		ImportSuivi myImport = new ImportSuivi();
		myImport.setType("comptant");
		myImport.setDate_import(new Date());
		myImport.setId_club(8);

        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
            FileInputStream in = new FileInputStream(UPLOADED_FOLDER + file.getOriginalFilename());
    		Workbook workbook = new XSSFWorkbook(in);
    		Sheet sheet = workbook.getSheetAt(0);
    		
    		
    		 //to evaluate cell type
    	    FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();

    	    
    	    for(Row row : sheet)
    	    {
    	    	
    	    	if(row.getRowNum() > 1){
    	    		
    	    		SuiviComptant sc = new SuiviComptant();
    	    		sc.setNum_adherent(row.getCell(0).getNumericCellValue());
    	    		sc.setNom_adherent(row.getCell(1).getStringCellValue());
    	    		sc.setPrenom_adherent(row.getCell(2).getStringCellValue());
    	    		sc.setNum_telephone(row.getCell(3).getNumericCellValue());
    	    		sc.setNom_contrat(row.getCell(4).getStringCellValue());
    	    		sc.setDate_expiration(row.getCell(6).getDateCellValue());
    	    		sc.setStatut(0);
    	    		
    	    		if(suiviComptantRepository.findOneEnCours(sc.getNum_adherent(), sc.getDate_expiration()) == 0) {
    	    			suiviComptantRepository.save(sc);
    	    			suivis.add(sc);
    	    		}else {
    	    			suivis.add(sc);
    	    			System.out.println("en cours");
    	    		}
    	    	}
    	    }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
      //given
	    String recipient = "eric.goueffon@sofrecom.com";
	    String message = "VH";
	    //when
	    mailClient.prepareAndSend(recipient, message, suivis);
	    
	    importSuiviRepository.save(myImport);

        return new ResponseEntity<String>("eeee", HttpStatus.OK);
	}

}