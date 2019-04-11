package soap;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.sql.Date;
import java.util.Hashtable;
import java.util.List;


public class GestorBd {
	
	/*****************Atributs******************/
	
	private String hostname;
	private String database;
	private String port;
	private String userLogin;
	private String userPasswd;
	private String temps;
	private Connection conn;
	
	/***************Constructors*****************/
	
	public GestorBd(){
		super();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException ex) {
			System.out.println("Error: unable to load driver class!");
		}
		this.temps = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false";
	}
	
	public GestorBd(String hostname, String database, String port, String user, String passwd) {
		this(hostname,database,user,passwd);
		this.port=port;
	}

	public GestorBd(String hostname, String database, String user, String passwd) {
		this();
		this.hostname = hostname;
		this.database = database;
		this.userLogin = user;
		this.userPasswd = passwd;
		
	}

	/***********Metodes de negoci*************/
	
	public boolean crearProducte(Producte producte) {
		boolean retorn = false;
		int retornSQL;
		try(Connection conn = DriverManager.getConnection("jdbc:mysql://"+this.hostname+"/"+this.database+this.temps,this.userLogin,this.userPasswd)){

			String sql = "INSERT INTO "+database+".producte(idUsuari,nom,disponibilitat,descripcio,preu,iniciVenda,fiVenda) VALUES( ? , ? , ? , ? , ? , ? , ?);";
			try(PreparedStatement insertedProduct = conn.prepareStatement(sql)){
				

				insertedProduct.setInt(1,producte.getIdUsuari());
				insertedProduct.setString(2,producte.getNom());
				insertedProduct.setInt(3,producte.getDisponibilitat());
				insertedProduct.setString(4,(producte.getDescripcio()));
				insertedProduct.setFloat(5,producte.getPreu());
				java.sql.Date dinici= new java.sql.Date(producte.getDataInici().getTime());
				insertedProduct.setDate(6,dinici);
				insertedProduct.setTimestamp(7, new java.sql.Timestamp(producte.getDataFi().getTime()));
				retornSQL = insertedProduct.executeUpdate();
				if(retornSQL > 0)
					retorn = true;
				
			}catch(SQLException stmte){
				stmte.printStackTrace();
			}

		} catch (SQLException conne) {
			conne.printStackTrace();
		}
		return retorn;
	}
	
	public Collection<Producte> obtenirProductes(){
		
		Collection<Producte> productes = new ArrayList<Producte>();
		
		try(Connection conn = DriverManager.getConnection("jdbc:mysql://"+this.hostname+"/"+this.database+this.temps,this.userLogin,this.userPasswd)){
			
			String sql = "SELECT * FROM "+database+".producte WHERE fiVenda >= NOW() ORDER BY preu ASC;";
			try(PreparedStatement usersFound = conn.prepareStatement(sql)){
				
				
				try(ResultSet rs = usersFound.executeQuery()){
					
					while(rs.next()){
						Producte producte = new Producte(rs.getInt("id"),rs.getInt("idUsuari"),rs.getString("nom"),rs.getInt("disponibilitat"),rs.getString("descripcio"),rs.getFloat("preu"),rs.getDate("iniciVenda"),new Date(rs.getTimestamp("fiVenda").getTime()));
						productes.add(producte);
					}
					
				}catch(SQLException rse){
					rse.printStackTrace();
				}
			}catch(SQLException stmte){
				stmte.printStackTrace();
			}

		} catch (SQLException conne) {
			conne.printStackTrace();
		}
		return productes;
		
	}

	public Producte obtenirProducte(int id){
		Producte producte=null;
		try(Connection conn = DriverManager.getConnection("jdbc:mysql://"+this.hostname+"/"+this.database+this.temps,this.userLogin,this.userPasswd)){
			
			String sql = "SELECT * FROM "+database+".producte WHERE id = ?;";
			try(PreparedStatement userFound = conn.prepareStatement(sql)){
				
				userFound.setInt(1, id);
				
				try(ResultSet rs = userFound.executeQuery()){
					
					while(rs.next()){
						producte = new Producte(rs.getInt("id"),rs.getInt("idUsuari"),rs.getString("nom"),rs.getInt("disponibilitat"),rs.getString("descripcio"),rs.getFloat("preu"),rs.getDate("iniciVenda"),rs.getDate("fiVenda"));
					}
					
				}catch(SQLException rse){
					rse.printStackTrace();
				}
			}catch(SQLException stmte){
				stmte.printStackTrace();
			}

		} catch (SQLException conne) {
			conne.printStackTrace();
		}
		return producte;
	}
	
	public Collection<Producte> filtrarProductes(Date dataInici,Date dataFi){
		Collection<Producte> productes = new ArrayList<Producte>();
				
		try(Connection conn = DriverManager.getConnection("jdbc:mysql://"+this.hostname+"/"+this.database+this.temps,this.userLogin,this.userPasswd)){
			
			String sql = "SELECT * FROM "+database+".producte WHERE iniciVenda BETWEEN ? AND  ? ORDER BY preu ASC;";
			try(PreparedStatement usersFiltro = conn.prepareStatement(sql)){
				
				usersFiltro.setDate(1,dataInici);
				usersFiltro.setDate(2,dataFi);
				
				try(ResultSet rs = usersFiltro.executeQuery()){
					
					while(rs.next()){
						Producte producte = new Producte(rs.getInt("id"),rs.getInt("idUsuari"),rs.getString("nom"),rs.getInt("disponibilitat"),rs.getString("descripcio"),rs.getFloat("preu"),rs.getDate("iniciVenda"),rs.getDate("fiVenda"));
						productes.add(producte);
					}
					
				}catch(SQLException rse){
					rse.printStackTrace();
				}
			}catch(SQLException stmte){
				stmte.printStackTrace();
			}

		} catch (SQLException conne) {
			conne.printStackTrace();
		}
		return productes;
	}
	
	/**************************************************************************************************************/	
	
	public boolean loginUsuari(String login, String pass) {
		boolean retorn = false;
		try(Connection conn = DriverManager.getConnection("jdbc:mysql://"+this.hostname+"/"+this.database+this.temps,this.userLogin,this.userPasswd)){

			String sql = "SELECT COUNT(*) AS NUMERO FROM "+database+".usuari WHERE login LIKE ? AND pass LIKE ?;";
			try(PreparedStatement loginUser = conn.prepareStatement(sql)){
				
				loginUser.setString(1,login);
				loginUser.setString(2,pass);
				
				try(ResultSet rs = loginUser.executeQuery()){
						if(rs.next()) {
							if(rs.getInt(1) > 0) retorn = true;
							else retorn = false;
						}
					
				}catch(SQLException rse){
					rse.printStackTrace();
				}
			
			}catch(SQLException stmte){
				stmte.printStackTrace();
			}

		} catch (SQLException conne) {
			conne.printStackTrace();
		}
		return retorn;
		
	}
	
	public Usuari getUsuari(String login) {
		Usuari usuari = null;
		try(Connection conn = DriverManager.getConnection("jdbc:mysql://"+this.hostname+"/"+this.database+this.temps,this.userLogin,this.userPasswd)){

			String sql = "SELECT * FROM "+database+".usuari WHERE login LIKE ?;";
			try(PreparedStatement loginUser = conn.prepareStatement(sql)){
				
				loginUser.setString(1,login);
				
				try(ResultSet rs = loginUser.executeQuery()){
						if(rs.next()) {
							usuari = new Usuari(rs.getInt("id"),rs.getString("nom"),rs.getString("cognom"),rs.getString("login"),rs.getString("pass"),rs.getString("mail"),rs.getString("token"));
						}
					
				}catch(SQLException rse){
					rse.printStackTrace();
				}
			
			}catch(SQLException stmte){
				stmte.printStackTrace();
			}

		} catch (SQLException conne) {
			conne.printStackTrace();
		}
		return usuari;
	}
	
	public void setTokenUsuari(String login,String pass,String token) {
		try(Connection conn = DriverManager.getConnection("jdbc:mysql://"+this.hostname+"/"+this.database+this.temps,this.userLogin,this.userPasswd)){

			String sql = "UPDATE "+database+".usuari SET token = ? WHERE login LIKE ? AND pass LIKE ?;";
			try(PreparedStatement updUser = conn.prepareStatement(sql)){
				
				updUser.setString(1,token);
				updUser.setString(2,login);
				updUser.setString(3,pass);
				updUser.executeUpdate();
			
			}catch(SQLException stmte){
				stmte.printStackTrace();
			}

		} catch (SQLException conne) {
			conne.printStackTrace();
		}
		
	}
	
	public boolean tokenExists(String token) {
		boolean retorn = false;
		try(Connection conn = DriverManager.getConnection("jdbc:mysql://"+this.hostname+"/"+this.database+this.temps,this.userLogin,this.userPasswd)){

			String sql = "SELECT COUNT(*) AS NUMERO FROM "+database+".usuari WHERE token LIKE ?;";
			try(PreparedStatement tokenExists = conn.prepareStatement(sql)){
				
				tokenExists.setString(1,token);
				
				try(ResultSet rs = tokenExists.executeQuery()){
						if(rs.next()) {
							if(rs.getInt(1) > 0) retorn = true;
							else retorn = false;
						}
					
				}catch(SQLException rse){
					rse.printStackTrace();
				}
			
			}catch(SQLException stmte){
				stmte.printStackTrace();
			}

		} catch (SQLException conne) {
			conne.printStackTrace();
		}
		return retorn;		
	}
	
	/****************Setters && Getters****************/
	
	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public String getuserPasswd() {
		return userPasswd;
	}

	public void setuserPasswd(String userPasswd) {
		this.userPasswd = userPasswd;
	}

}
