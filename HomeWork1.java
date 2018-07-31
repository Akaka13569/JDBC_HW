//input "SELECT ename, salary FROM employee" output
//output¤U­±
//select
//ename
//salary
//from
//employee

package com.charlielin;

import java.util.ArrayList;
import java.util.List;

public class HomeWork1 {

	public static void main(String[] args) {

		String str = "SELECT ename, salary From employee ,,,,";
		String[] str1 = str.split(",| ");
		List<String> list1 = new ArrayList<>();

		for (String str2 : str1) {
			if (!str2.isEmpty()) {
				list1.add(str2.toLowerCase());
			}
		}

		for (int i = 0; i < list1.size(); i++) {
			if (list1.get(i).equals("from")) {
				list1.remove(i);
				// break;
			}
			if (list1.get(i).equals("employee")) {
				list1.add(1, list1.get(i));
				list1.remove(i + 1);
				break;
			}
		}

		for (String str3 : list1) {
			System.out.println(str3);
		}

	}

}
