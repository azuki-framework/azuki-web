package jp.afw.web.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.afw.persistence.context.ContextSupport;
import jp.afw.persistence.parameter.Parameter;
import jp.afw.persistence.parameter.ParameterSupport;
import jp.afw.persistence.proterty.Property;
import jp.afw.persistence.proterty.PropertyManager;
import jp.afw.persistence.proterty.PropertySupport;
import jp.afw.persistence.session.SessionSupport;
import jp.afw.persistence.store.Store;
import jp.afw.web.WebServiceException;
import jp.afw.web.action.Action;
import jp.afw.web.action.purser.ActionHttpServletPurser;
import jp.afw.web.purser.DefaultHttpServletPurser;
import jp.afw.web.purser.HttpServletPurser;
import jp.afw.web.store.HttpSessionStore;
import jp.afw.web.view.View;

/**
 * このクラスは、アクションのマッピングコントロールを行うサーブレットクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/02/19
 * @author Kawakicchi
 */
public abstract class AbstractActionMappingServlet extends AbstractServlet {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2088382830721888186L;

	/**
	 * コンストラクタ
	 */
	public AbstractActionMappingServlet() {
		super();
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aName 名前
	 */
	public AbstractActionMappingServlet(final String aName) {
		super(aName);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aClass クラス
	 */
	public AbstractActionMappingServlet(final Class<?> aClass) {
		super(aClass);
	}

	/**
	 * アクションを生成する。
	 * 
	 * @param aReq リクエスト情報
	 * @param aRes レスポンス情報
	 * @return アクション。アクションにマッピング出来ない場合、<code>null</code>を返す。
	 */
	protected abstract Action createAction(final HttpServletRequest aReq, final HttpServletResponse aRes);

	/**
	 * 処理を実行する。
	 * 
	 * @param aReq リクエスト情報
	 * @param aRes レスポンス情報
	 * @throws ServletException サーブレット機能に起因する問題が発生した場合
	 * @throws IOException IO操作時に問題が発生した場合
	 */
	protected final void doTask(final HttpServletRequest aReq, final HttpServletResponse aRes) throws ServletException, IOException {
		Action action = createAction(aReq, aRes);
		if (null == action) {
			aRes.sendError(404);
			return;
		}

		try {
			appendActionSupport(action, aReq, aRes);
			View view = action.action();
			if (null != view) {
				view.view(aReq, aRes);
			} else {
				fatal("No return view.[" + action.getClass().getName() + "]");
				throw new WebServiceException("No View.");
			}

		} catch (WebServiceException ex) {
			fatal(ex);
			aRes.sendError(500);
		}
	}

	protected void appendActionSupport(final Action aAction, final HttpServletRequest aReq, final HttpServletResponse aRes)
			throws WebServiceException {
		try {
			// Session support
			if (aAction instanceof SessionSupport) {
				Store<String, Object> session = new HttpSessionStore(aReq.getSession(true));
				((SessionSupport) aAction).setSession(session);
			}

			// Context support
			if (aAction instanceof ContextSupport) {
				((ContextSupport) aAction).setContext(getContext());
			}

			// Property support
			if (aAction instanceof PropertySupport) {
				Property property = PropertyManager.get(aAction.getClass());
				if (null == property) {
					property = PropertyManager.load(aAction.getClass(), getContext());
				}
				((PropertySupport) aAction).setProperty(property);
			}

			// Parameter support (request parameter)
			if (aAction instanceof ParameterSupport) {
				Map<String, Object> params = new HashMap<String, Object>();
				ActionHttpServletPurser aPurser = aAction.getClass().getAnnotation(ActionHttpServletPurser.class);
				if (null != aPurser) {
					Class<? extends HttpServletPurser>[] classes = aPurser.value();
					for (Class<? extends HttpServletPurser> clazz : classes) {
						HttpServletPurser purser = clazz.newInstance();
						Map<String, Object> m = purser.purse(aReq, aRes);
						params.putAll(m);
					}
				} else {
					DefaultHttpServletPurser purser = new DefaultHttpServletPurser();
					Map<String, Object> m = purser.purse(aReq, aRes);
					params.putAll(m);
				}
				Parameter parameter = new Parameter(params);
				((ParameterSupport) aAction).setParameter(parameter);
			}
		} catch (InstantiationException ex) {
			throw new WebServiceException(ex);
		} catch (IllegalAccessException ex) {
			throw new WebServiceException(ex);
		}
	}
}
