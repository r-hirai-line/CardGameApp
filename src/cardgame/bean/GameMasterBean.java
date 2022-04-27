package cardgame.bean;

import java.util.ArrayList;
import java.util.List;

import cardgame.card.Card;

/**
 *
 * GameMasterBeanクラス
 *
 * @author kijima
 *
 */
public class GameMasterBean {

	/**
	 * 山札
	 */
	private List<Card> deck = new ArrayList<>();

	/**
	 * ディーラー情報
	 */
	private DealerBean dealerInfo = new DealerBean();

	/**
	 * プレイヤーリスト
	 */
	private List<BlackJackPlayerBean> playerInfoList = new ArrayList<>();

	/**
	 * どのプレイヤーのターンかを制御する番号
	 */
	private int playerTurnNum = 0;

	public List<Card> getDeck() {
		return deck;
	}

	public void setDeck(Card card) {
		this.deck.add(card);
	}

	public DealerBean getDealerInfo() {
		return dealerInfo;
	}

	public void setDealerInfo(DealerBean dealerInfo) {
		this.dealerInfo = dealerInfo;
	}

	public List<BlackJackPlayerBean> getPlayerInfoList() {
		return playerInfoList;
	}

	public void setPlayerInfoList(BlackJackPlayerBean playerInfo) {
		this.playerInfoList.add(playerInfo);
	}

	public int getPlayerTurnNum() {
		return playerTurnNum;
	}

	public void setPlayerTurnNum(int playerTurnNum) {
		this.playerTurnNum = playerTurnNum;
	}

}
