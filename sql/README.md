# SQL

The following is based on PostgreSQL 18.3.  This assumes that you have docker installed and can run docker containers on your workstation.  If not, getting that set up is an exercise for the reader.

## Setup

The following assumes that you are running these commands from the same directory as this README file.

Start the container.  It will automatically run the `sql` file in the `db-init` directory creating the schema and inserting some sample data.
```
docker-compose up
```

## Resetting the Schema and Data

Run the following in the same directory as the README to stop the container, and dump the data in the `./data` directory.

```
docker -exec it postgres-study
docker-compose down && sudo rm -rf ./data/*
```

## Running the Sample Queries

Connect to the database using your SQL IDE of choice using the credentials in `docker-compose.yml`.

Or, `exec` into the container and run `psql` commands.
1. Run the following from the same directory as this `README.md` file
    ```
    docker exec -it postgres-study /bin/bash`
    ```
1. Change directories to the directory in the container that contains the sample queries.
    ```
     cd /samples
    ```
1. Run `psql`
    ```
    psql -U postgres -d company
    ```
1.  Once running `psql`, you can run queries in files that are in the same directory from which you ran `psql` with the following syntax:
    ```
    \i <file-name>
    ```


