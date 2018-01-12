##��������centos
FROM java-base:1.0.1


##��������Ϣ
MAINTAINER qiuwei "vesalqiu_000@163.com"


##��װ����

# git����Դ���ļ�
WORKDIR /home/src
RUN git clone https://github.com/pein000/teacher-system-cloud.git

# ���
WORKDIR /home/src/teacher-system-cloud/src/teacher-system-cloud/ts-eureka-center
RUN mvn clean package -DskipTests=true
RUN mkdir /home/bin
RUN mv target/ts-eureka-center.jar /home/bin

# ��־Ŀ¼
RUN mkdir /home/logs
RUN chmod -R 777 /home/logs

# ��¶�˿�
EXPOSE 8701

# ����Ӧ��
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
