package com.lin.common.jmh;

import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@BenchmarkMode(Mode.AverageTime) // 测试方法平均执行时间
//@OutputTimeUnit(TimeUnit.MICROSECONDS) // 输出结果的时间粒度为微秒
@State(Scope.Thread) // 每个测试线程一个实例
public class FirstBenchMark {
    private static Logger log = LoggerFactory.getLogger(FirstBenchMark.class);
//    @Benchmark
//    public String stringConcat() {
//        String a = "a";
//        String b = "b";
//        String c = "c";
//        String s = a + b + c;
//        log.debug(s);
//        return s;
//    }

    @Benchmark
    public void stringConcat() {
    	contains("DDDD", "bb");
    }
    
    @Benchmark
    public void stringMessageFormat() {
    	containsFormat("DDDD", "bb");
    }

   
    public  boolean contains(String status,String str){
		if(("|"+status+"|").contains(("|"+str+"|"))){
			return true;
		}
		return false;
	}
    
    private String pattern="|{0}|";
    
    public  boolean containsFormat(String status,String str){
    	
		if(MessageFormat.format(pattern, status).contains(MessageFormat.format(pattern, str))){
			return true;
		}
		return false;
	}
    
    
    public static void main(String[] args) throws RunnerException {
        // 使用一个单独进程执行测试，执行5遍warmup，然后执行5遍测试
        Options opt = new OptionsBuilder().include(FirstBenchMark.class.getSimpleName()).forks(1).mode(Mode.Throughput).warmupIterations(5)
                .measurementIterations(10).build();
        new Runner(opt).run();
    }
}
