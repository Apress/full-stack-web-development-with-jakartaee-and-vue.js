package com.daniel.delivery.main;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

@Stateless
@LocalBean
public class GeneralProvider {
    
    @Produces
    public Properties getProperties() throws IOException {
        Properties properties = new Properties();
        
        InputStream inputStream = GeneralProvider.class.getResourceAsStream(Optional.ofNullable(System.getProperty("ENV"))
                .map(env -> "/application-" + env + ".properties")
                .orElse("/application.properties"));
        
        properties.load(inputStream);
        
        return properties;
    }
    
    @Produces
    public Client getClient(){
        return ClientBuilder.newClient();
    }
    
    @Produces
    public AmazonS3 getS3Client(Properties properties){
        return AmazonS3ClientBuilder.standard()
                    .withRegion(properties.getProperty("IMAGES_S3_REGION"))
                    .build();
    }
    
    @Produces
    public AWSCognitoIdentityProvider getCognitoClient(Properties properties){
        return AWSCognitoIdentityProviderClientBuilder
                .standard()
                .withRegion(properties.getProperty("COGNITO_REGION"))
                .build();
    }
}
