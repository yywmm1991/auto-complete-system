package org.sample;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.db.DBConfiguration;
import org.apache.hadoop.mapreduce.lib.db.DBOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.sample.LanguageModel.Map;
import org.sample.LanguageModel.Reduce;
import org.sample.NGramLibraryBuilder.NGramMapper;
import org.sample.NGramLibraryBuilder.NGramReducer;

public class Driver {

	public static void main (String[] args) throws ClassNotFoundException, IOException, InterruptedException {
		Configuration conf1 = new Configuration();
		conf1.set("noGram", args[3]);

		// First Job
		Job job1 = Job.getInstance(conf1);
		job1.setJobName("NGram");
		job1.setJarByClass(Driver.class);

		job1.setMapperClass(org.sample.NGramLibraryBuilder.NGramMapper.class);
		job1.setReducerClass(org.sample.NGramLibraryBuilder.NGramReducer.class);

		job1.setOutputKeyClass(Text.class);
		job1.setOutputValueClass(IntWritable.class);

		job1.setInputFormatClass(TextInputFormat.class);
		job1.setOutputFormatClass(TextOutputFormat.class);

		TextInputFormat.setInputPaths(job1, new Path(args[0]));
		TextOutputFormat.setOutputPath(job1, new Path(args[1]));
		job1.waitForCompletion(true);

		// Second Job
		Configuration conf2 = new Configuration();
		conf2.set("threshold", args[4]);
		conf2.set("n", args[5]);
		
		Job job2 = Job.getInstance(conf2);
		job2.setJobName("LanguageModel");
		job2.setJarByClass(Driver.class);
		
		job2.setMapOutputKeyClass(Text.class);
		job2.setMapOutputValueClass(Text.class);
		job2.setOutputKeyClass(Text.class);
		job2.setOutputValueClass(NullWritable.class);

		job2.setMapperClass(org.sample.LanguageModel.Map.class);
		job2.setReducerClass(org.sample.LanguageModel.Reduce.class);

		job2.setInputFormatClass(TextInputFormat.class);
		job2.setOutputFormatClass(TextOutputFormat.class);

		TextInputFormat.setInputPaths(job2, new Path(args[1]));
		TextOutputFormat.setOutputPath(job2, new Path(args[2]));
		System.exit(job2.waitForCompletion(true) ? 0 : 1);

	}

}
