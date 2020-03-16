IMAGE_NAME=oopp-db
DB_NAME=oopp
DB_PORT=3306
DB_USER=springuser
DB_PWD=krabbypatty

mysql_start:
	docker run -d -p $(DB_PORT):3306 -e MYSQL_ROOT_PASSWORD=$(DB_PWD) -d mysql:latest -e MYSQL_DATABASE=$(DB_NAME) -v `pwd` /data:/var/lib/mysql --rm --name $(IMAGE_NAME)
mysql_stop:
	docker kill $(IMAGE_NAME)
mysql_ssh:
	docker exec -it $(IMAGE_NAME) mysql -h$(IMAGE_NAME) -u$(IMAGE_NAME) -p$(DB_PWD)