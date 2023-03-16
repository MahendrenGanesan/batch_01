FROM gcr.io/dataflow-templates-base/java11-template-launcher-base

ARG WORKDIR=/dataflow/template
RUN mkdir -p ${WORKDIR}
WORKDIR ${WORKDIR}

COPY pom.xml .
ADD src /dataflow/template/src

ENV FLEX_TEMPLATE_JAVA_MAIN_CLASS="org.bnp.beam.ff.FlatFileTransform"
ENV FLEX_TEMPLATE_JAVA_CLASSPATH="${WORKDIR}/src/main/java/org/bnp/beam/ff/FlatFileTransform.java"

ENTRYPOINT ["/opt/google/dataflow/java_template_launcher"]