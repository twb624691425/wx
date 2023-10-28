package com.tang.wx.db.dao;

import com.tang.wx.db.pojo.TbRole;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Administrator
* @description 针对表【tb_role(角色表)】的数据库操作Mapper
* @createDate 2023-10-28 16:17:22
* @Entity com.tang.wx.db.pojo.TbRole
*/
@Mapper
public interface TbRoleMapper {

    int deleteByPrimaryKey(Long id);

    int insert(TbRole record);

    int insertSelective(TbRole record);

    TbRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbRole record);

    int updateByPrimaryKey(TbRole record);

}
