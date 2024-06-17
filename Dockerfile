FROM maven:3-amazoncorretto-17 AS builder
RUN mkdir -p /app/logs && \
    chown -R 1000:1000 /app/logs

COPY ./pom.xml ./pom.xml
COPY src ./src/
COPY src/main/resources/application.properties .
RUN mvn -Dmaven.test.skip=true clean package && cd target && java -Djarmode=layertools -jar interactions-backend.jar extract

FROM amazoncorretto:17-alpine AS corretto-jdk
RUN apk add --no-cache binutils

RUN jlink \
         --add-modules ALL-MODULE-PATH \
         --strip-debug \
         --no-man-pages \
         --no-header-files \
         --compress=2 \
         --output /jre

FROM alpine:3.19.0

RUN apk --no-cache add curl

ENV JAVA_HOME=/jre
ENV PATH="${JAVA_HOME}/bin:${PATH}"

COPY --from=corretto-jdk /jre $JAVA_HOME

LABEL PROJECT_NAME=interactions-backend \
      PROJECT=interactions:interactions-backend

HEALTHCHECK --interval=30s --timeout=10s --start-period=120s --retries=5 CMD curl -f HEALTHCHECK --interval=30s --timeout=10s --start-period=120s --retries=5 CMD curl -f http://localhost:8080/interactions-backend/actuator/health || exit 1 || exit 1

WORKDIR /app

COPY --from=builder target/dependencies/ ./
COPY --from=builder target/spring-boot-loader/ ./
COPY --from=builder target/snapshot-dependencies/ ./
COPY --from=builder target/application/ ./

ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]