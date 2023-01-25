#!/bin/bash

JAVA_HOME=/home/andrii/.jdks/corretto-17.0.6
export JAVA_HOME

PATH=$JAVA_HOME/bin:$PATH
export PATH

./gradlew clean build -x test

AGENT_FILE=opentelemetry-javaagent.jar
if [ ! -f "${AGENT_FILE}" ]; then
  curl -L https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/download/v1.22.1/opentelemetry-javaagent.jar --output ${AGENT_FILE}
fi

export OTEL_TRACES_EXPORTER=otlp
export OTEL_METRICS_EXPORTER=otlp
export OTEL_EXPORTER_OTLP_ENDPOINT=http://localhost:5555
export OTEL_RESOURCE_ATTRIBUTES=service.name=MovieChart,service.version=0.0.1-SNAPSHOT

${JAVA_HOME}/bin/java -javaagent:./${AGENT_FILE} -Dfile.encoding=UTF-8 -jar build/libs/MovieChart-0.0.1-SNAPSHOT.jar
