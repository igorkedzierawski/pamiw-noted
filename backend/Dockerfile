FROM gradle:8.2-jdk17-alpine

WORKDIR /app

COPY build.gradle .

COPY settings.gradle .

RUN gradle dependencies

RUN gradle --daemon

COPY src/ src/

ENTRYPOINT ["gradle", "bootRun"]

EXPOSE 8080
