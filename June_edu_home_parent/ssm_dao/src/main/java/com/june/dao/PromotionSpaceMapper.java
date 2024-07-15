package com.june.dao;

import com.june.domain.PromotionSpace;

import java.util.List;

public interface PromotionSpaceMapper {
    /**
     * 广告位列表查询
     */
    public List<PromotionSpace> findAllPromotionSpace();

    /**
     * 添加广告位
     * @param promotionSpace：在service层进行封装
     * 除了name字段，其他都需要在service层进行补充
     */
    public void savePromotionSpace(PromotionSpace promotionSpace);

    /**
     * 回显广告位名称
     * 根据id查询广告位信息
     */
    public PromotionSpace findPromotionSpaceById(int id);

    /**
     * 修改广告位
     * @param promotionSpace：在service层进行封装
     */
    public void updatePromotionSpace(PromotionSpace promotionSpace);
}
