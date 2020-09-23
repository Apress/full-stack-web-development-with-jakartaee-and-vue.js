PASSWORD=glassfish

java -version

echo "--- Setup the password file ---" && \
    echo "AS_ADMIN_PASSWORD=" > /tmp/glassfishpwd && \
    echo "AS_ADMIN_NEWPASSWORD=${PASSWORD}" >> /tmp/glassfishpwd  && \
    echo "--- Enable DAS, change admin password, and secure admin access ---" && \
    asadmin --user=admin --passwordfile=/tmp/glassfishpwd change-admin-password --domain_name domain1

echo "-------------------------------------"
echo "asadmin start-domain ${DOMAIN_NAME}"
asadmin start-domain ${DOMAIN_NAME}

sleep 20s

echo "AS_ADMIN_PASSWORD=${PASSWORD}" > /tmp/glassfishpwd && \
    asadmin --user=admin --passwordfile=/tmp/glassfishpwd enable-secure-admin

echo $(<glassfish-resources.xml)

echo "asadmin --user=admin --passwordfile=/tmp/glassfishpwd add-resources glassfish-resources.xml"
asadmin --user=admin --passwordfile=/tmp/glassfishpwd add-resources glassfish-resources.xml

echo "asadmin create-jvm-options -DENV=${ENV}"
asadmin --user=admin --passwordfile=/tmp/glassfishpwd create-jvm-options -DENV=${ENV}

echo "-------------------------------------"
echo "asadmin --user=admin --passwordfile=/tmp/glassfishpwd stop-domain ${DOMAIN_NAME}"
asadmin --user=admin --passwordfile=/tmp/glassfishpwd stop-domain ${DOMAIN_NAME}

sleep 20s

cp StartWithJEE-ear-1.0-SNAPSHOT.ear ${DEPLOYMENT_DIR}

echo "-------------------------------------"
echo "asadmin start-domain --verbose ${DOMAIN_NAME}"
asadmin start-domain --verbose ${DOMAIN_NAME}
