package cardgame.player;

import cardgame.Consts;
import cardgame.bean.BlackJackPlayerBean;
import cardgame.bean.GameMasterBean;
import cardgame.card.BlackJackCard;
import cardgame.card.Card;
import cardgame.exception.SystemErrorException;
import cardgame.util.BlackJackUtil;

public class BlackJackPlayer extends Player {

	/**
	 * GameMasterBean
	 */
	GameMasterBean gameMasterBean;

	public BlackJackPlayer(GameMasterBean gameMasterBean) {
		this.gameMasterBean = gameMasterBean;
	}

	/**
	 * プレイヤー作成
	 */
	@Override
	public void build() {
		createMyChar();
		createCPU();
	}

	/**
	 * プレイヤー作成 ゲムマスが作る方が自然では？
	 */
	private void createMyChar() {
		BlackJackPlayerBean bean = new BlackJackPlayerBean();
		bean.setName("YOU");
		gameMasterBean.setPlayerInfoList(bean);
	}

	/**
	 * CPU作成 ゲムマスが作る方が自然では？
	 */
	private void createCPU() {
		for (int i = 1; i <= 3; i++) {
			BlackJackPlayerBean bean = new BlackJackPlayerBean();
			bean.setName("COM" + String.valueOf(i));
			bean.setCPU(true);
			gameMasterBean.setPlayerInfoList(bean);
		}
	}

	/**
	 * プレイヤーの動作
	 *
	 * @throws SystemErrorException
	 */
	@Override
	public void action() throws SystemErrorException {

		for (BlackJackPlayerBean player : gameMasterBean.getPlayerInfoList()) {

			// ターン開始
			turnStart();

			// 手札の確認
			openHand();

			// 点数の計算
			calcSum();

			if (player.isCPU()) {

				// CPUアルゴリズム
				cpuAI();
			} else {

				// ヒットかステイか選ぶ
				chooseHitOrStay();
			}

			// playerTurnNumを加算
			turnEnd();
		}
	}

	/**
	 * ターン開始
	 */
	public void turnStart() {
		System.out.println("<<" + gameMasterBean.getPlayerInfoList().get(gameMasterBean.getPlayerTurnNum()).getName() + "のターン ! >>");
	}

	/**
	 * 手札を表示
	 *
	 * @param i
	 */
	public void openHand() {
		System.out.print("[" + gameMasterBean.getPlayerInfoList().get(gameMasterBean.getPlayerTurnNum()).getName() + "] ⇒ Hnad：");

		for (BlackJackCard card : gameMasterBean.getPlayerInfoList().get(gameMasterBean.getPlayerTurnNum()).getHand()) {
			System.out.print(card.getSuit() + card.getRank() + " ");
		}

		System.out.println(Consts.CRLF);
	}

	/**
	 * 得点確認
	 *
	 * @param i
	 */
	public void calcSum() {

		int result = 0;

		// 手札の合計を取得
		for (BlackJackCard card : gameMasterBean.getPlayerInfoList().get(gameMasterBean.getPlayerTurnNum()).getHand()) {
			int val = BlackJackUtil.calcPoint(card.getNum());
			result += val;
		}

		// 得点をセット
		gameMasterBean.getPlayerInfoList().get(gameMasterBean.getPlayerTurnNum()).setScore(result);

		System.out.println("[" + gameMasterBean.getPlayerInfoList().get(gameMasterBean.getPlayerTurnNum()).getName() + "] ⇒ Score：" + gameMasterBean.getPlayerInfoList().get(gameMasterBean.getPlayerTurnNum()).getScore());
	}

	/**
	 * ヒットかステイか選択
	 *
	 * @param i
	 * @throws SystemErrorException
	 */
	public void chooseHitOrStay() throws SystemErrorException {

		// ヒットかステイか入力を受け付ける
		String input = BlackJackUtil.getInputParam();

		boolean isContinue = isContinue(input);

		while (isContinue) {

			// カードを引く
			pickCard();

			// 引いたカードの確認
			checkPickCard();

			// 得点の確認
			calcSum();

			if (gameMasterBean.getPlayerInfoList().get(gameMasterBean.getPlayerTurnNum()).getScore() <= 21) {
				// // カードを引くかどうかの判定
				input = BlackJackUtil.getInputParam();
			} else {
				input = "s";
			}

			// 継続か判定
			isContinue = isContinue(input);
		}
	}

	/**
	 *
	 * カードを引くか引かないかを判定
	 *
	 * @param input
	 * @return
	 * @throws SystemErrorException
	 */
	public boolean isContinue(String input) throws SystemErrorException {

		boolean isContinue = false;

		// Hitの場合、isContinueをtrueに変更
		if ("h".equals(input)) {
			isContinue = true;
		}
		// 確率計算（隠し機能コマンド）
		else if ("cheat".equals(input)) {

			double probability = BlackJackUtil.calcProbability(gameMasterBean.getDeck(), (gameMasterBean.getPlayerInfoList().get(gameMasterBean.getPlayerTurnNum()).getScore()));
			System.out.println("バースト確率：" + probability + "%");

			input = BlackJackUtil.getInputParam();
			return isContinue(input);
		}

		return isContinue;
	}

	/**
	 * 手札にカードを追加
	 */
	public void pickCard() {
		gameMasterBean.getPlayerInfoList().get(gameMasterBean.getPlayerTurnNum()).setHand((BlackJackCard) pick());
	}

	/**
	 * デッキの先頭からカードを一枚取り出す（共通にすべき）
	 */
	public Card pick() {

		// デッキの先頭からカードを一枚取り出す
		BlackJackCard card = (BlackJackCard) gameMasterBean.getDeck().get(0);

		// 山札からカードを一枚削除する
		gameMasterBean.getDeck().remove(0);

		return card;
	}

	/**
	 * 引いたカードの確認
	 */
	private void checkPickCard() {

		// 手札の一番最後の数を表示
		System.out.println("[" + gameMasterBean.getPlayerInfoList().get(gameMasterBean.getPlayerTurnNum()).getName() + "] ⇒ Hit Card：["
				+ gameMasterBean.getPlayerInfoList().get(gameMasterBean.getPlayerTurnNum()).getHand().get(gameMasterBean.getPlayerInfoList().get(gameMasterBean.getPlayerTurnNum()).getHand().size() - 1).getSuit()
				+ gameMasterBean.getPlayerInfoList().get(gameMasterBean.getPlayerTurnNum()).getHand().get(gameMasterBean.getPlayerInfoList().get(gameMasterBean.getPlayerTurnNum()).getHand().size() - 1).getRank() + "]");
	}

	/**
	 * ターン終了
	 */
	public void turnEnd() {

		// プレイヤーターンがプレイヤーの人数よりも大きくなる場合は、一人目のターンをセット
		if (gameMasterBean.getPlayerTurnNum() >= 3) {
			gameMasterBean.setPlayerTurnNum(0);
		}
		// プレイヤーターンに次のプレイヤーターンをセット
		else {
			int num = gameMasterBean.getPlayerTurnNum() + 1;
			gameMasterBean.setPlayerTurnNum(num);
		}

		System.out.println(Consts.CRLF);
	}

	/**
	 * CPUヒット / ステイ アルゴリズム
	 *
	 * @param result
	 * @param i
	 */
	public void cpuAI() {

		int result = gameMasterBean.getPlayerInfoList().get(gameMasterBean.getPlayerTurnNum()).getScore();

		boolean isContinue = false;

		if (result < 17) {
			isContinue = true;
		}

		while (isContinue) {

			// カードを引く
			pickCard();

			// 引いたカードの確認
			checkPickCard();

			// 得点の確認
			calcSum();

			if (gameMasterBean.getPlayerInfoList().get(gameMasterBean.getPlayerTurnNum()).getScore() >= 17) {
				isContinue = false;
			}
		}
	}
}
