# Используем официальный образ WildFly 15 в качестве базового
FROM jboss/wildfly:15.0.1.Final

# Копируем конфигурацию для подключения к PostgreSQL в WildFly
COPY ./config/standalone15.xml /opt/jboss/wildfly/standalone/configuration/standalone.xml

ENV JAVA_OPTS="-XX:MaxMetaspaceSize=512m -XX:MetaspaceSize=256m -Dfile.encoding=UTF-8"

RUN mkdir -p /opt/jboss/wildfly/modules/org/postgresql/main
COPY ./lib/postgresql-42.7.4.jar /opt/jboss/wildfly/modules/org/postgresql/main/postgresql-42.7.4.jar
COPY ./config/postgresql-module.xml /opt/jboss/wildfly/modules/org/postgresql/main/module.xml

RUN /opt/jboss/wildfly/bin/add-user.sh -u tpupa -p admin -g admin

# Порты WildFly (по умолчанию 8080 для HTTP, 9990 для управления)
EXPOSE 8080 10100

# Запуск WildFly
CMD ["/opt/jboss/wildfly/bin/standalone.sh"]
