
#### cache 缓存

1. 整合 Spring Boot。
2. 基于 Spring Boot + MyBatis 实现简单的查询接口。
使用 WSL 的 wrk 压测接口性能。
wrk http://localhost:8081/user/list
Running 10s test @ http://localhost:8081/user/list
  2 threads and 10 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     1.59ms  576.51us  13.92ms   83.99%
    Req/Sec     2.83k   286.48     4.22k    85.00%
  56379 requests in 10.02s, 11.62MB read
Requests/sec:   5626.81
Transfer/sec:      1.16MB

