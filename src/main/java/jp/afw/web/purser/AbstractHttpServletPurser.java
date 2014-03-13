package jp.afw.web.purser;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.afw.core.lang.LoggingObject;
import jp.afw.web.WebServiceException;

/**
 * このクラスは、HTTPサーブレットの解析を行うための基底クラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/29
 * @author Kawakicchi
 */
public abstract class AbstractHttpServletPurser extends LoggingObject implements HttpServletPurser {

	/**
	 * コンストラクタ
	 */
	public AbstractHttpServletPurser() {
		super(HttpServletPurser.class);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aName 名前
	 */
	public AbstractHttpServletPurser(final String aName) {
		super(aName);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aClass クラス
	 */
	public AbstractHttpServletPurser(final Class<?> aClass) {
		super(aClass);
	}

	@Override
	public final void initialize() {
		doInitialize();
	}

	@Override
	public final void destroy() {
		doDestroy();
	}

	@Override
	public final Map<String, Object> purse(final HttpServletRequest aReq, final HttpServletResponse aRes) throws WebServiceException {
		return doPurse(aReq, aRes);
	}

	/**
	 * 初期化処理を行う。
	 */
	protected abstract void doInitialize();

	/**
	 * 解放処理を行う。
	 */
	protected abstract void doDestroy();

	/**
	 * HTTPサーブレットを解析する。
	 * 
	 * @param aReq リクエスト情報
	 * @param aRes レスポンス情報
	 * @return パラメーター
	 * @throws WebServiceException Webサービス層に起因する問題が発生した場合
	 */
	protected abstract Map<String, Object> doPurse(final HttpServletRequest aReq, final HttpServletResponse aRes) throws WebServiceException;

}
