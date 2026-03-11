package com.restaurante.util;

import net.thucydides.model.util.EnvironmentVariables;
import net.thucydides.model.environment.SystemEnvironmentVariables;

public class Urls {

    private static final String BASE = SystemEnvironmentVariables
            .currentEnvironmentVariables()
            .getProperty("webdriver.base.url", "http://localhost:5173");

    public static final String MESA     = BASE + "/client/table";
    public static final String COCINA   = BASE + "/kitchen";
    public static final String ESTADO   = BASE + "/client/status";
}
