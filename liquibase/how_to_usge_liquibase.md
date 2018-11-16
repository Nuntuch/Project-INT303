# liquibase can generate sql script from Database to other DBMS with dbtype.sql (ex. moommim.derby.sql) 

```
liquibase --driver=com.mysql.jdbc.Driver \
        --changeLogFile=/home/naijab/Project/moommim/liquibase/moommim.xml \
        --url="jdbc:mysql://localhost:33060/moommim_db" \
        --username=homestead \
        --password=secret \
        generateChangeLog
```

[liquibase](https://www.liquibase.org/)

> must download jar for liquibase,
> jdbc driver for dbms of database (ex. mysql use mysql connector j)
> slf4j-api-1.7.25.jar 

