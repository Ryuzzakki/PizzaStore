package model;

import java.util.HashSet;

public class User {

	private long id;
	private String first_name;
	private String last_name;
	private String password;
	private String phone_number;
	private String email;
	private HashSet<String> address = new HashSet<>();
	private HashSet<Order> orders = new HashSet<>();

	public User(String first_name, String last_name, String password, String phone_number, String email)
			throws UserException {
		setFirstName(first_name);
		setLastName(last_name);
		setPassword(password);
		setEmail(email);
		setNumber(phone_number);

	}

	private static final int MIN_USERNAME_LENGTH = 2;
	private static final int MAX_USERNAME_LENGTH = 20;
	private static final int MIN_PASSWORD_LENGTH = 6;
	private static final int MAX_PASSWORD_LENGTH = 30;

	public long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getFirst_name() {
		return first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public String getPassword() {
		return password;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public HashSet<String> getAddress() {
		return address;
	}

	public HashSet<Order> getOrders() {
		return orders;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

	public void setFirstName(String first_name) throws UserException {
		if (first_name.length() >= MIN_USERNAME_LENGTH && first_name.matches("[a-zA-Z]+")) {
			if (first_name.length() <= MAX_USERNAME_LENGTH) {
				this.first_name = first_name;
			} else {
				throw new UserException("First name too long!");
			}
		} else {
			throw new UserException("First name must be at least "
					+ " characters long and must not contain any whitespace characters!");
		}
	}

	public void setLastName(String last_name) throws UserException {
		if (last_name.length() >= MIN_USERNAME_LENGTH && last_name.matches("[a-zA-Z]+")) {
			if (last_name.length() <= MAX_USERNAME_LENGTH) {
				this.last_name = last_name;
			} else {
				throw new UserException("Last name too long!");
			}
		} else {
			throw new UserException(
					"Last name must be at least " + " characters long and must not contain any whitespace characters!");
		}
	}

	public void setPassword(String password) throws UserException {
		if (password.length() >= MIN_PASSWORD_LENGTH) {
			if (password.length() <= MAX_PASSWORD_LENGTH) {
				this.password = password; // TODO hashing required
			} else {
				throw new UserException("Password too long!");
			}

		} else {
			throw new UserException("Password too small");
		}
	}

	public void setEmail(String email) throws UserException {
		if ((email.matches(
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))) {
			this.email = email;
		} else {
			throw new UserException("Invalid email form!");
		}
	}

	public void setNumber(String phone_number) throws UserException {
		if ((phone_number.matches("[0-9]+")) && !phone_number.isEmpty() && !phone_number.equals("")
				&& phone_number.length() < 10) {
			this.phone_number = phone_number;
		} else {
			throw new UserException("Invalid number!");
		}
	}

}
