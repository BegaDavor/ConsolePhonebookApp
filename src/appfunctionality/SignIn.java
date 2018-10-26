package appfunctionality;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import bega.DAO.ContactsDAOImplementation;
import bega.DAO.ContactsDAOInterface;
import bega.DAO.UsersDAOImplementation;
import bega.DTO.Contacts;
import bega.DTO.Users;

public class SignIn {

	static Users user = null;
	static Contacts contact = null;
	
	static Scanner stringInput = new Scanner(System.in);
	
	public static String isString() { // handle exception za String
		while (true) {
			try {
				return stringInput.nextLine();
			} catch (InputMismatchException e) {
				stringInput.next();
				System.out.println("Vas unos nije dobar. Probajte ponovo: ");
			}
		}
	}

	private static String decrypt(String key, String initVector, String encrypted) {
		try {
			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

			byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));

			return new String(original);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	public static boolean login() throws SQLException {

		Scanner input = new Scanner(System.in);
		String username;
		String password;

		System.out.println("Username: ");
		username = input.next();

		System.out.println("Password: ");
		password = input.next();

		UsersDAOImplementation signIn = new UsersDAOImplementation();
		try {
		user = signIn.getUserByUsername(username);
		}catch(NullPointerException e) {
			return false;
		}
		if (decrypt("EID1703143825104", "RandomInitVector", user.getPassword()).equals(password)) {
			return true;
		} else {
			return false;
		}

	}

	static Scanner input = new Scanner(System.in);

	public static int isInteger() { // handle exception za integer brojeve
		while (true) {
			try {
				return input.nextInt();
			} catch (InputMismatchException e) {
				input.next();
				System.out.println("Your entry is wrong. Try again! ");
			}
		}
	}

	public static void options() throws SQLException {
		boolean logged = login();
		if (logged) {

			int option = -1;

			do {
				System.out.println("1 - Add contact" + "\n2 - Edit contact" + "\n3 - Delete contact"
						+ "\n4 - Show all contacts" + "\n5 - Search contacts" + "\n0 - Exit");
				option = isInteger();

				switch (option) {
				case 0:
					break;

				case 1:
					System.out.println("First name: ");
					String firstname = input.next();
					System.out.println("Last name: ");
					String lastname = input.next();
					System.out.println("City: ");
					String city = isString();
					System.out.println("E - mail: ");
					String email = input.next();
					System.out.println("Phone number: ");
					String phonenumber = input.next();
					int userID = user.getUserID();

					contact = new Contacts(firstname, lastname, city, email, phonenumber, userID);
					ContactsDAOImplementation impl = new ContactsDAOImplementation();
					impl.addContact(contact, user);
					break;
				case 2:

					int editOption = -1;
					int contactID = -1;
					ContactsDAOImplementation impl1 = new ContactsDAOImplementation();
					System.out.println("Insert ID of contact which you want to edit: ");
					contactID = isInteger();

					contact = impl1.getContactbyID(contactID, user);
					if(contact != null) {
					do {
						System.out.println("1 - Edit first name" + "\n2 - Edit last name" + "\n3 - Edit city"
								+ "\n4 - Edit email" + "\n5 - Edit phone number" + "\n0 - Exit");
						editOption = isInteger();
						switch (editOption) {

						case 0:
							break;
						case 1:
							System.out.println("New first name: ");
							String newFirstname = input.next();
							contact.setFirstname(newFirstname);
							impl1.updateContact(contact, user);
							break;
						case 2:
							System.out.println("New last name: ");
							String newLastname = input.next();
							contact.setLastname(newLastname);
							impl1.updateContact(contact, user);
							break;
						case 3:
							System.out.println("New city: ");
							String newCity = isString();
							contact.setCity(newCity);
							impl1.updateContact(contact, user);
							break;
						case 4:
							System.out.println("New e-mail: ");
							String newEmail = input.next();
							contact.setEmail(newEmail);
							impl1.updateContact(contact, user);
							break;
						case 5:
							System.out.println("New phone number: ");
							String newPhonenumber = input.next();
							contact.setPhonenumber(newPhonenumber);
							impl1.updateContact(contact, user);
							break;
						default:
							System.out.println("Wrong option!");
						}

					} while (editOption != 0);
					}else {
						System.out.println("Cant found contact with entered ID!");
					}
					break;

				case 3:
					ContactsDAOImplementation impl2 = new ContactsDAOImplementation();

					System.out.println("Insert ID of contact which you want to delete: ");
					int contactIDDelete = isInteger();

					contact = impl2.getContactbyID(contactIDDelete, user);
					if (contact != null) {
						System.out.println("Are you sure that you want to delete \n" + contact.toString()
								+ "?\n(1 - yes, 0 - no)");
						int choice = isInteger();
						if (choice == 1) {

							impl2.deleteContact(contact, user);

						} else if (choice == 0) {
							System.out.println("You have cancled deletion.");
							continue;
						} else {
							System.out.println("Wrong input!");
						}
					} else {
						System.out.println("Cant found contact with entered ID!");
					}
					break;
				case 4:
					ContactsDAOImplementation impl3 = new ContactsDAOImplementation();

					System.out.println("Your all contacts: ");
					ArrayList<Contacts> contacts = impl3.getConctacts(user);
					for (Contacts contact : contacts) {
						System.out.println(contact.toString());
					}
					break;
				case 5:
					ContactsDAOImplementation impl4 = new ContactsDAOImplementation();
					int searchOption = -1;
					ArrayList<Contacts> searchedContacts = null;

					do {

						System.out.println("1 - Search by first name \n2 - Search by last name: \n0 - Exit");
						searchOption = isInteger();

						if (searchOption == 1) {

							System.out.println("Input first name: ");
							String searchedFirstname = input.next();
							searchedContacts = impl4.getContactByName(searchedFirstname, user);

							for (Contacts contact : searchedContacts) {
								System.out.println(contact.toString());
							}
						} else if (searchOption == 2) {

							System.out.println("Input last name: ");
							String searchedLastname = input.next();
							searchedContacts = impl4.getContactByLastname(searchedLastname, user);

							for (Contacts contact : searchedContacts) {
								System.out.println(contact.toString());
							}

						} else if (searchOption == 0) {
							continue;
						} else {
							System.out.println("Wrong input!");
						}
					} while (searchOption != 0);

					break;
				default:
					System.out.println("Wrong input!");

				}

			} while (option != 0);
		} else {
			System.out.println("You are not registred, do you want to sign up now? (1 - yes, press any number for no) ");
			int validation = isInteger();
			if(validation == 1) {
			SignUp.register();
			}else {
			Run.run();
			}
		}
	}

}
