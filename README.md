# teacher-system-cloud
## 前端

## 后端

### 一、集群支持服务

#### 1、注册中心

​	ts.eureka.center

##### 	注册中心的docker集群部署（3台）

​		1 ）docker基础镜像搭建 （dockerfile搭建） 

​			

```
docker  build -f java-base/DockerFile -t java-base:1.0.1 .
```

​		2） docker搭建注册中心镜像（dockerfile搭建） 

```
docker  build -f spring-cloud-center/DockerFile -t ts-eureka-center:1.0.1 .
```

​	        3）启动ts-eureka-center集群（nodeA、nodeB、nodeC）

​		 启动nodeA

```

docker run -d -p 8701:8701 -v /home/logs/ts-eureka-center-nodeA:/home/logs -e NODE=nodeA -e node_ip_1=192.168.99.100:8701 -e node_ip_2=192.168.99.100:8702 -e node_ip_3=192.168.99.100:8703 --name=ts-eureka-center-nodeA ts-eureka-center:1.0.1

```

​		启动nodeB

		
	docker run -d -p 8702:8701 -v /home/logs/ts-eureka-center-nodeB:/home/logs -e NODE=nodeB -e node_ip_1=192.168.99.100:8701 -e node_ip_2=192.168.99.100:8702 -e node_ip_3=192.168.99.100:8703 --name=ts-eureka-center-nodeB ts-eureka-center:1.0.1
		

​		启动nodeC

```

docker run -d -p 8703:8701 -v /home/logs/ts-eureka-center-nodeC:/home/logs -e NODE=nodeC -e node_ip_1=192.168.99.100:8701 -e node_ip_2=192.168.99.100:8702 -e node_ip_3=192.168.99.100:8703 --name=ts-eureka-center-nodeC ts-eureka-center:1.0.1

```



#### 2、配置中心

　　ts.eureka.config

#### 3、系统监控

　　ts.eureka.turbine

#### 4、网关服务

　　ts.eureka.zuul

### 二、基础服务

　　ts.eureka.program