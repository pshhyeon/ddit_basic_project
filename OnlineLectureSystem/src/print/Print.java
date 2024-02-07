package print;

public class Print {

	// 선 긋기
	public void var(int num) {
		String s = "";
		for (int i = 0; i < num; i++) {
			s += "-";
		}
		System.out.print(s);
	}

	// 띄우기
	public void space(int num) {
		String s = "";
		for (int i = 0; i < num; i++) {
			s += " ";
		}
		System.out.print(s);
	}

	// #
	public void shap(int num) {
		String s = "";
		for (int i = 0; i < num; i++) {
			s += "#";
		}
		System.out.print(s);
	}

}
