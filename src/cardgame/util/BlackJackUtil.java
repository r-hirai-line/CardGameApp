package cardgame.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import cardgame.Consts;
import cardgame.card.Card;
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

		if ("h".equals(input) || "s".equals(input) || "cheat".equals(input)) {
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

	public static int calcPoint(int num) {

		int val = num;

		// J, Q, Kの場合
		if (num > 10) {
			val = 10;
		}

		return val;
	}

	/**
	 * バースト確率を返却（未実装）
	 *
	 * @param score
	 * @param deck
	 * @return
	 */
	public static double calcProbability(List<Card> deck, int score) {

		// バーストまでの得点
		int forgiveNum = 21 - score;

		// 引いても大丈夫な数の枚数
		int butstCard = 0;
		for (Card card : deck) {
			if (card.getNum() > forgiveNum) {
				butstCard++;
			}
		}

		// 確率計算
		double probability = Double.valueOf(butstCard) / Double.valueOf(deck.size()) * 100;

		return probability;

	}
}
