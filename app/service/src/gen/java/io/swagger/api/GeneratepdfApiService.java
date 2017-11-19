package io.swagger.api;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import io.swagger.model.Pdf;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-11-04T08:37:53.279Z")
public abstract class GeneratepdfApiService {
    public abstract Response createsPdf(Pdf body,SecurityContext securityContext) throws NotFoundException;
}
