package ru.luxoft.services;

import javax.ejb.Remote;

/**
 * Created by dprokofev on 17.07.2017.
 */

@Remote
public interface ITestJbossRemoteBean {
    String getName();
}
