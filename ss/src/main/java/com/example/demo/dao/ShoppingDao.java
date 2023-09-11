package com.example.demo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.dto.ShoppingBuyDto;
import com.example.demo.dto.ShoppingCartDto;
import com.example.demo.dto.ShoppingDto;

import jakarta.servlet.http.HttpSession;

@Mapper
public interface ShoppingDao {
    
	public boolean reserve_shopping_select(ShoppingDto shDto);

    @Insert("insert into shopping values(#{item},#{price},#{ea},#{sh_contents}")
//	@Insert("insert into a_hos_check values('예약일','예약자','예약병원','예약동물','진료내용')")
	public boolean shopping(ShoppingDto shDto);
    
    @Update("update shopping set OriFileName = #{oriFileName}, SysFileName = #{sysFileName}, filePath = #{filePath} where item = #{item}")
	public boolean fileInsertMap(Map<String, String> fMap);

	public boolean sh_join(ShoppingDto shDto);
	
	@Select("select count(*) from shopping where item = #{item}")
	public int sh_itemcheck(String item);

	public boolean sh_u_profile(ShoppingDto shDto);

	public ShoppingDto shoppingDataSelect(ShoppingDto shDto);

	@Select("select item,price,ea,sh_content,OriFileName as OriFileName,SysFileName as SysFileName,filePath from shopping")
	public List<ShoppingDto> findShoppingList();

    @Select("select * from shopping where item = #{item}")
	public ShoppingDto findShoppingBuyList(ShoppingDto shDto);
	
	@Select("select * from shopping where item = #{item}")
	public ShoppingDto getShoppingDetail(String item);

	@Insert("insert into shopping values(#{item},#{price},#{ea},#{sh_content},null,null,null)")
	public boolean writePro(ShoppingDto shDto);
	
	public void save(ShoppingDto shDto);

	public List<ShoppingDto> getShoppingList(String item);

	public String[] getSysNameFiles(String item);

	public boolean shoppingExist(String item);


		@Insert("insert into cart(id,item,ea) values(#{id},#{item},#{ea})")
		boolean addToCart(ShoppingCartDto scDto);
		   // 장바구니에 담긴 상품 목록 조회
	    @Select("select c.id, c.item, c.ea, s.sysFileName from cart c join shopping s on c.item = s.item where c.id = #{id}")
	    List<ShoppingDto> getCartItems(ShoppingCartDto scDto);
	    
	    // 장바구니에서 상품 제거
	    @Delete("delete from cart where id = #{id} and item = #{item}")
	    boolean removeFromCart(ShoppingCartDto scDto);
	    
	    @Select("select * from cart where id = #{id} and item = #{item}")
		public ShoppingCartDto cartSelect(ShoppingCartDto scDto);
	    
	    @Update("update cart set ea = ea + #{ea} where item = #{item}")
		public boolean addToCartUpdate(ShoppingCartDto scDto);

	    @Delete("delete from shopping where item=#{item}")
		public boolean ShoppingDelete(String item);

	    @Delete("delete from cart where id = #{id} and item = #{item}")
		public boolean CartDelete(ShoppingCartDto scDto);
    
	    @Insert("insert into buy(buynum,id,item,price,ea,buydate) values(#{buynum},#{id},#{item},#{price},#{ea},#{buydate})")
	    public boolean CartBuy(ShoppingBuyDto scbDto);
	    
	    @Select("select c.id,c.item,.c.ea,s.price s.sysFileName from cart c join shopping s on c.item = s.item where c.id = #{id}")
	    List<ShoppingBuyDto> getcartItems(ShoppingBuyDto scbDto);
	    
	    @Insert("insert into buy values(#{buynum},#{id},#{item},#{price},#{ea},sysdate)")
		public boolean detailbuyProduct(ShoppingBuyDto shbDto);

	    @Update("update shopping set ea = ea - #{ea} where item = #{item}")
		public boolean updateBuyProduct(String item, int ea);

	    @Select("Select * from buynum where id = #{id}")
		public ShoppingBuyDto SelectBuyNum(String id);

	    @Update("update buynum set buynum = #{count} where id = #{id}")
		public boolean UpdateBuyNum(String id, int count);

	    @Insert("insert into buynum values(#{count},#{id})")
		public boolean InsertBuyNum(String id, int count);

	    @Select("select ea from shopping where item = #{item}")
		public ShoppingDto SelectEa(ShoppingCartDto scDto);

	    @Delete("delete from cart where id = #{id} and item = #{item}")
		public boolean CartDelete2(ShoppingBuyDto sDto);

	    @Select("select * from buy where id = #{id} order by buynum desc")
		public List<ShoppingBuyDto> getBuyList(String id);

	    @Select("select * from shopping where item = #{item}")
		public ShoppingDto getShoppingLists(String item);

	    @Update("update shopping set price = #{price}, ea = #{ea},sh_content = #{sh_content} where item = #{item}")
		public boolean updateShopping(ShoppingDto shDto);


}