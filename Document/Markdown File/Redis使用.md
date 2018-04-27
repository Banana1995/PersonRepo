# Redis使用

项目中的Redis采用集群部署，通过实现Spring 的`FactoryBean<JedisCluster>`来构造出`JedisCluster`实例，然后通过`JedisCluster`实例来实现Redis的CRUD操作。

在`FactoryBean`中通过如下代码来实例化`JedisCluster` :

```java
//获取节点的set集合
Set<HostAndPort> jedisClusterNode = this.parseHostAndPort();
//new出JedisCluster实例
jedisCluster = new JedisCluster(jedisClusterNode, connectionTimeout, soTimeout, maxAttempts, password, genericObjectPoolConfig);
  
```

在`JedisClusterClient`客户端中，使用`JedisCluster`来完成CRUD操作，我们在Spring中采用**设置注入**的方法完成客户端中`JedisCluster`的自动注入。如下所示：

```xml
<!-- 定义FactoryBean产生的实例，即jedisCluster实例 -->
<bean id="jedisCluster" class="com.hundsun.config.redis.JedisClusterFactoryBean">
        <!-- 配置集群地址 -->
        <!--<property name="addressConfig" value="classpath:properties/redis.properties"/> -->
        <!-- 配置地址的前缀 -->
        <property name="addressKeyPrefix" value="address"/>
        <!-- 连接超时时间 -->
        <property name="connectionTimeout" value="3000"/>
        <property name="soTimeout" value="3000"/>
        <!-- 最大尝试次数 -->
        <property name="maxAttempts" value="3"/>
        <!-- 密码 -->
        <property name="password" value="${redis.password}"/>
        <property name="genericObjectPoolConfig" ref="jedisConfig"/>
</bean>
<!-- 通过引用上面定义的FactoryBean产生的JedisCluster实例，设置注入到客户端中去 -->
<bean id="jedisClusterClient" class="com.hundsun.config.redis.JedisClusterClient" >
        <property name="jedisCluster" ref="jedisCluster" />
</bean>
```



