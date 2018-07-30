package es.profile.phone.config;

import java.io.FileReader;
import java.io.IOException;

import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.commons.lang3.StringUtils;
import org.apache.maven.model.Model;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * Configuración para Swagger2.
 *
 * @author Jose A. Braojos <jabraojos@profile.es>
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	/** The log. */
	private final Logger log = LoggerFactory.getLogger(SwaggerConfig.class);
	
	/**
	 * Product api.
	 *
	 * @return the docket
	 */
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("es.profile.phone.web.controller"))
				//PathSelectors.any()
				//PathSelectors.regex("/api/user.*")
				.paths(PathSelectors.any())
				.build()
				.apiInfo(generateApiInfo());

	}

	/* --- Privates ---*/
	/**
	 * Generate api info.
	 *
	 * @return the api info
	 */
	private ApiInfo generateApiInfo() {
		MavenXpp3Reader reader = new MavenXpp3Reader();
		Model model = null;
        try {
			model = reader.read(new FileReader("pom.xml"));
		} catch (IOException | XmlPullParserException e) {
			log.error("Error al leer el fichero pom.xml", e);
		}
        return new ApiInfoBuilder()
                .title(model != null ? model.getArtifactId() : StringUtils.EMPTY)
                .description("\"Proyecto base de API Rest con Spring Boot\"")
                .version(model != null ? model.getVersion() : StringUtils.EMPTY)
                .license("Profile Software Service")
                .licenseUrl("https://profile.es")
                .contact(new Contact("Profile Software Service", "https://profile.es", "info@profile.es"))
                .build();
    }
}
