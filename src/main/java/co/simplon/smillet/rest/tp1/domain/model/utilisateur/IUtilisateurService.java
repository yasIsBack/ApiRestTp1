package co.simplon.smillet.rest.tp1.domain.model.utilisateur;

import java.util.List;

public interface IUtilisateurService {

	Utilisateur addUser(Utilisateur utilisateur);

	Utilisateur getUser(long id);

	List<Utilisateur> getUsers();

	void deleteUser(long id);

	void updateUser(Utilisateur utilisateur);

}
