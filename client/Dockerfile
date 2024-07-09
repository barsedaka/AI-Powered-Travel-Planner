FROM nginx:alpine
COPY dist /usr/share/nginx/html
COPY nginx.conf /etc/nginx/nginx.conf
RUN chmod -R 755 /usr/share/nginx/html
RUN mkdir -p /usr/share/nginx/html/50x
RUN echo "Internal Server Error" > /usr/share/nginx/html/50x.html