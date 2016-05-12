# ifinance-common
## 環境構築
### PostgreSQL構築
1. Install PostgreSQL
```
$ yum install postgresql-server
$ service postgresql initdb
$ chkconfig postgresql on
```
2. Start PostgreSQL
```
$ sudo -u postgres pg_ctl start -D /var/lib/pgsql/data
```
3. Initialize for ifinance
```
$ sudo -u postgres psql postgres
```
