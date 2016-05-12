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
postgres=# create database ifinance Encoding 'UTF8' lc_collate 'ja_JP.UTF-8' lc_ctype 'ja_JP.UTF-8' Owner ifinance;
```
