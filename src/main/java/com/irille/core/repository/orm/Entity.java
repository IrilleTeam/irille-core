package com.irille.core.repository.orm;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.irille.core.repository.Query2;
import com.irille.core.repository.db.ConnectionManager;
import com.irille.core.repository.query.EntityQuery;

import irille.pub.Log;

public abstract class Entity extends Query2 {
	
	private static final Log LOG = new Log(Entity.class);

	private static final Logger logger = LoggerFactory.getLogger(Entity.class);

	public Entity init() {
		return this;
	};

	public Entity ins() {
		Table<?> table = Entity.table(this.getClass());
		EntityQuery<?> q = insert(this.getClass());
		for (IColumnField field : Entity.fields(this.getClass())) {
			if (field.column().isPrimary())
				continue;
			try {
				Serializable value = (Serializable) field.column().getterMethod().invoke(this);
				q.values(field.value(value));
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				throw LOG.err(e, "setTo", "对象【{0}】赋值到数据库记录时出错!", getClass());
			}
		}
		Serializable key = q.executeUpdateReturnGeneratedKey(table.primaryKey().type());
		try {
			table.primaryKey().setterMethod().invoke(this, key);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw LOG.err(e, "setTo", "对象【{0}】赋值到数据库记录时出错!", getClass());
		}
		return this;
	}

	public Entity upd() {
		EntityQuery<?> q = update(this.getClass());
		try {
			IColumnField primaryField = null;
			for (IColumnField field : Entity.fields(this.getClass())) {
				if (field.column().isPrimary()) {
					primaryField = field;
				} else {
					Serializable value = (Serializable) field.column().getterMethod().invoke(this);
					q.set(field, value);
				}
			}
			q.where(primaryField, "=?", (Serializable) primaryField.column().getterMethod().invoke(this));
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw LOG.err(e, "getmetod", "取对象【{0}】字段时出错!", getClass());
		}
		q.execute();
		return this;
	}

	public void del() {
		Table<?> table = Entity.table(this.getClass());
		Column column = table.primaryKey();
		Serializable primaryKeyValue;
		try {
			primaryKeyValue = (Serializable) column.getterMethod().invoke(this);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw LOG.err(e, "setTo", "对象【{0}】赋值到数据库记录时出错!", getClass());
		}
		int row = delete(this.getClass()).where(column.field(), "=?", primaryKeyValue).execute();
		if (row == 0)
			throw LOG.err("deleteNotFound", "删除表【{0}】主键为【{1}】的记录不存在!", table.name(), primaryKeyValue);
	}

	@Test
	public void testCreateTableIfNotExists() {
		try {
			Table<?> table = this.table();
			table.drop(false);
			table.create();
			ConnectionManager.commitConnection();
			new EntitySrc(this).outSrc();
		} catch (IllegalArgumentException | SecurityException | SQLException e) {
			e.printStackTrace();
		}
	}

	public Table<?> table() {
		try {
			return (Table<?>) this.getClass().getField("table").get(null);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			logger.error("实体[{}]没有table属性", this.getClass().getName());
			return null;
		}
	}

	public static <T extends Entity> Table<?> table(Class<T> entityClass) {
		try {
			return (Table<?>) entityClass.getField("table").get(null);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			logger.error("实体[{}]没有table属性", entityClass.getName());
			return null;
		}
	}

	public static <T extends Entity> IColumnField[] fields(Class<T> entityClass) {
		try {
			IColumnField[] fields = (IColumnField[]) Class.forName(entityClass.getName() + "$T").getEnumConstants();
			return fields;
		} catch (IllegalArgumentException | SecurityException | ClassNotFoundException e) {
			logger.error("实体[{}]没有定义field枚举类", entityClass.getName());
			return null;
		}
	}
}
