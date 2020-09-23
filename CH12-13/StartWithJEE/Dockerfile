FROM airhacks/java-s2i

ENV GLASSFISH_ARCHIVE glassfish5
ENV DOMAIN_NAME domain1
ENV INSTALL_DIR /opt
RUN useradd -b /opt -m -s /bin/sh -d ${INSTALL_DIR} serveradmin && echo serveradmin:serveradmin | chpasswd
RUN curl -o ${INSTALL_DIR}/${GLASSFISH_ARCHIVE}.zip -L http://mirrors.xmission.com/eclipse/glassfish/glassfish-5.1.0.zip \ 
    && unzip ${INSTALL_DIR}/${GLASSFISH_ARCHIVE}.zip -d ${INSTALL_DIR} \ 
    && rm ${INSTALL_DIR}/${GLASSFISH_ARCHIVE}.zip \
    && chown -R serveradmin:serveradmin /opt \
    && chmod -R a+rw /opt
ENV GLASSFISH_HOME ${INSTALL_DIR}/${GLASSFISH_ARCHIVE}/glassfish
ENV DEPLOYMENT_DIR ${GLASSFISH_HOME}/domains/domain1/autodeploy
WORKDIR ${GLASSFISH_HOME}/bin 
USER serveradmin
EXPOSE 4848 8009 8080 8181

ARG ENV_ARG
ENV ENV=$ENV_ARG

RUN curl -o ${INSTALL_DIR}/mysql-connector-java-8.0.18.zip -L https://dev.mysql.com/get/Downloads/Connector-J/mysql-connector-java-8.0.18.zip \ 
    && unzip ${INSTALL_DIR}/mysql-connector-java-8.0.18.zip -d ${INSTALL_DIR} \ 
    && rm ${INSTALL_DIR}/mysql-connector-java-8.0.18.zip \ 
    && cp ${INSTALL_DIR}/mysql-connector-java-8.0.18/mysql-connector-java-8.0.18.jar ${GLASSFISH_HOME}/domains/${DOMAIN_NAME}/lib/mysql-connector-java-8.0.16.jar \ 
    && rm -R ${INSTALL_DIR}/mysql-connector-java-8.0.18

COPY docker .

RUN keytool -import -trustcacerts -alias awsidp -storepass changeit -noprompt -file ${GLASSFISH_HOME}/bin/cacerts/cognito-idp.us-east-1.amazonaws.com -keystore ${GLASSFISH_HOME}/domains/domain1/config/cacerts.jks \ 
    && keytool -import -trustcacerts -alias awsauth -storepass changeit -noprompt -file ${GLASSFISH_HOME}/bin/cacerts/_.auth.us-east-1.amazoncognito.com -keystore ${GLASSFISH_HOME}/domains/domain1/config/cacerts.jks \ 
    && keytool -import -trustcacerts -alias awss3g -storepass changeit -noprompt -file ${GLASSFISH_HOME}/bin/cacerts/_.s3.amazonaws.com -keystore ${GLASSFISH_HOME}/domains/domain1/config/cacerts.jks \ 
    && keytool -import -trustcacerts -alias awss3 -storepass changeit -noprompt -file ${GLASSFISH_HOME}/bin/cacerts/s3.amazonaws.com -keystore ${GLASSFISH_HOME}/domains/domain1/config/cacerts.jks
   
COPY StartWithJEE-ear/target/StartWithJEE-ear-1.0-SNAPSHOT.ear .

ENTRYPOINT sh docker-entrypoint.sh
