package ViewAPP;

import java.io.IOException;
import java.io.InputStreamReader;

public class ClassForView implements ViewableAPP {
	private static final String MENU_ENTRANCE = "Ќаш кооператив приветствует вас!"+
	"\n"+"выберите 1 дл€ регистрации"+"\n"+"выберите 2 дл€ входа"+"\n"+"выберите 3 если вы администратор";
	
	public void printMenuEntrance() {
		System.out.println(MENU_ENTRANCE);
	}
	
	public void printMenuForChairman() {
		System.out.println("¬ы вошли в аккаунт јдминистратора \n выберите 1 дл€ создани€ нового голосовани€ \n "
				+ "выберите 2 чтобы просмотреть результаты голосовани€ \n "
				+ "выберите 3 дл€ удалени€ голосовани€ \n выберите 4 дл€ выхода из аккаунта");
	}
	public void printMenuForResident(String s) {
		System.out.println("¬ы вошли в аккаунт "+s+" \n выберите 1 дл€ того чтобы проголосовать \n "
				+ "выберите 2 чтобы просмотреть результаты голосований \n "
				+ "выберите 3 дл€ просмотра голосований в которых вы проголосовали \n выберите 4 дл€ выхода из аккаунта");	
	}
	
}
