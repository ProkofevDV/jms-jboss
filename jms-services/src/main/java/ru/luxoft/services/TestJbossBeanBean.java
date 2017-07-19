package ru.luxoft.services;

import javax.ejb.Stateless;

/**
 * Created by dprokofev on 17.07.2017.
 */
@Stateless
public class TestJbossBeanBean implements ITestJbossLocalBean, ITestJbossRemoteBean {


    public TestJbossBeanBean() {
    }

    @Override
    public String getName() {
        return "this is maskau!";
    }
}
