package com.library.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Copies")
public class Copy
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int copyId;
	//changed int titleId to an entire Title for a many-to-one-relation
	
	/*@JoinColumn(nullable=false)
	private int title;*/
	
	@JoinColumn(nullable=false)
	private String title;

	@JoinColumn(nullable=false)
	private String author;
	
	@JoinColumn(nullable=false)
	private Date publicationDate;
	
	private boolean inStock = true;
	
	private Date returnDate;
	
	@ManyToOne
	@JoinColumn(name="user")
	private User user;
	
	public Copy() {}
	
	public Copy(String title, String author, Date date)
	{
		this.title = title;
		this.author = author;
		this.publicationDate = date;
	}
	
	public int getCopyId()
	{
		return this.copyId;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public boolean isInStock() {
		return inStock;
	}
	
	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}
	
	public String getWhoCheckedOut() {
		if(user != null)
			return user.getUsername();
		else
			return "";
		
	}
	
	public void setWhoCheckedOut(User whoCheckedOut) {
		user = whoCheckedOut;
	}
	
	public Date getReturnDate() {
		return returnDate;
	}
	
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}
	
}
