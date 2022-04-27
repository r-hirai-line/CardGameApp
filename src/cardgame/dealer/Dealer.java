package cardgame.dealer;

import cardgame.Consts;
import cardgame.bean.GameMasterBean;
import cardgame.card.BlackJackCard;
import cardgame.card.Card;
import cardgame.util.BlackJackUtil;

public class Dealer {

	/**
	 * GameMasterBean
	 */
	GameMasterBean gameMasterBean;

	/**
	 * コンストラクタ
	 *
	 * @param gameMasterBean
	 */
	public Dealer(GameMasterBean gameMasterBean) {
		this.gameMasterBean = gameMasterBean;
	}

	/**
	 * 初回手札を表示
	 */
	public void openCardFirstTime() {
		System.out.println("[Dealer] ⇒ [" + gameMasterBean.getDealerInfo().getHand().get(0).getSuit() + gameMasterBean.getDealerInfo().getHand().get(0).getRank() + "]");
		System.out.println("[Dealer] ⇒ [?]");
		System.out.println(Consts.CRLF);
	}

	/**
	 * 手札を表示
	 */
	public void openCard() {

		for (Card card : gameMasterBean.getDealerInfo().getHand()) {
			System.out.print(card.getSuit() + card.getRank() + " ");
		}

		System.out.println(Consts.CRLF);
	}

	/**
	 * ディーラーの動作
	 */
	public void action() {

		// 手札の確認
		System.out.print("[Dealer] ⇒ Hnad：");
		openCard();

		// 点数計算
		calcSum();

		boolean isContinue = false;

		if (gameMasterBean.getDealerInfo().getScore() < 17) {
			isContinue = true;
		}

		while (isContinue) {

			// カードを引く
			pickCard();

			// 引いたカードの確認
			checkPickCard();

			// 得点計算
			calcSum();

			if (gameMasterBean.getDealerInfo().getScore() >= 17) {
				isContinue = false;
			}
		}
	}

	/**
	 * 得点確認
	 *
	 * @param i
	 */
	public void calcSum() {

		int result = 0;

		// 手札の合計を取得
		for (Card card : gameMasterBean.getDealerInfo().getHand()) {
			int val = BlackJackUtil.calcPoint(card.getNum());
			result += val;
		}

		// 得点をセット
		gameMasterBean.getDealerInfo().setScore(result);

		System.out.println("[Dealer] ⇒ Score：" + gameMasterBean.getDealerInfo().getScore());
	}

	/**
	 * 手札にカードを追加
	 */
	public void pickCard() {
		gameMasterBean.getDealerInfo().setHand((BlackJackCard) pick());
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
		System.out.println("[Dealer] ⇒ Hit Card：[" + gameMasterBean.getDealerInfo().getHand().get(gameMasterBean.getDealerInfo().getHand().size() - 1).getSuit()
				+ gameMasterBean.getDealerInfo().getHand().get(gameMasterBean.getDealerInfo().getHand().size() - 1).getRank() + "]");
	}
}
