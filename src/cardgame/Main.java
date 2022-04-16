package cardgame;

import cardgame.gamemaster.BlackJackGameMaster;
import cardgame.gamemaster.GameMaster;

/**
 *
 * @author kijima
 *
 */
public class Main {

	static GameMaster game;

	/**
	 *
	 * args[0]:種目 args[1]:プレイヤー数
	 *
	 * @param args
	 */
	public static void main(String[] args) {

		// バッチIDチェック
		if (args == null || args.length == 0 || args.length == 2 || args.length >= 3) {

			System.out.println("ゲーム番号とプレイヤー人数を入力して下さい。");
			System.out.println("ブラックジャック：1");
			System.out.println("大富豪：2");
		} else if (Consts.BLACK_JACK.equals(args[0])) {

			// ブラックジャックのBlを呼び出し
			game = new BlackJackGameMaster();
		} else if (Consts.DAIFUGO.equals(args[0])) {

			// 大富豪のBlを呼び出し
			// game = new DaihugouGameMaster();
		} else {

			System.out.println("ゲーム番号とプレイヤー人数を入力して下さい。");
			System.out.println("ブラックジャック：1");
			System.out.println("大富豪：2");
		}

		// ゲーム開始
		game.execute(args);
	}
}
