package com.dmitrubondarev.stand.data;

import org.apache.log4j.Logger;

public class DataStateListener {

    private static volatile DataStateListener listener;
    private Boolean actualDataState = true;
    private Boolean initialDataLoaded = false;
    private static final Logger logger = Logger.getLogger(DataStateListener.class);


    private DataStateListener() {
    }

    public Boolean getDataState() {
        return actualDataState;
    }

    public Boolean isInitialDataLoaded() {
        return initialDataLoaded;
    }

    public void setInitialDataLoaded(Boolean loaded) {
        initialDataLoaded = loaded;
    }

    public void resetDataState() {
        actualDataState = true;
    }

    void dataIsNotActual() {
        logger.info("[START] dataIsNotActual()");
        actualDataState = false;
        logger.info("[END] dataIsNotActual()");
    }

    public static DataStateListener getInstance() {
        DataStateListener localInstance = listener;
        if (localInstance == null) {
            synchronized (DataStateListener.class) {
                localInstance = listener;
                if (localInstance == listener) {
                    localInstance = listener = new DataStateListener();
                }
            }
        }
        return localInstance;
    }
}
