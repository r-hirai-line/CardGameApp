package cardgame.gamemaster;

import java.util.List;

import cardgame.bean.GameMasterBean;
import cardgame.deck.Deck;
import cardgame.exception.SystemErrorException;
import cardgame.player.Player;

/**
 * GameMasterの抽象クラス
 *
 * ※各ゲームのゲームマスタークラスはこのクラスを継承すること
 *
 * @author kijima
 *
 */
public abstract class GameMaster {

	/**
	 * 山札クラス
	 */
	public Deck deck;

	/**
	 * GameMasterBean
	 */
	public GameMasterBean gameMasterBean;

	/**
	 * プレイヤー人数
	 */
	public int players;

	/**
	 * プレイヤー情報
	 */
	public List<Player> playerInfoList;

	/**
	 *
	 * 全ゲームの共通処理
	 *
	 * @param args
	 * @throws SystemErrorException
	 * @throws Exception
	 */
	public void execute(String[] args) {

		try {
			// パラメータチェック
			// cheackParam(args);

			// 初期処理
			init();

			// ゲーム開始
			start();

		} catch (SystemErrorException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 初期処理
	 *
	 * @param
	 */
	public void init() {

		gameMasterBean = new GameMasterBean();

		// 山札作成
		createCardList();

		// プレイヤー作成
		createPlayer();

		// カード分配
		distribute();
	}

	/**
	 * パラメータチェック
	 *
	 * @param args
	 * @throws SystemErrorException
	 */
	// abstract void cheackParam(String[] args) throws SystemErrorException;

	/**
	 * 山札作成
	 *
	 * @return
	 */
	abstract void createCardList();

	/**
	 * プレーヤー作成
	 */
	abstract void createPlayer();

	/**
	 * カード分配
	 */
	abstract void distribute();

	/**
	 * ゲーム開始
	 */
	abstract void start() throws SystemErrorException;
}
