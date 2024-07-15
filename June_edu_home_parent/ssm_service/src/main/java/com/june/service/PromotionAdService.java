package com.june.service;

import com.github.pagehelper.PageInfo;
import com.june.domain.PromotionAd;
import com.june.domain.PromotionAdVO;

import java.util.List;

public interface PromotionAdService {
    /**
     * 广告分页查询
     * @param promotionAdVO：要接收参数，即controller传递过来的分页信息
     */
    public PageInfo<PromotionAd> findAllAdByPage(PromotionAdVO promotionAdVO);

    /**
     * 广告状态上下线
     */
    public void updatePromotionAdStatus(int id, int status);

    /**
     * 新建广告
     */
    public void savePromotionAd(PromotionAd promotionAd);

    /**
     * 修改广告
     */
    public void updatePromotionAd(PromotionAd promotionAd);

    /**
     * 回显广告信息
     * （根据id查询广告信息）
     */
    public PromotionAd findPromotionAdById(int id);
}
