package com.qifei.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

import com.qifei.entity.Account;

@Mapper
public interface IAccountMapper {

	@Select("select * from Account where id=#{id}")
	Account findById(int id);
	
	@Select("select * from Account limit #{param1} , #{param2}")
	List<Account> find(int skip, int take);
	
	@Select("SELECT count(*) FROM account;")
	int getCount();
	
	@Insert("insert into Account (name, password, mobile, created, available, departmentid) values(#{name}, #{password}, #{mobile}, #{created}, #{available}, #{departmentid})")
	int insert(Account Account);
	
	@Update("update Account set name=#{name}, password=#{password}, mobile=#{mobile}, available=#{available}, departmentid=#{departmentid} where id=#{id}")
	int update(Account Account);
	
//	@Update("update Account set name=#{name}, password=#{password}, mobile=#{mobile}, available=#{available} where id=#{id}")
	@UpdateProvider(type = AccountSqlBuilder.class, method = "updateAvaliableByIds")
	int updateAvaliableByIds(String ids, boolean isAvaliable);
	
//	@Delete("delete from Account where id in (#{ids})") //this is not fucking work!!! when I pass ids like "7,8", it always only delete data whose id is 7
//	int delete(String ids);
	
	//so I am gonna try this way, fuck, //I tried, this way works, fuck!!
	@DeleteProvider(type = AccountSqlBuilder.class, method = "deleteByIds")
	int delete(String ids);
	
	class AccountSqlBuilder {
		//删除的方法
        public String deleteByIds(@Param("ids") final String ids){
            StringBuffer sql =new StringBuffer();
            sql.append("DELETE FROM Account WHERE id in ("+ ids + ");");
            return sql.toString();
        }
        
        //启用和禁用Account的方法
        //据说，如果是想传多个参数，就不能在@Param("xxx")，"xxx"为在上面接口里写的参数的名字，而非要写成arg0,arg1，WTF!!!
        public String updateAvaliableByIds(@Param("arg0") final String ids, @Param("arg1") boolean isAvaliable){
        	List<String> newIds = new ArrayList<String>(); 
			
        	if (ids != null && !ids.equals("")) {
            	String[] idArray = ids.split(",");
            	for(String id : idArray) {
            		newIds.add("\"" + id + "\"");
            	}
        	}
        	
            StringBuffer sql =new StringBuffer();
            sql.append("update Account set available="+ isAvaliable +" WHERE id in ("+ String.join(",", newIds) + ");");
            return sql.toString();
        }
    }
}
