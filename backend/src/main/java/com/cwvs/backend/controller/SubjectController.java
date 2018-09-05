package com.cwvs.backend.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cwvs.backend.model.Subject;
import com.cwvs.backend.repo.SubjectRepository;;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class SubjectController {
	@Autowired
	SubjectRepository repository;

	@GetMapping("/subjects")
	public List<Subject> getAllSubjects() {
		System.out.println("Get all Subjects...");

		List<Subject> subjects = new ArrayList<>();
		repository.findAll().forEach(subjects::add);

		return subjects;
	}

	@PostMapping("/subjects/create")
	public Subject postSubject(@RequestBody Subject subject) {

		Subject _subject = repository.save(new Subject(subject.getName(), subject.getSsn()));
		return _subject;
	}

	@DeleteMapping("/subjects/{id}")
	public ResponseEntity<String> deleteSubject(@PathVariable("id") String id) {
		System.out.println("Delete subject with ID = " + id + "...");

		repository.deleteById(id);

		return new ResponseEntity<>("Subject has been deleted!", HttpStatus.OK);
	}

	@DeleteMapping("/subjects/delete")
	public ResponseEntity<String> deleteAllSubjects() {
		System.out.println("Delete All Subjects...");

		repository.deleteAll();

		return new ResponseEntity<>("All subjects have been deleted!", HttpStatus.OK);
	}

	@GetMapping("subjects/ssn/{ssn}")
	public List<Subject> findBySsn(@PathVariable int ssn) {

		List<Subject> subjects = repository.findBySsn(ssn);
		return subjects;
	}

	@PutMapping("/subjects/{id}")
	public ResponseEntity<Subject> updateSubject(@PathVariable("id") String id, @RequestBody Subject subject) {
		System.out.println("Update Subject with ID = " + id + "...");

		Optional<Subject> subjectData = repository.findById(id);

		if (subjectData.isPresent()) {
			Subject _subject = subjectData.get();
			_subject.setName(subject.getName());
			_subject.setSsn(subject.getSsn());
			_subject.setActive(subject.isActive());
			return new ResponseEntity<>(repository.save(_subject), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}