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
		System.out.println("Введите логин. Логин должен содержать от 5 до 15 цифр или букв латинского алфавита.");
		str2 = reader.readLine();
		if (str2.toCharArray().length<5||str2.toCharArray().length>15) {
			System.out.println("Недопустимая длина. Попробуйте еще раз.");
			registration(); return;
		}
		if (service.checkExistLogin(str2)==true) {
			System.out.println("такой логин занят. Попробуйте еще раз.");
			registration(); return;
		}
		if (!str2.matches("[0-9a-zA-Z]+")) {
			System.out.println("Вы используете недопустимые символы. Попробуйте еще раз.");
			registration(); return;
		}
		System.out.println("Введите пароль. Пароль должен содержать от 5 до 10 символов.");
		str3 = reader.readLine();
		if (str3.toCharArray().length<5||str3.toCharArray().length>10) {
			System.out.println("ошибка. Попробуйте еще раз.");
			registration(); return;
		}
		System.out.println("Введите имя. От 2 до 15 символов кирилицей.");
		str4 = reader.readLine();
		if (str4.toCharArray().length<2||str4.toCharArray().length>15) {
			System.out.println("Недопустимая длина Имени. Попробуйте еще раз.");
			registration(); return;
		}
		if (!str4.matches("[а-яА-Я]+")) {
			System.out.println("Вы используете недопустимые символы. Попробуйте еще раз.");
			registration(); return;
		}
		System.out.println("Введите Фамилию. От 2 до 15 символов кирилицей.");
		str5 = reader.readLine();
		if (str5.toCharArray().length<2||str4.toCharArray().length>15) {
			System.out.println("Недопустимая длина Имени. Попробуйте еще раз.");
			registration(); return;
		}
		if (!str5.matches("[а-яА-Я]+")) {
			System.out.println("Вы используете недопустимые символы. Попробуйте еще раз.");
			registration(); return;
		}
		service.writeRegistrationData(str2, str3, str4, str5);
		System.out.println("Регистрация прошла успешно!");
		entrance();
	}
	
	public void entrance() throws IOException {
		System.out.println("Для входа необходимо ввести логин и пароль.");
		System.out.println("Ведите логин.");
		st1 = reader.readLine();
		if (st1.toCharArray().length<5||st1.toCharArray().length>15) {
			System.out.println("Недопустимая длина. Попробуйте еще раз.");
			entrance(); return;
		}
		if (!st1.matches("[0-9a-zA-Z]+")) {
			System.out.println("Вы используете недопустимые символы. Попробуйте еще раз.");
			entrance(); return;
		}
		if (!service.checkExistLogin(st1)) {
			System.out.println("такой логин не существует. Попробуйте еще раз.");
			entrance(); return;
		}
		System.out.println("Ведите пароль.");
		st2 = reader.readLine();
		if (st2.toCharArray().length<5||st2.toCharArray().length>10) {
			System.out.println("ошибка. Попробуйте еще раз.");
			entrance(); return;
		}
		if (!service.checkPassword(st1, st2)) {
			System.out.println("Не верный пароль");
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
