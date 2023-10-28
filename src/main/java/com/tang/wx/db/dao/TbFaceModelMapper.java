package com.tang.wx.db.dao;

import com.tang.wx.db.pojo.TbFaceModel;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Administrator
* @description 针对表【tb_face_model】的数据库操作Mapper
* @createDate 2023-10-28 16:17:21
* @Entity com.tang.wx.db.pojo.TbFaceModel
*/
@Mapper
public interface TbFaceModelMapper {

    int deleteByPrimaryKey(Long id);

    int insert(TbFaceModel record);

    int insertSelective(TbFaceModel record);

    TbFaceModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbFaceModel record);

    int updateByPrimaryKey(TbFaceModel record);

}
