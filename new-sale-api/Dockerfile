FROM tomcat:8.0
USER root
WORKDIR /usr/local/tomcat/webapps/
RUN \
apt-get update && \
apt-get install unzip wget -y && \
rm -rf /var/lib/apt/lists/*
RUN wget -O /usr/local/tomcat/webapps/github-actions-artifact.zip https://pipelines.actions.githubusercontent.com/e2ZPK4xf89mBqgIY7gydLapLD6ax8DMwT5BrJu1L15da7GYu1O/_apis/pipelines/1/runs/76/signedartifactscontent?artifactName=github-actions-artifact&urlExpires=2021-08-05T08%3A47%3A03.0461819Z&urlSigningMethod=HMACV1&urlSignature=mx6fRX%2BgW54s%2BaA7aO4biCon1Qqjk9p6496zrL3K2is%3D \
&& unzip '/usr/local/tomcat/webapps/github-actions-artifact.zip' -d /usr/local/tomcat/webapps/ && rm /usr/local/tomcat/webapps/github-actions-artifact.zip || true;
ENV JAVA_HOME=/usr/bin/java
WORKDIR /usr/local/tomcat/webapps
USER  root
EXPOSE 8080
CMD ['catalina.sh','run']
