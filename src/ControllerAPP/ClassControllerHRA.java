package ControllerAPP;
import java.io.*;
import ServiceAPP.ClassForServiceHRA;
import ViewAPP.ClassForView;

public class ClassControllerHRA {
	private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	ClassForView view = new ClassForView();
	ClassForServiceHRA service = new ClassForServiceHRA();
	private String strq, str2, str3, str4, str5;
	private String st1, st2;
	public void start() throws IOException {
		view.printMenuEntrance();
		strq = reader.readLine();
		switch (strq) {
		case "1" : registration(); break;
		case "2" : entrance(); break;
		case "3" : actionChairman(); break;
		default : start();
		}
	}
	public void registration() throws IOException {
		System.out.println("������� �����. ����� ������ ��������� �� 5 �� 15 ���� ��� ���� ���������� ��������.");
		str2 = reader.readLine();
		if (str2.toCharArray().length<5||str2.toCharArray().length>15) {
			System.out.println("������������ �����. ���������� ��� ���.");
			registration(); return;
		}
		if (service.checkExistLogin(str2)==true) {
			System.out.println("����� ����� �����. ���������� ��� ���.");
			registration(); return;
		}
		if (!str2.matches("[0-9a-zA-Z]+")) {
			System.out.println("�� ����������� ������������ �������. ���������� ��� ���.");
			registration(); return;
		}
		System.out.println("������� ������. ������ ������ ��������� �� 5 �� 10 ��������.");
		str3 = reader.readLine();
		if (str3.toCharArray().length<5||str3.toCharArray().length>10) {
			System.out.println("������. ���������� ��� ���.");
			registration(); return;
		}
		System.out.println("������� ���. �� 2 �� 15 �������� ���������.");
		str4 = reader.readLine();
		if (str4.toCharArray().length<2||str4.toCharArray().length>15) {
			System.out.println("������������ ����� �����. ���������� ��� ���.");
			registration(); return;
		}
		if (!str4.matches("[�-��-�]+")) {
			System.out.println("�� ����������� ������������ �������. ���������� ��� ���.");
			registration(); return;
		}
		System.out.println("������� �������. �� 2 �� 15 �������� ���������.");
		str5 = reader.readLine();
		if (str5.toCharArray().length<2||str4.toCharArray().length>15) {
			System.out.println("������������ ����� �����. ���������� ��� ���.");
			registration(); return;
		}
		if (!str5.matches("[�-��-�]+")) {
			System.out.println("�� ����������� ������������ �������. ���������� ��� ���.");
			registration(); return;
		}
		service.writeRegistrationData(str2, str3, str4, str5);
		System.out.println("����������� ������ �������!");
		entrance();
	}
	
	public void entrance() throws IOException {
		System.out.println("��� ����� ���������� ������ ����� � ������.");
		System.out.println("������ �����.");
		st1 = reader.readLine();
		if (st1.toCharArray().length<5||st1.toCharArray().length>15) {
			System.out.println("������������ �����. ���������� ��� ���.");
			entrance(); return;
		}
		if (!st1.matches("[0-9a-zA-Z]+")) {
			System.out.println("�� ����������� ������������ �������. ���������� ��� ���.");
			entrance(); return;
		}
		if (!service.checkExistLogin(st1)) {
			System.out.println("����� ����� �� ����������. ���������� ��� ���.");
			entrance(); return;
		}
		System.out.println("������ ������.");
		st2 = reader.readLine();
		if (st2.toCharArray().length<5||st2.toCharArray().length>10) {
			System.out.println("������. ���������� ��� ���.");
			entrance(); return;
		}
		if (!service.checkPassword(st1, st2)) {
			System.out.println("�� ������ ������");
			entrance(); return;
		}
		actionResident(st1);
	}
	private void actionResident(String login) throws IOException {
		view.printMenuForResident(login);
		String str = reader.readLine();
		switch (str) {
		case "1" : service.createNewVote(login); actionResident(login); break;
		case "2" : service.checkResultPollings(); actionResident(login); break;
		case "3" : service.checkPollingWithoutVote(login); actionResident(login); break;
		case "4" : start(); break;
		default : actionResident(login);
		}
	}
	public void actionChairman() throws IOException {
		view.printMenuForChairman();
		String str = reader.readLine();
		switch (str) {
		case "1" : service.createNewpolling(); actionChairman(); break;
		case "2" : service.checkResultPollings(); actionChairman(); break;
		case "3" : service.deletePolling(); actionChairman(); break;
		case "4" : start(); break;
		default : actionChairman();
		}
	}
}
