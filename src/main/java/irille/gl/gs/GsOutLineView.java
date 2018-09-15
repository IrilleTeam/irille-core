package irille.gl.gs;

import irille.pub.bean.BeanGoods;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.pub.tb.TbView;

import java.math.BigDecimal;

public class GsOutLineView extends BeanGoods<GsOutLineView> {
	public static final Tb TB = new TbView(GsOutLineView.class, "出库单明细", "出库单明细").setAutoLocal();

	public enum T implements IEnumFld {//@formatter:off
		PKEY(TB.crtLongPkey()),
		GOODS(GsGoods.fldOutKey()),
		QTY(SYS.QTY), //数量
		UOM(GsUom.fldOutKey()),
		BATCH_CODE(SYS.CODE__40, "存货批次"),
		LOCATION(GsLocation.fldOutKey()),
//		(FLDS.),
		//>>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
		//<<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
		;
		//>>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
		//<<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
		// 索引
		//public static final Index IDX_CODE = TB.addIndex("code", true,CODE);
		private Fld _fld;
		private T(Class clazz,String name,boolean... isnull) 
			{_fld=TB.addOutKey(clazz,this,name,isnull);	}
		private T(IEnumFld fld,boolean... isnull) { this(fld,null,isnull); } 
		private T(IEnumFld fld, String name,boolean... null1) {
			_fld=TB.add(fld,this,name,null1);}
		private T(IEnumFld fld, String name,int strLen) {
			_fld=TB.add(fld,this,name,strLen);}
		private T(Fld fld) {_fld=TB.add(fld, this); }
		public Fld getFld(){return _fld;}
	}		
	static { //在此可以加一些对FLD进行特殊设定的代码
		T.PKEY.getFld().getTb().lockAllFlds();//加锁所有字段,不可以修改
	}
	//@formatter:on

	//>>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Long _pkey;	// 编号  LONG
  private Integer _goods;	// 货物 <表主键:GsGoods>  INT
  private BigDecimal _qty;	// 数量  DEC(14,4)
  private Integer _uom;	// 计量单位 <表主键:GsUom>  INT
  private String _batchCode;	// 存货批次  STR(40)
  private Integer _location;	// 货位 <表主键:GsLocation>  INT

	@Override
  public GsOutLineView init(){
		super.init();
    _goods=null;	// 货物 <表主键:GsGoods>  INT
    _qty=ZERO;	// 数量  DEC(14,4)
    _uom=null;	// 计量单位 <表主键:GsUom>  INT
    _batchCode=null;	// 存货批次  STR(40)
    _location=null;	// 货位 <表主键:GsLocation>  INT
    return this;
  }

  //方法----------------------------------------------
  public Long getPkey(){
    return _pkey;
  }
  public void setPkey(Long pkey){
    _pkey=pkey;
  }
  public Integer getGoods(){
    return _goods;
  }
  public void setGoods(Integer goods){
    _goods=goods;
  }
  public GsGoods gtGoods(){
    if(getGoods()==null)
      return null;
    return (GsGoods)get(GsGoods.class,getGoods());
  }
  public void stGoods(GsGoods goods){
    if(goods==null)
      setGoods(null);
    else
      setGoods(goods.getPkey());
  }
  public BigDecimal getQty(){
    return _qty;
  }
  public void setQty(BigDecimal qty){
    _qty=qty;
  }
  public Integer getUom(){
    return _uom;
  }
  public void setUom(Integer uom){
    _uom=uom;
  }
  public GsUom gtUom(){
    if(getUom()==null)
      return null;
    return (GsUom)get(GsUom.class,getUom());
  }
  public void stUom(GsUom uom){
    if(uom==null)
      setUom(null);
    else
      setUom(uom.getPkey());
  }
  public String getBatchCode(){
    return _batchCode;
  }
  public void setBatchCode(String batchCode){
    _batchCode=batchCode;
  }
  public Integer getLocation(){
    return _location;
  }
  public void setLocation(Integer location){
    _location=location;
  }
  public GsLocation gtLocation(){
    if(getLocation()==null)
      return null;
    return (GsLocation)get(GsLocation.class,getLocation());
  }
  public void stLocation(GsLocation location){
    if(location==null)
      setLocation(null);
    else
      setLocation(location.getPkey());
  }

	//<<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
