# ifinance-common
## 環境構築
### PostgreSQL構築
* Install PostgreSQL
```
$ yum install postgresql-server
$ service postgresql initdb
$ chkconfig postgresql on
```
* Start PostgreSQL
```
$ sudo -u postgres pg_ctl start -D /var/lib/pgsql/data
```
* Initialize for ifinance
```
$ sudo -u postgres psql postgres

postgres=# CREATE USER ifinance;
postgres=# create database ifinance Encoding 'UTF8' lc_collate 'ja_JP.UTF-8' 
  lc_ctype 'ja_JP.UTF-8' Owner ifinance TEMPLATE template0;
postgres=# show hba_file;
(例)/var/lib/pgsql/data/pg_hba.conf
(例)$ sudo vim /var/lib/pgsql/data/pg_hba.conf
## ident => trust
local   all         all                               trust
host    all         all         127.0.0.1/32          trust
host    all         all         ::1/128               trust
```
* Restart PostgreSQL
```
$ sudo -u postgres pg_ctl restart -D /var/lib/pgsql/data
```

### Install ifinance applications
* [ifinance-batch](https://github.com/toru1055/ifinance-batch)
* [ifinance-admin](https://github.com/toru1055/ifinance-admin)
* [ifinance-api](https://github.com/toru1055/ifinance-api)
```
$ cd /var/apps
$ git clone git@github.com:toru1055/ifinance-batch.git
$ git clone git@github.com:toru1055/ifinance-admin.git
$ git clone git@github.com:toru1055/ifinance-api.git
```

### Initialize applications
* Insert active scrapers to DB
```
$ /var/apps/ifinance-batch/bin/ifinance-init
```
* Add Industries and Subscriptions
```
$ /var/apps/ifinance-admin/bin/ifinance-admin 
ifinance> industry add
...
ifinance> subscription add
...
ifinance> mi_collector add
...
ifinance> mi_master add
...
```

### Start ifinance application
* Start Subscription
```
$ cd /var/log/ifinance/
$ nohup /var/apps/ifinance-batch/bin/ifinance-runner daemon > batch.out 2> batch.err &
```
* Start WebApi
```
$ cd /var/log/ifinance/
$ nohup java -jar /var/apps/ifinance-api/bin/ifinance-api-1.2.1.jar > api.out 2> api.err &
```
