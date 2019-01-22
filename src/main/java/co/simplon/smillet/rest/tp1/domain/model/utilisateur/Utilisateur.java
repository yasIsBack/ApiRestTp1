package co.simplon.smillet.rest.tp1.domain.model.utilisateur;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Utilisateur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	private long id;

	@NotEmpty
	@Getter
	@Setter
	private String login;

	@NotEmpty
	@Getter
	@Setter
	private String email;

	public Utilisateur(@NotEmpty String login, @NotEmpty String email) {
		this.login = login;
		this.email = email;
	}

	protected Utilisateur() {
	}

}
