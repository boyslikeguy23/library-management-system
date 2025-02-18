package com.library.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="Users", uniqueConstraints = {@UniqueConstraint(columnNames = "username")})
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "Username is mandatory")
	private String username;
	
	@NotBlank(message = "Password is mandatory")
	private String password;
	
	@OneToMany(mappedBy="user")
	private List<Copy> books;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles",
		joinColumns = {@JoinColumn(name = "user_id")},
		inverseJoinColumns = {@JoinColumn(name = "role_id")})
	private Set<Role> roles = new HashSet<>();
	
	public User() {}
	
	public User(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
	}
	
	
//Additonal Methods
	public boolean checkOut(Copy book) {
		if(book.isInStock()) {
			book.setInStock(false);
			book.setWhoCheckedOut(this);
			
			LocalDate returnDate = LocalDate.now();
			returnDate  = returnDate.plusWeeks(2);
			book.setReturnDate(Date.valueOf(returnDate));
			
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean checkIn(Copy book) {
		if(getBooks().contains(book)) {
			book.setInStock(true);
			book.setWhoCheckedOut(null);
			book.setReturnDate(null);
			//book.setHolder(null);
			return true;
		}
		return false;	
	}
	
	
//Getters and Setters
	public int getId() {
		return id;
	}

	
	public void setId(int id) {
		this.id = id;
	}

	
	public String getUsername() {
		return username;
	}

	
	public void setUsername(String userName) {
		this.username = userName;
	}

	
	public String getPassword() {
		return password;
	}

	
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public List<Copy> getBooks() {
		return books;
	}

	
	public void setBooks(List<Copy> books) {
		this.books = books;
	}


	public Set<Role> getRoles() {
		return roles;
	}


	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}


	void update(User user) {
		this.username = user.username;
		this.password = user.password;
	}
	
}
