FROM qlik/gradle

ADD . /morse
RUN cd /morse && gradle build
WORKDIR /morse/build/libs
ENTRYPOINT java -jar morse.jar

