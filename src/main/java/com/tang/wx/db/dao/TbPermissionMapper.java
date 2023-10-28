package com.tang.wx.db.dao;

import com.tang.wx.db.pojo.TbPermission;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Administrator
* @description 针对表【tb_permission】的数据库操作Mapper
* @createDate 2023-10-28 16:17:22
* @Entity com.tang.wx.db.pojo.TbPermission
*/
@Mapper
public interface TbPermissionMapper {

    int deleteByPrimaryKey(Long id);

    int insert(TbPermission record);

    int insertSelective(TbPermission record);

    TbPermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbPermission record);

    int updateByPrimaryKey(TbPermission record);

}
