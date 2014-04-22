package assignment4;
//package com.yahoo.count;

import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;


public class WordCountMapper extends MapReduceBase 
	implements 
	Mapper<LongWritable, Text, Text, Text> {
	private Text counts = new Text();
	private Text campaign_id = new Text();
	
	public void map(LongWritable key, Text value,
			OutputCollector<Text, Text> output, Reporter reporter) throws IOException {
		String line = value.toString();
		String[] tokens = line.split(",");
		
		campaign_id.set(tokens[5]);
		counts.set(tokens[2]+";"+tokens[3]+";"+tokens[4]);
		output.collect(campaign_id, counts);
	}

}
