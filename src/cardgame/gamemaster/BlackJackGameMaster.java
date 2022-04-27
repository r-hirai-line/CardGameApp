package cardgame.gamemaster;

import cardgame.Consts;
import cardgame.bean.BlackJackPlayerBean;
import cardgame.card.BlackJackCard;
import cardgame.dealer.Dealer;
import cardgame.deck.BlackJackDeck;
import cardgame.exception.SystemErrorException;
import cardgame.player.BlackJackPlayer;

/**
 *
 * BlackJackゲームマスタークラス
 *
 * @author kijima
 *
 */
public class BlackJackGameMaster extends GameMaster {

	/**
	 * Dealer
	 */
	public Dealer dealer;

	/**
	 * BlackJackPlayer
	 */
	public BlackJackPlayer player;

	/**
	 * 山札作成
	 */
	@Override
	public void createCardList() {
		this.deck = new BlackJackDeck(gameMasterBean);
	}

	/**
	 * プレイヤー作成
	 */
	@Override
	void createPlayer() {
		this.dealer = new Dealer(gameMasterBean);
		this.player = new BlackJackPlayer(gameMasterBean);
		this.player.build();
	}

	/**
	 * カード分配
	 */
	@Override
	void distribute() {

		// ディーラーに手札を配る
		for (int j = 0; j < Consts.BLACK_JACK_HAND; j++) {
			gameMasterBean.getDealerInfo().setHand(deck.pick());
		}

		// プレイヤーに手札を配る
		for (BlackJackPlayerBean player : gameMasterBean.getPlayerInfoList()) {
			for (int j = 0; j < Consts.BLACK_JACK_HAND; j++) {
				player.setHand((BlackJackCard) deck.pick());
			}
		}
	}

	/**
	 * @throws SystemErrorException
	 *
	 */
	@Override
	public void start() throws SystemErrorException {
		System.out.println("<< Welcome to Blackjack !! >>");

		// ディーラーの手札を表示
		dealer.openCardFirstTime();

		// プレイヤーアクション
		player.action();

		// ディーラーアクション
		dealer.action();

		// 勝敗判定
		judg();
	}

	/**
	 * 勝敗判定
	 */
	public void judg() {
		int dealerPoint = gameMasterBean.getDealerInfo().getScore();

		// ディーラーがバーストしていない場合
		if (dealerPoint <= 21) {

			for (BlackJackPlayerBean player : gameMasterBean.getPlayerInfoList()) {
				// 得点が21を超える場合バースト
				if (player.getScore() > 21) {
					System.out.println(player.getName() + "：BURST...");
				} else {
					// 勝利
					if (dealerPoint < player.getScore()) {
						System.out.println(player.getName() + "：WIN！");
					}
					// 敗北
					else if (dealerPoint > player.getScore()) {
						System.out.println(player.getName() + "：LOSE...");
					}
					// 引き分け
					else {
						System.out.println(player.getName() + "：DROW");
					}
				}
			}
		}
		// ディーラーがバーストしている場合
		else {
			for (BlackJackPlayerBean player : gameMasterBean.getPlayerInfoList()) {

				// 21を超えていなければ勝利
				if (player.getScore() <= 21) {
					System.out.println(player.getName() + "：WIN!");
				}
				// 21を超えていればバースト
				else {
					System.out.println(player.getName() + "：BURST...");
				}
			}
		}
	}

}
