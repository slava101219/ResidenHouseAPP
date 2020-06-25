package RepositoryAPP;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ClassRepositoryHRA {
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/MyAppResidentHous?serverTimezone=UTC";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "12345";
	private static Connection connect;
	private static Statement statement;
	
	static {
        try {
        	connect = DriverManager.getConnection(DATABASE_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {
        	e.printStackTrace();
        }
    }
	
	public boolean checkLoginRep(String s) {	
		String str = null;
		try {
			statement = connect.createStatement();
			ResultSet resultset = statement.executeQuery("select login from user_app where login = \""+s+"\";" );
			while (resultset.next()) {
				str = resultset.getString(1);
				if (str.equals(s)) {
					return true;
				}
			}
		} catch (SQLException e) {e.printStackTrace();}
		return false;
	}

	public void writeNewUser(String s1, String s2, String s3, String s4) {
		int role = 1;
		try {
			statement = connect.createStatement();
			statement.executeUpdate("insert into user_app values (null,\""+s3+"\", \""+s4+"\", \""+s1+"\", \""+s2+"\","+
			role+");");
		} catch (SQLException e) {
			e.printStackTrace();}
	}

	public boolean checkPasswordRep(String st1, String st2) {
		String str = null;
		try {
			statement = connect.createStatement();
			ResultSet resultset = statement.executeQuery("select password from user_app where login = \""+st1+"\";" );
			while (resultset.next()) {
				str = resultset.getString(1);
				if (str.equals(st2)) {
					return true;
				}
			}
		} catch (SQLException e) {e.printStackTrace();}
		return false;
	}

	public void createNewPollingInDB(String str, String str2) {
		try {
			statement = connect.createStatement();
			statement.executeUpdate("insert into pollings_app values (null, \""+str+"\", \""+str2+"\");");
		} catch (SQLException e) {e.printStackTrace();}
		
	}

	public List<String> allPlollingWithStatistic() {
		ArrayList<String> list = new ArrayList<>();	
		ArrayList<Integer> list2 = new ArrayList<>();
		ArrayList<String> listWithUserID = new ArrayList<>();
		ArrayList<String> listWithVote = new ArrayList<>();
		try {
			statement = connect.createStatement();
			ResultSet resultset2 = statement.executeQuery("select id from pollings_app;");
			while (resultset2.next()) {
				list2.add(resultset2.getInt(1));
			}
			ResultSet resultset = statement.executeQuery("select name_poll from pollings_app;");
			while (resultset.next()) {
				String str = resultset.getString(1);
				list.add(str);
			}
			for (int i = 0; i<list2.size(); i++) {
				ResultSet resultset3 = statement.executeQuery("select id_user from votes_app where id_polling = "
						+list2.get(i)+";");
				while (resultset3.next()) {
					listWithUserID.add(resultset3.getString(1));
				}
				ResultSet resultset4 = statement.executeQuery("select vote from votes_app where id_polling = "
						+list2.get(i)+" and vote = "+true+";");
				while (resultset4.next()) {
					listWithVote.add(resultset4.getString(1));
				}
				String str = list.get(i);
				str = "в голосовании:"+ str + "| проголосовало: " + listWithUserID.size()
				+ "| из них положительно: " + listWithVote.size();
				list.set(i, str);
				listWithUserID.clear(); listWithVote.clear();
			}
		} catch (SQLException e) {e.printStackTrace();}
		return list;
	}

	public boolean deletePollingDB(String s) {
		try {
			statement = connect.createStatement();
			statement.executeUpdate("delete from pollings_app where name_poll =\""+s+"\";");
			return true;
		} catch (SQLException e) {return false;}
	}

	public boolean CreateNewVoteDB(String login, String str, String str2) {
		try {
			String idUser = ""; String idPolling = "";
			statement = connect.createStatement();
			ResultSet resultset = statement.executeQuery("select id from  user_app where login =\""+login+"\";");
			while (resultset.next()) {
				idUser += resultset.getString(1);
			} 
			ResultSet resultset2 = statement.executeQuery("select id from  pollings_app where name_poll =\""+str+"\";");
			while (resultset2.next()) {
				idPolling += resultset2.getString(1);
			}
			statement.executeUpdate("insert into votes_app values (\""+idUser+"\", \""+idPolling+"\", \""+str2+"\");");
			return true;
		} catch (SQLException e) {e.printStackTrace(); return false;}
		
	}

	public Set<String> pollingWithoutVote(String login) {
		try {
			Set<String> setIdPolling = new HashSet<>();
			Set<String> setNamePolling = new HashSet<>();
			String idUser = ""; String idPolling = "";
			statement = connect.createStatement();
			ResultSet resultset = statement.executeQuery("select id from  user_app where login =\""+login+"\";");
			while (resultset.next()) {
				idUser = resultset.getString(1);
			}
			ResultSet resultset2 = statement.executeQuery("select id_polling from  votes_app where id_user =\""+idUser+"\";");
			while (resultset2.next()) {
				setIdPolling.add(resultset2.getString(1));
			}
			Iterator<String> i = setIdPolling.iterator();
			while (i.hasNext()) {
				ResultSet resultset3 = statement.executeQuery("select name_poll from  pollings_app where id =\""+i.next()+"\";");
				while (resultset3.next()) {
					setNamePolling.add(resultset3.getString(1));
				}
			}
			return setNamePolling;
		} catch (SQLException e) {
			e.printStackTrace(); return null;} 
	}
	
	
}
