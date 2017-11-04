package io.swagger.api.factories;

import io.swagger.api.GeneratepdfApiService;
import io.swagger.api.impl.GeneratepdfApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-11-04T08:37:53.279Z")
public class GeneratepdfApiServiceFactory {
    private final static GeneratepdfApiService service = new GeneratepdfApiServiceImpl();

    public static GeneratepdfApiService getGeneratepdfApi() {
        return service;
    }
}
