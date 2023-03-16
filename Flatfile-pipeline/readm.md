mvn compile exec:java -D exec.mainClass=org.bnp.beam.ff.FlatFileTransform -D exec.args="--input=C:\Users\everm\gcp-pubsub\java-pubsub\mvn-gen\word-count-beam\src\test\java\org\apache\beam\examples\WordCountTest.java --output=counts" -P direct-runner


mvn compile exec:java -D exec.mainClass=org.apache.beam.examples.WordCount `
-D exec.args="--runner=DataflowRunner --project=compact-scene-375910`
--gcpTempLocation=gs://fund_accounting_gcp12_mahe/tmp `
--inputFile=gs://apache-beam-samples/shakespeare/* `
--output=gs://fund_accounting_gcp12_mahe/tmp/counts "`
-Pdataflow-runner


# Working Command
mvn compile exec:java -D exec.mainClass=org.bnp.beam.ff.FlatFileTransform -D exec.args="--runner=DataflowRunner --project=compact-scene-375910 --gcpTempLocation=gs://fund_accounting_gcp12_mahe/tmp --input=gs://apache-beam-samples/shakespeare/* --output=gs://fund_accounting_gcp12_mahe/tmp/counts_01 --region=us-central1 " -P dataflow-runner
mvn compile exec:java -D exec.mainClass=org.bnp.beam.ff.FlatFileTransform -D exec.args="--runner=DataflowRunner --project=compact-scene-375910 --gcpTempLocation=gs://fund_accounting_gcp12_mahe/tmp --input=gs://apache-beam-samples/shakespeare/* --output=gs://fund_accounting_gcp12_mahe/tmp/counts_02 --region=us-central1 " -P direct-runner



Flex Template Setup:
https://medium.com/google-cloud/ci-cd-for-dataflow-java-with-flex-templates-and-cloud-build-e3c584b8e564
https://github.com/tosun-si/dataflow-java-ci-cd

mvn compile exec:java \
-Dexec.mainClass=org.bnp.beam.ff.FlatFileTransform \
-Dexec.args=" \
--project=gb-poc-373711 \
--runner=DataflowRunner \
--jobName=file-copy-pjt-name-$(date +'%Y-%m-%d-%H-%M-%S') \
--region=europe-west1 \
--streaming=false \
--zone=europe-west1-d \
--tempLocation=gs://fund_accounting_gcp12_mahe/tmp \
--gcpTempLocation=gs://fund_accounting_gcp12_mahe/tmp \
--stagingLocation=gs://fund_accounting_gcp12_mahe/tmp \
--serviceAccount=1098257669558-compute@developer.gserviceaccount.com \
--input=gs://apache-beam-samples/shakespeare/* \
--output=gs://fund_accounting_gcp12_mahe/tmp/counts_02 \
--jobType=beam_prj_name_ingestion_job \
--buildNumber=1.01 \
" \
-Pdataflow-runner

mvn compile exec:java -D exec.mainClass=org.bnp.beam.ff.FlatFileTransform  -D exec.args="--project=compact-scene-375910 --runner=DataflowRunner --jobName=file-copy-pjt-name-2023-03-16-time --region=europe-west1 --streaming=false --zone=europe-west1-d --tempLocation=gs://fund_accounting_gcp12_mahe/tmp --gcpTempLocation=gs://fund_accounting_gcp12_mahe/tmp --stagingLocation=gs://fund_accounting_gcp12_mahe/tmp --serviceAccount=1098257669558-compute@developer.gserviceaccount.com --input=gs://apache-beam-samples/shakespeare/* --output=gs://fund_accounting_gcp12_mahe/tmp/counts_02 " -Pdataflow-runner