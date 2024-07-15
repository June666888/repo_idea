package com.june.service.impl;

import com.june.dao.PromotionSpaceMapper;
import com.june.domain.PromotionSpace;
import com.june.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PromotionSpaceServiceImpl implements PromotionSpaceService {
    @Autowired
    private PromotionSpaceMapper promotionSpaceMapper;

    @Override
    public List<PromotionSpace> findAllPromotionSpace() {
        List<PromotionSpace> allPromotionSpace = promotionSpaceMapper.findAllPromotionSpace();

        return allPromotionSpace;
    }

    @Override
    public void savePromotionSpace(PromotionSpace promotionSpace) {
        //1.封装数据
        promotionSpace.setSpaceKey(UUID.randomUUID().toString());
        Date date = new Date();
        promotionSpace.setCreateTime(date);
        promotionSpace.setUpdateTime(date);
        promotionSpace.setIsDel(0);

        //2.调用dao
        promotionSpaceMapper.savePromotionSpace(promotionSpace);
    }

    @Override
    public PromotionSpace findPromotionSpaceById(int id) {
        PromotionSpace promotionSpaceById = promotionSpaceMapper.findPromotionSpaceById(id);
        return promotionSpaceById;
    }

    @Override
    public void updatePromotionSpace(PromotionSpace promotionSpace) {
        //1.封装数据
        Date date = new Date();
        promotionSpace.setUpdateTime(date);

        //2.调用dao
        promotionSpaceMapper.updatePromotionSpace(promotionSpace);
    }
}
