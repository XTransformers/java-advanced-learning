
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

5. 开启 Spring Cache + Redis。
5.1 默认序列化方式：Java自带序列化、反序列化方式
wrk http://localhost:8081/user/list
Running 10s test @ http://localhost:8081/user/list
  2 threads and 10 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     2.49ms    0.97ms  18.75ms   93.02%
    Req/Sec     2.00k   204.70     2.98k    77.50%
  39840 requests in 10.01s, 8.21MB read
Requests/sec:   3978.35
Transfer/sec:    839.91KB

5.2 自定义序列化方式 JSON <- new GenericJackson2JsonRedisSerializer()
```bash
192.168.56.95:6379> get "userCache::list"
"[\"java.util.ArrayList\",[{\"@class\":\"com.xtransformers.entity.User\",\"id\":1,\"name\":\"KK\",\"age\":19},{\"@class\":\"com.xtransformers.entity.User\",\"id\":2,\"name\":\"CC\",\"age\":20},{\"@class\":\"com.xtransformers.entity.User\",\"id\":3,\"name\":\"MM\",\"age\":21}]]"
192.168.56.95:6379> get "userCache::2"
"{\"@class\":\"com.xtransformers.entity.User\",\"id\":2,\"name\":\"CC\",\"age\":20}"
```
自定义 Cache key - KeyGenerator。

