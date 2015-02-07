package edu.kit.isco.KitAlumniApp.server.dataobject;

import java.util.Calendar;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "news")
public class DataAccessNews {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long Id;

	@Column(name = "title")
	private String title;
	
	@Column(name = "short_info", length = 500)
	private String shortInfo;
	
	@Column(name = "all_text", length = 10000)
	private String allText;

	@Column(name = "url")
	private String url;
	
	@Column(name = "image_url")
	private String imageUrl;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date")
	private Calendar date;
	
	public DataAccessNews(){}
	public DataAccessNews(String title, String shortInfo, String allText, String url, String imageUrl, Calendar date) {
		this.title = title;
		this.shortInfo = shortInfo;
		this.allText = allText;
		this.url = url;
		this.imageUrl = imageUrl;
		this.date = date;
	}
	public long getId() {return Id;}
	public String getTitle() {return title;}
	public String getShortInfo() {return shortInfo;}
	public String getAllText() {return allText;}
	public String getUrl() {return url;}
	public String getImageUrl() {return imageUrl;}
	public Calendar getDate() {return date;}
	public void setId(long id) {Id = id;}
	public void setTitle(String title) {this.title = title;}
	public void setShortInfo(String shortInfo) {this.shortInfo = shortInfo;}
	public void setAllText(String allText) {this.allText = allText;}
	public void setUrl(String url) {this.url = url;}
	public void setImageUrl(String imageUrl) {this.imageUrl = imageUrl;}
	public void setDate(Calendar date) {this.date = date;}	
}
