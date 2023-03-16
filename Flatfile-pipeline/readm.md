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


//01 UT
gcloud beta builds triggers create github \
--project=compact-scene-375910 \
--region=us-central1 \
--name="trigger-dataflow-unit-tests-team-beam-project" \
--repo-name=batch_01 \
--repo-owner=MahendrenGanesan \
--branch-pattern=".*" \
--build-config=dataflow-run-test.yaml \
--include-logs-with-status \
--verbosity="debug"

gcloud beta builds triggers delete  github --project=compact-scene-375910 --region=us-central1 --name="trigger-dataflow-unit-tests-team-beam-project" 
gcloud beta builds triggers create github --project=compact-scene-375910 --region=us-central1 --name="trigger-dataflow-unit-tests-team-beam-project" --repo-name=batch_01 --repo-owner=MahendrenGanesan --branch-pattern=".*" --build-config=Flatfile-pipeline/dataflow-run-test.yaml --include-logs-with-status --verbosity="debug"


gcloud beta builds triggers create manual \
--project=compact-scene-375910 \
--region=us-central1 \
--name="deploy-dataflow-template-beam-project-java" \
--repo="https://github.com/mahendrenganesan/batch_01" \
--repo-type="GITHUB" \
--branch="master" \
--build-config="Flatfile-pipeline/dataflow-deploy-job.yaml" \
--substitutions _REPO_NAME="internal-images",_IMAGE_NAME="dataflow/beam-project-java",_IMAGE_TAG="latest",_METADATA_TEMPLATE_FILE_PATH="gs://fund_accounting_gcp12_mahe/config/metadata.json",_SDK_LANGUAGE="JAVA",_FLEX_TEMPLATE_BASE_IMAGE="JAVA11",_METADATA_FILE="-",_JAR="target/beam-project-0.1.0.jar",_FLEX_TEMPLATE_JAVA_MAIN_CLASS="org.bnp.beam.ff.FlatFileTransform" \
--verbosity="debug"