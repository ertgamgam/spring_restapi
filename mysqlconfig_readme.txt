change xampp mysql default charset!
1. Open "my.ini" in "C:\xampp\mysql\bin\" directory.  

2. Edit the following lines. 

# Add the following code before [client].
default-character-set=utf8

# Add the following code before [mysqld].
character-set-server=utf8
skip-character-set-client-handshake

# Add the following code before [mysqldump].
default-character-set=utf8

# Add the following code before [mysql].
default-character-set=utf8