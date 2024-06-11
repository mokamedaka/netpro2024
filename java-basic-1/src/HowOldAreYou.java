// C言語では、#include に相当する
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HowOldAreYou {

	public static void main(String[] args) { 

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			// BufferedReader というのは、データ読み込みのクラス(型)
			// クラスの変数を作るには、new を使う。

			// readLine() は、入出力エラーの可能性がある。エラー処理がないとコンパイルできない。
			//  Java では、 try{ XXXXXXXX }  catch(エラーの型 変数) { XXXXXXXXXXXXXXXXXX} と書く
			while (true) {
				try {
					System.out.println("何歳ですか? (qまたはeで終了)");
					String line = reader.readLine();
					
					if (line.equals("q") || line.equals("e")) {
						break;
					}
					
					int age = Integer.parseInt(line);
					if (age < 0 || age >= 120) {
						System.out.println("無効な年齢です。再入力してください。");
						continue;
					}
					
					System.out.println("あなたは" + age + "歳ですね。");
					System.out.println("あなたは10年後、" + (age + 10) + "歳ですね。");
					
					int birthYear = 2030 - age;
					String era = getEra(birthYear);
					
					System.out.println("あなたは2030年、" + age + "歳ですね。");
					System.out.println("あなたは" + era + "生まれです。");
				}	catch(IOException e) {
				System.out.println(e);
				}
			}
		}
		
		public static String getEra(int year) {
			if (year <= 1912) {
				return "明治";
			} else if (year <= 1926) {
				return "大正";
			} else if (year <= 1989) {
				return "昭和";
			} else if (year <= 2019) {
				return "平成";
			} else {
				return "令和";
			}
		}
	}
	
//  課題    キーボードから数字を打ち込む
//  その結果、 あなたは、???歳ですね、と画面に表示させる。
//  その後、あなたは10年後、????歳ですね、と画面に表示させる。