package co.simplon.smillet.rest.tp1.web.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import co.simplon.smillet.rest.tp1.domain.model.utilisateur.IUtilisateurService;
import co.simplon.smillet.rest.tp1.domain.model.utilisateur.Utilisateur;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	IUtilisateurService userService;

	@PostMapping(value = "/addUser")
	public ResponseEntity<?> addUser(@RequestBody Utilisateur newUser, UriComponentsBuilder ucBuilder) {

		userService.addUser(newUser);

		HttpHeaders headers = new HttpHeaders();

		headers.setLocation(ucBuilder.path("admin/getUser/{id}").buildAndExpand(newUser.getId()).toUri());

		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);

	}

	@GetMapping(value = "/getUsers")
	public ResponseEntity<?> getUsers() {
		List<Utilisateur> allUser = userService.getUsers();

		if (allUser.isEmpty())
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<Utilisateur>>(allUser, HttpStatus.OK);
	}

	@GetMapping(value = "getUser/{id}")
	public ResponseEntity<?> getUser(@PathVariable Long id) {

		Utilisateur user = userService.getUser(id);

		if (Objects.isNull(user))
			return new ResponseEntity<Utilisateur>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<Utilisateur>(user, HttpStatus.OK);
	}

	@DeleteMapping(value = "deleteUser/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {

		Utilisateur userTmp = userService.getUser(id);

		if (Objects.isNull(userTmp))
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);

		userService.deleteUser(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@PutMapping(value = "/updateUser")
	public ResponseEntity<?> updateUser(@RequestBody Utilisateur userUpdated) {

		Utilisateur userToUpdate = userService.getUser(userUpdated.getId());

		if (Objects.isNull(userToUpdate))
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);

		userService.updateUser(userUpdated);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
