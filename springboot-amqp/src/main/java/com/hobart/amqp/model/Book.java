package com.hobart.amqp.model;

public class Book {
	private String name,author;

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Book(String name, String author) {
		super();
		this.name = name;
		this.author = author;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "Book [name=" + name + ", author=" + author + "]";
	}
	
	
}
