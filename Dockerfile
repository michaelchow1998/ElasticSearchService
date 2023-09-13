FROM openjdk:17
ADD target/ElasticSearchService-0.0.1-SNAPSHOT.jar ElasticSearchService-0.0.1-SNAPSHOT.jar
EXPOSE 8090
ENTRYPOINT ["java","-Dspring.profiles.active=dev","-jar","ElasticSearchService-0.0.1-SNAPSHOT.jar"]