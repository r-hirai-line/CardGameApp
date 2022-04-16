package cardgame.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import cardgame.Consts;
import cardgame.exception.SystemErrorException;

/**
 * ブラックジャックの計算/判定ロジックのUtilクラス
 *
 * @author kijima
 *
 */
public class BlackJackUtil {

	/**
	 * 入力受付オブジェクト
	 */
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * J, Q, Kが含まれていないかを判定
	 *
	 * @param rank
	 * @return
	 */
	public static boolean isJQK(String rank) {

		if ("J".equals(rank) || "Q".equals(rank) || "K".equals(rank)) {
			return true;
		}
		return false;
	}

	/**
	 * 入力の妥当性を判定
	 *
	 * @param input
	 * @return
	 */
	public static boolean isCorrectInput(String input) {

		if ("h".equals(input) || "s".equals(input)) {
			return true;
		}
		return false;
	}

	/**
	 * 入力パラメータ返却
	 *
	 * @return
	 * @throws SystemErrorException
	 */
	public static String getInputParam() throws SystemErrorException {

		// 入力
		String input = null;

		// 判定
		boolean isInputOk = false;

		// 正しい入力を受け付けるまで繰り返し入力を求める
		while (!isInputOk) {

			try {
				// 入力受付
				System.out.println("Hit or Stand? [h/s]" + Consts.CRLF);
				input = br.readLine();
				// 論理チェック
				if (BlackJackUtil.isCorrectInput(input)) {
					isInputOk = true;
				} else {
					System.out.println("Hitならばhを、Standならばsを入力して下さい。" + Consts.CRLF);
				}
			} catch (IOException e) {
				throw new SystemErrorException();
			}
		}

		return input;
	}

	/**
	 *
	 * @param input
	 * @return
	 */
	public static boolean isContinue(String input) {

		boolean isContinue = false;

		// Hitの場合、isContinueをtrueに変更
		if ("h".equals(input)) {
			isContinue = true;
		}
		return isContinue;
	}

	public static int calcPoint(int num) {

		int val = num;

		// J, Q, Kの場合
		if (num > 10) {
			val = 10;
		}

		return val;
	}
}
