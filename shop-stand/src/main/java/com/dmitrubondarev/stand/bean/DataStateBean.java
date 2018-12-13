package com.dmitrubondarev.stand.bean;

import com.dmitrubondarev.stand.data.DataLoader;
import com.dmitrubondarev.stand.data.DataStateListener;
import com.dmitrubondarev.stand.data.MQConsumer;
import com.dmitrubondarev.stand.model.Product;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

@Singleton
public class DataStateBean {

    private List<Product> products;
    private MQConsumer mqConsumer = new MQConsumer();
    private static final Logger logger = Logger.getLogger(DataStateBean.class);
    private DataStateListener listener = DataStateListener.getInstance();
    private DataLoader dataLoader = DataLoader.getInstance();

    public void update() {
        logger.info("[START] update()");
        if (!listener.isInitialDataLoaded()) {
            logger.info("[isInitialDataLoaded=false] update()");
            products = dataLoader.getProducts();
            listener.setInitialDataLoaded(true);
        }

        if (!listener.getDataState()) {
            logger.info("[getDataState=false] update()");
            products = dataLoader.getProducts();
            listener.resetDataState();
        }
        logger.info("[END] update()");
    }

    public List<Product> getProducts() {
        return this.products;
    }


//  =================== NON-API ===================

    @PostConstruct
    private void init() throws IOException, TimeoutException {
        logger.info("[START] init()");
        mqConsumer.start();
        update();
        logger.info("[END] init()");
    }

    @PreDestroy
    private void destroy() throws IOException, TimeoutException {
        mqConsumer.stop();
    }
}
