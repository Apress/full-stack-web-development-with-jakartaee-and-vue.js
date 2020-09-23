package com.daniel.delivery.main.restful.files;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import static org.assertj.core.api.Assertions.assertThat;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.extension.rest.client.ArquillianResteasyResource;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataOutput;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class FileControllerIT {

    private static String uuid;
    private String testFile = "testFile.txt";

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        Path persistence = Paths.get("../StartWithJEE-ejb/src/test/resources/META-INF/persistence.xml");
        Path deliveryData = Paths.get("../StartWithJEE-ejb/src/test/resources/META-INF/foodproductdata.sql");
        Path applicationItProperties = Paths.get("../StartWithJEE-ejb/src/main/resources/application-it.properties");

        System.setProperty("ENV", "it");
        
        return ShrinkWrap.create(WebArchive.class)
                .addPackages(true, "com.daniel.delivery")
                .addAsResource(applicationItProperties.toFile(), "application-it.properties")
                .addAsResource(persistence.toFile(), "META-INF/persistence.xml")
                .addAsResource(deliveryData.toFile(), "META-INF/data.sql");
    }

    @Test
    @RunAsClient
    @InSequence(1)
    public void uploadFile(@ArquillianResteasyResource("api") WebTarget webTarget) throws IOException {

        MultipartFormDataOutput multiPart = new MultipartFormDataOutput();
        multiPart.addFormData("file", Files.newInputStream(Paths.get("src/test/resources/", testFile)), MediaType.MULTIPART_FORM_DATA_TYPE, testFile);

        Response response = webTarget
                .path("files")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(multiPart, MediaType.MULTIPART_FORM_DATA));

        ImageDTO imageDTO = response.readEntity(ImageDTO.class);

        uuid = imageDTO.getImageUrl().replace("-" + testFile, "")
                .replace(webTarget.path("files").getUri().toString() + "/", "");

        assertThat(imageDTO.getImageUrl()).endsWith(testFile);
        assertThat(imageDTO.getImageUrl()).startsWith(webTarget
                .path("files").getUri().toString());
        assertThat(UUID.fromString(uuid)).isNotNull();
    }

    @Test
    @RunAsClient
    @InSequence(2)
    public void download(@ArquillianResteasyResource("api") WebTarget webTarget) throws IOException {
        Response response = webTarget
                .path("files")
                .path(uuid + "-" + testFile)
                .request(MediaType.TEXT_PLAIN)
                .get();

        try (InputStream inputStream = response.readEntity(InputStream.class)) {

            Files.copy(inputStream, Paths.get("target", testFile + "it"), StandardCopyOption.REPLACE_EXISTING);

            assertThat(Files.exists(Paths.get("target/", testFile + "it"))).isTrue();
            assertThat(Files.readAllLines(Paths.get("target/", testFile + "it"))).isEqualTo(Files.readAllLines(Paths.get("target/", uuid + "-" + testFile)));
        }
    }

}
