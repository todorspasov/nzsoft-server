This is the server of the nzsoft project. It serves words to the client so that the words can get emitted through morse code.

How to build nzsoft-server container:
git checkout https://github.com/todorspasov/nzsoft-server.git .
cd nzsoft-server
./gradlew clean build docker

How to run nzsoft-server container:
docker run -p 8080:8080 -it spworks7/nzsoft-server

The container is also available on docker hub under:
docker pull spworks7/nzsoft-server

To run it in Google Cloud Platform - Compute Engine:
1) Provision a VM instance with a "Container Optimized OS"
2) Activate cloud shell
3) SSH into the instance from the cloud shell
4) docker pull spworks7/nzsoft-server
5) docker run -p 8080:8080 -it spworks7/nzsoft-server
