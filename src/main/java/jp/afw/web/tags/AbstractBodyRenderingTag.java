package jp.afw.web.tags;import javax.servlet.jsp.JspException;import javax.servlet.jsp.tagext.BodyContent;import javax.servlet.jsp.tagext.BodyTag;import javax.servlet.jsp.tagext.Tag;/** * このクラスは、レンダリング機能を実装したタグクラスです。 *  * @since 1.0.0 * @version 1.0.0 2013/07/09 * @author Kawakicchi */public abstract class AbstractBodyRenderingTag extends AbstractRenderingTag implements BodyTag {	/**	 * ボディコンテンツ	 */	private BodyContent bodyContent;	/**	 * ボディ文字列	 */	private String bodyString;	/**	 * コンストラクタ	 */	public AbstractBodyRenderingTag() {		super();	}	/**	 * コンストラクタ	 * 	 * @param aName 名前	 */	public AbstractBodyRenderingTag(final String aName) {		super(aName);	}	/**	 * コンストラクタ	 * 	 * @param aClass クラス	 */	public AbstractBodyRenderingTag(final Class<?> aClass) {		super(aClass);	}	@Override	public final int doStartTag() throws JspException {		return (BodyTag.EVAL_BODY_BUFFERED);	}	@Override	public void setBodyContent(final BodyContent aBodyContent) {		bodyContent = aBodyContent;	}	@Override	public void doInitBody() throws JspException {		// TODO Auto-generated method stub	}	@Override	public int doAfterBody() throws JspException {		bodyString = bodyContent.getString();		return (Tag.SKIP_BODY);	}	/**	 * ボディー部の文字列を取得する。	 * 	 * @return ボディー部の文字列を取得する。	 */	protected final String getBodyString() {		return bodyString;	}}