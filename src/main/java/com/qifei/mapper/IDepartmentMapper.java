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

import com.qifei.entity.Department;

@Mapper
public interface IDepartmentMapper {
	@Select("select * from Department where id=#{id}")
	Department findById(String id);
	
	@Select("select * from Department limit #{param1} , #{param2}")
	List<Department> find(int skip, int take);
	
	@Select("select * from Department where parentid=#{param1} limit #{param2} , #{param3}")
	List<Department> findByParentId(String parentId, int skip, int take);
	
	@Select("SELECT count(*) FROM Department;")
	int getCount();
	
	@Insert("insert into Department (id, name, created, available, parentid, idpath, namepath) values(#{id}, #{name}, #{created}, #{available}, #{parentid}, #{idpath}, #{namepath})")
	int insert(Department Department);
	
	@Update("update Department set name=#{name}, available=#{available}, parentid=#{parentid}, idpath=#{idpath}, namepath=#{namepath} where id=#{id}")
	int update(Department Department);
	
//	@Update("update Department set name=#{name}, password=#{password}, mobile=#{mobile}, available=#{available} where id=#{id}")
	@UpdateProvider(type = DepartmentSqlBuilder.class, method = "updateAvaliableByIds")
	int updateAvaliableByIds(String ids, boolean isAvaliable);
	
//	@Delete("delete from Department where id in (#{ids})") //this is not fucking work!!! when I pass ids like "7,8", it always only delete data whose id is 7
//	int delete(String ids);
	
	//so I am gonna try this way, fuck, //I tried, this way works, fuck!!
	@DeleteProvider(type = DepartmentSqlBuilder.class, method = "deleteByIds")
	int delete(String ids);
	
	class DepartmentSqlBuilder {
		//删除的方法
        public String deleteByIds(@Param("ids") final String ids){
            StringBuffer sql =new StringBuffer();
            sql.append("DELETE FROM Department WHERE id in (\""+ ids + "\");");
            return sql.toString();
        }
        
        //启用和禁用Department的方法
        //据说，如果是想传多个参数，就不能在@Param("xxx")，"xxx"为在上面接口里写的参数的名字，而非要写成arg0,arg1，WTF!!!
        public String updateAvaliableByIds(@Param("arg0") final String ids, @Param("arg1") boolean isAvaliable){
        	List<String> newIds = new ArrayList<String>(); 
        			
        	if (ids != null && !ids.equals("")) {
	        	String[] idArray = ids.split(",");
	        	for(int i = 0; i < idArray.length; i++) {
	        		newIds.add("\"" + idArray[i] + "\"");
	        	}
        	}
            StringBuffer sql =new StringBuffer();
            sql.append("update Department set available="+ isAvaliable +" WHERE id in ("+ String.join(",", newIds) + ");");
            return sql.toString();
        }
    }

	
}
