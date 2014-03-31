/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jp.afw.web.servlet;

import javax.servlet.http.HttpServlet;

import jp.afw.core.log.Logger;
import jp.afw.core.log.LoggerFactory;

/**
 * このクラスは、サーブレット機能を実装した基底クラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/06/26
 * @author Kawakicchi
 */
public abstract class PrimitiveServlet extends HttpServlet {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2063808859381345201L;

	/**
	 * Default logger
	 */
	private static Logger LOGGER = LoggerFactory.create(PrimitiveServlet.class);

	/**
	 * logger
	 */
	private Logger logger;

	/**
	 * コンストラクタ
	 */
	public PrimitiveServlet() {
		logger = null;
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aName 名前
	 */
	public PrimitiveServlet(final String aName) {
		logger = LoggerFactory.create(aName);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aClass クラス
	 */
	public PrimitiveServlet(final Class<?> aClass) {
		logger = LoggerFactory.create(aClass);
	}

	/**
	 * debugレベルのログを出力します。
	 * 
	 * @param aMessage Message
	 */
	protected final void debug(final String aMessage) {
		if (null != logger) {
			logger.debug(aMessage);
		} else {
			LOGGER.debug(aMessage);
		}
	}

	/**
	 * debugレベルのログを出力します。
	 * 
	 * @param aThrow Throwable
	 */
	protected final void debug(final Throwable aThrow) {
		if (null != logger) {
			logger.debug(aThrow);
		} else {
			LOGGER.debug(aThrow);
		}
	}

	/**
	 * debugレベルのログを出力します。
	 * 
	 * @param aMessage Message
	 * @param aThrow Throwable
	 */
	protected final void debug(final String aMessage, final Throwable aThrow) {
		if (null != logger) {
			logger.debug(aMessage, aThrow);
		} else {
			LOGGER.debug(aMessage, aThrow);
		}
	}

	/**
	 * infoレベルのログを出力します。
	 * 
	 * @param aMessage Message
	 */
	protected final void info(final String aMessage) {
		if (null != logger) {
			logger.info(aMessage);
		} else {
			LOGGER.info(aMessage);
		}
	}

	/**
	 * infoレベルのログを出力します。
	 * 
	 * @param aThrow Throwable
	 */
	protected final void info(final Throwable aThrow) {
		if (null != logger) {
			logger.info(aThrow);
		} else {
			LOGGER.info(aThrow);
		}
	}

	/**
	 * infoレベルのログを出力します。
	 * 
	 * @param aMessage Message
	 * @param aThrow Throwable
	 */
	protected final void info(final String aMessage, final Throwable aThrow) {
		if (null != logger) {
			logger.info(aMessage, aThrow);
		} else {
			LOGGER.info(aMessage, aThrow);
		}
	}

	/**
	 * warnレベルのログを出力します。
	 * 
	 * @param aMessage Message
	 */
	protected final void warn(final String aMessage) {
		if (null != logger) {
			logger.warn(aMessage);
		} else {
			LOGGER.warn(aMessage);
		}
	}

	/**
	 * warnレベルのログを出力します。
	 * 
	 * @param aThrow Throwable
	 */
	protected final void warn(final Throwable aThrow) {
		if (null != logger) {
			logger.warn(aThrow);
		} else {
			LOGGER.warn(aThrow);
		}
	}

	/**
	 * warnレベルのログを出力します。
	 * 
	 * @param aMessage Message
	 * @param aThrow Throwable
	 */
	protected final void warn(final String aMessage, final Throwable aThrow) {
		if (null != logger) {
			logger.warn(aMessage, aThrow);
		} else {
			LOGGER.warn(aMessage, aThrow);
		}
	}

	/**
	 * errorレベルのログを出力します。
	 * 
	 * @param aMessage Message
	 */
	protected final void error(final String aMessage) {
		if (null != logger) {
			logger.error(aMessage);
		} else {
			LOGGER.error(aMessage);
		}
	}

	/**
	 * errorレベルのログを出力します。
	 * 
	 * @param aThrow Throwable
	 */
	protected final void error(final Throwable aThrow) {
		if (null != logger) {
			logger.error(aThrow);
		} else {
			LOGGER.error(aThrow);
		}
	}

	/**
	 * errorレベルのログを出力します。
	 * 
	 * @param aMessage Message
	 * @param aThrow Throwable
	 */
	protected final void error(final String aMessage, final Throwable aThrow) {
		if (null != logger) {
			logger.error(aMessage, aThrow);
		} else {
			LOGGER.error(aMessage, aThrow);
		}
	}

	/**
	 * fatalレベルのログを出力します。
	 * 
	 * @param aMessage Message
	 */
	protected final void fatal(final String aMessage) {
		if (null != logger) {
			logger.fatal(aMessage);
		} else {
			LOGGER.fatal(aMessage);
		}
	}

	/**
	 * fatalレベルのログを出力します。
	 * 
	 * @param aThrow Throwable
	 */
	protected final void fatal(final Throwable aThrow) {
		if (null != logger) {
			logger.fatal(aThrow);
		} else {
			LOGGER.fatal(aThrow);
		}
	}

	/**
	 * fatalレベルのログを出力します。
	 * 
	 * @param aMessage Message
	 * @param aThrow Throwable
	 */
	protected final void fatal(final String aMessage, final Throwable aThrow) {
		if (null != logger) {
			logger.fatal(aMessage, aThrow);
		} else {
			LOGGER.fatal(aMessage, aThrow);
		}
	}
}
