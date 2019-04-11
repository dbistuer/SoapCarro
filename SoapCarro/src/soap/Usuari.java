package soap;

import java.util.Date;
import java.util.UUID;

public class Usuari {
	
	private int id;
	private String login;
	private String nom;
	private String cognoms;
	private Date dataNaixement;
	private String email;
	private String mobil;
	private String pass;
	private String password;
	private Date sessio;
	private Date iniciSessio;
	private String token = null;

	public Usuari() {
		super();
	}
	
	public Usuari(String login, String pass) {
		super();
		this.login = login;
		this.pass = pass;
	}
	
	public Usuari(String login, String nom,String pass) {
		super();
		this.login = login;
		this.nom = nom;
		this.pass= pass;
	}
	
	public Usuari(String login, String nom, String cognoms, Date dataNaixement, String email, String mobil,String pass) {
		super();
		this.login = login;
		this.nom = nom;
		this.cognoms = cognoms;
		this.dataNaixement = dataNaixement;
		this.email = email;
		this.mobil = mobil;
		this.pass= pass;
		this.sessio = new Date();
	}
	
	public Usuari(int id, String nom, String cognoms, String login, String pass, String email,String token) {
		this.login = login;
		this.nom = nom;
		this.cognoms = cognoms;
		this.email = email;
		this.pass= pass;
		this.id=id;
		this.token = token;
	}

	public int getId() {
		return id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getCognoms() {
		return cognoms;
	}
	public void setCognoms(String cognoms) {
		this.cognoms = cognoms;
	}
	public Date getDataNaixement() {
		return dataNaixement;
	}
	public void setDataNaixement(Date dataNaixement) {
		this.dataNaixement = dataNaixement;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobil() {
		return mobil;
	}
	public void setMobil(String mobil) {
		this.mobil = mobil;
	}
	
	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getSessio() {
		return sessio;
	}

	public void setSessio(Date sessio) {
		this.sessio = sessio;
	}

	public Date getIniciSessio() {
		return iniciSessio;
	}

	public void setIniciSessio(Date iniciSessio) {
		this.iniciSessio = iniciSessio;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((pass == null) ? 0 : pass.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuari other = (Usuari) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (pass == null) {
			if (other.pass != null)
				return false;
		} else if (!pass.equals(other.pass))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuari [login=" + login + ", nom=" + nom + ", cognoms=" + cognoms + ", dataNaixement=" + dataNaixement
				+ ", email=" + email + ", mobil=" + mobil + ", pass=" + pass + "]";
	}	

}
