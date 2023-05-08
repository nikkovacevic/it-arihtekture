FROM postgres:14-alpine
ENV POSTGRES_PASSWORD root
ENV POSTGRES_DB ita
COPY data.csv /
COPY dbInit.sql /docker-entrypoint-initdb.d/