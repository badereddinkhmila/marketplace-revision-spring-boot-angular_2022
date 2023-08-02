# Build: $ docker build -t frontend .
# Run: $ docker run --rm -it -p 8080:80 frontend
FROM node:18-alpine AS builder

# Create a Virtual directory inside the docker image
WORKDIR /app
# Copy files to virtual directory
# COPY package.json package-lock.json ./
# Run command in Virtual directory
RUN npm cache clean --force
# Copy files from local machine to virtual directory in docker image
COPY . .
RUN yarn install
RUN yarn build


# stage 2
FROM nginx:alpine
# Set current working dir
WORKDIR /usr/share/nginx/html
# Remove all nginx files
RUN rm -rf /usr/share/nginx/html/*
# Use angular files
EXPOSE 80

COPY --from=builder /app/dist/app_tuto /usr/share/nginx/html

COPY nginx.conf  /etc/nginx/nginx.conf
ENTRYPOINT ["nginx"]
