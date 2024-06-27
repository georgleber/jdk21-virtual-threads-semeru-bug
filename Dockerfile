FROM ibm-semeru-runtimes:open-21-jre
COPY build/libs/async-test.jar async-test.jar
ENTRYPOINT ["java","-jar","/async-test.jar"]
