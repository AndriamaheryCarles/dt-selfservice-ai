FROM harbor-tdf.telma.net/dt-docker-base/java:eclipse-temurin-17-jre-alpine

ENV APPLICATION_NAME=llm
ENV APPLICATION_PATH=/target/appassembler

ENV STARTUP_SCRIPT=bin/${APPLICATION_NAME}.sh
ENV WORKDIR=/usr/app

COPY --chown=datadigital:datadigital ${APPLICATION_PATH}/bin ${WORKDIR}/bin
COPY --chown=datadigital:datadigital ${APPLICATION_PATH}/resources ${WORKDIR}/resources
COPY --chown=datadigital:datadigital ${APPLICATION_PATH}/libs ${WORKDIR}/libs

WORKDIR ${WORKDIR}

ENTRYPOINT ["sh", "-c", "${STARTUP_SCRIPT}"]