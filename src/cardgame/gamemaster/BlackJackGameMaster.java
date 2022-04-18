package cardgame.gamemaster;

import cardgame.Consts;
import cardgame.card.BlackJackCard;
import cardgame.dealer.Dealer;
import cardgame.deck.BlackJackDeck;
import cardgame.deck.Deck;
import cardgame.exception.SystemErrorException;
import cardgame.player.BlackJackPlayer;
import cardgame.util.BlackJackUtil;

/**
 *
 * @author kijima
 *
 */
public class BlackJackGameMaster extends GameMaster {

	/**
	 * 山札クラス
	 */
	public Deck deck;

	/**
	 * プレイヤークラス
	 */
	public Dealer dealer;

	/**
	 * プレイヤークラス
	 */
	public BlackJackPlayer player;

	/**
	 * パラメータチェック
	 */
	@Override
	void cheackParam(String[] args) throws SystemErrorException {
	}

	/**
	 * 山札作成
	 */
	@Override
	public void createCardList() {
		deck = new BlackJackDeck();
	}

	/**
	 * プレイヤー作成
	 */
	@Override
	void createPlayer() {
		this.dealer = new Dealer();
		this.player = new BlackJackPlayer();
		this.player.build();
	}

	/**
	 * カード分配
	 */
	@Override
	void distribute() {

		// ディーラーに手札を配る
		for (int j = 0; j < Consts.BLACK_JACK_HAND; j++) {
			dealer.hand.add(deck.pick());
		}

		// プレイヤーの持ち札に手札を追加
		for (int i = 0; i < this.player.players.size(); i++) {
			for (int j = 0; j < Consts.BLACK_JACK_HAND; j++) {
				player.players.get(i).hand.add((BlackJackCard) deck.pick());
			}
		}
	}

	/**
	 * @throws SystemErrorException
	 *
	 */
	@Override
	public void start() throws SystemErrorException {
		System.out.println("<< Welcome to Blackjack !! >>" + Consts.CRLF);

		// ディーラーカード表示
		openDealerCard();

		// プレイヤーアクション
		playerAction();

		// ディーラーアクション
		dealerAction();

		// 勝敗判定
		judg();
	}

	/**
	 * 勝敗判定
	 */
	public void judg() {
		int dealerPoint = this.dealer.score;

		// ディーラーがバーストしていない場合
		if (dealerPoint <= 21) {
			for (int i = 0; i < this.player.players.size(); i++) {

				if (this.player.players.get(i).score > 21) {
					System.out.println(this.player.players.get(i).name + "バースト。乙。");
				} else {
					// 勝利
					if (dealerPoint < this.player.players.get(i).score) {
						System.out.println(this.player.players.get(i).name + "：勝ち");
					}
					// 敗北
					else if (dealerPoint > this.player.players.get(i).score) {
						System.out.println(this.player.players.get(i).name + "：はい！負け〜！！！");
					}
					// 引き分け
					else {
						System.out.println(this.player.players.get(i).name + "引き分け");
					}
				}
			}
		}
		// ディーラーがバーストしている場合
		else {
			for (int i = 0; i < this.player.players.size(); i++) {
				if (this.player.players.get(i).score <= 21) {
					System.out.println(this.player.players.get(i).name + "：勝ち");
				} else {
					System.out.println(this.player.players.get(i).name + "：はい！負け〜！！！");
				}
			}
		}
	}

	/**
	 * ディーラーのカード表示
	 */
	public void openDealerCard() {
		System.out.println("[Dealer] ⇒ [" + this.player.players.get(0).hand.get(0).suit + this.player.players.get(0).hand.get(0).rank + "]");
		System.out.println("[Dealer] ⇒ [?]");
	}

	/**
	 * プレイヤーアクション
	 *
	 * @throws SystemErrorException
	 */
	public void playerAction() throws SystemErrorException {

		for (int i = 0; i < this.player.players.size(); i++) {

			// 開始宣言
			showPlayerInfo(i);

			// 手札の確認
			checkHand(i);

			// 点数の確認
			calcSum(i);

			if (this.player.players.get(i).isCPU) {

				// CPUアルゴリズム
				cpuAI(i);
			} else {

				// ヒットかステイか選ぶ
				chooseHitOrStay(i);
			}

		}
	}

	/**
	 * 誰のターンかを宣言する
	 *
	 * @param i
	 */
	public void showPlayerInfo(int i) {

		System.out.println("<<" + player.players.get(i).name + "のturn ! >>" + Consts.CRLF);
	}

	/**
	 * 手札確認
	 *
	 * @param i
	 */
	public void checkHand(int i) {
		System.out.print("[" + player.players.get(i).name + "] ⇒ Hnad：");

		for (int j = 0; j < this.player.players.get(i).hand.size(); j++) {
			System.out.print(this.player.players.get(i).hand.get(j).suit + this.player.players.get(i).hand.get(j).rank + " ");
		}

		System.out.println(Consts.CRLF);
	}

	/**
	 * 得点確認
	 *
	 * @param i
	 */
	public void calcSum(int i) {

		int result = 0;

		for (int j = 0; j < this.player.players.get(i).hand.size(); j++) {
			int val = BlackJackUtil.calcPoint(this.player.players.get(i).hand.get(j).num);
			result += val;
		}

		this.player.players.get(i).score = result;

		System.out.println("[" + player.players.get(i).name + "] ⇒ Score：" + this.player.players.get(i).score + Consts.CRLF);
	}

	/**
	 * ヒットかステイか選択
	 *
	 * @param i
	 * @throws SystemErrorException
	 */
	public void chooseHitOrStay(int i) throws SystemErrorException {

		// ヒットかステーか入力を受け付ける
		String input = BlackJackUtil.getInputParam();

		boolean isContinue = BlackJackUtil.isContinue(input);

		while (isContinue) {

			// カードを引く
			pickCard(i);

			// 引いたカードの確認
			checkPickCard(i);

			// 得点の確認
			calcSum(i);

			// // カードを引くかどうかの判定
			input = BlackJackUtil.getInputParam();

			// 継続か判定
			isContinue = BlackJackUtil.isContinue(input);
		}
	}

	/**
	 * CPUアルゴリズム
	 *
	 * @param result
	 * @param i
	 */
	public void cpuAI(int i) {

		int result = this.player.players.get(i).score;

		boolean isContinue = false;

		if (result < 17) {
			isContinue = true;
		}

		while (isContinue) {

			// カードを引く
			pickCard(i);

			// 引いたカードの確認
			checkPickCard(i);

			// 得点の確認
			calcSum(i);

			if (this.player.players.get(i).score >= 17) {
				isContinue = false;
			}
		}
	}

	/**
	 * カードを一枚引く
	 *
	 * @param i
	 */
	private void pickCard(int i) {

		this.player.players.get(i).hand.add((BlackJackCard) deck.pick());
	}

	/**
	 * 引いたカードの確認
	 *
	 * @param i
	 */
	private void checkPickCard(int i) {

		for (int j = this.player.players.get(i).hand.size() - 1; j < this.player.players.get(i).hand.size(); j++) {
			System.out.println("[" + player.players.get(i).name + "] ⇒ [" + this.player.players.get(i).hand.get(j).suit + this.player.players.get(i).hand.get(j).rank + "]");
		}
	}

	/**
	 * ディーラーアクション
	 */
	public void dealerAction() {

		// 手札の確認
		System.out.print("[ Dealer ] ⇒ Hnad：");

		for (int j = 0; j < this.dealer.hand.size(); j++) {
			System.out.print(this.dealer.hand.get(j).suit + this.dealer.hand.get(j).rank + " ");
		}

		System.out.println(Consts.CRLF);

		// 点数計算
		calcDealerSum();

		boolean isContinue = false;

		if (this.dealer.score < 17) {
			isContinue = true;
		}

		while (isContinue) {

			// カードを引く
			this.dealer.hand.add((BlackJackCard) deck.pick());

			// 引いたカードの確認
			for (int j = this.dealer.hand.size() - 1; j < this.dealer.hand.size(); j++) {
				System.out.println("[ Dealer ] ⇒ [" + this.dealer.hand.get(j).suit + this.dealer.hand.get(j).rank + "]");
			}

			// 得点計算
			calcDealerSum();

			if (this.dealer.score >= 17) {
				isContinue = false;
			}
		}
	}

	/**
	 * 得点計算
	 *
	 * @param i
	 */
	public void calcDealerSum() {

		int result = 0;

		for (int j = 0; j < this.dealer.hand.size(); j++) {
			int val = BlackJackUtil.calcPoint(this.dealer.hand.get(j).num);
			result += val;
		}

		this.dealer.score = result;

		System.out.println("[ Dealer ] ⇒ Score：" + this.dealer.score + Consts.CRLF);
	}
}
