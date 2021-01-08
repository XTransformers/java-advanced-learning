
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

3. 开启 MyBatis 的二级缓存，基于 Ehcache。
wrk http://localhost:8081/user/list
Running 10s test @ http://localhost:8081/user/list
  2 threads and 10 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency   846.00us  528.05us  19.11ms   91.33%
    Req/Sec     5.15k     1.13k   10.82k    84.50%
  102489 requests in 10.01s, 21.13MB read
Requests/sec:  10241.35
Transfer/sec:      2.11MB

4. 开启 Spring Cache + Ehcache。
引入 cache starter 后自动装配，启动项目后会在 java.io.tmpdir(C:\Users\hero0\AppData\Local\Temp) 路径下生成 user%0043ache.data 文件。
wrk http://localhost:8081/user/list
Running 10s test @ http://localhost:8081/user/list
  2 threads and 10 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     0.87ms  434.98us  16.66ms   87.78%
    Req/Sec     5.06k   665.30     8.91k    78.22%
  101778 requests in 10.10s, 20.98MB read
Requests/sec:  10076.34
Transfer/sec:      2.08MB
