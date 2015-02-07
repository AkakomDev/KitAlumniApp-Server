package edu.kit.isco.KitAlumniApp.server.dataobject;

import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "user")
public class DataAccessUser implements DataAccessObject {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long Id;

	/**
	 * Dieses Attribut stellt die RegistrierungsID des Benutzerger�ts dar. Die RegistrierungsID wird beim Google Cloud Messaging Service angefragt.
	 * Diese ID ist f�r jede Applikation f�r jedes Ger�t einzigartig und wird nur neuvergeben, falls eine Applikation neuinstalliert wird.
	 */
	@Column(name = "client_id")
	private String clientId;
	
	/**
	 * Diese Liste enth�lt alle vom Benutzer ausgew�hlten Jobkategorien f�r die er Benachrichtigungen erhalten m�chte, sobald ein neues Jobangebot, welches auf
	 * mindestens eine dieser Kategorien zutrifft, erscheint.
	 */
	@ManyToMany
	@JoinTable(name = "user_tag",
	joinColumns={@JoinColumn(name = "user_id", referencedColumnName = "Id")},
	inverseJoinColumns={@JoinColumn(name = "tag_id", referencedColumnName = "Id")})
	private List<DataAccessTag> tags;
	
	/**
	 * Ein zuf�llig generiertes Passwort, �ber das der Benutzer keinerlei Informationen besitzt. Dieses Passwort wird aus Sicherheitsgr�nden serverseitig ben�tigt,
	 * um die Chance eines erfolgreichen Bruteforce-Angriffs zu verringern.
	 */
	@Column(name = "password")
	private String password;
	
	public DataAccessUser(){}
	
	public DataAccessUser(String clientId, List<DataAccessTag> tags, String password) {
		this.clientId = clientId;
		this.tags = tags;
		this.password = password;
	}
	
	public long getId() { return this.Id; }
	public String getClientId() {return this.clientId;}
	public String getPassword() {return this.password;}
	public List<DataAccessTag> getTags() { return this.tags;}
	public void setId(long Id) { this.Id = Id; }
	public void setClientId(String clientId) {this.clientId = clientId;}
	public void setPassword(String password) {this.password = password;}
	public void setTags(List<DataAccessTag> tags) {this.tags = tags;}
}
