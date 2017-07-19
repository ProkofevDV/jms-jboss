package ru.luxoft.services;

import javax.ejb.Local;
import javax.ejb.Remote;

/**
 * Created by dprokofev on 17.07.2017.
 */

@Local
public interface ITestJbossLocalBean {
    String getName();
}
