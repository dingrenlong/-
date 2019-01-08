package com.drl.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.drl.entity.Demo;
import com.drl.mapper.DemoMapper;
import com.drl.service.DemoService;

@Service("demoService")
public class DemoServiceImpl implements DemoService{
	@Resource
	DemoMapper demoMapper;

	@Override
	public Demo getDemoById(int demoId) {
		return demoMapper.selectByPrimaryKey(demoId);
	}

	@Override
	public boolean addDemor(Demo record) {
		boolean result = false;
        try {
        	demoMapper.insertSelective(record);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
	}
	
	
}
