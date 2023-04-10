FROM openjdk:11
EXPOSE 9090
ADD target/advertising-app-images.jar advertising-app-images.jar
ENTRYPOINT ["java","-jar","advertising-app-images.jar"]