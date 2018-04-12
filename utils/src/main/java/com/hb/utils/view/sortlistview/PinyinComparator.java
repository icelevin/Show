package com.hb.utils.view.sortlistview;

import java.util.Comparator;

/**
 * 
 * 根据拼音来排列ListView里面的数据类
 * 
 * @author xiaanming
 * 
 */
public class PinyinComparator implements Comparator<SortModel> {

	public int compare(SortModel o1, SortModel o2) {

		String letters_1 = o1.getSortLetters();

		if (o1.getSortLetters().contains("(")) {
			letters_1 = o1.getSortLetters().substring(0, o1.getSortLetters().indexOf("("));
		}

		String letters_2 = o2.getSortLetters();

		if (o2.getSortLetters().contains("(")) {
			letters_2 = o2.getSortLetters().substring(0, o2.getSortLetters().indexOf("("));
		}

		if (letters_1.equals("@") || letters_2.equals("#")) {
			return -1;
		} else if (letters_1.equals("#") || letters_2.equals("@")) {
			return 1;
		} else {
			int x =  letters_1.compareTo(letters_2);
			
			if(x != 0){
				return x;
			}
			
			//如果两者完全相同,判断是否包含括号（院区名称在括号之中），比对括号后面的院区大小，将”本院“放在前面
			if (o1.getSortLetters().contains("(") && o1.getSortLetters().contains(")")) {
				letters_1 = o1.getSortLetters().substring(o1.getSortLetters().indexOf("(")+1, o1.getSortLetters().indexOf(")"));
			}else{
				//如果不包含括号，说明可能没有院区,则直接比对整个内容
				return o1.getSortLetters().compareTo(o2.getSortLetters());
			}
			
			if (o2.getSortLetters().contains("(") && o1.getSortLetters().contains(")")) {
				letters_2 = o2.getSortLetters().substring(o2.getSortLetters().indexOf("(")+1, o2.getSortLetters().indexOf(")"));
			}else{
				//如果不包含括号，说明可能没有院区,则直接比对整个内容
				return o1.getSortLetters().compareTo(o2.getSortLetters());
			}
			
			//这里是 “主院区” “东院区”,主院区需要在东院区前面，所以只能取反
				return -(letters_1.compareTo(letters_2));
		}
	}

}
