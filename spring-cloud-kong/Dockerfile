############ Image ############
FROM openjdk:17-ea-3-jdk-slim

############ Definition Arguments Variable on Dockerfile ############
ARG BUILD_PATH="./build"
ARG ARTIFACT_PATH="/libs"
ARG APPLICATION_PATH="spring-cloud-kong"
ARG JAR_FILE="spring-cloud-kong-1.0.jar"

############ Definition Environment Variable on Container ############
ENV TZ=Asia/Hanoi
ENV APPLICATION_PATH=${APPLICATION_PATH}
ENV JAR_FILE=${JAR_FILE}

############ Make Directory ############
RUN mkdir -p --verbose ${APPLICATION_PATH}
RUN mkdir -p --verbose ${APPLICATION_PATH}/${SH_SCRIPT_PATH}

############ Copy Files ############
COPY ${BUILD_PATH}/${ARTIFACT_PATH}/${JAR_FILE} ${APPLICATION_PATH}/${JAR_FILE}
RUN chmod +rx ${APPLICATION_PATH}/${JAR_FILE}

############ Startup Command ############
WORKDIR ${APPLICATION_PATH}
CMD ["sh", "-c", "java -jar ${JAR_FILE}"]