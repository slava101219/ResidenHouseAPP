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
		System.out.println("������� �������� �����������. ��������� �� 5 �� 50 ��������.");
		String str = reader.readLine();
		if (str.toCharArray().length<5||str.toCharArray().length>50) {
			System.out.println("������������ ����� ��������. ���������� ��� ���.");
			createNewpolling(); return;
		}
		if (!str.matches("[�-��-�]+")) {
			System.out.println("�� ����������� ������������ �������. ���������� ��� ���.");
			createNewpolling(); return;
		}
		System.out.println("������� ��������. �� 5 �� 500 �������� ���������.");
		String str2 = reader.readLine();
		if (str2.toCharArray().length<5||str2.toCharArray().length>500) {
			System.out.println("������������ ����� ��������. ���������� ��� ���.");
			createNewpolling(); return;
		}
		if (!str2.matches("[�-��-�]+")) {
			System.out.println("�� ����������� ������������ �������. ���������� ��� ���.");
			createNewpolling(); return;
		}
		repository.createNewPollingInDB(str, str2);
		System.out.println("����������� �������!");
	}

	public void checkResultPollings() {
		List<String> list = repository.allPlollingWithStatistic();
		for (String s : list) {
		System.out.println(s);
		}
	}

	public void deletePolling() throws IOException {
		System.out.println("������� �������� �����������");
		String str = reader.readLine();
		if (repository.deletePollingDB(str)==true) {
			System.out.println("����������� �������");
		} else System.out.println("����������� � ����� ��������� �� ����������.");
	}

	public void createNewVote(String login) throws IOException {
		System.out.println("������� �������� �����������.");
		String str = reader.readLine();
		System.out.println("������� 1 ���� �������� � 2 ���� �� ��������.");
		String str2 = reader.readLine();
		switch (str2) {
		case "1" : str2 = "1"; break;
		case "2" : str2 = "2"; break;
		default : createNewVote(login);
		}
		 if (repository.CreateNewVoteDB(login, str, str2)) {
			 System.out.println("�� �������������!");
		 }else {
			 System.out.println("���-�� ����� �� ���. ���������� ������� ��������.");
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
