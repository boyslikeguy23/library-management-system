package com.library.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Title
{
	private static int count;
	private int ISBN;
	private static List<Title> titleList = new ArrayList<>();
	private String title;
	private String author;
	private Date publicationDate;
	
	public Title(String title, String author, Date pubDate)
	{
		++count;
		this.ISBN = count;
		this.title = title;
		this.author = author;
		this.publicationDate = pubDate;
		if(!titleList.contains(this)) titleList.add(this);
	}
	
	public static Title get(int ISBN) {
		return titleList.get(ISBN-1);
	}
	
	public static List<Title> getTitles() {
		return titleList;
	}
	
	public int getISBN() {
		return ISBN;
	}
	
	public String getTitle() {
		return title;
	}
	
	
	public void setTitle(String title) {
		this.title = title;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((publicationDate == null) ? 0 : publicationDate.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Title other = (Title) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (publicationDate == null) {
			if (other.publicationDate != null)
				return false;
		} else if (!publicationDate.equals(other.publicationDate))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	
}
