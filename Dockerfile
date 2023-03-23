ENV PROD_ENV_FILE=openshift/config/prod.env
FROM openjdk:11
EXPOSE 8080
ADD target/advertising-app-images.jar advertising-app-images.jar
ENTRYPOINT ["java","-jar","advertising-app-images.jar"]