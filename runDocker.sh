sudo docker stop settinyurlapp_ctr
sudo docker rm -f settinyurlapp_ctr
sudo docker run -d -p 8102:8102 --mount source=logging-area,destination=/logs --name settinyurlapp_ctr abhishekgem84/settinyurlapp:latest 