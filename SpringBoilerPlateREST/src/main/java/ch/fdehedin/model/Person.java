package ch.fdehedin.model;

import java.util.Date;

public class Person {

	private int id;

	private String firstName = "";

	private String lastName = "";

	private Date birthDate;

	private Status status;

	private String email = "";

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Get the value of email
	 *
	 * @return the value of email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Set the value of email
	 *
	 * @param email
	 *            new value of email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * Get the value of birthDate
	 *
	 * @return the value of birthDate
	 */
	public Date getBirthDate() {
		return birthDate;
	}

	/**
	 * Set the value of birthDate
	 *
	 * @param birthDate
	 *            new value of birthDate
	 */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * Get the value of lastName
	 *
	 * @return the value of lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Set the value of lastName
	 *
	 * @param lastName
	 *            new value of lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Get the value of firstName
	 *
	 * @return the value of firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Set the value of firstName
	 *
	 * @param firstName
	 *            new value of firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public boolean isPersisted() {
		return id != 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj instanceof Person && obj.getClass().equals(getClass())) {
			return this.id == ((Person) obj).getId();
		}

		return false;
	}

	@Override
	public String toString() {
		return firstName + " " + lastName;
	}

	/**
	 * Builder class as outlined in the Second Edition of Joshua Bloch's
	 * Effective Java that is used to build a {@link Person} instance.
	 */
	public static class PersonBuilder {
		private String firstName;
		private String lastName;
		private String email;
		private Date birthDate;

		public PersonBuilder() {

		}

		public PersonBuilder firstName(final String firstName) {
			this.firstName = firstName;
			return this;
		}

		public PersonBuilder lastName(final String lastName) {
			this.lastName = lastName;
			return this;
		}

		public PersonBuilder email(final String email) {
			this.email = email;
			return this;
		}

		public PersonBuilder birthDate(final Date birthDate) {
			this.birthDate = birthDate;
			return this;
		}

		public Person createPerson() {
			Person person = new Person();
			person.setFirstName(firstName);
			person.setLastName(lastName);
			person.setEmail(email);
			person.setBirthDate(birthDate);
			person.setStatus(Status.ACTIVE);
			return person;
		}
	}

}
