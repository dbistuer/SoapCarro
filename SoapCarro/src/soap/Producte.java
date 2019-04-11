package soap;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Producte {
	
	private int id;
	private String nom;
	private int disponibilitat;
	private String descripcio;
	private float preu;
	private int idUsuari;
	private Date dataInici;
	private Date dataFi;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	
	public Producte(){
		super();
	}
	
	public Producte(int id){
		this();
		this.id=id;
	}
	
	public Producte(String nom, int disponibilitat, int idUsuari) {
		this();
		this.nom = nom;
		this.disponibilitat = disponibilitat;
		this.idUsuari = idUsuari;
	}

	public Producte(String nom, int disponibilitat, int idUsuari, String descripcio,
			float preu, Date fechaInici){
		this(nom,disponibilitat, idUsuari);
		this.descripcio = descripcio;
		this.preu = preu;
		this.dataInici=fechaInici;
	}
	
	public Producte(int id,String nom,float preu,int disponibilitat,String descripcio,int idUsuari){
		this(nom,disponibilitat, idUsuari);
		this.descripcio = descripcio;
		this.preu = preu;
		this.id=id;
	}
	
	public Producte(int id,int idUsuari,String nom,int disponibilitat,String descripcio,float preu,Date iniciVenda){
		this(nom,disponibilitat, idUsuari);
		this.descripcio = descripcio;
		this.preu = preu;
		this.dataInici=iniciVenda;
		this.id=id;
	}
	public Producte(int id,int idUsuari,String nom,int disponibilitat,String descripcio,float preu,Date iniciVenda,Date fiVenda){
		this(nom,disponibilitat, idUsuari);
		this.descripcio = descripcio;
		this.preu = preu;
		this.dataInici=iniciVenda;
		this.dataFi = fiVenda;
		this.id=id;
	}
	
	public Producte(String nom,int idUsuari,int disponibilitat,String descripcio,float preu,Date iniciVenda,Date fiVenda) {
		this.nom = nom;
		this.idUsuari = idUsuari;
		this.disponibilitat = disponibilitat;
		this.descripcio = descripcio;
		this.preu = preu;
		this.dataInici = iniciVenda;
		this.dataFi = fiVenda; 
	}

	public Date getDataInici() {
		return dataInici;
	}

	public void setDataInici(Date dataInici) {
		this.dataInici = dataInici;
	}

	public String getFechaInici() {
		return sdf.format(dataInici);
	}

	/*public void setFechaInici(String fechaInici) {
		try {
			this.dataInici = sdf.parse(fechaInici);
		}catch(Exception e) {
			this.dataInici = new Date();
		}
	}*/

	public int getIdUsuari() {
		return idUsuari;
	}

	public void setIdUsuari(int idUsuari) {
		this.idUsuari = idUsuari;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getDisponibilitat() {
		return disponibilitat;
	}

	public void setDisponibilitat(int disponibilitat) {
		this.disponibilitat = disponibilitat;
	}

	public String getDescripcio() {
		return descripcio;
	}

	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}

	public float getPreu() {
		return preu;
	}

	public void setPreu(float preu) {
		this.preu = preu;
	}

	public int getId() {
		return id;
	}

	public Date getDataFi() {
		return dataFi;
	}

	public void setDataFi(Date dataFi) {
		this.dataFi = dataFi;
	}
	
}
