package util;


import java.util.Scanner;

public class ScanUtil {
	// 스캐너를 손쉽게 사용할 수 있는 static 메서드를 가지고있음
	static Scanner sc = new Scanner(System.in);

	public static int menu() {
		return nextInt("메뉴: ");
	}

	// 한글 체크 메서드
	public static String checkHangle(String print) {
		while (true) {
			String str = nextLine(print);
			for (int i = 0; i < str.length(); i++) {
				// 한글이 아닌 값이 입력 받아지면 계속 while되게 하기
			}
			return nextLine(print);
		}
	}

	public static String nextLine(String print) {
		System.out.print(print);
		return nextLine();
	}

	// 안내 문구 없이 scan 못쓰게 private로 막아놨음
	private static String nextLine() {
		return sc.nextLine();
	}

	public static int nextInt(String print) {
		System.out.print(print);
		return nextInt();
	}

	// 잘못입력 받았을때 다시 입력 받게 하는 매서드
	// 정상 입력 되지 않으면 return 안하고 while재실행
	private static int nextInt() {
		while (true) {
			try {
				int result = Integer.parseInt(sc.nextLine());
				return result;
			} catch (NumberFormatException e) {
				System.out.println("잘못 입력!!");
			}
		}
	}
}
