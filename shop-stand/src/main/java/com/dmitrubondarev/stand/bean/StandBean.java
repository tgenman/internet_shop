package com.dmitrubondarev.stand.bean;

import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

@ManagedBean
@ViewScoped
public class StandBean implements Serializable {

    @EJB
    DataStateBean dataStateBean;
    private static final Logger logger = Logger.getLogger(StandBean.class);


}
