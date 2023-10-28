package com.tang.wx.db.dao;

import com.tang.wx.db.pojo.TbDept;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Administrator
* @description 针对表【tb_dept】的数据库操作Mapper
* @createDate 2023-10-28 16:17:21
* @Entity com.tang.wx.db.pojo.TbDept
*/
@Mapper
public interface TbDeptMapper {

    int deleteByPrimaryKey(Long id);

    int insert(TbDept record);

    int insertSelective(TbDept record);

    TbDept selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbDept record);

    int updateByPrimaryKey(TbDept record);

}
