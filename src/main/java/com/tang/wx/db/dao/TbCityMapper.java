package com.tang.wx.db.dao;

import com.tang.wx.db.pojo.TbCity;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Administrator
* @description 针对表【tb_city(疫情城市列表)】的数据库操作Mapper
* @createDate 2023-10-28 16:17:21
* @Entity com.tang.wx.db.pojo.TbCity
*/
@Mapper
public interface TbCityMapper {

    int deleteByPrimaryKey(Long id);

    int insert(TbCity record);

    int insertSelective(TbCity record);

    TbCity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbCity record);

    int updateByPrimaryKey(TbCity record);

}
