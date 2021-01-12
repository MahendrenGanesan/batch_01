package org.bnp.beam.ff;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.Default;
import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.DoFn.ProcessContext;
import org.apache.beam.sdk.transforms.DoFn.ProcessElement;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.vendor.guava.v26_0_jre.com.google.common.collect.Lists;
//import org.bnp.beam.constants.constants;

import com.google.api.gax.paging.Page;
import com.google.api.services.bigquery.BigqueryScopes;
//import com.google.api.services.dataflow.DataflowScopes;
import com.google.api.services.pubsub.PubsubScopes;
import com.google.api.services.storage.StorageScopes;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

public class FlatFileTransform {

	public static ClassLoader classLoader = FlatFileTransform.class.getClassLoader();
	
	public static class TestClass
	{
		String name;
		public void testMe()
		{
			System.out.println("AbC");
		}
	}

	public static void main(String[] args) {

		
		
		
		/*
		 * GoogleCredentials credentials=null;
		 * 
		 * try { credentials = GoogleCredentials.fromStream(new FileInputStream(
		 * "/data/home/mahendren/workspace-spring/Flatfile-pipeline/src/main/resources/googleCredentials.json"
		 * )) .createScoped(Lists.newArrayList(
		 * "https://www.googleapis.com/auth/cloud-platform")); } catch (IOException e1)
		 * { // TODO Auto-generated catch block e1.printStackTrace(); } Storage storage
		 * =
		 * StorageOptions.newBuilder().setCredentials(credentials).build().getService();
		 * 
		 * System.out.println("Buckets:"); Page<Bucket> buckets = storage.list(); for
		 * (Bucket bucket : buckets.iterateAll()) {
		 * System.out.println(bucket.toString()); }
		 */
		Credentials credential = null;
		List<String> SCOPES = new ArrayList<>();
		SCOPES.addAll(StorageScopes.all());
		SCOPES.addAll(BigqueryScopes.all());
		//SCOPES.addAll(DataflowScopes.all());
		//System.out.println(constants.KEYFILE);
		InputStream in = classLoader.getResourceAsStream("googleCredentials.json");
		try {
			credential = GoogleCredentials.fromStream(in).createScoped(SCOPES);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		PipelineOptionsFactory.register(FFpipelineOptions.class);
		FFpipelineOptions options = PipelineOptionsFactory.fromArgs(args).withValidation().as(FFpipelineOptions.class);

		Pipeline p = Pipeline.create(options);
		System.out.println("Staring....");
		String inputFile = options.getInput();
		String outputFile = options.getOutput();
		System.out.println("inputFile=" + inputFile);
		System.out.println("outputFile=" + outputFile);
		p.apply("init", TextIO.read().from(inputFile)).apply(new BlankLineFilter()) // Filter
				// .apply("trans")//Transform
		.apply("writeToSink", TextIO.write().to(outputFile)); // write to sink;
		p.run().waitUntilFinish();
		System.out.println("finished.");
 
	}

	public static class BlankLineFilter extends PTransform<PCollection<String>, PCollection<String>> {
		@Override
		public PCollection<String> expand(PCollection<String> inputRawMessage) {

			System.out.println("Filter Applying...");
			PCollection<String> retCollection = inputRawMessage.apply(ParDo.of(new DoFnRawMessageFilter()));
			System.out.println("Filter Applied...");
			return retCollection;

		}

	}

	public static class DoFnRawMessageFilter extends DoFn<String, String> {
		@ProcessElement
		public void ProcessElement(ProcessContext pc) {
			String rawmessage = pc.element().trim();
			if (rawmessage.length() > 1)
				pc.output(rawmessage);
		}
	}

	public interface FFpipelineOptions extends PipelineOptions {
		@Description("Input for the additional values")
		@Default.String("var:val;var2:val2")
		public String getOptionalValues();

		public void setOptionalValues(String optionalValues);

		@Description("Input for the pipeline")
		@Default.String("gs://portfolio_accounting_staging/in/Indices_value_02SEP2020.csv")
		String getInput();

		void setInput(String input);

		@Description("Output for the pipeline")
		@Default.String("gs://portfolio_accounting_staging/out")
		String getOutput();

		void setOutput(String output);

	}

}
