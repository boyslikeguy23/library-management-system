package com.library.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.entity.Copy;
import com.library.repository.CopyRepository;

@RestController
@RequestMapping("copies")
@CrossOrigin(origins = "http://localhost:4200")
public class CopyController
{
	@Autowired
	private CopyRepository copyRepository;
	
	
	@PostMapping("/addBook")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Copy> createNewBook(@RequestParam String title, @RequestParam String author, @RequestParam String date)
	{
		//System.out.println("inside create new book");
		
		try {
			String[] day = date.split("-");
			Date d = Date.valueOf(LocalDate.of(Integer.parseInt(day[0]), Integer.parseInt(day[1]), Integer.parseInt(day[2])));
			
			Copy copy = new Copy(title, author, d);
			Copy c = copyRepository.save(copy);
			return new ResponseEntity<>(c, HttpStatus.OK);
			
		}
		catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@DeleteMapping("/remove/{copyid}")
	@PreAuthorize("hasRole('ADMIN')")
	public void removeCopy(@RequestBody int copyid)
	{
		copyRepository.delete(copyRepository.getById(copyid));
	}
	
	
	@GetMapping("/totalBooks")
	public ResponseEntity<Integer> getNumofTotalBooks(){
		try {
			//copyRepository.findAll().size();
			return new ResponseEntity<>(copyRepository.findAll().size(), HttpStatus.OK);
		}
		catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
	}
	
	
	@GetMapping("/totalAvailable")
	public ResponseEntity<Integer> getNumofAvailBooks(){
		try {
			int count = 0;
			
			List<Copy> copies = copyRepository.findAll();
			
			for(Copy copy: copies) {
				if(copy.isInStock()) count += 1;
			}
				
			return new ResponseEntity<>(count, HttpStatus.OK);
		}
		catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
	}
	
	
	@GetMapping("/catalog")
	public ResponseEntity<List<Copy>> viewCatalog(){
		try {
			List<Copy> catalog = copyRepository.findAll();
			
			if(!catalog.isEmpty()) {
				return new ResponseEntity<>(catalog, HttpStatus.OK);
			}
			
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
		catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping("/catalogAvail")
	public ResponseEntity<List<Copy>> viewAvailable()
	{
		try {
			List<Copy> catalog = copyRepository.findAll();
			List<Copy> available = new ArrayList<>();
			
			if(!catalog.isEmpty()) {
				for(Copy book: catalog) {
					if(book.isInStock()) {
						available.add(book);
					}
				}
				
				if(!available.isEmpty()) return new ResponseEntity<>(available, HttpStatus.OK);
			}
			
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
		catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/checkedOut")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<Copy>> viewCheckedOut()
	{
		try {
			List<Copy> catalog = copyRepository.findAll();
			List<Copy> checkedOut = new ArrayList<>();
			
			if(!catalog.isEmpty()) {
				for(Copy book: catalog) {
					if(!book.isInStock()) {
						checkedOut.add(book);
					}
				}
				
				if(!checkedOut.isEmpty()) return new ResponseEntity<>(checkedOut, HttpStatus.OK);
			}
			
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
		catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
//====================================================================================================================================================================================
	
	@PostMapping("/save-default")
	@PreAuthorize("hasRole('ADMIN')")
	public void createDefaultCatalog(){
		//src/main/resources/static/books.txt
		
		try
		{
			File file = new File("src/main/resources/static/books.txt");
			
			if(!file.canRead())
				throw new Exception("Cant read the file " + file.getPath());
			
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			String line;
			
			while((line = br.readLine()) != null) {
				String[] lineArray = line.split(", ");
				
				int numOfCopies = Integer.parseInt(lineArray[0]);
				String title = lineArray[1];
				String author = lineArray[2];
				
				String[] dateArray = lineArray[3].split("-");
				Date date = Date.valueOf(LocalDate.of(Integer.parseInt(dateArray[2]), Integer.parseInt(dateArray[0]), Integer.parseInt(dateArray[1])));
				
				//Title t = new Title(title, author, date);
				
				for(int i= 0; i < numOfCopies; i++) {
					Copy c = new Copy(title, author, date);
					//createNewBook(c);
					copyRepository.save(c);
				}
			}
			
			br.close();
		}
		catch(Exception ex)
		{
			System.out.println(ex.fillInStackTrace());
		}
	}
}
