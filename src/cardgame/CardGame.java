package cardgame;

import cardgame.gamemaster.BlackJackGameMaster;
import cardgame.gamemaster.GameMaster;

/**
 *
 * Mainクラス
 *
 * @author kijima
 *
 */
public class CardGame {

	/**
	 * GameMaster
	 */
	static GameMaster game;

	/**
	 * ゲームマスタクラス起動
	 *
	 * @param args
	 *
	 */
	public CardGame(String[] args) {

		// 引数チェック
		if (args == null || args.length == 0) {

			System.out.println("ゲーム種目番号を入力して下さい。");
		} else if (Consts.BLACK_JACK.equals(args[0])) {

			game = new BlackJackGameMaster();
		} else if (Consts.DAIFUGO.equals(args[0])) {

			// game = new DaihugouGameMaster();
		} else {

			System.out.println("ゲーム種目番号を確認して下さい。");
			System.out.println("ブラックジャック：001");
			System.out.println("大富豪：002");
		}

		// ゲーム開始
		game.execute(args);
	}

	/**
	 *
	 * main
	 *
	 * args[0]:種目 args[1]:プレイヤー数
	 *
	 * @param args
	 */
	public static void main(String[] args) {

		CardGame cardGame = new CardGame(args);
	}
}
