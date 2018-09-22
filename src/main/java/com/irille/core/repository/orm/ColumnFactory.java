package com.irille.core.repository.orm;

import com.irille.core.repository.orm.columns.I18NColumnBuilder;
import com.irille.core.repository.orm.columns.NormalColumnBuilder;
import com.irille.core.repository.orm.columns.OneToManyColumnBuilder;
import com.irille.core.repository.orm.columns.OptColumnBuilder;

import irille.pub.tb.IEnumOpt;

public class ColumnFactory {

	public static NormalColumnBuilder type(ColumnTypes type) {
		return new NormalColumnBuilder(type);
	}
	
	public static <T extends Entity> OneToManyColumnBuilder<T> oneToMany(Class<T> entityClass) {
		return new OneToManyColumnBuilder<T>(entityClass);
	}
	
	public static <T extends Entity> OptColumnBuilder opt(IEnumOpt opt) {
		return new OptColumnBuilder(opt);
	}

	public static I18NColumnBuilder i18n() {
		return new I18NColumnBuilder();
	}
	
	public static ColumnBuilder fromBuilder(ColumnBuilder builder) {
		if(builder instanceof I18NColumnBuilder) {
			return new I18NColumnBuilder() {{
				type(ColumnTypes.JSONOBJECT);
				showName(builder.showName());
				columnName(builder.columnName());
				defaultValue(builder.defaultValue());
				unique(builder.unique());
				primary(builder.primary());
				autoIncrement(builder.autoIncrement());
				nullable(builder.nullable());
				length(builder.length());
				precision(builder.precision());
				scale(builder.scale());
			}};
		} else if(builder instanceof NormalColumnBuilder) {
			return new NormalColumnBuilder(builder.type()) {{
				showName(builder.showName());
				columnName(builder.columnName());
				defaultValue(builder.defaultValue());
				unique(builder.unique());
				primary(builder.primary());
				autoIncrement(builder.autoIncrement());
				nullable(builder.nullable());
				length(builder.length());
				precision(builder.precision());
				scale(builder.scale());
			}};
		} else if(builder instanceof OneToManyColumnBuilder) {
			return new OneToManyColumnBuilder(((OneToManyColumnBuilder)builder).targetEntity()) {{
				showName(builder.showName());
				columnName(builder.columnName());
				defaultValue(builder.defaultValue());
				unique(builder.unique());
				primary(builder.primary());
				autoIncrement(builder.autoIncrement());
				nullable(builder.nullable());
				length(builder.length());
				precision(builder.precision());
				scale(builder.scale());
			}};
		} else if(builder instanceof OptColumnBuilder) {
			return new OptColumnBuilder(((OptColumnBuilder)builder).opt()) {{
				showName(builder.showName());
				columnName(builder.columnName());
				defaultValue(builder.defaultValue());
				unique(builder.unique());
				primary(builder.primary());
				autoIncrement(builder.autoIncrement());
				nullable(builder.nullable());
				length(builder.length());
				precision(builder.precision());
				scale(builder.scale());
			}};
		}
		return null;
    }
}
