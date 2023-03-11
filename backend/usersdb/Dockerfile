FROM postgres:14-alpine
ENV POSTGRES_PASSWORD root
ENV POTGRES_DB itausers
COPY dbInit.sql /docker-entrypoint-initdb.d/