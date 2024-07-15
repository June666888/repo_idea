package com.june.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.june.dao.PromotionAdMapper;
import com.june.domain.PromotionAd;
import com.june.domain.PromotionAdVO;
import com.june.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PromotionAdServiceImpl implements PromotionAdService {
    @Autowired
    private PromotionAdMapper promotionAdMapper;

    @Override
    public PageInfo<PromotionAd> findAllAdByPage(PromotionAdVO promotionAdVO) {
        //查询数据库的方法之前，PageHelper的startPage方法
        PageHelper.startPage(promotionAdVO.getCurrentPage(),promotionAdVO.getPageSize());
        List<PromotionAd> adMapperAllAdByPage = promotionAdMapper.findAllAdByPage();

        PageInfo<PromotionAd> pageInfo = new PageInfo<>(adMapperAllAdByPage);

        //pageInfo封装了分页的参数信息
        return pageInfo;
    }

    @Override
    public void updatePromotionAdStatus(int id, int status) {
        //封装数据
        PromotionAd promotionAd = new PromotionAd();
        promotionAd.setId(id);
        promotionAd.setStatus(status);
        promotionAd.setUpdateTime(new Date());

        //调用dao
        promotionAdMapper.updatePromotionAdStatus(promotionAd);
    }

    @Override
    public void savePromotionAd(PromotionAd promotionAd) {
        //补全信息
        Date date=new Date();
        promotionAd.setCreateTime(date);
        promotionAd.setUpdateTime(date);

        //调用dao
        promotionAdMapper.savePromotionAd(promotionAd);
    }

    @Override
    public void updatePromotionAd(PromotionAd promotionAd) {
        //补全信息
        promotionAd.setUpdateTime(new Date());

        //调用dao
        promotionAdMapper.updatePromotionAd(promotionAd);
    }

    @Override
    public PromotionAd findPromotionAdById(int id) {
        PromotionAd promotionAd = promotionAdMapper.findPromotionAdById(id);

        return promotionAd;
    }
}
