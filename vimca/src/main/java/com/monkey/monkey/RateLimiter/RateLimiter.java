//package com.monkey.monkey.RateLimiter;
//
//import java.time.Duration;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.ConcurrentMap;
//
//import javax.annotation.PostConstruct;
//
//import org.springframework.stereotype.Component;
//
//import io.github.bucket4j.Bucket;
//import io.github.bucket4j.BucketConfiguration;
//
//@Component
//public class RateLimiter {
//
//    private final ConcurrentMap<String, Bucket> buckets = new ConcurrentHashMap<>();
//
//    @PostConstruct
//    public void initialize() {
//        // Setup rate limit configuration
//        BucketConfiguration configuration = BucketConfiguration.builder()
//            .addLimit(1, Duration.ofMinutes(1)) // 1 request per minute
//            .build();
//        buckets.put("api", Bucket.builder().configuration(configuration).build());
//    }
//
//    public boolean tryConsume(String key) {
//        Bucket bucket = buckets.get(key);
//        return bucket != null && bucket.tryConsume(1);
//    }
//}
