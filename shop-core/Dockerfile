FROM openjdk:8-jre-alpine
RUN mkdir /var/lib/shop
COPY /target/core_shop.war /var/lib/shop
WORKDIR /var/lib/shop
EXPOSE 2142
CMD ["java", "-jar", "post-1.0.jar"]