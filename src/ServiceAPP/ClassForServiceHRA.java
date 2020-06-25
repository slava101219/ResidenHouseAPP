package ServiceAPP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Set;

import RepositoryAPP.ClassRepositoryHRA;

public class ClassForServiceHRA {
	ClassRepositoryHRA repository = new ClassRepositoryHRA();
	private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	public boolean checkExistLogin(String s) {
		return repository.checkLoginRep(s);
	}
	
	public void writeRegistrationData(String s1, String s2, String s3, String s4) {
		s2 = encryptionPassword(s2);
		repository.writeNewUser(s1, s2, s3, s4);
	}
	
	private String encryptionPassword(String s) {
		String str1 = "";
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] bytes = md5.digest(s.getBytes());
			for (byte b : bytes) {
				String str = String.format("%02X", b);
				str1 += str;
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();}
		return str1;
	}
	
	public boolean checkPassword(String st1, String st2) {
		st2 = encryptionPassword(st2);
		return repository.checkPasswordRep(st1, st2);
	}

	public void createNewpolling() throws IOException {
		System.out.println("введите название голосования. Кирилицей от 5 до 50 символов.");
		String str = reader.readLine();
		if (str.toCharArray().length<5||str.toCharArray().length>50) {
			System.out.println("Недопустимая длина названия. Попробуйте еще раз.");
			createNewpolling(); return;
		}
		if (!str.matches("[а-яА-Я]+")) {
			System.out.println("Вы используете недопустимые символы. Попробуйте еще раз.");
			createNewpolling(); return;
		}
		System.out.println("Введите описание. От 5 до 500 символов кирилицей.");
		String str2 = reader.readLine();
		if (str2.toCharArray().length<5||str2.toCharArray().length>500) {
			System.out.println("Недопустимая длина Описания. Попробуйте еще раз.");
			createNewpolling(); return;
		}
		if (!str2.matches("[а-яА-Я]+")) {
			System.out.println("Вы используете недопустимые символы. Попробуйте еще раз.");
			createNewpolling(); return;
		}
		repository.createNewPollingInDB(str, str2);
		System.out.println("Голосование зоздано!");
	}

	public void checkResultPollings() {
		List<String> list = repository.allPlollingWithStatistic();
		for (String s : list) {
		System.out.println(s);
		}
	}

	public void deletePolling() throws IOException {
		System.out.println("Введите название голосования");
		String str = reader.readLine();
		if (repository.deletePollingDB(str)==true) {
			System.out.println("Голосование удалено");
		} else System.out.println("Голосования с таким названием не существует.");
	}

	public void createNewVote(String login) throws IOException {
		System.out.println("Введите название голосования.");
		String str = reader.readLine();
		System.out.println("Введите 1 если согласны и 2 если не согласны.");
		String str2 = reader.readLine();
		switch (str2) {
		case "1" : str2 = "1"; break;
		case "2" : str2 = "2"; break;
		default : createNewVote(login);
		}
		 if (repository.CreateNewVoteDB(login, str, str2)) {
			 System.out.println("Вы проголосовали!");
		 }else {
			 System.out.println("что-то пошло не так. Попробуйте сменить название.");
			 createNewVote(login);
		 }
	}

	public void checkPollingWithoutVote(String login) {
		Set<String> set = repository.pollingWithoutVote(login);
		for (String s : set) {
		System.out.println(s);
		}
	}
}
