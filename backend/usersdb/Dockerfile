FROM postgres:14-alpine
ENV POSTGRES_PASSWORD root
ENV POTGRES_DB itausers
EXPOSE 5432
COPY dbInit.sql /docker-entrypoint-initdb.d/