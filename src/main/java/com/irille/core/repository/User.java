package com.irille.core.repository;


import java.sql.SQLException;
import java.util.Map;

import com.irille.core.controller.JsonWriter;
import com.irille.core.repository.db.ConnectionManager;
import com.irille.core.repository.orm.Column;
import com.irille.core.repository.orm.ColumnBuilder;
import com.irille.core.repository.orm.ColumnFactory;
import com.irille.core.repository.orm.ColumnTemplate;
import com.irille.core.repository.orm.ColumnTypes;
import com.irille.core.repository.orm.Entity;
import com.irille.core.repository.orm.IColumnField;
import com.irille.core.repository.orm.IColumnTemplate;
import com.irille.core.repository.orm.Table;
import com.irille.core.repository.orm.TableFactory;

import irille.pub.tb.EnumLine;
import irille.pub.tb.IEnumOpt;

public class User extends Entity {
	
	public static void main(String[] args) {
		User.table.getClass();
//		Map<String, Object> map = SELECT(User.class).queryMap();
		Map<String, Object> map = SELECT(field.PKEY).SELECT(field.PRODUCT_NAME).FROM(User.class).queryMap();
		JsonWriter.toConsole(map);
//		User.SELECT(User.class).queryMap();
//		testLoad();
//		Integer pkey = testIns();
//		testLoad();
//		testUpd();
//		testLoad();
//		testDel(pkey);
//		testLoad();
		try {
			ConnectionManager.commitConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static Integer testIns() {
		User user = new User();
		user.setId(2);
		user.setNormalBean(1);
		user.setName("名字");
		user.stType(OptType.admin);
		user.setEnabled(true);
		user.setUsername("用户名");
		user.setPassword("密码");
		user.setBillAddr("zhangdandizhi");
		user.setEmail("a86291151@163.com");
		user.setProductName("{}");
		user.setIsValid(true);
		user.ins();
		return user.getPkey();
	}
	public static void testLoad() {
		JsonWriter.toConsole(User.SELECT(User.class).queryMap());
	}
	public static void testUpd() {
		User user = User.SELECT(User.class).query();
		user.setName("新名字");
		user.upd();
	}
	public static void testDel(Integer pkey) {
		SELECT(User.class, pkey).del();
	}

	public static final Table<User> table = TableFactory.entity(User.class).column(field.values()).index(true, field.ID).index(false, field.ID, field.NAME).create();

	public enum OptType implements IEnumOpt{
		admin(0, "管理员"),
		anonymous(1, "匿名用户"),
		normal(2, "普通用户")
		;
		private EnumLine line;
		private OptType(int key, String name) {line=new EnumLine(this,key,name);}
		@Override
		public EnumLine getLine() {
			return line;
		}
		
	}
	public enum field implements IColumnField {
		PKEY(ColumnTemplate.PKEY),
		ID(ColumnTemplate.INT__11),
		NORMAL_BEAN(ColumnFactory.oneToMany(NormalBean.class)),
		NAME(ColumnTemplate.STR__200.nullable(true).showName("名字").defaultValue("")),
		TYPE(ColumnFactory.opt(OptType.anonymous).showName("用户类型")),
		ENABLED(ColumnFactory.type(ColumnTypes.BOOLEAN)),
		USERNAME(ColumnTemplate.STR__200.showName("用户名")),
		PASSWORD(ColumnTemplate.STR__200.nullable(true).showName("密码")),
		BILL_ADDR(ColumnTemplate.STR__200.nullable(true)),
		EMAIL(ColumnTemplate.EMAIL),
		PRODUCT_NAME(ColumnTemplate.I18N),
		IS_VALID(ColumnTemplate.BOOLEAN.showName("是否合法"))
		;
		private Column column;

		field(IColumnTemplate template) {
			this.column = template.builder().create(this);
		}

		field(ColumnBuilder builder) {
			this.column = builder.create(this);
		}

		@Override
		public Column column() {
			return column;
		}
	}
	// >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>

	// 实例变量定义-----------------------------------------
	private Integer pkey; // pkey INT(11)
	private Integer id; // id INT(11)
	private Integer normalBean; // normalBean<表主键:NormalBean> INT(11)
	private String name; // 名字 VARCHAR(200)<null>
	private Byte type; // 用户类型<OptType> TINYINT(4)
	// admin:0,管理员
	// anonymous:1,匿名用户
	// normal:2,普通用户
	private Boolean enabled; // enabled TINYINT(1)
	private String username; // 用户名 VARCHAR(200)
	private String password; // 密码 VARCHAR(200)<null>
	private String billAddr; // billAddr VARCHAR(200)<null>
	private String email; // 邮箱地址 VARCHAR(100)<null>
	private String productName; // productName JSON(0)
	private Boolean isValid; // 是否合法 TINYINT(1)

	@Override
	public User init() {
		super.init();
		id = null; // id INT(11)
		normalBean = null; // normalBean INT(11)
		name = ""; // 名字 VARCHAR(200)
		type = OptType.anonymous.getLine().getKey(); // 用户类型<OptType> TINYINT(4)
		enabled = null; // enabled TINYINT(1)
		username = null; // 用户名 VARCHAR(200)
		password = null; // 密码 VARCHAR(200)
		billAddr = null; // billAddr VARCHAR(200)
		email = null; // 邮箱地址 VARCHAR(100)
		productName = null; // productName JSON(0)
		isValid = null; // 是否合法 TINYINT(1)
		return this;
	}

	// 方法------------------------------------------------
	public Integer getPkey() {
		return pkey;
	}
	public void setPkey(Integer pkey) {
		this.pkey = pkey;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNormalBean() {
		return normalBean;
	}
	public void setNormalBean(Integer normalBean) {
		this.normalBean = normalBean;
	}
	public NormalBean gtNormalBean() {
		return SELECT(NormalBean.class, getNormalBean());
	}
	public void stNormalBean(NormalBean normalBean) {
		this.normalBean = normalBean.getPkey();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Byte getType() {
		return type;
	}
	public void setType(Byte type) {
		this.type = type;
	}
	public OptType gtType() {
		return (OptType)(OptType.anonymous.getLine().get(type));
	}
	public void stType(OptType type) {
		this.type = type.getLine().getKey();
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getBillAddr() {
		return billAddr;
	}
	public void setBillAddr(String billAddr) {
		this.billAddr = billAddr;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Boolean getIsValid() {
		return isValid;
	}
	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	// <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
