package com.dmitrubondarev.stand.bean;

import com.dmitrubondarev.stand.model.Product;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@ViewScoped
public class StandBean implements Serializable {

    @EJB
    DataStateBean dataStateBean;
    private static final Logger logger = Logger.getLogger(StandBean.class);

    public void update() {
        logger.info("[START] update()");
        dataStateBean.update();
        logger.info("[END] update()");
    }

    public List<Product> getProducts() {
        logger.info("[START] getTrucks()");
        return dataStateBean.getProducts();
    }




}
