package com.drl.service;

import com.drl.entity.Demo;

public interface DemoService {

    public Demo getDemoById(int demoId);

    boolean addDemor(Demo record);
}
