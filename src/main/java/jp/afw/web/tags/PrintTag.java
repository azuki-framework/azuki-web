package jp.afw.web.tags;import javax.servlet.jsp.JspException;import jp.afw.core.util.StringUtility;/** * このクラスは、値の表示を行うタグクラスです。 *  * @since 1.0.0 * @version 1.0.0 2013/07/11 * @author Kawakicchi */public final class PrintTag extends AbstractPrintTag {	/**	 * コンストラクタ	 */	public PrintTag() {		super(PrintTag.class);	}	@Override	protected String doPrint(final Object aValue) throws JspException {		String string = StringUtility.EMPTY;		if (null != aValue) {			string = aValue.toString();		}		return string;	}}