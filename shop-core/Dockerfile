FROM openjdk:8-jre-alpine
RUN mkdir /var/lib/shop
COPY /target/shop-core.jar /var/lib/shop
WORKDIR /var/lib/shop
EXPOSE 8081
CMD ["java", "-jar", "shop-core.jar"]