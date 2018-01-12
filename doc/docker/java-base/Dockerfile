##基础镜像centos
FROM centos


##创建者信息
MAINTAINER qiuwei "vesalqiu_000@163.com"


##安装程序
#安装wget
RUN yum -y install wget

#安装网络工具如netstat
yum -y install net-tools

# 安装jdk，并配置环境变量
# 下载jdk8 官网不能直接使用wget 必须添加必要的http头设置。
WORKDIR /home/tools/zip
RUN wget --no-cookies --no-check-certificate --header "Cookie: gpw_e24=http%3A%2F%2Fwww.oracle.com%2F; oraclelicense=accept-securebackup-cookie" "http://download.oracle.com/otn-pub/java/jdk/8u141-b15/336fa29ff2bb4ef291e347e091f7f4a7/jdk-8u141-linux-x64.tar.gz"
RUN tar -zxvf jdk-8u141-linux-x64.tar.gz 
RUN mv /home/tools/zip/jdk1.8.0_141/ /home/tools/
# 设置JAVA_HOME环境变量
ENV JAVA_HOME /home/tools/jdk1.8.0_141/

# 安装git
RUN yum -y install git

# 安装maven
RUN wget http://mirrors.shuosc.org/apache/maven/maven-3/3.5.2/binaries/apache-maven-3.5.2-bin.tar.gz
RUN tar -zxvf apache-maven-3.5.2-bin.tar.gz
RUN mv /home/tools/zip/apache-maven-3.5.2 /home/tools/
# 设置maven环境变量
ENV MAVEN_HOME /home/tools/apache-maven-3.5.2

## 设置PATH
ENV PATH $PATH:$JAVA_HOME/bin:$MAVEN_HOME/bin