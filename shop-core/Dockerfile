FROM openjdk:8-jre-alpine
RUN mkdir /var/lib/shop
COPY /target/shop-core-1.0.0.jar /var/lib/shop
WORKDIR /var/lib/shop
EXPOSE 8081
CMD ["java", "-jar", "shop-core-1.0.0.jar"]