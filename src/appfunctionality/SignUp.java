package appfunctionality;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import bega.DAO.UsersDAOImplementation;
import bega.DTO.Users;

public class SignUp {

	private static String encrypt(String key, String initVector, String value) {
		try {
			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

			byte[] encrypted = cipher.doFinal(value.getBytes());

			return Base64.encodeBase64String(encrypted);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	public static void register() throws SQLException {
		Scanner input = new Scanner(System.in);

		String username = null;
		String password = null;
		String hashedPassword = null;

		System.out.println("Username: ");

		username = input.next();

		System.out.println("Password: ");

		password = input.next();

		hashedPassword = encrypt("EID1703143825104", "RandomInitVector", password);

		UsersDAOImplementation impl = new UsersDAOImplementation();
		impl.addUser(new Users(username, hashedPassword));

	}

}
