package co.simplon.smillet.rest.tp1.domain.model.utilisateur.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.simplon.smillet.rest.tp1.domain.model.utilisateur.IUtilisateurService;
import co.simplon.smillet.rest.tp1.domain.model.utilisateur.Utilisateur;
import co.simplon.smillet.rest.tp1.domain.model.utilisateur.UtilisateurRepository;

@Service
public class UtilisateurService implements IUtilisateurService {

	@Autowired
	UtilisateurRepository userRepo;

	@Override
	public Utilisateur addUser(Utilisateur utilisateur) {
		return userRepo.save(utilisateur);
	}

	@Override
	public Utilisateur getUser(long id) {

		Optional<Utilisateur> toFind = userRepo.findById(id);

		if (!toFind.isPresent())
			return null;
		return userRepo.findById(id).get();
	}

	@Override
	public List<Utilisateur> getUsers() {
		return userRepo.findAll();
	}

	@Override
	public void deleteUser(long id) {
		userRepo.deleteById(id);
	}

	@Override
	public void updateUser(Utilisateur utilisateur) {

		Optional<Utilisateur> userToUpdate = userRepo.findById(utilisateur.getId());

		Utilisateur user = userToUpdate.get();

		user.setEmail(utilisateur.getEmail());
		user.setLogin(utilisateur.getLogin());
		userRepo.save(user);

	}

}
