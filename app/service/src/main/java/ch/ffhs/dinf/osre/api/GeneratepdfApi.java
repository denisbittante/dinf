package ch.ffhs.dinf.osre.api;

import javax.servlet.ServletConfig;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import ch.ffhs.dinf.osre.api.factories.GeneratepdfApiServiceFactory;
import ch.ffhs.dinf.osre.api.model.Pdf;
import io.swagger.annotations.ApiParam;

@Path("/generatepdf")


@io.swagger.annotations.Api(description = "the generatepdf API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-11-04T08:37:53.279Z")
public class GeneratepdfApi  {
   private final GeneratepdfApiService delegate;

   public GeneratepdfApi(@Context ServletConfig servletContext) {
      GeneratepdfApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("GeneratepdfApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (GeneratepdfApiService) Class.forName(implClass).newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         } 
      }

      if (delegate == null) {
         delegate = GeneratepdfApiServiceFactory.getGeneratepdfApi();
      }

      this.delegate = delegate;
   }

    @POST
    
    @Consumes({ "application/json", "application/xml" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "generates PDF with a OpenSource Generator", notes = "", response = void.class, tags={ "pdf", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 405, message = "Invalid input", response = void.class) })
    public Response createsPdf(@ApiParam(value = "Data to create a PDF" ,required=true) Pdf body
,@Context SecurityContext securityContext)
    throws Exception {
        return delegate.createsPdf(body,securityContext);
    }
}
