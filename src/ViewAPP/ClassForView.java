package ViewAPP;

import java.io.IOException;
import java.io.InputStreamReader;

public class ClassForView implements ViewableAPP {
	private static final String MENU_ENTRANCE = "��� ���������� ������������ ���!"+
	"\n"+"�������� 1 ��� �����������"+"\n"+"�������� 2 ��� �����"+"\n"+"�������� 3 ���� �� �������������";
	
	public void printMenuEntrance() {
		System.out.println(MENU_ENTRANCE);
	}
	
	public void printMenuForChairman() {
		System.out.println("�� ����� � ������� �������������� \n �������� 1 ��� �������� ������ ����������� \n "
				+ "�������� 2 ����� ����������� ���������� ����������� \n "
				+ "�������� 3 ��� �������� ����������� \n �������� 4 ��� ������ �� ��������");
	}
	public void printMenuForResident(String s) {
		System.out.println("�� ����� � ������� "+s+" \n �������� 1 ��� ���� ����� ������������� \n "
				+ "�������� 2 ����� ����������� ���������� ����������� \n "
				+ "�������� 3 ��� ��������� ����������� � ������� �� ������������� \n �������� 4 ��� ������ �� ��������");	
	}
	
}
