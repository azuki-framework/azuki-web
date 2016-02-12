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
package org.azkfw.web.action;

import java.sql.SQLException;

import org.azkfw.business.database.DatabaseConnection;
import org.azkfw.database.DatabaseManager;

/**
 * このクラスは、データベース機能を実装するアクションクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/02/13
 * @author Kawakicchi
 */
public abstract class AbstractDatabaseAction extends AbstractPersistenceAction {

	/** Database */
	private DatabaseConnection connection;

	/**
	 * コンストラクタ
	 */
	public AbstractDatabaseAction() {

	}

	/**
	 * コンストラクタ
	 * 
	 * @param name 名前
	 */
	public AbstractDatabaseAction(final String name) {
		super(name);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param clazz クラス
	 */
	public AbstractDatabaseAction(final Class<?> clazz) {
		super(clazz);
	}
	
	/**
	 * コネクションを取得する。
	 * 
	 * @return コネクション
	 * @throws SQLException SQL操作に起因する問題が発生した場合
	 */
	protected final DatabaseConnection getConnection() throws SQLException {
		try {
			if (null == connection) {
				connection = new DatabaseConnection(DatabaseManager.getInstance().getConnection());
				connection.getConnection().setAutoCommit(false);
			}
		} catch (SQLException ex) {
			throw ex;
		}
		return connection;
	}

	/**
	 * コミット処理を行う。
	 * 
	 * @throws SQLException SQL実行中に問題が発生した場合
	 */
	protected final void commit() throws SQLException {
		DatabaseConnection connection = getConnection();
		if (null != connection) {
			connection.getConnection().commit();
		}
	}

	/**
	 * ロールバック処理を行う。
	 * 
	 * @throws SQLException SQL実行中に問題が発生した場合
	 */
	protected final void rollback() throws SQLException {
		DatabaseConnection connection = getConnection();
		if (null != connection) {
			connection.getConnection().rollback();
		}
	}

	@Override
	protected void doBeforeAction() {
		super.doBeforeAction();
		// TODO Write doBeforeAction code.

	}

	@Override
	protected void doAfterAction() {
		if (null != connection) {
			try {
				connection.getConnection().commit();
			} catch (SQLException ex) {
				fatal(ex);
			}
			try {
				connection.getConnection().close();
			} catch (SQLException ex) {
				fatal(ex);
			}
			connection = null;
		}

		super.doAfterAction();
	}
}
