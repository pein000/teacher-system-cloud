##基础镜像centos
FROM java-base:1.0.1


##创建者信息
MAINTAINER qiuwei "vesalqiu_000@163.com"


##安装程序

# git下载源码文件
WORKDIR /home/src
RUN git clone https://github.com/pein000/teacher-system-cloud.git

# 打包
WORKDIR /home/src/teacher-system-cloud/src/teacher-system-cloud/ts-eureka-center
RUN mvn clean package -DskipTests=true
RUN mkdir /home/bin
RUN mv target/ts-eureka-center.jar /home/bin

# 日志目录
RUN mkdir /home/logs
RUN chmod -R 777 /home/logs

# 暴露端口
EXPOSE 8701

# 启动应用
WORKDIR /home/bin
ENTRYPOINT java -jar ts-eureka-center.jar \
--spring.profiles.active=$NODE   \
-server  \
--server.port=8701 \
-Djava.net.preferIPv4Stack=true \
-Djava.awt.headless=true  \
-Xms1g \
-Xmx1g \
-XX:NewRatio=1  \
-XX:SurvivorRatio=1 \
-XX:MetaspaceSize=256m \
-XX:MaxMetaspaceSize=256m \
-XX:ThreadStackSize=128k \
-XX:+UnlockExperimentalVMOptions \
-XX:+UseBiasedLocking \
-XX:+AggressiveOpts\
-XX:+UseConcMarkSweepGC \
-XX:+UseParNewGC \
-XX:+CMSClassUnloadingEnabled \
-XX:-UseTLAB \
-XX:-UseAdaptiveSizePolicy \
-XX:+ExplicitGCInvokesConcurrentAndUnloadsClasses \
-Xloggc:/home/log/dump/ts-eureka-center-gc.log \
-XX:+UseGCLogFileRotation \
-XX:NumberOfGCLogFiles=3 \
-XX:GCLogFileSize=128M \
-XX:+PrintGC \
-XX:+PrintGCDetails \
-XX:+PrintGCTimeStamps \
-XX:+PrintTenuringDistribution

