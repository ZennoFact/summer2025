package day1_1;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author k_maeno
 *　サマーコース1日目1時間目用。
 *　とりあえず，ざっとコードを書くことと，Eclipse上でF3を使うとどうなるかっていうあたりから，学びを進めていきたいと思います。
 */
public class Main {
	public static void main(String[] args) {
		// そもそも，System　out println ってなに？
		System.out.println("Hello World");

		System.out.println("FizzBuzz start!");
		System.out.print("Enter the number of the maximum... > ");

		// 警告が出るが，理由はcloseされていないことによる。closeメソッドの呼び出しが期待されているが，必要か。
		Scanner scanner = new Scanner(System.in);
		int max = scanner.nextInt();

//		// 授業での学習はこんな感じでの記載が多い印象
//		for (int i = 1; i <= max; i++) {
//			boolean isFizz = i % 3 == 0;
//			boolean isBuzz = i % 5 == 0;
//
//			if (isFizz && isBuzz)　System.out.println("FizzBuzz");
//			else if (isFizz)　System.out.println("Fizz");
//			else if (isBuzz)　System.out.println("Buzz");
//			else　System.out.println(i);
//		}

		// こんな考え方をしてもいい
		for (int i = 1; i <= max; i++) {
			String result = "";
			if (i % 3 == 0) result += "Fizz";
			if (i % 5 == 0) result += "Buzz";
			System.out.println(result.isEmpty() ? i : result);
		}

		System.out.println();

		// ちなみにScannerはファイルも取れる。（ぶっちゃけ文字列でも通る）
		try {
			scanner = new Scanner(new File("./src/day1_1/sample.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 取ったものがnull（もう無い）か，と書く方法もあるが，この手のものはhasNext（次があるか）メソッドで，データがあれば処理としていくのが素敵かなと。
		while(scanner.hasNext()) {
			System.out.println(scanner.nextLine());
		}

		// この処理が必要か考えたい
		scanner.close();
	}

}
