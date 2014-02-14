package jp.afw.web.purser;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.afw.web.WebServiceException;

/**
 * このインターフェースは、HTTPサーブレットの解析機能を表現したインターフェースです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/29
 * @author Kawakicchi
 */
public interface HttpServletPurser {

	/**
	 * HTTPサーブレットを解析する。
	 * 
	 * @param aReq リクエスト情報
	 * @param aRes レスポンス情報
	 * @return パラメーター
	 * @throws WebServiceException Webサービス層に起因する問題が発生した場合
	 */
	public Map<String, Object> purse(final HttpServletRequest aReq, final HttpServletResponse aRes) throws WebServiceException;
}
