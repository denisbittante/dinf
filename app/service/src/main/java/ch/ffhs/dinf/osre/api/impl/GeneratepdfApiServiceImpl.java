package ch.ffhs.dinf.osre.api.impl;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import ch.ffhs.dinf.osre.api.GeneratepdfApiService;
import ch.ffhs.dinf.osre.api.model.Pdf;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-11-04T08:37:53.279Z")
public class GeneratepdfApiServiceImpl extends GeneratepdfApiService {
    @Override
    public Response createsPdf(Pdf body, SecurityContext securityContext) throws Exception {
        // do some magic!
        return Response.ok().entity("magic!").build();
    }
}
