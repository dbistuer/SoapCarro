package soap;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class ApiSoapCarro {
	
	private GestorBd db = new GestorBd(Constants.DBSERVER, Constants.DATABASE, Constants.USUARIDB, Constants.PASSUDB);
	private UUID tokenUsuari;
	private String tkUsuariS;
	
	public String autentificar(String login, String pass) {
		if(db.loginUsuari(login, pass)) {
			Usuari u = db.getUsuari(login);
			if(u.getToken() == null || u.getToken() == "") {
				tkUsuariS = tokenUsuari.randomUUID().toString();
				db.setTokenUsuari(login, pass, tkUsuariS);
				return tkUsuariS;
			}else
				return u.getToken();
		}else {
			return null;
		}
	}

	public Producte obtenirProducte(String token, int idProducte) {
		Producte producte = null;
		if(db.tokenExists(token))
			producte = db.obtenirProducte(idProducte);
		return producte;
	}
	
	public boolean producteAfegit(String token, Producte producte) {
		boolean retorn = false;
		if(db.tokenExists(token))
			retorn = db.crearProducte(producte);
		return retorn;
	}
	
	public Producte[] obtenirProductes(String token){
		Collection<Producte> llistaProductes = null;
		if(db.tokenExists(token))
			llistaProductes =  db.obtenirProductes();
		return llistaProductes.toArray(new Producte[llistaProductes.size()]);
	}
}
