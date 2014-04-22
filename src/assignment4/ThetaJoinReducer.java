package assignment4;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;

import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public  class ThetaJoinReducer extends MapReduceBase implements Reducer<Text, Text, Text, Text> {

	@Override
	public void reduce(Text key, Iterator<Text> values,
			OutputCollector<Text, Text> output, Reporter arg3) throws IOException {
		
		int sumImpressions = 0;
		int sumClicks = 0;
		int sumConversions = 0;
		String []countLineArr = null;
		Text countLine  = null;
		while(values.hasNext()){
			countLine = values.next();
			String countLineStr = countLine.toString();
			countLineArr = countLineStr.split(";");
			
			sumImpressions+=Integer.parseInt(countLineArr[0]);
			sumClicks+=Integer.parseInt(countLineArr[1]);
			sumConversions+=Integer.parseInt(countLineArr[2]);
			
		}
		
		Text valueOut = new Text(key+":"+sumImpressions+";"+sumClicks+";"+sumConversions);
		output.collect(valueOut, new Text());
		
	}
	
}
