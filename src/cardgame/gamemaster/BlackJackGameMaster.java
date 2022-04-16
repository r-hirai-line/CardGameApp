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
		this.deck = new BlackJackDeck();
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
	void start() throws SystemErrorException {
		System.out.println("<< Welcome to Blackjack !! >>" + Consts.CRLF);

		// ディーラーアクション
		// カード表示
		openDealerCard();

		// プレイヤーアクション
		for (int i = 0; i < this.player.players.size(); i++) {

			if (this.player.players.get(i).isCPU) {
				// cpuAction();
				System.out.println("<<" + player.players.get(i).name + "さんのturn ! >>" + Consts.CRLF);

				// 手札の確認
				System.out.print("[" + player.players.get(i).name + "さん] ⇒ Hnad：");

				for (int j = 0; j < this.player.players.get(i).hand.size(); j++) {
					System.out.print(this.player.players.get(i).hand.get(j).suit + this.player.players.get(i).hand.get(j).rank + " ");
				}

				System.out.println(Consts.CRLF);

				// 点数の確認
				int result = 0;

				for (int j = 0; j < this.player.players.get(i).hand.size(); j++) {
					int val = BlackJackUtil.calcPoint(this.player.players.get(i).hand.get(j).num);
					result += val;
				}

				this.player.players.get(i).score = result;
				System.out.println("[" + player.players.get(i).name + "さん] ⇒ Score：" + this.player.players.get(i).score + Consts.CRLF);

				boolean isContinue = false;

				if (result < 17) {
					isContinue = true;
				}

				while (isContinue) {
					// カードを引く
					this.player.players.get(i).hand.add((BlackJackCard) deck.pick());

					// 引いたカードの確認
					for (int j = this.player.players.get(i).hand.size() - 1; j < this.player.players.get(i).hand.size(); j++) {
						System.out.println("[" + player.players.get(i).name + "さん] ⇒ [" + this.player.players.get(i).hand.get(j).suit + this.player.players.get(i).hand.get(j).rank + "]");
					}

					// 得点の確認
					result = 0;

					for (int j = 0; j < this.player.players.get(i).hand.size(); j++) {
						int val = BlackJackUtil.calcPoint(this.player.players.get(i).hand.get(j).num);
						result += val;
					}

					this.player.players.get(i).score = result;
					System.out.println("[" + player.players.get(i).name + "さん] ⇒ Score：" + this.player.players.get(i).score + Consts.CRLF);

					isContinue = false;

					if (result < 17) {
						isContinue = true;
					}
				}

			} else {

				System.out.println("<<" + player.players.get(i).name + "さんのturn ! >>" + Consts.CRLF);

				// 手札の確認
				System.out.print("[" + player.players.get(i).name + "さん] ⇒ Hnad：");

				for (int j = 0; j < this.player.players.get(i).hand.size(); j++) {
					System.out.print(this.player.players.get(i).hand.get(j).suit + this.player.players.get(i).hand.get(j).rank + " ");
				}

				System.out.println(Consts.CRLF);

				// 点数の確認
				int result = 0;

				for (int j = 0; j < this.player.players.get(i).hand.size(); j++) {
					int val = BlackJackUtil.calcPoint(this.player.players.get(i).hand.get(j).num);
					result += val;
				}

				this.player.players.get(i).score = result;
				System.out.println("[" + player.players.get(i).name + "さん] ⇒ Score：" + this.player.players.get(i).score + Consts.CRLF);

				// ヒットかステイか選ぶ
				String input = BlackJackUtil.getInputParam();
				boolean isContinue = BlackJackUtil.isContinue(input);
				while (isContinue) {
					// カードを引く
					this.player.players.get(i).hand.add((BlackJackCard) deck.pick());

					// 引いたカードの確認
					for (int j = this.player.players.get(i).hand.size() - 1; j < this.player.players.get(i).hand.size(); j++) {
						System.out.println("[" + player.players.get(i).name + "さん] ⇒ [" + this.player.players.get(i).hand.get(j).suit + this.player.players.get(i).hand.get(j).rank + "]");
					}

					// 得点の確認
					result = 0;

					for (int j = 0; j < this.player.players.get(i).hand.size(); j++) {
						int val = BlackJackUtil.calcPoint(this.player.players.get(i).hand.get(j).num);
						result += val;
					}

					this.player.players.get(i).score = result;
					System.out.println("[" + player.players.get(i).name + "さん] ⇒ Score：" + this.player.players.get(i).score + Consts.CRLF);

					// // カードを引くかどうかの判定
					input = BlackJackUtil.getInputParam();

					// 継続か判定
					isContinue = BlackJackUtil.isContinue(input);
				}
			}
		}
	}

	/**
	 * 得点計算
	 *
	 * フィールドにプレイヤーの順番情報を持つ
	 */
	public void calc() {

		int result = 0;

		for (int j = 0; j < this.player.players.get(0).hand.size(); j++) {
			result += BlackJackUtil.calcPoint(this.player.players.get(j).hand.get(j).num);
		}

		this.player.players.get(0).score = result;
	}

	/**
	 * ディーラーのカード表示
	 */
	public void openDealerCard() {
		System.out.println("[Dealer] ⇒ [" + this.player.players.get(0).hand.get(0).suit + this.player.players.get(0).hand.get(0).rank + "]");
		System.out.println("[Dealer] ⇒ [?]");
	}

	/**
	 * 手札表示
	 */
	public void openHand() {
		for (int i = 0; i < this.player.players.size(); i++) {

			System.out.println("[" + player.players.get(i).name + "さん] ⇒ Hnad：");

			for (int j = 0; j < this.player.players.get(i).hand.size(); j++) {
				System.out.print(this.player.players.get(i).hand.get(j).suit + this.player.players.get(i).hand.get(j).rank + " ");
			}

			System.out.println(Consts.CRLF);
		}
	}

}
