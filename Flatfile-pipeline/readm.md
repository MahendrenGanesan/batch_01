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
