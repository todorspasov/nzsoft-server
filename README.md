This is the server of the nzsoft project. It serves words to the client so that the words can get emitted through morse code.

How to run the nzsoft-server in a container on GCP:
git checkout https://github.com/todorspasov/nzsoft-server.git .
cd nzsoft-server
./gradlew clean build docker
docker run -p 8080:8080 -it spworks7/nzsoft-server

The container is also available on docker hub under:
docker pull spworks7/nzsoft-server
